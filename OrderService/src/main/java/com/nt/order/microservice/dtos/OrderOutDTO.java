package com.nt.order.microservice.dtos;

import com.nt.order.microservice.util.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * Data Transfer Object for Order Output.
 * This class encapsulates the information about an order that is returned to the client.
 */
public class OrderOutDTO {

  private Integer orderId;
  private Integer userId;
  private Integer restaurantId;
  private Integer addressId;
  private OrderStatus orderStatus;
  private Double totalPrice;
  private List<CartItemDTO> cartItems;
  private LocalDateTime placedTiming;

  /**
   * Default constructor.
   */
  public OrderOutDTO() {
  }

  /**
   * Parameterized constructor to create an OrderOutDTO with specified details.
   *
   * @param orderId       the ID of the order
   * @param userId        the ID of the user who placed the order
   * @param restaurantId  the ID of the restaurant
   * @param addressId     the ID of the delivery address
   * @param orderStatus   the status of the order
   * @param totalPrice    the total price of the order
   * @param cartItems     the list of cart items included in the order
   * @param placedTiming  the time when the order was placed
   */
  public OrderOutDTO(Integer orderId, Integer userId, Integer restaurantId, Integer addressId, OrderStatus orderStatus,
                     Double totalPrice, List<CartItemDTO> cartItems, LocalDateTime placedTiming) {
    this.orderId = orderId;
    this.userId = userId;
    this.restaurantId = restaurantId;
    this.addressId = addressId;
    this.orderStatus = orderStatus;
    this.totalPrice = totalPrice;
    this.cartItems = cartItems;
    this.placedTiming = placedTiming;
  }

  /**
   * Retrieves the order ID.
   *
   * @return the order ID
   */
  public Integer getOrderId() {
    return orderId;
  }

  /**
   * Sets the order ID.
   *
   * @param orderId the order ID to set
   */
  public void setOrderId(Integer orderId) {
    this.orderId = orderId;
  }

  /**
   * Retrieves the user ID.
   *
   * @return the user ID
   */
  public Integer getUserId() {
    return userId;
  }

  /**
   * Sets the user ID.
   *
   * @param userId the user ID to set
   */
  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  /**
   * Retrieves the restaurant ID.
   *
   * @return the restaurant ID
   */
  public Integer getRestaurantId() {
    return restaurantId;
  }

  /**
   * Sets the restaurant ID.
   *
   * @param restaurantId the restaurant ID to set
   */
  public void setRestaurantId(Integer restaurantId) {
    this.restaurantId = restaurantId;
  }

  /**
   * Retrieves the address ID.
   *
   * @return the address ID
   */
  public Integer getAddressId() {
    return addressId;
  }

  /**
   * Sets the address ID.
   *
   * @param addressId the address ID to set
   */
  public void setAddressId(Integer addressId) {
    this.addressId = addressId;
  }

  /**
   * Retrieves the order status.
   *
   * @return the order status
   */
  public OrderStatus getOrderStatus() {
    return orderStatus;
  }

  /**
   * Sets the order status.
   *
   * @param orderStatus the order status to set
   */
  public void setOrderStatus(OrderStatus orderStatus) {
    this.orderStatus = orderStatus;
  }

  /**
   * Retrieves the total price of the order.
   *
   * @return the total price
   */
  public Double getTotalPrice() {
    return totalPrice;
  }

  /**
   * Sets the total price of the order.
   *
   * @param totalPrice the total price to set
   */
  public void setTotalPrice(Double totalPrice) {
    this.totalPrice = totalPrice;
  }

  /**
   * Retrieves the list of cart items.
   *
   * @return the list of cart items
   */
  public List<CartItemDTO> getCartItems() {
    return cartItems;
  }

  /**
   * Sets the list of cart items.
   *
   * @param cartItems the list of cart items to set
   */
  public void setCartItems(List<CartItemDTO> cartItems) {
    this.cartItems = cartItems;
  }

  /**
   * Retrieves the time when the order was placed.
   *
   * @return the placed timing
   */
  public LocalDateTime getPlacedTiming() {
    return placedTiming;
  }

  /**
   * Sets the time when the order was placed.
   *
   * @param placedTiming the placed timing to set
   */
  public void setPlacedTiming(LocalDateTime placedTiming) {
    this.placedTiming = placedTiming;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OrderOutDTO that = (OrderOutDTO) o;
    return Objects.equals(orderId, that.orderId) && Objects.equals(userId, that.userId) &&
      Objects.equals(restaurantId, that.restaurantId) && Objects.equals(addressId, that.addressId) &&
      orderStatus == that.orderStatus && Objects.equals(totalPrice, that.totalPrice) &&
      Objects.equals(cartItems, that.cartItems) && Objects.equals(placedTiming, that.placedTiming);
  }

  @Override
  public int hashCode() {
    return Objects.hash(orderId, userId, restaurantId, addressId, orderStatus, totalPrice, cartItems, placedTiming);
  }

  @Override
  public String toString() {
    return "OrderOutDTO{" +
      "orderId=" + orderId +
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
