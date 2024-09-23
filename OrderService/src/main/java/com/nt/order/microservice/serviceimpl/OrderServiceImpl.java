package com.nt.order.microservice.serviceimpl;

import com.nt.order.microservice.dtoconverter.OrderDtoConverter;
import com.nt.order.microservice.dtos.AddressOutDTO;
import com.nt.order.microservice.dtos.AmountInDTO;
import com.nt.order.microservice.dtos.CartItemDTO;
import com.nt.order.microservice.dtos.CommonResponse;
import com.nt.order.microservice.dtos.FoodItemOutDTO;
import com.nt.order.microservice.dtos.OrderInDTO;
import com.nt.order.microservice.dtos.OrderOutDTO;
import com.nt.order.microservice.dtos.RestaurantOutDTO;
import com.nt.order.microservice.dtos.UserOutDTO;
import com.nt.order.microservice.entities.Order;
import com.nt.order.microservice.exception.InsufficientBalanceException;
import com.nt.order.microservice.exception.InvalidRequestException;
import com.nt.order.microservice.exception.ResourceAlreadyExistException;
import com.nt.order.microservice.exception.ResourceNotFoundException;
import com.nt.order.microservice.exception.UnauthorizedException;
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

/**
 * Implementation of the OrderService interface.
 */
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
  private FoodItemFClient foodItemFClient;

  @Autowired
  private CartRepository cartRepository;

  /**
   * Places an order for a user.
   *
   * @param orderInDTO the order data transfer object containing order details
   * @return a CommonResponse indicating the result of the operation
   */
  @Override
  @Transactional
  public CommonResponse placeOrder(final OrderInDTO orderInDTO) {
    logger.info("Placing order for userId: {}", orderInDTO.getUserId());
    UserOutDTO userOutDto = fetchUserProfile(orderInDTO.getUserId());
    validateUserRole(userOutDto);

    fetchAndValidateRestaurant(orderInDTO.getRestaurantId());

    validateUserAddress(orderInDTO, userOutDto);

    validateCartItems(orderInDTO);

    Order order = createAndSaveOrder(orderInDTO, userOutDto);
    clearUserCart(order);

    updateUserWalletBalance(userOutDto, order.getTotalPrice());

    return new CommonResponse(Constants.ORDER_PLACED_SUCCESSFULLY);

  }

  private UserOutDTO fetchUserProfile(final Integer userId) {
    try {
      UserOutDTO userOutDto = userFClient.getUserProfile(userId);
      logger.info("Fetched user profile for userId: {}", userId);
      return userOutDto;
    } catch (FeignException.NotFound ex) {
      logger.error("User not found for userId: {}", userId);
      throw new ResourceNotFoundException(Constants.USER_NOT_FOUND);
    }
  }

  private void validateUserRole(final UserOutDTO userOutDto) {
    if (userOutDto.getRole().equals(Role.RESTAURANT_OWNER.name())) {
      logger.warn("Restaurant owner trying to place order. userId: {}", userOutDto.getId());
      throw new UnauthorizedException(Constants.RESTAURANT_OWNER_ORDER_ERROR);
    }
  }

  private void fetchAndValidateRestaurant(final Integer restaurantId) {
    try {
      RestaurantOutDTO restaurantOutDTO = restaurantFClient.getRestaurantById(restaurantId);
      if (restaurantOutDTO == null) {
        logger.error("Restaurant not found for restaurantId: {}", restaurantId);
        throw new ResourceNotFoundException(Constants.INVALID_RESTAURANT_ID);
      }
      logger.info("Fetched restaurant details for restaurantId: {}", restaurantId);
    } catch (FeignException.NotFound ex) {
      logger.error("Invalid restaurantId: {}", restaurantId);
      throw new ResourceNotFoundException(Constants.INVALID_RESTAURANT_ID);
    }
  }

  private void validateUserAddress(final OrderInDTO orderInDTO, final UserOutDTO userOutDto) {
    List<AddressOutDTO> userAddresses;
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
  }

  private void validateCartItems(final OrderInDTO orderInDTO) {
    for (CartItemDTO cartItem : orderInDTO.getCartItems()) {
      validateFoodItem(cartItem, orderInDTO.getRestaurantId());
    }
  }

  private void validateFoodItem(final CartItemDTO cartItem, final Integer restaurantId) {
    FoodItemOutDTO foodItemOutDTO;
    try {
      foodItemOutDTO = foodItemFClient.getFoodItemById(cartItem.getFoodItemId());
      if (foodItemOutDTO == null) {
        logger.error("Food item not found for foodItemId: {}", cartItem.getFoodItemId());
        throw new ResourceNotFoundException(Constants.INVALID_FOOD_ITEM_ID);
      }
      logger.info("Fetched food item details for foodItemId: {}", cartItem.getFoodItemId());
    } catch (Exception ex) {
      logger.error("Invalid foodItemId: {}", cartItem.getFoodItemId());
      throw new ResourceNotFoundException(Constants.INVALID_FOOD_ITEM_ID);
    }

    List<FoodItemOutDTO> foodItemsInRestaurant;
    try {
      foodItemsInRestaurant = foodItemFClient.getFoodItemsByRestaurant(restaurantId);
    } catch (Exception ex) {
      throw new ResourceNotFoundException(Constants.FoodItem_NOT_FOUND);
    }

    boolean isFoodItemValid = foodItemsInRestaurant.stream()
      .anyMatch(foodItem -> foodItem.getFoodItemId().equals(cartItem.getFoodItemId()));
    if (!isFoodItemValid) {
      logger.error("Food item with id: {} does not belong to restaurantId: {}",
        cartItem.getFoodItemId(), restaurantId);
      throw new ResourceNotFoundException(Constants.FOOD_ITEM_DOES_NOT_BELONG_TO_RESTAURANT);
    }
  }

  private Order createAndSaveOrder(final OrderInDTO orderInDTO, final UserOutDTO userOutDto) {
    Order order = OrderDtoConverter.convertToEntity(orderInDTO);
    Double totalPrice = calculateTotalPrice(orderInDTO);
    order.setTotalPrice(totalPrice);
    order.setOrderStatus(OrderStatus.PLACED);
    order.setPlacedTiming(LocalDateTime.now());

    Order savedOrder = orderRepository.save(order);
    logger.info("Order placed successfully for userId: {}, orderId: {}", orderInDTO.getUserId(), savedOrder.getOrderId());

    return savedOrder;
  }

  private void clearUserCart(final Order savedOrder) {
    cartRepository.deleteByUserId(savedOrder.getUserId());
    logger.info("Cleared cart for userId: {}", savedOrder.getUserId());
  }

  private void updateUserWalletBalance(final UserOutDTO userOutDto, final Double totalPrice) {
    AmountInDTO amountInDTO = new AmountInDTO();
    amountInDTO.setBalance(totalPrice);

    try {
      userFClient.updateWalletBalance(userOutDto.getId(), amountInDTO);
      logger.info("Updated wallet balance for userId: {}", userOutDto.getId());
    } catch (Exception e) {
      logger.error("Insufficient balance for userId: {}", userOutDto.getId());
      throw new InsufficientBalanceException(Constants.INSUFFICIENT_BALANCE);
    }
  }

  private Double calculateTotalPrice(final OrderInDTO orderInDTO) {
    return orderInDTO.getCartItems().stream()
      .mapToDouble(cart -> cart.getPrice() * cart.getQuantity())
      .sum();
  }

  /**
   * Cancels an order by its ID.
   *
   * @param orderId the ID of the order to cancel
   * @return a CommonResponse indicating the result of the operation
   */
  @Transactional
  @Override
  public CommonResponse cancelOrder(final Integer orderId) {
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

    userFClient.addMoney(order.getUserId(), amountInDTO);
    order.setOrderStatus(OrderStatus.CANCELLED);
    orderRepository.save(order);

    logger.info("Order cancelled successfully for orderId: {}", orderId);
    return new CommonResponse(Constants.ORDER_CANCELLED_SUCCESSFULLY);
  }

  /**
   * Marks an order as completed by its ID.
   *
   * @param orderId the ID of the order to mark as completed
   * @param userId the ID of the user requesting the action
   * @return a CommonResponse indicating the result of the operation
   * @throws ResourceNotFoundException if the order or user is not found
   * @throws UnauthorizedException if the user is unauthorized to complete the order
   * @throws ResourceAlreadyExistException if the order is already marked as completed
   */
  @Override
  public CommonResponse markOrderAsCompleted(final Integer orderId, final Integer userId) {
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

  /**
   * Fetches all orders for a specific user by their ID.
   *
   * @param userId the ID of the user whose orders to fetch
   * @return a list of OrderOutDTO representing the user's orders
   * @throws ResourceNotFoundException if the user is not found
   * @throws UnauthorizedException if the user is unauthorized to view orders
   */
  @Override
  public List<OrderOutDTO> getOrdersByUserId(final Integer userId) {
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

  /**
   * Fetches all orders for a specific restaurant by its ID.
   *
   * @param restaurantId the ID of the restaurant whose orders to fetch
   * @return a list of OrderOutDTO representing the restaurant's orders
   * @throws ResourceNotFoundException if the restaurant is not found
   */
  @Override
  public List<OrderOutDTO> getOrdersByRestaurantId(final Integer restaurantId) {
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