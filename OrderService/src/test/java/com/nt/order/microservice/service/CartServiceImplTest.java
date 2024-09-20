package com.nt.order.microservice.service;

import com.nt.order.microservice.dtos.*;
import com.nt.order.microservice.entities.Cart;
import com.nt.order.microservice.exception.InvalidRequestException;
import com.nt.order.microservice.exception.ResourceNotFoundException;
import com.nt.order.microservice.exception.UnauthorizedException;
import com.nt.order.microservice.repository.CartRepository;
import com.nt.order.microservice.repository.OrderRepository;
import com.nt.order.microservice.serviceimpl.CartServiceImpl;
import com.nt.order.microservice.serviceimpl.FoodItemFClient;
import com.nt.order.microservice.serviceimpl.RestaurantFClient;
import com.nt.order.microservice.serviceimpl.UserFClient;
import com.nt.order.microservice.util.Constants;
import com.nt.order.microservice.util.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CartServiceImplTest {

  @Mock
  private CartRepository cartRepository;

  @Mock
  private OrderRepository orderRepository;

  @Mock
  private UserFClient userFClient;

  @Mock
  private RestaurantFClient restaurantFClient;

  @Mock
  private FoodItemFClient foodItemFClient;

  @InjectMocks
  private CartServiceImpl cartServiceImpl;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testAddItemToCart_Success() {
    // Mock input data
    CartInDTO cartInDTO = new CartInDTO();
    cartInDTO.setUserId(1);
    cartInDTO.setRestaurantId(2);
    cartInDTO.setFoodItemId(3);
    cartInDTO.setQuantity(1);
    cartInDTO.setPrice(100.0);

    // Mock external service calls
    UserOutDTO userOutDTO = new UserOutDTO();
    userOutDTO.setId(1);
    userOutDTO.setRole(Role.USER.name());

    when(userFClient.getUserProfile(1)).thenReturn(userOutDTO);

    RestaurantOutDTO restaurantOutDTO = new RestaurantOutDTO();
    restaurantOutDTO.setRestaurantId(2);

    when(restaurantFClient.getRestaurantById(2)).thenReturn(restaurantOutDTO);

    FoodItemOutDTO foodItemOutDTO = new FoodItemOutDTO();
    foodItemOutDTO.setFoodItemId(3);
    foodItemOutDTO.setPrice(100.0);

    when(foodItemFClient.getFoodItemById(3)).thenReturn(foodItemOutDTO);

    when(cartRepository.findByUserId(1)).thenReturn(Collections.emptyList());

    // Call the method and verify behavior
    CommonResponse response = cartServiceImpl.addItemToCart(cartInDTO);

    assertNotNull(response);
    assertEquals(Constants.ITEM_ADDED_TO_CART_SUCCESS, response.getMessage());
    verify(cartRepository, times(1)).save(any(Cart.class));
  }

  @Test
  public void testAddItemToCart_RestaurantOwnerNotAllowed() {
    CartInDTO cartInDTO = new CartInDTO();
    cartInDTO.setUserId(1);
    cartInDTO.setRestaurantId(2);
    cartInDTO.setFoodItemId(3);
    cartInDTO.setQuantity(1);

    UserOutDTO userOutDTO = new UserOutDTO();
    userOutDTO.setId(1);
    userOutDTO.setRole(Role.RESTAURANT_OWNER.name());

    when(userFClient.getUserProfile(1)).thenReturn(userOutDTO);

    UnauthorizedException exception = assertThrows(UnauthorizedException.class, () -> {
      cartServiceImpl.addItemToCart(cartInDTO);
    });

    assertEquals(Constants.RESTAURANT_OWNER_CART_ERROR, exception.getMessage());
  }

  @Test
  public void testAddItemToCart_FoodItemPriceMismatch() {
    CartInDTO cartInDTO = new CartInDTO();
    cartInDTO.setUserId(1);
    cartInDTO.setRestaurantId(2);
    cartInDTO.setFoodItemId(3);
    cartInDTO.setQuantity(1);
    cartInDTO.setPrice(150.0);

    UserOutDTO userOutDTO = new UserOutDTO();
    userOutDTO.setId(1);
    userOutDTO.setRole(Role.USER.name());

    when(userFClient.getUserProfile(1)).thenReturn(userOutDTO);

    RestaurantOutDTO restaurantOutDTO = new RestaurantOutDTO();
    when(restaurantFClient.getRestaurantById(2)).thenReturn(restaurantOutDTO);

    FoodItemOutDTO foodItemOutDTO = new FoodItemOutDTO();
    foodItemOutDTO.setFoodItemId(3);
    foodItemOutDTO.setPrice(100.0);

    when(foodItemFClient.getFoodItemById(3)).thenReturn(foodItemOutDTO);

    InvalidRequestException exception = assertThrows(InvalidRequestException.class, () -> {
      cartServiceImpl.addItemToCart(cartInDTO);
    });

    assertEquals(Constants.PRICE_MISMATCH, exception.getMessage());
  }

  @Test
  public void testGetCartById_Success() {
    Cart cart = new Cart(1, 1, 2, 3, 1, 100.0);

    when(cartRepository.findById(1)).thenReturn(Optional.of(cart));

    CartOutDTO result = cartServiceImpl.getCartById(1);

    assertNotNull(result);
    assertEquals(cart.getCartId(), result.getCartId());
    verify(cartRepository, times(1)).findById(1);
  }

  @Test
  public void testGetCartById_NotFound() {
    when(cartRepository.findById(1)).thenReturn(Optional.empty());

    ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
      cartServiceImpl.getCartById(1);
    });

    assertEquals(Constants.CART_NOT_FOUND, exception.getMessage());
  }

  @Test
  public void testRemoveItemFromCart_Success() {
    Cart cart = new Cart(1, 1, 2, 3, 1, 100.0);

    when(cartRepository.findById(1)).thenReturn(Optional.of(cart));

    CommonResponse response = cartServiceImpl.removeItemFromCart(1);

    assertNotNull(response);
    assertEquals(Constants.CART_ITEM_REMOVED_SUCCESSFULLY, response.getMessage());
    verify(cartRepository, times(1)).deleteById(1);
  }

  @Test
  public void testRemoveItemFromCart_NotFound() {
    when(cartRepository.findById(1)).thenReturn(Optional.empty());

    ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
      cartServiceImpl.removeItemFromCart(1);
    });

    assertEquals(Constants.CART_ITEM_NOT_FOUND, exception.getMessage());
  }

  @Test
  public void testClearCartAfterPlaceAnOrder_Success() {
    List<Cart> carts = Arrays.asList(
      new Cart(1, 1, 2, 3, 1, 100.0),
      new Cart(2, 1, 2, 4, 2, 200.0)
    );

    when(cartRepository.findByUserId(1)).thenReturn(carts);

    CommonResponse response = cartServiceImpl.clearCartAfterPlaceAnOrder(1);

    assertNotNull(response);
    assertEquals(Constants.CART_CLEARED_SUCCESSFULLY, response.getMessage());
    verify(cartRepository, times(1)).deleteAll(carts);
  }
  @Test
  void testGetCartItemsByUserIdAndRestaurantId_Success() {
    // Arrange
    Integer userId = 1;
    Integer restaurantId = 2;
    UserOutDTO userOutDTO = new UserOutDTO();
    Cart cart = new Cart();
    cart.setCartId(1);
    cart.setUserId(userId);
    cart.setRestaurantId(restaurantId);
    List<Cart> carts = Collections.singletonList(cart);
    when(userFClient.getUserProfile(userId)).thenReturn(userOutDTO);
    when(cartRepository.findByUserIdAndRestaurantId(userId, restaurantId)).thenReturn(carts);

    // Act
    List<Cart> result = cartServiceImpl.getCartItemsByUserIdAndRestaurantId(userId, restaurantId);

    // Assert
    assertNotNull(result);
    assertEquals(1, result.size());
    assertEquals(userId, result.get(0).getUserId());
    assertEquals(restaurantId, result.get(0).getRestaurantId());
  }

  @Test
  void testGetCartItemsByUserIdAndRestaurantId_UserNotFound() {
    // Arrange
    Integer userId = 1;
    Integer restaurantId = 2;
    when(userFClient.getUserProfile(userId)).thenThrow(new RuntimeException("User not found"));

    // Act & Assert
    ResourceNotFoundException thrown = assertThrows(
      ResourceNotFoundException.class,
      () -> cartServiceImpl.getCartItemsByUserIdAndRestaurantId(userId, restaurantId)
    );
    assertEquals(Constants.USER_NOT_FOUND, thrown.getMessage());
  }

  @Test
  void testGetCartItemsByUserIdAndRestaurantId_NoItemsFound() {
    // Arrange
    Integer userId = 1;
    Integer restaurantId = 2;
    UserOutDTO userOutDTO = new UserOutDTO();
    when(userFClient.getUserProfile(userId)).thenReturn(userOutDTO);
    when(cartRepository.findByUserIdAndRestaurantId(userId, restaurantId)).thenReturn(Collections.emptyList());

    // Act & Assert
    ResourceNotFoundException thrown = assertThrows(
      ResourceNotFoundException.class,
      () -> cartServiceImpl.getCartItemsByUserIdAndRestaurantId(userId, restaurantId)
    );
    assertEquals(Constants.CART_NOT_FOUND, thrown.getMessage());
  }

  @Test
  void testUpdateQuantity_Success() {
    // Arrange
    Integer cartId = 1;
    Integer quantityChange = 3;
    Cart cart = new Cart();
    cart.setCartId(cartId);
    cart.setQuantity(5);
    cart.setPrice(50.0);
    when(cartRepository.findById(cartId)).thenReturn(Optional.of(cart));
    when(cartRepository.save(any(Cart.class))).thenReturn(cart);

    // Act
    CommonResponse response = cartServiceImpl.updateQuantity(cartId, quantityChange);

    // Assert
    assertNotNull(response);
    assertEquals(Constants.ITEM_QUANTITY_UPDATED_SUCCESS, response.getMessage());
    assertEquals(8, cart.getQuantity());
    assertEquals(80.0, cart.getPrice());
  }

  @Test
  void testUpdateQuantity_ItemRemoved() {
    // Arrange
    Integer cartId = 1;
    Integer quantityChange = -5;
    Cart cart = new Cart();
    cart.setCartId(cartId);
    cart.setQuantity(5);
    cart.setPrice(50.0);
    when(cartRepository.findById(cartId)).thenReturn(Optional.of(cart));
    doNothing().when(cartRepository).deleteById(cartId);

    // Act & Assert
    InvalidRequestException thrown = assertThrows(
      InvalidRequestException.class,
      () -> cartServiceImpl.updateQuantity(cartId, quantityChange)
    );
    assertEquals(Constants.ITEM_REMOVED_SUCCESSFULLY, thrown.getMessage());
  }

  @Test
  void testUpdateQuantity_NotFound() {
    // Arrange
    Integer cartId = 1;
    Integer quantityChange = 3;
    when(cartRepository.findById(cartId)).thenReturn(Optional.empty());

    // Act & Assert
    ResourceNotFoundException thrown = assertThrows(
      ResourceNotFoundException.class,
      () -> cartServiceImpl.updateQuantity(cartId, quantityChange)
    );
    assertEquals(Constants.CART_NOT_FOUND, thrown.getMessage());
  }

  @Test
  public void testClearCartAfterPlaceAnOrder_NoCartItems() {
    when(cartRepository.findByUserId(1)).thenReturn(Collections.emptyList());

    ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
      cartServiceImpl.clearCartAfterPlaceAnOrder(1);
    });

    assertEquals(Constants.CART_NOT_FOUND, exception.getMessage());
  }
}
