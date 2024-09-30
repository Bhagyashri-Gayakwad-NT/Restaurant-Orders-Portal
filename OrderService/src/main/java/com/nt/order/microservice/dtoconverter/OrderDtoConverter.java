package com.nt.order.microservice.dtoconverter;

import com.nt.order.microservice.dtos.OrderInDTO;
import com.nt.order.microservice.dtos.OrderOutDTO;
import com.nt.order.microservice.entities.Order;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;

/**
 * Utility class for converting between Order entity and Order DTOs.
 */
public class OrderDtoConverter {

  /**
   * Converts an {@link OrderInDTO} to an {@link Order} entity.
   *
   * @param orderInDTO The input DTO containing order details.
   * @return The {@link Order} entity.
   */
  public static Order convertToEntity(final OrderInDTO orderInDTO) {
    final Order order = new Order();
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

  /**
   * Converts an {@link Order} entity to an {@link OrderOutDTO}.
   *
   * @param order The {@link Order} entity to be converted.
   * @return The {@link OrderOutDTO} containing order details.
   */
  public static OrderOutDTO convertToOutDto(final Order order) {
    final OrderOutDTO orderOutDTO = new OrderOutDTO();

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
    } catch (Exception e) {
      orderOutDTO.setCartItems(Collections.emptyList());
    }

    orderOutDTO.setPlacedTiming(order.getPlacedTiming());
    return orderOutDTO;
  }
}

