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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/**
 * Represents an Order entity in the system.
 */
@Entity
@Table(name = "orders")
public class Order {

  /** Unique identifier for the order. */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer orderId;

  /** Identifier for the user who placed the order. */
  private Integer userId;

  /** Identifier for the restaurant from which the order is placed. */
  private Integer restaurantId;

  /** Identifier for the address associated with the order. */
  private Integer addressId;

  /** Status of the order, represented by an enum. */
  @Enumerated(EnumType.STRING)
  private OrderStatus orderStatus;

  /** Total price of the order. */
  private Double totalPrice;

  /** Cart items in JSON format as a String. */
  private String cartItems;

  /** Time when the order was placed. */
  private LocalDateTime placedTiming;

  /** ObjectMapper instance for JSON processing. */
  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  /**
   * Converts the cartItems JSON string into a list of CartItemDTO objects.
   *
   * @return List of CartItemDTO representing the cart items.
   * @throws JsonProcessingException if there is an error during JSON processing.
   */
  public List<CartItemDTO> getCartItemOutDTOAsList() throws JsonProcessingException {
    List<Cart> list = OBJECT_MAPPER.readValue(cartItems, OBJECT_MAPPER.getTypeFactory()
      .constructCollectionType(List.class, Cart.class));

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

  /**
   * Sets the cartItems field from a list of CartItemDTO.
   *
   * @param cartItems List of CartItemDTO to be converted to JSON string.
   * @throws JsonProcessingException if there is an error during JSON processing.
   */
  public void setCartItemsFromList(
    final List<CartItemDTO> cartItems) throws JsonProcessingException {
    this.cartItems = OBJECT_MAPPER.writeValueAsString(cartItems);
  }

  /** Default constructor. */
  public Order() {
  }

  /**
   * Constructs an Order with specified parameters.
   *
   * @param orderId        Unique identifier for the order.
   * @param userId        Identifier for the user who placed the order.
   * @param restaurantId   Identifier for the restaurant from which the order is placed.
   * @param addressId      Identifier for the address associated with the order.
   * @param orderStatus    Status of the order.
   * @param totalPrice     Total price of the order.
   * @param cartItems      Cart items in JSON format.
   * @param placedTiming   Time when the order was placed.
   */
  public Order(final Integer orderId, final Integer userId, final Integer restaurantId,
               final Integer addressId, final OrderStatus orderStatus, final Double totalPrice,
               final String cartItems, final LocalDateTime placedTiming) {
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
   * Gets the unique identifier for the order.
   *
   * @return the orderId.
   */
  public Integer getOrderId() {
    return orderId;
  }

  /**
   * Sets the unique identifier for the order.
   *
   * @param orderId the orderId to set.
   */
  public void setOrderId(final Integer orderId) {
    this.orderId = orderId; // fixed this line to set orderId correctly
  }

  /**
   * Gets the identifier for the user who placed the order.
   *
   * @return the userId.
   */
  public Integer getUserId() {
    return userId;
  }

  /**
   * Sets the identifier for the user who placed the order.
   *
   * @param userId the userId to set.
   */
  public void setUserId(final Integer userId) {
    this.userId = userId;
  }

  /**
   * Gets the identifier for the restaurant from which the order is placed.
   *
   * @return the restaurantId.
   */
  public Integer getRestaurantId() {
    return restaurantId;
  }

  /**
   * Sets the identifier for the restaurant from which the order is placed.
   *
   * @param restaurantId the restaurantId to set.
   */
  public void setRestaurantId(final Integer restaurantId) {
    this.restaurantId = restaurantId;
  }

  /**
   * Gets the identifier for the address associated with the order.
   *
   * @return the addressId.
   */
  public Integer getAddressId() {
    return addressId;
  }

  /**
   * Sets the identifier for the address associated with the order.
   *
   * @param addressId the addressId to set.
   */
  public void setAddressId(final Integer addressId) {
    this.addressId = addressId;
  }

  /**
   * Gets the status of the order.
   *
   * @return the orderStatus.
   */
  public OrderStatus getOrderStatus() {
    return orderStatus;
  }

  /**
   * Sets the status of the order.
   *
   * @param orderStatus the orderStatus to set.
   */
  public void setOrderStatus(final OrderStatus orderStatus) {
    this.orderStatus = orderStatus;
  }

  /**
   * Gets the total price of the order.
   *
   * @return the totalPrice.
   */
  public Double getTotalPrice() {
    return totalPrice;
  }

  /**
   * Sets the total price of the order.
   *
   * @param totalPrice the totalPrice to set.
   */
  public void setTotalPrice(final Double totalPrice) {
    this.totalPrice = totalPrice;
  }

  /**
   * Gets the cart items in JSON format as a String.
   *
   * @return the cartItems.
   */
  public String getCartItems() {
    return cartItems;
  }

  /**
   * Sets the cart items in JSON format.
   *
   * @param cartItems the cartItems to set.
   */
  public void setCartItems(final String cartItems) {
    this.cartItems = cartItems;
  }

  /**
   * Gets the time when the order was placed.
   *
   * @return the placedTiming.
   */
  public LocalDateTime getPlacedTiming() {
    return placedTiming;
  }

  /**
   * Sets the time when the order was placed.
   *
   * @param placedTiming the placedTiming to set.
   */
  public void setPlacedTiming(final LocalDateTime placedTiming) {
    this.placedTiming = placedTiming;
  }

  /**
   * Checks equality of two Order objects.
   *
   * @param o the object to compare with.
   * @return true if the objects are equal, false otherwise.
   */
  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Order order = (Order) o;
    return Objects.equals(orderId, order.orderId) && Objects.equals(userId, order.userId)
      && Objects.equals(restaurantId, order.restaurantId) && Objects.equals(addressId, order.addressId)
      && orderStatus == order.orderStatus && Objects.equals(totalPrice, order.totalPrice)
      && Objects.equals(cartItems, order.cartItems) && Objects.equals(placedTiming, order.placedTiming);
  }

  /**
   * Generates a hash code for the Order object.
   *
   * @return hash code representing the Order.
   */
  @Override
  public int hashCode() {
    return Objects.hash(orderId, userId, restaurantId, addressId, orderStatus, totalPrice, cartItems, placedTiming);
  }

  /**
   * Provides a string representation of the Order object.
   *
   * @return string representation of the Order.
   */
  @Override
  public String toString() {
    return "Order{"
      + "OrderId=" + orderId
      + ", userId=" + userId
      + ", restaurantId=" + restaurantId
      + ", addressId=" + addressId
      + ", orderStatus=" + orderStatus
      + ", totalPrice=" + totalPrice
      + ", cartItems='" + cartItems
      + '\'' + ", placedTiming=" + placedTiming
      + '}';
  }
}

