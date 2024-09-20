package com.nt.order.microservice.entities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nt.order.microservice.dtos.CartItemDTO;
import com.nt.order.microservice.util.OrderStatus;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer OrderId;
  private Integer userId;
  private Integer restaurantId;
  private Integer addressId;
  @Enumerated(EnumType.STRING)
  private OrderStatus orderStatus;
  private Double totalPrice;
  private String cartItems;
  private LocalDateTime placedTiming;

  private static final ObjectMapper objectMapper = new ObjectMapper();

  public List<CartItemDTO> getCartItemOutDTOAsList() throws JsonProcessingException {
    List<Cart> list = objectMapper.readValue(cartItems, objectMapper.getTypeFactory().constructCollectionType(List.class, Cart.class));

    List<CartItemDTO> cartItemOutDTOS = new ArrayList<>();
    for (Cart item : list) {
      CartItemDTO cartItemDTO = new CartItemDTO();
      cartItemDTO.setFoodItemId(item.getFoodItemId());
      cartItemDTO.setPrice(item.getPrice());
      cartItemDTO.setQuantity(item.getQuantity());
      cartItemOutDTOS.add(cartItemDTO);
    }
    return cartItemOutDTOS;
  }
  public void setCartItemsFromList(List<CartItemDTO> cartItems) throws JsonProcessingException {
    this.cartItems = objectMapper.writeValueAsString(cartItems);
  }

  public Order() {
  }

  public Order(Integer orderId, Integer userId, Integer restaurantId, Integer addressId, OrderStatus orderStatus, Double totalPrice,
               String cartItems, LocalDateTime placedTiming) {
    OrderId = orderId;
    this.userId = userId;
    this.restaurantId = restaurantId;
    this.addressId = addressId;
    this.orderStatus = orderStatus;
    this.totalPrice = totalPrice;
    this.cartItems = cartItems;
    this.placedTiming = placedTiming;
  }

  public Integer getOrderId() {
    return OrderId;
  }

  public void setOrderId(Integer orderId) {
    OrderId = orderId;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public Integer getRestaurantId() {
    return restaurantId;
  }

  public void setRestaurantId(Integer restaurantId) {
    this.restaurantId = restaurantId;
  }

  public Integer getAddressId() {
    return addressId;
  }

  public void setAddressId(Integer addressId) {
    this.addressId = addressId;
  }

  public OrderStatus getOrderStatus() {
    return orderStatus;
  }

  public void setOrderStatus(OrderStatus orderStatus) {
    this.orderStatus = orderStatus;
  }

  public Double getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(Double totalPrice) {
    this.totalPrice = totalPrice;
  }

  public String getCartItems() {
    return cartItems;
  }

  public void setCartItems(String cartItems) {
    this.cartItems = cartItems;
  }

  public LocalDateTime getPlacedTiming() {
    return placedTiming;
  }

  public void setPlacedTiming(LocalDateTime placedTiming) {
    this.placedTiming = placedTiming;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Order order = (Order) o;
    return Objects.equals(OrderId, order.OrderId) && Objects.equals(userId, order.userId) &&
      Objects.equals(restaurantId, order.restaurantId) && Objects.equals(addressId, order.addressId) &&
      orderStatus == order.orderStatus && Objects.equals(totalPrice, order.totalPrice) &&
      Objects.equals(cartItems, order.cartItems) && Objects.equals(placedTiming, order.placedTiming);
  }

  @Override
  public int hashCode() {
    return Objects.hash(OrderId, userId, restaurantId, addressId, orderStatus, totalPrice, cartItems, placedTiming);
  }

  @Override
  public String toString() {
    return "Order{" +
      "OrderId=" + OrderId +
      ", userId=" + userId +
      ", restaurantId=" + restaurantId +
      ", addressId=" + addressId +
      ", orderStatus=" + orderStatus +
      ", totalPrice=" + totalPrice +
      ", cartItems='" + cartItems + '\'' +
      ", placedTiming=" + placedTiming +
      '}';
  }
}
