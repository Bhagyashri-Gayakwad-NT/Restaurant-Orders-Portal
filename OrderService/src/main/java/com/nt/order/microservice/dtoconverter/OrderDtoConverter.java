package com.nt.order.microservice.dtoconverter;

import com.nt.order.microservice.dtos.OrderInDTO;
import com.nt.order.microservice.dtos.OrderOutDTO;
import com.nt.order.microservice.entities.Cart;
import com.nt.order.microservice.entities.Order;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;


public class OrderDtoConverter {
  public static Order convertToEntity(OrderInDTO orderInDTO) {
    Order order = new Order();
    order.setUserId(orderInDTO.getUserId());
    order.setRestaurantId(orderInDTO.getRestaurantId());
    order.setAddressId(orderInDTO.getAddressId());
    try {
      order.setCartItemsFromList(orderInDTO.getCartItems());
    } catch (Exception e) {
      order.setCartItems("");
    }

    return order;
  }
  public static  OrderOutDTO convertToOutDto(Order order) {
    OrderOutDTO orderOutDTO = new OrderOutDTO();
    System.out.println(order.getOrderId());
    orderOutDTO.setOrderId(order.getOrderId());

    orderOutDTO.setUserId(order.getUserId());
    orderOutDTO.setRestaurantId(order.getRestaurantId());
    orderOutDTO.setAddressId(order.getAddressId());
    orderOutDTO.setOrderStatus(order.getOrderStatus());
    if (order.getTotalPrice() != null) {
      BigDecimal roundedPrice = BigDecimal.valueOf(order.getTotalPrice())
        .setScale(2, RoundingMode.HALF_EVEN);
      orderOutDTO.setTotalPrice(roundedPrice.doubleValue());
    } else {
      orderOutDTO.setTotalPrice(null);
    }
    try {
      orderOutDTO.setCartItems(order.getCartItemOutDTOAsList());
    }
    catch (Exception e) {
      orderOutDTO.setCartItems(Collections.emptyList());
    }
    orderOutDTO.setPlacedTiming(order.getPlacedTiming());
    return orderOutDTO;
  }

  // Utility method to calculate total price from cart items
//  private Double calculateTotalPrice(List<Cart> cartItems) {
//    return cartItems.stream()
//      .mapToDouble(cartItem -> cartItem.getPrice() * cartItem.getQuantity())
//      .sum();
//  }
}