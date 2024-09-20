package com.nt.order.microservice.dtos;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class CartInDTO {

  @NotNull(message = "User ID cannot be null")
  private Integer userId;

  @NotNull(message = "Restaurant ID cannot be null")
  private Integer restaurantId;

  @NotNull(message = "Food Item ID cannot be null")
  private Integer foodItemId;

  @NotNull(message = "Quantity cannot be null")
  @Min(value = 1, message = "Quantity must be at least 1")
  private Integer quantity;

  @NotNull(message = "Price cannot be null")
  @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
  @Digits(integer = 10, fraction = 2, message = "Price format is invalid")
  private Double price;

  public CartInDTO() {
  }

  public CartInDTO(Integer userId, Integer restaurantId, Integer foodItemId, Integer quantity, Double price) {
    this.userId = userId;
    this.restaurantId = restaurantId;
    this.foodItemId = foodItemId;
    this.quantity = quantity;
    this.price = price;
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
    CartInDTO cartInDTO = (CartInDTO) o;
    return Objects.equals(userId, cartInDTO.userId) && Objects.equals(restaurantId, cartInDTO.restaurantId) &&
      Objects.equals(foodItemId, cartInDTO.foodItemId) && Objects.equals(quantity, cartInDTO.quantity) &&
      Objects.equals(price, cartInDTO.price);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, restaurantId, foodItemId, quantity, price);
  }

  @Override
  public String toString() {
    return "CartInDTO{" +
      "userId=" + userId +
      ", restaurantId=" + restaurantId +
      ", foodItemId=" + foodItemId +
      ", quantity=" + quantity +
      ", price=" + price +
      '}';
  }
}
