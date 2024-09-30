package com.nt.order.microservice.dtos;

import com.nt.order.microservice.util.OrderStatus;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderOutDTOTest {

  @Test
  public void testDefaultConstructor() {
    OrderOutDTO orderOutDTO = new OrderOutDTO();

    assertNull(orderOutDTO.getOrderId());
    assertNull(orderOutDTO.getUserId());
    assertNull(orderOutDTO.getRestaurantId());
    assertNull(orderOutDTO.getAddressId());
    assertNull(orderOutDTO.getOrderStatus());
    assertNull(orderOutDTO.getTotalPrice());
    assertNull(orderOutDTO.getCartItems());
    assertNull(orderOutDTO.getPlacedTiming());
  }

  @Test
  public void testParameterizedConstructor() {
    OrderOutDTO orderOutDTO = new OrderOutDTO(1, 2, 3, 4, OrderStatus.PLACED,
      100.0, Collections.emptyList(), LocalDateTime.now());

    assertEquals(1, orderOutDTO.getOrderId());
    assertEquals(2, orderOutDTO.getUserId());
    assertEquals(3, orderOutDTO.getRestaurantId());
    assertEquals(4, orderOutDTO.getAddressId());
    assertEquals(OrderStatus.PLACED, orderOutDTO.getOrderStatus());
    assertEquals(100.0, orderOutDTO.getTotalPrice());
    assertNotNull(orderOutDTO.getCartItems());
    assertEquals(0, orderOutDTO.getCartItems().size());
    assertNotNull(orderOutDTO.getPlacedTiming());
  }

  @Test
  public void testSettersAndGetters() {
    OrderOutDTO orderOutDTO = new OrderOutDTO();

    orderOutDTO.setOrderId(1);
    orderOutDTO.setUserId(2);
    orderOutDTO.setRestaurantId(3);
    orderOutDTO.setAddressId(4);
    orderOutDTO.setOrderStatus(OrderStatus.PLACED);
    orderOutDTO.setTotalPrice(100.0);
    orderOutDTO.setCartItems(Collections.emptyList());
    orderOutDTO.setPlacedTiming(LocalDateTime.now());

    assertEquals(1, orderOutDTO.getOrderId());
    assertEquals(2, orderOutDTO.getUserId());
    assertEquals(3, orderOutDTO.getRestaurantId());
    assertEquals(4, orderOutDTO.getAddressId());
    assertEquals(OrderStatus.PLACED, orderOutDTO.getOrderStatus());
    assertEquals(100.0, orderOutDTO.getTotalPrice());
    assertNotNull(orderOutDTO.getCartItems());
    assertEquals(0, orderOutDTO.getCartItems().size());
  }

  @Test
  public void testEqualsAndHashCode() {
    LocalDateTime fixedTiming = LocalDateTime.of(2024, 9, 21, 18, 19, 26);

    OrderOutDTO orderOutDTO1 = new OrderOutDTO(1, 2, 3, 4, OrderStatus.PLACED,
      100.0, Collections.emptyList(), fixedTiming);
    OrderOutDTO orderOutDTO2 = new OrderOutDTO(1, 2, 3, 4, OrderStatus.PLACED,
      100.0, Collections.emptyList(), fixedTiming);

    assertEquals(orderOutDTO1, orderOutDTO2);
    assertEquals(orderOutDTO1.hashCode(), orderOutDTO2.hashCode());

    // Given a different OrderOutDTO
    OrderOutDTO orderOutDTO3 = new OrderOutDTO(2, 3, 4, 5, OrderStatus.CANCELLED,
      200.0, Collections.emptyList(), fixedTiming);

    // Then they should not be equal
    assertNotEquals(orderOutDTO1, orderOutDTO3);
  }


  @Test
  public void testToString() {
    OrderOutDTO orderOutDTO = new OrderOutDTO(1, 2, 3, 4, OrderStatus.PLACED,
      100.0, Collections.emptyList(), LocalDateTime.now());

    String expectedString = "OrderOutDTO{" +
      "OrderId=1" +
      ", userId=2" +
      ", restaurantId=3" +
      ", addressId=4" +
      ", orderStatus=PLACED" +
      ", totalPrice=100.0" +
      ", cartItems=[]" +
      ", placedTiming=" + orderOutDTO.getPlacedTiming() +
      '}';
    assertTrue(orderOutDTO.toString().startsWith("OrderOutDTO{"));
    assertFalse(orderOutDTO.toString().contains("OrderId=1"));
  }
}
