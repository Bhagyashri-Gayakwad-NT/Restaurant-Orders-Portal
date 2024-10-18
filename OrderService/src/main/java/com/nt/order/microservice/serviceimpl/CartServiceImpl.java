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

  /** Logger for CartServiceImpl class, used for logging information and errors. */
  private static final Logger LOGGER = LoggerFactory.getLogger(CartServiceImpl.class);

  /**
   * Repository for managing Cart entities.
   */
  @Autowired
  private CartRepository cartRepository;

  /**
   * Repository for managing Order entities.
   */
  @Autowired
  private OrderRepository orderRepository;

  /**
   * Feign client for interacting with the User service.
   */
  @Autowired
  private UserFClient userFClient;

  /**
   * Feign client for interacting with the Restaurant service.
   */
  @Autowired
  private RestaurantFClient restaurantFClient;

  /**
   * Feign client for interacting with the Food Item service.
   */
  @Autowired
  private FoodItemFClient foodItemFClient;

  /**
   * Adds an item to the cart for a specific user.
   *
   * @param cartInDTO the DTO containing the cart input data
   * @return a response indicating the result of the operation
   * @throws UnauthorizedException if the user is a restaurant owner
   * @throws ResourceNotFoundException if the user, restaurant, or food item is not found
   * @throws InvalidRequestException if the provided price does not match the food item price
   */
  @Override
  @Transactional
  public CommonResponse addItemToCart(final CartInDTO cartInDTO) {
    LOGGER.info("Adding item to cart for userId: {}", cartInDTO.getUserId());

    UserOutDTO userOutDto = fetchUser(cartInDTO.getUserId());
    validateUserRole(userOutDto);
    validateRestaurant(cartInDTO.getRestaurantId());
    try {
      validateFoodItemInRestaurant(cartInDTO);
    } catch (Exception e) {
      throw new ResourceNotFoundException(Constants.FOODITEM_NOT_FOUND);
    }

    FoodItemOutDTO foodItemOutDTO = fetchFoodItem(cartInDTO.getFoodItemId());
    validatePrice(cartInDTO, foodItemOutDTO);

    return processCartUpdate(cartInDTO, foodItemOutDTO);
  }

  /**
   * Fetches the user details based on the user ID.
   *
   * @param userId the ID of the user to fetch
   * @return the UserOutDTO object containing user details
   * @throws ResourceNotFoundException if the user is not found
   */
  private UserOutDTO fetchUser(final Integer userId) {
    try {
      UserOutDTO userOutDto = userFClient.getUserProfile(userId);
      LOGGER.info("User fetched successfully: {}", userOutDto);
      return userOutDto;
    } catch (FeignException ex) {
      LOGGER.error("User not found for userId: {}", userId);
      throw new ResourceNotFoundException(Constants.USER_NOT_FOUND);
    }
  }

  /**
   * Validates the user's role to ensure they are not a restaurant owner.
   *
   * @param userOutDto the UserOutDTO object containing user details
   * @throws UnauthorizedException if the user is a restaurant owner
   */
  private void validateUserRole(final UserOutDTO userOutDto) {
    if (userOutDto.getRole().equals(Role.RESTAURANT_OWNER.name())) {
      LOGGER.warn("Restaurant owner attempting to add items to cart, userId: {}", userOutDto.getId());
      throw new UnauthorizedException(Constants.RESTAURANT_OWNER_CART_ERROR);
    }
  }

  /**
   * Validates the existence of a restaurant based on the restaurant ID.
   *
   * @param restaurantId the ID of the restaurant to validate
   * @throws ResourceNotFoundException if the restaurant is not found
   */
  private void validateRestaurant(final Integer restaurantId) {
    try {
      RestaurantOutDTO restaurantOutDTO = restaurantFClient.getRestaurantById(restaurantId);
      LOGGER.info("Restaurant found: {}", restaurantOutDTO);
    } catch (FeignException.NotFound ex) {
      LOGGER.error("Restaurant not found for restaurantId: {}", restaurantId);
      throw new ResourceNotFoundException(Constants.INVALID_RESTAURANT_ID);
    }
  }

  /**
   * Validates whether the food item belongs to the specified restaurant.
   *
   * @param cartInDTO the DTO containing cart input data
   * @throws UnauthorizedException if the food item does not belong to the restaurant
   */
  private void validateFoodItemInRestaurant(final CartInDTO cartInDTO) {
    List<FoodItemOutDTO> foodItemsInRestaurant = foodItemFClient.getFoodItemsByRestaurant(cartInDTO.getRestaurantId());
    boolean isFoodItemValid = foodItemsInRestaurant.stream()
      .anyMatch(foodItem -> foodItem.getFoodItemId().equals(cartInDTO.getFoodItemId()));

    if (!isFoodItemValid) {
      LOGGER.error("Food item with id: {} does not belong to restaurantId: {}",
        cartInDTO.getFoodItemId(), cartInDTO.getRestaurantId());
      throw new UnauthorizedException(Constants.FOOD_ITEM_DOES_NOT_BELONG_TO_RESTAURANT);
    }
  }

  /**
   * Fetches the food item details based on the food item ID.
   *
   * @param foodItemId the ID of the food item to fetch
   * @return the FoodItemOutDTO object containing food item details
   * @throws ResourceNotFoundException if the food item is not found
   */
  private FoodItemOutDTO fetchFoodItem(final Integer foodItemId) {
    try {
      FoodItemOutDTO foodItemOutDTO = foodItemFClient.getFoodItemById(foodItemId);
      LOGGER.info("Food item found: {}", foodItemOutDTO);
      return foodItemOutDTO;
    } catch (FeignException.NotFound ex) {
      LOGGER.error("Food item not found for foodItemId: {}", foodItemId);
      throw new ResourceNotFoundException(Constants.INVALID_FOOD_ITEM_ID);
    }
  }

  /**
   * Validates the price of the food item against the provided price.
   *
   * @param cartInDTO the DTO containing cart input data
   * @param foodItemOutDTO the FoodItemOutDTO object containing food item details
   * @throws InvalidRequestException if the prices do not match
   */
  private void validatePrice(final CartInDTO cartInDTO, final FoodItemOutDTO foodItemOutDTO) {
    if (foodItemOutDTO.getPrice().compareTo(cartInDTO.getPrice()) != 0) {
      LOGGER.error("Price mismatch for foodItemId: {}, expected: {}, provided: {}",
        cartInDTO.getFoodItemId(), foodItemOutDTO.getPrice(), cartInDTO.getPrice());
      throw new InvalidRequestException(Constants.PRICE_MISMATCH);
    }
  }

  /**
   * Processes the cart update by either updating the quantity of an existing item or adding a new item.
   *
   * @param cartInDTO the DTO containing cart input data
   * @param foodItemOutDTO the FoodItemOutDTO object containing food item details
   * @return a response indicating the result of the operation
   */
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
      LOGGER.info("Item quantity updated for cartId: {}", cart.getCartId());
      return new CommonResponse(Constants.ITEM_QUANTITY_UPDATED_SUCCESS);
    } else {
      Cart cart = CartDtoConverter.toEntity(cartInDTO);
      cartRepository.save(cart);
      LOGGER.info("Item added to cart for userId: {}", cartInDTO.getUserId());
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
    LOGGER.info("Fetching cart by cartId: {}", cartId);
    Cart cart = cartRepository.findById(cartId)
      .orElseThrow(() -> new ResourceNotFoundException(Constants.CART_NOT_FOUND));
    LOGGER.info("Cart found: {}", cart);
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
    LOGGER.info("Fetching carts for userId: {}", userId);
    UserOutDTO userOutDto;
    try {
      userOutDto = userFClient.getUserProfile(userId);
    } catch (Exception e) {
      LOGGER.error("User not found for userId: {}", userId);
      throw new ResourceNotFoundException(Constants.USER_NOT_FOUND);
    }
    List<Cart> carts = cartRepository.findByUserId(userId);
    if (carts.isEmpty()) {
      LOGGER.warn("No carts found for userId: {}", userId);
      throw new ResourceNotFoundException(Constants.CART_NOT_FOUND);
    }
    List<CartOutDTO> cartOutDTOs = new ArrayList<>();
    for (Cart cart : carts) {
      cartOutDTOs.add(CartDtoConverter.toOutDTO(cart));
    }
    LOGGER.info("Carts fetched successfully for userId: {}", userId);
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
    LOGGER.info("Fetching cart items for userId: {} and restaurantId: {}", userId, restaurantId);
    UserOutDTO userOutDto;
    try {
      userOutDto = userFClient.getUserProfile(userId);
    } catch (Exception e) {
      LOGGER.error("User not found for userId: {}", userId);
      throw new ResourceNotFoundException(Constants.USER_NOT_FOUND);
    }
    List<Cart> carts = cartRepository.findByUserIdAndRestaurantId(userId, restaurantId);
    if (carts.isEmpty()) {
      LOGGER.warn("No cart items found for userId: {} and restaurantId: {}", userId, restaurantId);
      throw new ResourceNotFoundException(Constants.CART_NOT_FOUND);
    }
    LOGGER.info("Cart items fetched successfully for userId: {} and restaurantId: {}", userId, restaurantId);
    return carts;
  }

  /**
   * Updates the quantity of an item in the cart.
   *
   * @param cartId         the ID of the cart to update
   * @param quantityChange the change in quantity to apply
   * @return a CommonResponse indicating the result of the operation
   */
  @Override
  @Transactional
  public CommonResponse updateQuantity(final Integer cartId, final Integer quantityChange) {
    LOGGER.info("Updating quantity for cartId: {}, quantity change: {}", cartId, quantityChange);
    Cart cart = cartRepository.findById(cartId)
      .orElseThrow(() -> new ResourceNotFoundException(Constants.CART_NOT_FOUND));
    double unitPrice = cart.getPrice() / cart.getQuantity();
    int newQuantity = Math.max(0, cart.getQuantity() + quantityChange);
    if (newQuantity == 0) {
      cartRepository.deleteById(cartId);
      LOGGER.info("Item removed from cart with cartId: {}", cartId);
      return new CommonResponse(Constants.ITEM_REMOVED_SUCCESSFULLY);
    }
    double newPrice = unitPrice * newQuantity;
    BigDecimal roundedPrice = BigDecimal.valueOf(newPrice).setScale(2, RoundingMode.HALF_EVEN);
    cart.setQuantity(newQuantity);
    cart.setPrice(roundedPrice.doubleValue());
    cartRepository.save(cart);
    LOGGER.info("Cart updated with new quantity for cartId: {}", cartId);
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
  public CommonResponse removeItemFromCart(final Integer cartId) {
    LOGGER.info("Removing item from cart with cartId: {}", cartId);
    Optional<Cart> cartOptional = cartRepository.findById(cartId);
    if (!cartOptional.isPresent()) {
      LOGGER.warn("Cart item not found with cartId: {}", cartId);
      throw new ResourceNotFoundException(Constants.CART_ITEM_NOT_FOUND);
    }
    cartRepository.deleteById(cartId);
    LOGGER.info("Cart item removed successfully for cartId: {}", cartId);
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
  public CommonResponse clearCartAfterPlaceAnOrder(final Integer userId) {
    LOGGER.info("Clearing cart after placing an order for userId: {}", userId);
    List<Cart> userCart = cartRepository.findByUserId(userId);
    if (userCart.isEmpty()) {
      LOGGER.warn("No cart items found for userId: {}", userId);
      throw new ResourceNotFoundException(Constants.CART_NOT_FOUND);
    }
    cartRepository.deleteAll(userCart);
    LOGGER.info("Cart cleared successfully for userId: {}", userId);
    return new CommonResponse(Constants.CART_CLEARED_SUCCESSFULLY);
  }
}



