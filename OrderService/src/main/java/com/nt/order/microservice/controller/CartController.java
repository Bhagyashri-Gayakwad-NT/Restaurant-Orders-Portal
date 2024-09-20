package com.nt.order.microservice.controller;

import com.nt.order.microservice.dtos.CartInDTO;
import com.nt.order.microservice.dtos.CartOutDTO;
import com.nt.order.microservice.dtos.CommonResponse;
import com.nt.order.microservice.entities.Cart;
import com.nt.order.microservice.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cart")
@CrossOrigin
public class CartController {

  private static final Logger logger = LoggerFactory.getLogger(CartController.class);  // SLF4J logger

  @Autowired
  private CartService cartService;

  @PostMapping("/add")
  public ResponseEntity<CommonResponse> addItemToCart(@Valid @RequestBody CartInDTO cartInDTO) {
    logger.info("Received request to add item to cart: {}", cartInDTO);
    CommonResponse response = cartService.addItemToCart(cartInDTO);
    logger.info("Item added to cart with response: {}", response);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PutMapping("/update/{cartId}")
  public ResponseEntity<CommonResponse> updateCartQuantity(@Valid @PathVariable Integer cartId, @RequestParam Integer quantityChange) {
    logger.info("Received request to update quantity for cartId: {}, quantityChange: {}", cartId, quantityChange);
    CommonResponse commonResponse = cartService.updateQuantity(cartId, quantityChange);
    logger.info("Cart quantity updated for cartId: {}, response: {}", cartId, commonResponse);
    return new ResponseEntity<>(commonResponse, HttpStatus.OK);
  }

  @GetMapping("/{cartId}")
  public ResponseEntity<CartOutDTO> getCartById(@PathVariable Integer cartId) {
    logger.info("Fetching cart by cartId: {}", cartId);
    CartOutDTO cartOutDTO = cartService.getCartById(cartId);
    logger.info("Fetched cart: {}", cartOutDTO);
    return ResponseEntity.ok(cartOutDTO);
  }

  @GetMapping("/user/{userId}")
  public ResponseEntity<List<CartOutDTO>> getCartsByUserId(@PathVariable Integer userId) {
    logger.info("Fetching carts for userId: {}", userId);
    List<CartOutDTO> carts = cartService.getCartsByUserId(userId);
    logger.info("Fetched carts for userId: {}: {}", userId, carts);
    return ResponseEntity.ok(carts);
  }

  @GetMapping("/user/{userId}/restaurant/{restaurantId}")
  public ResponseEntity<List<Cart>> getCartItemsByUserIdAndRestaurantId(
    @PathVariable Integer userId,
    @PathVariable Integer restaurantId) {
    logger.info("Fetching cart items for userId: {} and restaurantId: {}", userId, restaurantId);
    List<Cart> carts = cartService.getCartItemsByUserIdAndRestaurantId(userId, restaurantId);
    logger.info("Fetched cart items: {}", carts);
    return ResponseEntity.ok(carts);
  }

  @DeleteMapping("/{cartId}")
  public ResponseEntity<CommonResponse> removeItemFromCart(@PathVariable Integer cartId) {
    logger.info("Received request to remove item from cart with cartId: {}", cartId);
    CommonResponse response = cartService.removeItemFromCart(cartId);
    logger.info("Item removed from cart with response: {}", response);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @DeleteMapping("/clear/{userId}")
  public ResponseEntity<CommonResponse> clearCartAfterPlaceAnOrder(@PathVariable Integer userId) {
    logger.info("Received request to clear cart for userId: {}", userId);
    CommonResponse response = cartService.clearCartAfterPlaceAnOrder(userId);
    logger.info("Cart cleared for userId: {}, response: {}", userId, response);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
