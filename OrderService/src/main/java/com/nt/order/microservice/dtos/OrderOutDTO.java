package com.nt.order.microservice.dtos;

import com.nt.order.microservice.util.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class OrderOutDTO {
  private Integer OrderId;
  private Integer userId;
  private Integer restaurantId;
  private Integer addressId;
  private OrderStatus orderStatus;
  private Double totalPrice;
  private List<CartItemDTO> cartItems;
  private LocalDateTime placedTiming;

  public OrderOutDTO() {
  }

  public OrderOutDTO(Integer orderId, Integer userId, Integer restaurantId, Integer addressId, OrderStatus orderStatus,
                     Double totalPrice,
                     List<CartItemDTO> cartItems, LocalDateTime placedTiming) {
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

  public List<CartItemDTO> getCartItems() {
    return cartItems;
  }

  public void setCartItems(List<CartItemDTO> cartItems) {
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
    OrderOutDTO that = (OrderOutDTO) o;
    return Objects.equals(OrderId, that.OrderId) && Objects.equals(userId, that.userId) &&
      Objects.equals(restaurantId, that.restaurantId) && Objects.equals(addressId, that.addressId) &&
      orderStatus == that.orderStatus && Objects.equals(totalPrice, that.totalPrice) &&
      Objects.equals(cartItems, that.cartItems) && Objects.equals(placedTiming, that.placedTiming);
  }

  @Override
  public int hashCode() {
    return Objects.hash(OrderId, userId, restaurantId, addressId, orderStatus, totalPrice, cartItems, placedTiming);
  }

  @Override
  public String toString() {
    return "OrderOutDTO{" +
      "OrderId=" + OrderId +
      ", userId=" + userId +
      ", restaurantId=" + restaurantId +
      ", addressId=" + addressId +
      ", orderStatus=" + orderStatus +
      ", totalPrice=" + totalPrice +
      ", cartItems=" + cartItems +
      ", placedTiming=" + placedTiming +
      '}';
  }
}
