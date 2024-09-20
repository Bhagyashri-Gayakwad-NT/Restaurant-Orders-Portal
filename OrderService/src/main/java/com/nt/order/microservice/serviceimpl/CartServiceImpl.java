package com.nt.order.microservice.serviceimpl;


import com.nt.order.microservice.dtoconverter.CartDtoConverter;
import com.nt.order.microservice.dtos.*;
import com.nt.order.microservice.entities.Cart;
import com.nt.order.microservice.exception.InvalidRequestException;
import com.nt.order.microservice.exception.ResourceNotFoundException;
import com.nt.order.microservice.exception.UnauthorizedException;
import com.nt.order.microservice.repository.CartRepository;
import com.nt.order.microservice.repository.OrderRepository;
import com.nt.order.microservice.service.CartService;
import com.nt.order.microservice.util.Constants;
import com.nt.order.microservice.util.Role;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class CartServiceImpl implements CartService {

  private static final Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);

  @Autowired
  private CartRepository cartRepository;

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private UserFClient userFClient;

  @Autowired
  private RestaurantFClient restaurantFClient;

  @Autowired
  private FoodItemFClient foodItemFClient;

  @Override
  @Transactional
  public CommonResponse addItemToCart(CartInDTO cartInDTO) {
    logger.info("Adding item to cart for userId: {}", cartInDTO.getUserId());
    UserOutDTO userOutDto;
    FoodItemOutDTO foodItemOutDTO;
    try {
      userOutDto = userFClient.getUserProfile(cartInDTO.getUserId());
      logger.info("User fetched successfully: {}", userOutDto);
    } catch (FeignException.NotFound ex) {
      logger.error("User not found for userId: {}", cartInDTO.getUserId());
      throw new ResourceNotFoundException(Constants.USER_NOT_FOUND);
    }
    if (userOutDto.getRole().equals(Role.RESTAURANT_OWNER.name())) {
      logger.warn("Restaurant owner attempting to add items to cart, userId: {}", cartInDTO.getUserId());
      throw new UnauthorizedException(Constants.RESTAURANT_OWNER_CART_ERROR);
    }
    try {
      RestaurantOutDTO restaurantOutDTO = restaurantFClient.getRestaurantById(cartInDTO.getRestaurantId());
      if (restaurantOutDTO == null) {
        logger.error("Invalid restaurantId: {}", cartInDTO.getRestaurantId());
        throw new ResourceNotFoundException(Constants.INVALID_RESTAURANT_ID);
      }
      logger.info("Restaurant found: {}", restaurantOutDTO);
    } catch (FeignException.NotFound ex) {
      logger.error("Restaurant not found for restaurantId: {}", cartInDTO.getRestaurantId());
      throw new ResourceNotFoundException(Constants.INVALID_RESTAURANT_ID);
    }
    try {
      foodItemOutDTO = foodItemFClient.getFoodItemById(cartInDTO.getFoodItemId());
      if (foodItemOutDTO == null) {
        logger.error("Invalid foodItemId: {}", cartInDTO.getFoodItemId());
        throw new ResourceNotFoundException(Constants.INVALID_FOOD_ITEM_ID);
      }
      logger.info("Food item found: {}", foodItemOutDTO);
    } catch (FeignException.NotFound ex) {
      logger.error("Food item not found for foodItemId: {}", cartInDTO.getFoodItemId());
      throw new ResourceNotFoundException(Constants.INVALID_FOOD_ITEM_ID);
    }
    if (foodItemOutDTO.getPrice().compareTo(cartInDTO.getPrice()) != 0) {
      logger.error("Price mismatch for foodItemId: {}, expected: {}, provided: {}",
        cartInDTO.getFoodItemId(), foodItemOutDTO.getPrice(), cartInDTO.getPrice());
      throw new InvalidRequestException(Constants.PRICE_MISMATCH);
    }
    List<Cart> existingCartItems = cartRepository.findByUserId(cartInDTO.getUserId());
    Optional<Cart> existingCart = existingCartItems.stream()
      .filter(cart -> cart.getFoodItemId().equals(cartInDTO.getFoodItemId())
        && cart.getRestaurantId().equals(cartInDTO.getRestaurantId()))
      .findFirst();
    if (existingCart.isPresent()) {
      Cart cart = existingCart.get();
      cart.setQuantity(cart.getQuantity() + cartInDTO.getQuantity());
      cart.setPrice(cart.getPrice());
      cartRepository.save(cart);
      logger.info("Item quantity updated for cartId: {}", cart.getCartId());
      return new CommonResponse(Constants.ITEM_QUANTITY_UPDATED_SUCCESS);
    } else {
      Cart cart = CartDtoConverter.toEntity(cartInDTO);
      cartRepository.save(cart);
      logger.info("Item added to cart for userId: {}", cartInDTO.getUserId());
      return new CommonResponse(Constants.ITEM_ADDED_TO_CART_SUCCESS);
    }
  }

  @Override
  public CartOutDTO getCartById(Integer cartId) {
    logger.info("Fetching cart by cartId: {}", cartId);
    Cart cart = cartRepository.findById(cartId)
      .orElseThrow(() -> new ResourceNotFoundException(Constants.CART_NOT_FOUND));
    logger.info("Cart found: {}", cart);
    return CartDtoConverter.toOutDTO(cart);
  }

  @Override
  public List<CartOutDTO> getCartsByUserId(Integer userId) {
    logger.info("Fetching carts for userId: {}", userId);
    UserOutDTO userOutDto;
    try {
      userOutDto = userFClient.getUserProfile(userId);
    } catch (Exception e) {
      logger.error("User not found for userId: {}", userId);
      throw new ResourceNotFoundException(Constants.USER_NOT_FOUND);
    }
    List<Cart> carts = cartRepository.findByUserId(userId);
    if (carts.isEmpty()) {
      logger.warn("No carts found for userId: {}", userId);
      throw new ResourceNotFoundException(Constants.CART_NOT_FOUND);
    }
    List<CartOutDTO> cartOutDTOs = new ArrayList<>();
    for (Cart cart : carts) {
      cartOutDTOs.add(CartDtoConverter.toOutDTO(cart));
    }
    logger.info("Carts fetched successfully for userId: {}", userId);
    return cartOutDTOs;
  }

  @Override
  public List<Cart> getCartItemsByUserIdAndRestaurantId(Integer userId, Integer restaurantId) {
    logger.info("Fetching cart items for userId: {} and restaurantId: {}", userId, restaurantId);
    UserOutDTO userOutDto;
    try {
      userOutDto = userFClient.getUserProfile(userId);
    } catch (Exception e) {
      logger.error("User not found for userId: {}", userId);
      throw new ResourceNotFoundException(Constants.USER_NOT_FOUND);
    }
    List<Cart> carts = cartRepository.findByUserIdAndRestaurantId(userId, restaurantId);
    if (carts.isEmpty()) {
      logger.warn("No cart items found for userId: {} and restaurantId: {}", userId, restaurantId);
      throw new ResourceNotFoundException(Constants.CART_NOT_FOUND);
    }
    logger.info("Cart items fetched successfully for userId: {} and restaurantId: {}", userId, restaurantId);
    return carts;
  }

  @Override
  @Transactional
  public CommonResponse updateQuantity(Integer cartId, Integer quantityChange) {
    logger.info("Updating quantity for cartId: {}, quantity change: {}", cartId, quantityChange);
    Cart cart = cartRepository.findById(cartId)
      .orElseThrow(() -> new ResourceNotFoundException(Constants.CART_NOT_FOUND));
    double unitPrice = cart.getPrice() / cart.getQuantity();
    int newQuantity = Math.max(0, cart.getQuantity() + quantityChange);
    if (newQuantity == 0) {
      cartRepository.deleteById(cartId);
      logger.info("Item removed from cart with cartId: {}", cartId);
      throw new InvalidRequestException(Constants.ITEM_REMOVED_SUCCESSFULLY);
    }
    double newPrice = unitPrice * newQuantity;
    BigDecimal roundedPrice = BigDecimal.valueOf(newPrice).setScale(2, RoundingMode.HALF_EVEN);
    cart.setQuantity(newQuantity);
    cart.setPrice(roundedPrice.doubleValue());
    cartRepository.save(cart);
    logger.info("Cart updated with new quantity for cartId: {}", cartId);
    return new CommonResponse(Constants.ITEM_QUANTITY_UPDATED_SUCCESS);
  }

  @Override
  @Transactional
  public CommonResponse removeItemFromCart(Integer cartId) {
    logger.info("Removing item from cart with cartId: {}", cartId);
    Optional<Cart> cartOptional = cartRepository.findById(cartId);
    if (!cartOptional.isPresent()) {
      logger.warn("Cart item not found with cartId: {}", cartId);
      throw new ResourceNotFoundException(Constants.CART_ITEM_NOT_FOUND);
    }
    cartRepository.deleteById(cartId);
    logger.info("Cart item removed successfully for cartId: {}", cartId);
    return new CommonResponse(Constants.CART_ITEM_REMOVED_SUCCESSFULLY);
  }

  @Override
  @Transactional
  public CommonResponse clearCartAfterPlaceAnOrder(Integer userId) {
    logger.info("Clearing cart after placing an order for userId: {}", userId);
    List<Cart> userCart = cartRepository.findByUserId(userId);
    if (userCart.isEmpty()) {
      logger.warn("No cart items found for userId: {}", userId);
      throw new ResourceNotFoundException(Constants.CART_NOT_FOUND);
    }
    cartRepository.deleteAll(userCart);
    logger.info("Cart cleared successfully for userId: {}", userId);
    return new CommonResponse(Constants.CART_CLEARED_SUCCESSFULLY);
  }
}



