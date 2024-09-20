package com.nt.order.microservice.dtos;

import com.nt.order.microservice.entities.Cart;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

public class OrderInDTO {

  @NotNull(message = "User ID cannot be null")
  private Integer userId;

  @NotNull(message = "Restaurant ID cannot be null")
  private Integer restaurantId;

  @NotNull(message = "Delivery Address ID cannot be null")
  private Integer addressId;

  @NotEmpty(message = "Cart items cannot be empty")
  private List<CartItemDTO> cartItems;

  public OrderInDTO() {
  }

  public OrderInDTO(Integer userId, Integer restaurantId, Integer addressId, List<CartItemDTO> cartItems) {
    this.userId = userId;
    this.restaurantId = restaurantId;
    this.addressId = addressId;
    this.cartItems = cartItems;
  }

  public @NotNull(message = "User ID cannot be null") Integer getUserId() {
    return userId;
  }

  public void setUserId(@NotNull(message = "User ID cannot be null") Integer userId) {
    this.userId = userId;
  }

  public @NotNull(message = "Restaurant ID cannot be null") Integer getRestaurantId() {
    return restaurantId;
  }

  public void setRestaurantId(@NotNull(message = "Restaurant ID cannot be null") Integer restaurantId) {
    this.restaurantId = restaurantId;
  }

  public @NotNull(message = "Delivery Address ID cannot be null") Integer getAddressId() {
    return addressId;
  }

  public void setAddressId(@NotNull(message = "Delivery Address ID cannot be null") Integer addressId) {
    this.addressId = addressId;
  }

  public @NotEmpty(message = "Cart items cannot be empty") @Valid List<CartItemDTO> getCartItems() {
    return cartItems;
  }

  public void setCartItems(
    @NotEmpty(message = "Cart items cannot be empty") @Valid List<CartItemDTO> cartItems) {
    this.cartItems = cartItems;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof OrderInDTO)) return false;
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
