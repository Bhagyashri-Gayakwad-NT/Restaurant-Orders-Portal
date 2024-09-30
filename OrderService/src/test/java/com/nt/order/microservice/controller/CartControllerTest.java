package com.nt.order.microservice.controller;

import com.nt.order.microservice.dtos.CartInDTO;
import com.nt.order.microservice.dtos.CartOutDTO;
import com.nt.order.microservice.dtos.CommonResponse;
import com.nt.order.microservice.entities.Cart;
import com.nt.order.microservice.service.CartService;
import com.nt.order.microservice.util.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CartControllerTest {

  @Mock
  private CartService cartService;

  @InjectMocks
  private CartController cartController;

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

    // Mock service response
    CommonResponse commonResponse = new CommonResponse(Constants.ITEM_ADDED_TO_CART_SUCCESS);
    when(cartService.addItemToCart(cartInDTO)).thenReturn(commonResponse);

    // Call the controller method
    ResponseEntity<CommonResponse> responseEntity = cartController.addItemToCart(cartInDTO);

    // Assertions
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(commonResponse, responseEntity.getBody());
    verify(cartService, times(1)).addItemToCart(cartInDTO);
  }

  @Test
  public void testUpdateCartQuantity_Success() {
    int cartId = 1;
    int quantityChange = 2;

    // Mock service response
    CommonResponse commonResponse = new CommonResponse(Constants.ITEM_QUANTITY_UPDATED_SUCCESS);
    when(cartService.updateQuantity(cartId, quantityChange)).thenReturn(commonResponse);

    // Call the controller method
    ResponseEntity<CommonResponse> responseEntity = cartController.updateCartQuantity(cartId, quantityChange);

    // Assertions
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(commonResponse, responseEntity.getBody());
    verify(cartService, times(1)).updateQuantity(cartId, quantityChange);
  }

  @Test
  public void testGetCartById_Success() {
    int cartId = 1;

    // Mock service response
    CartOutDTO cartOutDTO = new CartOutDTO();
    cartOutDTO.setCartId(cartId);
    cartOutDTO.setQuantity(2);
    cartOutDTO.setPrice(100.0);

    when(cartService.getCartById(cartId)).thenReturn(cartOutDTO);

    // Call the controller method
    ResponseEntity<CartOutDTO> responseEntity = cartController.getCartById(cartId);

    // Assertions
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(cartOutDTO, responseEntity.getBody());
    verify(cartService, times(1)).getCartById(cartId);
  }

  @Test
  public void testGetCartsByUserId_Success() {
    int userId = 1;

    // Mock service response
    CartOutDTO cartOutDTO1 = new CartOutDTO();
    cartOutDTO1.setCartId(1);
    cartOutDTO1.setQuantity(1);
    cartOutDTO1.setPrice(100.0);

    CartOutDTO cartOutDTO2 = new CartOutDTO();
    cartOutDTO2.setCartId(2);
    cartOutDTO2.setQuantity(2);
    cartOutDTO2.setPrice(200.0);

    List<CartOutDTO> carts = Arrays.asList(cartOutDTO1, cartOutDTO2);
    when(cartService.getCartsByUserId(userId)).thenReturn(carts);

    // Call the controller method
    ResponseEntity<List<CartOutDTO>> responseEntity = cartController.getCartsByUserId(userId);

    // Assertions
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(carts, responseEntity.getBody());
    verify(cartService, times(1)).getCartsByUserId(userId);
  }

  @Test
  public void testGetCartItemsByUserIdAndRestaurantId_Success() {
    int userId = 1;
    int restaurantId = 2;

    // Mock service response
    Cart cart1 = new Cart(1, 1, 2, 3, 1, 100.0);
    Cart cart2 = new Cart(2, 1, 2, 4, 2, 200.0);
    List<Cart> carts = Arrays.asList(cart1, cart2);

    when(cartService.getCartItemsByUserIdAndRestaurantId(userId, restaurantId)).thenReturn(carts);

    // Call the controller method
    ResponseEntity<List<Cart>> responseEntity = cartController.getCartItemsByUserIdAndRestaurantId(userId, restaurantId);

    // Assertions
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(carts, responseEntity.getBody());
    verify(cartService, times(1)).getCartItemsByUserIdAndRestaurantId(userId, restaurantId);
  }

  @Test
  public void testRemoveItemFromCart_Success() {
    int cartId = 1;

    // Mock service response
    CommonResponse commonResponse = new CommonResponse(Constants.CART_ITEM_REMOVED_SUCCESSFULLY);
    when(cartService.removeItemFromCart(cartId)).thenReturn(commonResponse);

    // Call the controller method
    ResponseEntity<CommonResponse> responseEntity = cartController.removeItemFromCart(cartId);

    // Assertions
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(commonResponse, responseEntity.getBody());
    verify(cartService, times(1)).removeItemFromCart(cartId);
  }

  @Test
  public void testClearCartAfterPlaceAnOrder_Success() {
    int userId = 1;

    // Mock service response
    CommonResponse commonResponse = new CommonResponse(Constants.CART_CLEARED_SUCCESSFULLY);
    when(cartService.clearCartAfterPlaceAnOrder(userId)).thenReturn(commonResponse);

    // Call the controller method
    ResponseEntity<CommonResponse> responseEntity = cartController.clearCartAfterPlaceAnOrder(userId);

    // Assertions
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(commonResponse, responseEntity.getBody());
    verify(cartService, times(1)).clearCartAfterPlaceAnOrder(userId);
  }
}
