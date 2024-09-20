package com.nt.order.microservice.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Cart {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer cartId;
  private Integer userId;
  private Integer restaurantId;
  private Integer foodItemId;
  private Integer quantity;
  private Double price;

  public Cart() {
  }

  public Cart(Integer cartId, Integer userId, Integer restaurantId, Integer foodItemId, Integer quantity, Double price) {
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
    Cart cart = (Cart) o;
    return Objects.equals(cartId, cart.cartId) && Objects.equals(userId, cart.userId) &&
      Objects.equals(restaurantId, cart.restaurantId) && Objects.equals(foodItemId, cart.foodItemId) &&
      Objects.equals(quantity, cart.quantity) && Objects.equals(price, cart.price);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cartId, userId, restaurantId, foodItemId, quantity, price);
  }

  @Override
  public String toString() {
    return "Cart{" +
      "cartId=" + cartId +
      ", userId=" + userId +
      ", restaurantId=" + restaurantId +
      ", foodItemId=" + foodItemId +
      ", quantity=" + quantity +
      ", price=" + price +
      '}';
  }
}
