package com.nt.order.microservice.service;

import com.nt.order.microservice.dtos.CartInDTO;
import com.nt.order.microservice.dtos.CartOutDTO;
import com.nt.order.microservice.dtos.CommonResponse;
import com.nt.order.microservice.entities.Cart;

import java.util.List;

/**
 * Service interface for handling shopping cart operations.
 * <p>
 * This service defines methods for adding items to the cart, retrieving carts,
 * updating item quantities, removing items, and clearing the cart after an order is placed.
 * </p>
 */
public interface CartService {

  /**
   * Adds an item to the user's cart.
   *
   * @param cartInDTO the data transfer object containing the item details to be added to the cart
   * @return {@link CommonResponse} indicating the result of the operation
   */
  CommonResponse addItemToCart(CartInDTO cartInDTO);

  /**
   * Retrieves the cart details by the cart ID.
   *
   * @param cartId the ID of the cart to be retrieved
   * @return {@link CartOutDTO} containing the cart details
   */
  CartOutDTO getCartById(Integer cartId);

  /**
   * Retrieves a list of carts for a specific user.
   *
   * @param userId the ID of the user whose carts are being retrieved
   * @return a list of {@link CartOutDTO} representing the user's carts
   */
  List<CartOutDTO> getCartsByUserId(Integer userId);

  /**
   * Retrieves cart items for a user and a specific restaurant.
   *
   * @param userId       the ID of the user
   * @param restaurantId the ID of the restaurant
   * @return a list of {@link Cart} entities representing the cart items
   */
  List<Cart> getCartItemsByUserIdAndRestaurantId(Integer userId, Integer restaurantId);

  /**
   * Updates the quantity of an item in the cart.
   *
   * @param cartId        the ID of the cart item to be updated
   * @param quantityChange the amount by which the item quantity is to be changed
   * @return {@link CommonResponse} indicating the result of the quantity update
   */
  CommonResponse updateQuantity(Integer cartId, Integer quantityChange);

  /**
   * Removes an item from the cart.
   *
   * @param cartId the ID of the cart item to be removed
   * @return {@link CommonResponse} indicating the result of the removal
   */
  CommonResponse removeItemFromCart(Integer cartId);

  /**
   * Clears the cart after an order has been placed for a specific user.
   *
   * @param userId the ID of the user whose cart is to be cleared
   * @return {@link CommonResponse} indicating the result of the cart clearance
   */
  CommonResponse clearCartAfterPlaceAnOrder(Integer userId);
}

