package com.nt.order.microservice.serviceimpl;

import com.nt.order.microservice.dtoconverter.OrderDtoConverter;
import com.nt.order.microservice.dtos.*;
import com.nt.order.microservice.entities.Order;
import com.nt.order.microservice.exception.*;
import com.nt.order.microservice.repository.CartRepository;
import com.nt.order.microservice.repository.OrderRepository;
import com.nt.order.microservice.service.CartService;
import com.nt.order.microservice.service.OrderService;
import com.nt.order.microservice.util.Constants;
import com.nt.order.microservice.util.OrderStatus;
import com.nt.order.microservice.util.Role;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

  private static final int TIME_LIMIT_SECONDS = 30;

  private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private CartService cartService;

  @Autowired
  private UserFClient userFClient;

  @Autowired
  private RestaurantFClient restaurantFClient;

  @Autowired
  private AddressFClient addressFClient;

  @Autowired
  private CartRepository cartRepository;


  @Override
  @Transactional
  public CommonResponse placeOrder(OrderInDTO orderInDTO) {
    logger.info("Placing order for userId: {}", orderInDTO.getUserId());
    UserOutDTO userOutDto;
    List<AddressOutDTO> userAddresses;
    try {
      userOutDto = userFClient.getUserProfile(orderInDTO.getUserId());
      logger.info("Fetched user profile for userId: {}", orderInDTO.getUserId());
    } catch (FeignException.NotFound ex) {
      logger.error("User not found for userId: {}", orderInDTO.getUserId());
      throw new ResourceNotFoundException(Constants.USER_NOT_FOUND);
    }
    if (userOutDto.getRole().equals(Role.RESTAURANT_OWNER.name())) {
      logger.warn("Restaurant owner trying to place order. userId: {}", orderInDTO.getUserId());
      throw new UnauthorizedException(Constants.RESTAURANT_OWNER_ORDER_ERROR);
    }
    try {
      RestaurantOutDTO restaurantOutDTO = restaurantFClient.getRestaurantById(orderInDTO.getRestaurantId());
      if (restaurantOutDTO == null) {
        logger.error("Restaurant not found for restaurantId: {}", orderInDTO.getRestaurantId());
        throw new ResourceNotFoundException(Constants.INVALID_RESTAURANT_ID);
      }
      logger.info("Fetched restaurant details for restaurantId: {}", orderInDTO.getRestaurantId());
    } catch (FeignException.NotFound ex) {
      logger.error("Invalid restaurantId: {}", orderInDTO.getRestaurantId());
      throw new ResourceNotFoundException(Constants.INVALID_RESTAURANT_ID);
    }
    try {
      userAddresses = addressFClient.getUserAddresses(orderInDTO.getUserId());
      logger.info("Fetched addresses for userId: {}", orderInDTO.getUserId());
    } catch (FeignException.NotFound ex) {
      logger.error("No addresses found for userId: {}", orderInDTO.getUserId());
      throw new ResourceNotFoundException(Constants.ADDRESS_NOT_FOUND);
    }
    boolean isAddressValid = userAddresses.stream()
      .anyMatch(address -> address.getId().equals(orderInDTO.getAddressId()));

    if (!isAddressValid) {
      logger.error("Invalid addressId: {} for userId: {}", orderInDTO.getAddressId(), orderInDTO.getUserId());
      throw new ResourceNotFoundException(Constants.ADDRESS_NOT_FOUND);
    }
    Order order = OrderDtoConverter.convertToEntity(orderInDTO);
    Double totalPrice = calculateTotalPrice(orderInDTO);
    order.setTotalPrice(totalPrice);
    order.setOrderStatus(OrderStatus.PLACED);
    order.setPlacedTiming(LocalDateTime.now());

    Order savedOrder = orderRepository.save(order);
    logger.info("Order placed successfully for userId: {}, orderId: {}", orderInDTO.getUserId(), savedOrder.getOrderId());

    cartRepository.deleteByUserId(savedOrder.getUserId());
    logger.info("Cleared cart for userId: {}", savedOrder.getUserId());

    AmountInDTO amountInDTO = new AmountInDTO();
    amountInDTO.setBalance(totalPrice);

    try {
      userFClient.updateWalletBalance(userOutDto.getId(), amountInDTO);
      logger.info("Updated wallet balance for userId: {}", userOutDto.getId());
    } catch (Exception e) {
      logger.error("Insufficient balance for userId: {}", userOutDto.getId());
      throw new InsufficientBalanceException(Constants.INSUFFICIENT_BALANCE);
    }

//
    return new CommonResponse(Constants.ITEM_ADDED_TO_CART_SUCCESS);
  }

  private Double calculateTotalPrice(OrderInDTO orderInDTO) {
    return orderInDTO.getCartItems().stream()
      .mapToDouble(cart -> cart.getPrice() * cart.getQuantity())
      .sum();
  }

  @Override
  public CommonResponse cancelOrder(Integer orderId) {
    logger.info("Cancelling order with orderId: {}", orderId);
    Optional<Order> orderOptional = orderRepository.findById(orderId);

    if (!orderOptional.isPresent()) {
      logger.error("Order not found with orderId: {}", orderId);
      throw new ResourceNotFoundException(Constants.ORDER_NOT_FOUND);
    }

    Order order = orderOptional.get();
    LocalDateTime currentTime = LocalDateTime.now();

    if (order.getPlacedTiming().plusSeconds(TIME_LIMIT_SECONDS).isBefore(currentTime)) {
      logger.error("Order cancellation time limit exceeded for orderId: {}", orderId);
      throw new InvalidRequestException(Constants.ORDER_CANCELLATION_TIME_LIMIT_EXCEEDED);
    }

    AmountInDTO amountInDTO = new AmountInDTO();
    amountInDTO.setBalance(order.getTotalPrice());

    userFClient.updateWalletBalance(order.getUserId(), amountInDTO);
    order.setOrderStatus(OrderStatus.CANCELLED);
    orderRepository.save(order);

    logger.info("Order cancelled successfully for orderId: {}", orderId);
    return new CommonResponse(Constants.ORDER_CANCELLED_SUCCESSFULLY);
  }

  @Override
  public CommonResponse markOrderAsCompleted(Integer orderId, Integer userId) {
    logger.info("Marking order as completed for orderId: {}", orderId);

    Optional<Order> optionalOrder = orderRepository.findById(orderId);

    if (!optionalOrder.isPresent()) {
      logger.error("Order not found with orderId: {}", orderId);
      throw new ResourceNotFoundException(Constants.ORDER_NOT_FOUND);
    }

    UserOutDTO userOutDto;
    try {
      userOutDto = userFClient.getUserProfile(userId);
      logger.info("Fetched user profile for userId: {}", userId);
    } catch (Exception e) {
      logger.error("User not found with userId: {}", userId);
      throw new ResourceNotFoundException(Constants.USER_NOT_FOUND);
    }

    Order order = optionalOrder.get();

    if (userOutDto.getRole().equals(Role.USER.name())) {
      logger.warn("Unauthorized user trying to mark order as completed. userId: {}", userId);
      throw new UnauthorizedException(Constants.UNAUTHORIZED_USER);
    }

    if (order.getOrderStatus() == OrderStatus.COMPLETED) {
      logger.warn("Order already marked as completed. orderId: {}", orderId);
      throw new ResourceAlreadyExistException(Constants.ALREADY_COMPLETED);
    }

    order.setOrderStatus(OrderStatus.COMPLETED);
    orderRepository.save(order);

    logger.info("Order marked as completed successfully for orderId: {}", orderId);
    return new CommonResponse(Constants.ORDER_COMPLETED_SUCCESSFULLY);
  }

  @Override
  public List<OrderOutDTO> getOrdersByUserId(Integer userId) {
    logger.info("Fetching orders for userId: {}", userId);

    UserOutDTO userOutDto;
    try {
      userOutDto = userFClient.getUserProfile(userId);
      logger.info("Fetched user profile for userId: {}", userId);
    } catch (FeignException e) {
      logger.error("User not found for userId: {}", userId);
      throw new ResourceNotFoundException(Constants.USER_NOT_FOUND);
    }

    if (userOutDto.getRole().equals(Role.RESTAURANT_OWNER.name())) {
      logger.warn("Unauthorized access by restaurant owner. userId: {}", userId);
      throw new UnauthorizedException(Constants.RESTAURANT_OWNER);
    }

    List<Order> orders = orderRepository.findByUserId(userId);

    return orders.stream()
      .map(OrderDtoConverter::convertToOutDto)
      .collect(Collectors.toList());
  }

  @Override
  public List<OrderOutDTO> getOrdersByRestaurantId(Integer restaurantId) {
    logger.info("Fetching orders for restaurantId: {}", restaurantId);

    try {
      restaurantFClient.getRestaurantById(restaurantId);
      logger.info("Fetched restaurant details for restaurantId: {}", restaurantId);
    } catch (Exception e) {
      logger.error("Restaurant not found for restaurantId: {}", restaurantId);
      throw new ResourceNotFoundException(Constants.INVALID_RESTAURANT_ID);
    }

    List<Order> orders = orderRepository.findByRestaurantId(restaurantId);

    return orders.stream()
      .map(OrderDtoConverter::convertToOutDto)
      .collect(Collectors.toList());
  }

}