package com.nt.order.microservice.controller;

import com.nt.order.microservice.dtos.CartInDTO;
import com.nt.order.microservice.dtos.CartOutDTO;
import com.nt.order.microservice.dtos.CommonResponse;
import com.nt.order.microservice.entities.Cart;
import com.nt.order.microservice.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;


/**
 * Controller class for handling operations related to the Cart.
 * This includes adding, updating, retrieving, and removing cart items for users.
 */
@RestController
@RequestMapping("/cart")
@CrossOrigin
public class CartController {

  private static final Logger logger = LoggerFactory.getLogger(CartController.class);  // SLF4J logger

  @Autowired
  private CartService cartService;

  /**
   * Adds an item to the cart.
   *
   * @param cartInDTO the CartInDTO object containing item details to be added to the cart
   * @return ResponseEntity containing the response message
   */
  @PostMapping("/add")
  public ResponseEntity<CommonResponse> addItemToCart(@Valid @RequestBody final CartInDTO cartInDTO) {
    logger.info("Received request to add item to cart: {}", cartInDTO);
    CommonResponse response = cartService.addItemToCart(cartInDTO);
    logger.info("Item added to cart with response: {}", response);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  /**
   * Updates the quantity of a cart item.
   *
   * @param cartId        the ID of the cart item to be updated
   * @param quantityChange the new quantity change for the cart item
   * @return ResponseEntity containing the response message
   */
  @PutMapping("/update/{cartId}")
  public ResponseEntity<CommonResponse> updateCartQuantity(@Valid @PathVariable final Integer cartId,
                                                           @RequestParam final Integer quantityChange) {
    logger.info("Received request to update quantity for cartId: {}, quantityChange: {}", cartId, quantityChange);
    CommonResponse commonResponse = cartService.updateQuantity(cartId, quantityChange);
    logger.info("Cart quantity updated for cartId: {}, response: {}", cartId, commonResponse);
    return new ResponseEntity<>(commonResponse, HttpStatus.OK);
  }

  /**
   * Fetches the cart by its ID.
   *
   * @param cartId the ID of the cart to be retrieved
   * @return ResponseEntity containing the cart details
   */
  @GetMapping("/{cartId}")
  public ResponseEntity<CartOutDTO> getCartById(@PathVariable final Integer cartId) {
    logger.info("Fetching cart by cartId: {}", cartId);
    CartOutDTO cartOutDTO = cartService.getCartById(cartId);
    logger.info("Fetched cart: {}", cartOutDTO);
    return ResponseEntity.ok(cartOutDTO);
  }

  /**
   * Fetches all cart items for a specific user.
   *
   * @param userId the ID of the user whose cart items are to be retrieved
   * @return ResponseEntity containing the list of cart items
   */
  @GetMapping("/user/{userId}")
  public ResponseEntity<List<CartOutDTO>> getCartsByUserId(@PathVariable final Integer userId) {
    logger.info("Fetching carts for userId: {}", userId);
    List<CartOutDTO> carts = cartService.getCartsByUserId(userId);
    logger.info("Fetched carts for userId: {}: {}", userId, carts);
    return ResponseEntity.ok(carts);
  }

  /**
   * Fetches cart items for a specific user and restaurant.
   *
   * @param userId       the ID of the user
   * @param restaurantId the ID of the restaurant
   * @return ResponseEntity containing the list of cart items for the user and restaurant
   */
  @GetMapping("/user/{userId}/restaurant/{restaurantId}")
  public ResponseEntity<List<Cart>> getCartItemsByUserIdAndRestaurantId(@PathVariable final Integer userId,
                                                                        @PathVariable final Integer restaurantId) {
    logger.info("Fetching cart items for userId: {} and restaurantId: {}", userId, restaurantId);
    List<Cart> carts = cartService.getCartItemsByUserIdAndRestaurantId(userId, restaurantId);
    logger.info("Fetched cart items: {}", carts);
    return ResponseEntity.ok(carts);
  }

  /**
   * Removes an item from the cart by its ID.
   *
   * @param cartId the ID of the cart item to be removed
   * @return ResponseEntity containing the response message
   */
  @DeleteMapping("/{cartId}")
  public ResponseEntity<CommonResponse> removeItemFromCart(@PathVariable final Integer cartId) {
    logger.info("Received request to remove item from cart with cartId: {}", cartId);
    CommonResponse response = cartService.removeItemFromCart(cartId);
    logger.info("Item removed from cart with response: {}", response);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  /**
   * Clears the cart for a user after placing an order.
   *
   * @param userId the ID of the user whose cart is to be cleared
   * @return ResponseEntity containing the response message
   */
  @DeleteMapping("/clear/{userId}")
  public ResponseEntity<CommonResponse> clearCartAfterPlaceAnOrder(@PathVariable final Integer userId) {
    logger.info("Received request to clear cart for userId: {}", userId);
    CommonResponse response = cartService.clearCartAfterPlaceAnOrder(userId);
    logger.info("Cart cleared for userId: {}, response: {}", userId, response);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
