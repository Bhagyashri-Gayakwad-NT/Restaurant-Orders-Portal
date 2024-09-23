package com.nt.order.microservice.dtoconverter;

import com.nt.order.microservice.dtos.CartInDTO;
import com.nt.order.microservice.dtos.CartOutDTO;
import com.nt.order.microservice.entities.Cart;

/**
 * Utility class for converting between Cart entity and Cart DTOs.
 */
public class CartDtoConverter {

  /**
   * Converts a {@link CartInDTO} to a {@link Cart} entity.
   *
   * @param cartInDTO The input DTO containing cart details.
   * @return A {@link Cart} entity.
   */
  public static Cart toEntity(final CartInDTO cartInDTO) {
    final Cart cart = new Cart();
    cart.setUserId(cartInDTO.getUserId());
    cart.setRestaurantId(cartInDTO.getRestaurantId());
    cart.setFoodItemId(cartInDTO.getFoodItemId());
    cart.setQuantity(cartInDTO.getQuantity());
    cart.setPrice(cartInDTO.getPrice());
    return cart;
  }

  /**
   * Converts a {@link Cart} entity to a {@link CartOutDTO}.
   *
   * @param cart The Cart entity to be converted.
   * @return A {@link CartOutDTO} containing cart details.
   */
  public static CartOutDTO toOutDTO(final Cart cart) {
    final CartOutDTO cartOutDTO = new CartOutDTO();
    cartOutDTO.setCartId(cart.getCartId());
    cartOutDTO.setUserId(cart.getUserId());
    cartOutDTO.setRestaurantId(cart.getRestaurantId());
    cartOutDTO.setFoodItemId(cart.getFoodItemId());
    cartOutDTO.setQuantity(cart.getQuantity());
    cartOutDTO.setPrice(cart.getPrice());
    return cartOutDTO;
  }
}