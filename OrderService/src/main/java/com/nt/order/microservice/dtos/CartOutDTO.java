package com.nt.order.microservice.dtos;

import java.util.Objects;

public class CartOutDTO {

  private Integer cartId;
  private Integer userId;
  private Integer restaurantId;
  private Integer foodItemId;
  private Integer quantity;
  private Double price;

  public CartOutDTO() {
  }

  public CartOutDTO(Integer cartId, Integer userId, Integer restaurantId, Integer foodItemId, Integer quantity, Double price) {
    this.cartId = cartId;
    this.userId = userId;
    this.restaurantId = restaurantId;
    this.foodItemId = foodItemId;
    this.quantity = quantity;
    this.price = price;
  }

  public Integer getCartId() {
    return cartId;
  }

  public void setCartId(Integer cartId) {
    this.cartId = cartId;
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

  public Integer getFoodItemId() {
    return foodItemId;
  }

  public void setFoodItemId(Integer foodItemId) {
    this.foodItemId = foodItemId;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    CartOutDTO that = (CartOutDTO) o;
    return Objects.equals(cartId, that.cartId) && Objects.equals(userId, that.userId) &&
      Objects.equals(restaurantId, that.restaurantId) && Objects.equals(foodItemId, that.foodItemId) &&
      Objects.equals(quantity, that.quantity) && Objects.equals(price, that.price);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cartId, userId, restaurantId, foodItemId, quantity, price);
  }

  @Override
  public String toString() {
    return "CartOutDTO{" +
      "cartId=" + cartId +
      ", userId=" + userId +
      ", restaurantId=" + restaurantId +
      ", foodItemId=" + foodItemId +
      ", quantity=" + quantity +
      ", price=" + price +
      '}';
  }
}
