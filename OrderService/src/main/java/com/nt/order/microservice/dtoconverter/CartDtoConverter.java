package com.nt.order.microservice.dtoconverter;

import com.nt.order.microservice.dtos.CartInDTO;
import com.nt.order.microservice.dtos.CartOutDTO;
import com.nt.order.microservice.entities.Cart;

public  class CartDtoConverter {
  public static Cart toEntity(CartInDTO cartInDTO) {
    Cart cart = new Cart();
    cart.setUserId(cartInDTO.getUserId());
    cart.setRestaurantId(cartInDTO.getRestaurantId());
    cart.setFoodItemId(cartInDTO.getFoodItemId());
    cart.setQuantity(cartInDTO.getQuantity());
    cart.setPrice(cartInDTO.getPrice());
    return cart;
  }
  public static CartOutDTO toOutDTO(Cart cart) {
    CartOutDTO cartOutDTO = new CartOutDTO();
    cartOutDTO.setCartId(cart.getCartId());
    cartOutDTO.setUserId(cart.getUserId());
    cartOutDTO.setRestaurantId(cart.getRestaurantId());
    cartOutDTO.setFoodItemId(cart.getFoodItemId());
    cartOutDTO.setQuantity(cart.getQuantity());
    cartOutDTO.setPrice(cart.getPrice());
    return cartOutDTO;
  }
}
