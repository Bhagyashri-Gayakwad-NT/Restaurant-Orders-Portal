package com.nt.order.microservice.entities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nt.order.microservice.dtos.CartItemDTO;
import com.nt.order.microservice.util.OrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {


  private Order order;
  private LocalDateTime fixedPlacedTiming;

  @BeforeEach
  void setUp() {
    fixedPlacedTiming = LocalDateTime.of(2024, 9, 22, 22, 51, 5, 425000000);  // Fixed timing to prevent test failures
    order = new Order();
    order.setOrderId(1);
    order.setUserId(101);
    order.setRestaurantId(201);
    order.setAddressId(301);
    order.setOrderStatus(OrderStatus.PLACED);
    order.setTotalPrice(150.0);
    order.setCartItems("[]");
    order.setPlacedTiming(fixedPlacedTiming);
  }

  @Test
  void testOrderGettersAndSetters() {
    assertEquals(1, order.getOrderId());
    assertEquals(101, order.getUserId());
    assertEquals(201, order.getRestaurantId());
    assertEquals(301, order.getAddressId());
    assertEquals(OrderStatus.PLACED, order.getOrderStatus());
    assertEquals(150.0, order.getTotalPrice());
    assertNotNull(order.getCartItems());
    assertNotNull(order.getPlacedTiming());
  }

  @Test
  void testEqualsAndHashCode() {
    Order sameOrder = new Order(1, 101, 201, 301, OrderStatus.PLACED, 150.0, "[]", fixedPlacedTiming);

    assertEquals(order, sameOrder);
    assertEquals(order.hashCode(), sameOrder.hashCode());
  }

  @Test
  void testToString() {
    String expectedString = "Order{" +
      "OrderId=1, userId=101, restaurantId=201, addressId=301, orderStatus=PLACED, " +
      "totalPrice=150.0, cartItems='[]', placedTiming=" + fixedPlacedTiming + '}';  // Use the fixed time in expected string
    assertEquals(expectedString, order.toString());
  }
  @Test
  void testSetCartItemsFromList() throws JsonProcessingException {
    List<CartItemDTO> cartItems = new ArrayList<>();
    CartItemDTO cartItemDTO = new CartItemDTO();
    cartItemDTO.setFoodItemId(501);
    cartItemDTO.setQuantity(2);
    cartItemDTO.setPrice(25.0);
    cartItems.add(cartItemDTO);

    order.setCartItemsFromList(cartItems);

    assertNotNull(order.getCartItems());
    assertTrue(order.getCartItems().contains("501"));
    assertTrue(order.getCartItems().contains("25.0"));
  }

  @Test
  void testGetCartItemOutDTOAsList() throws JsonProcessingException {
    String cartItemsJson = "[{\"foodItemId\":501,\"quantity\":2,\"price\":25.0}]";
    order.setCartItems(cartItemsJson);

    List<CartItemDTO> cartItemDTOs = order.getCartItemOutDTOAsList();

    assertNotNull(cartItemDTOs);
    assertEquals(1, cartItemDTOs.size());
    assertEquals(501, cartItemDTOs.get(0).getFoodItemId());
    assertEquals(2, cartItemDTOs.get(0).getQuantity());
    assertEquals(25.0, cartItemDTOs.get(0).getPrice());
  }
}
