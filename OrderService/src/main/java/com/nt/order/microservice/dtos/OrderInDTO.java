package com.nt.order.microservice.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

/**
 * Data Transfer Object for Order Input.
 * This class encapsulates the information required to place an order.
 */
public class OrderInDTO {

  @NotNull(message = "User ID cannot be null")
  private Integer userId;

  @NotNull(message = "Restaurant ID cannot be null")
  private Integer restaurantId;

  @NotNull(message = "Delivery Address ID cannot be null")
  private Integer addressId;

  @NotEmpty(message = "Cart items cannot be empty")
  private List<CartItemDTO> cartItems;

  /**
   * Default constructor.
   */
  public OrderInDTO() {
  }

  /**
   * Parameterized constructor to create an OrderInDTO with specified details.
   *
   * @param userId       the ID of the user placing the order
   * @param restaurantId the ID of the restaurant
   * @param addressId    the ID of the delivery address
   * @param cartItems    the list of cart items included in the order
   */
  public OrderInDTO(Integer userId, Integer restaurantId, Integer addressId, List<CartItemDTO> cartItems) {
    this.userId = userId;
    this.restaurantId = restaurantId;
    this.addressId = addressId;
    this.cartItems = cartItems;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof OrderInDTO)) {
      return false;
    }
    OrderInDTO that = (OrderInDTO) o;
    return Objects.equals(userId, that.userId) && Objects.equals(restaurantId, that.restaurantId) &&
      Objects.equals(addressId, that.addressId) && Objects.equals(cartItems, that.cartItems);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, restaurantId, addressId, cartItems);
  }

  @Override
  public String toString() {
    return "OrderInDTO{" +
      "userId=" + userId +
      ", restaurantId=" + restaurantId +
      ", addressId=" + addressId +
      ", cartItems=" + cartItems +
      '}';
  }
}