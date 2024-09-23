package com.nt.order.microservice.serviceimpl;

import com.nt.order.microservice.dtoconverter.CartDtoConverter;
import com.nt.order.microservice.dtos.CartInDTO;
import com.nt.order.microservice.dtos.CartOutDTO;
import com.nt.order.microservice.dtos.CommonResponse;
import com.nt.order.microservice.dtos.FoodItemOutDTO;
import com.nt.order.microservice.dtos.RestaurantOutDTO;
import com.nt.order.microservice.dtos.UserOutDTO;
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

/**
 * Implementation of the CartService interface. This class handles the business logic
 * for managing shopping cart items, such as adding items, updating quantities, and
 * removing items from the cart.
 */
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

  /**
   * Adds an item to the cart for a specific user.
   *
   * @param cartInDTO the DTO containing the cart input data
   * @return a response indicating the result of the operation
   */
  @Override
  @Transactional
  public CommonResponse addItemToCart(final CartInDTO cartInDTO) {
    logger.info("Adding item to cart for userId: {}", cartInDTO.getUserId());

    UserOutDTO userOutDto = fetchUser(cartInDTO.getUserId());
    validateUserRole(userOutDto);
    validateRestaurant(cartInDTO.getRestaurantId());
    try {
      validateFoodItemInRestaurant(cartInDTO);
    } catch (Exception e) {
      throw new ResourceNotFoundException(Constants.FoodItem_NOT_FOUND);
    }

    FoodItemOutDTO foodItemOutDTO = fetchFoodItem(cartInDTO.getFoodItemId());
    validatePrice(cartInDTO, foodItemOutDTO);

    return processCartUpdate(cartInDTO, foodItemOutDTO);
  }

  private UserOutDTO fetchUser(final Integer userId) {
    try {
      UserOutDTO userOutDto = userFClient.getUserProfile(userId);
      logger.info("User fetched successfully: {}", userOutDto);
      return userOutDto;
    } catch (FeignException.NotFound ex) {
      logger.error("User not found for userId: {}", userId);
      throw new ResourceNotFoundException(Constants.USER_NOT_FOUND);
    }
  }

  private void validateUserRole(UserOutDTO userOutDto) {
    if (userOutDto.getRole().equals(Role.RESTAURANT_OWNER.name())) {
      logger.warn("Restaurant owner attempting to add items to cart, userId: {}", userOutDto.getId());
      throw new UnauthorizedException(Constants.RESTAURANT_OWNER_CART_ERROR);
    }
  }

  private void validateRestaurant(final Integer restaurantId) {
    try {
      RestaurantOutDTO restaurantOutDTO = restaurantFClient.getRestaurantById(restaurantId);
      if (restaurantOutDTO == null) {
        logger.error("Invalid restaurantId: {}", restaurantId);
        throw new ResourceNotFoundException(Constants.INVALID_RESTAURANT_ID);
      }
      logger.info("Restaurant found: {}", restaurantOutDTO);
    } catch (FeignException.NotFound ex) {
      logger.error("Restaurant not found for restaurantId: {}", restaurantId);
      throw new ResourceNotFoundException(Constants.INVALID_RESTAURANT_ID);
    }
  }

  private void validateFoodItemInRestaurant(final CartInDTO cartInDTO) {
    List<FoodItemOutDTO> foodItemsInRestaurant = foodItemFClient.getFoodItemsByRestaurant(cartInDTO.getRestaurantId());
    boolean isFoodItemValid = foodItemsInRestaurant.stream()
      .anyMatch(foodItem -> foodItem.getFoodItemId().equals(cartInDTO.getFoodItemId()));

    if (!isFoodItemValid) {
      logger.error("Food item with id: {} does not belong to restaurantId: {}", cartInDTO.getFoodItemId(), cartInDTO.getRestaurantId());
      throw new UnauthorizedException(Constants.FOOD_ITEM_DOES_NOT_BELONG_TO_RESTAURANT);
    }
  }

  private FoodItemOutDTO fetchFoodItem(final Integer foodItemId) {
    try {
      FoodItemOutDTO foodItemOutDTO = foodItemFClient.getFoodItemById(foodItemId);
      if (foodItemOutDTO == null) {
        logger.error("Invalid foodItemId: {}", foodItemId);
        throw new ResourceNotFoundException(Constants.INVALID_FOOD_ITEM_ID);
      }
      logger.info("Food item found: {}", foodItemOutDTO);
      return foodItemOutDTO;
    } catch (FeignException.NotFound ex) {
      logger.error("Food item not found for foodItemId: {}", foodItemId);
      throw new ResourceNotFoundException(Constants.INVALID_FOOD_ITEM_ID);
    }
  }

  private void validatePrice(final CartInDTO cartInDTO, FoodItemOutDTO foodItemOutDTO) {
    if (foodItemOutDTO.getPrice().compareTo(cartInDTO.getPrice()) != 0) {
      logger.error("Price mismatch for foodItemId: {}, expected: {}, provided: {}",
        cartInDTO.getFoodItemId(), foodItemOutDTO.getPrice(), cartInDTO.getPrice());
      throw new InvalidRequestException(Constants.PRICE_MISMATCH);
    }
  }

  private CommonResponse processCartUpdate(final CartInDTO cartInDTO, final FoodItemOutDTO foodItemOutDTO) {
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

  /**
   * Retrieves a cart by its cart ID.
   *
   * @param cartId the ID of the cart to retrieve
   * @return the CartOutDTO object containing the cart details
   */
  @Override
  public CartOutDTO getCartById(final Integer cartId) {
    logger.info("Fetching cart by cartId: {}", cartId);
    Cart cart = cartRepository.findById(cartId)
      .orElseThrow(() -> new ResourceNotFoundException(Constants.CART_NOT_FOUND));
    logger.info("Cart found: {}", cart);
    return CartDtoConverter.toOutDTO(cart);
  }

  /**
   * Retrieves all carts for a specific user by user ID.
   *
   * @param userId the ID of the user whose carts are to be fetched
   * @return a list of CartOutDTO objects containing cart details
   */
  @Override
  public List<CartOutDTO> getCartsByUserId(final Integer userId) {
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

  /**
   * Retrieves all cart items for a user by user ID and restaurant ID.
   *
   * @param userId       the ID of the user
   * @param restaurantId the ID of the restaurant
   * @return a list of Cart objects representing the cart items
   */
  @Override
  public List<Cart> getCartItemsByUserIdAndRestaurantId(final Integer userId, final Integer restaurantId) {
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

  /**
   * Updates the quantity of an item in the cart.
   *
   * @param cartId        the ID of the cart to update
   * @param quantityChange the change in quantity to apply
   * @return a CommonResponse indicating the result of the operation
   */
  @Override
  @Transactional
  public CommonResponse updateQuantity(final Integer cartId, final Integer quantityChange) {
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

  /**
   * Removes an item from the cart by cart ID.
   *
   * @param cartId the ID of the cart item to remove
   * @return a CommonResponse indicating the result of the operation
   */
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

  /**
   * Clears the cart for a user after placing an order.
   *
   * @param userId the ID of the user whose cart is to be cleared
   * @return a CommonResponse indicating the result of the operation
   */
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



