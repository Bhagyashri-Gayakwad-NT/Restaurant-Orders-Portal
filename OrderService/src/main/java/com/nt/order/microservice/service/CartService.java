package com.nt.order.microservice.service;

import com.nt.order.microservice.dtos.CartInDTO;
import com.nt.order.microservice.dtos.CartOutDTO;
import com.nt.order.microservice.dtos.CommonResponse;
import com.nt.order.microservice.entities.Cart;

import java.util.List;

public interface CartService {

 CommonResponse addItemToCart(CartInDTO cartInDTO);
  CartOutDTO getCartById(Integer cartId);
  List<CartOutDTO> getCartsByUserId(Integer userId);
  List<Cart> getCartItemsByUserIdAndRestaurantId(Integer userId, Integer restaurantId);
  CommonResponse updateQuantity(Integer cartId, Integer quantityChange);
  CommonResponse removeItemFromCart(Integer cartId);
  CommonResponse clearCartAfterPlaceAnOrder(Integer userId);
}

