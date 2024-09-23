package com.nt.order.microservice.dtos;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Objects;


/**
 * Data Transfer Object for Cart Input.
 * This class is used to represent the details required
 * to add an item to the cart, including user ID, restaurant ID,
 * food item ID, quantity, and price.
 */
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

  /**
   * Default constructor.
   */
  public CartInDTO() {
  }

  /**
   * Parameterized constructor to create a CartInDTO with specified values.
   *
   * @param userId        the ID of the user
   * @param restaurantId  the ID of the restaurant
   * @param foodItemId    the ID of the food item
   * @param quantity       the quantity of the food item
   * @param price          the price of the food item
   */
  public CartInDTO(Integer userId, Integer restaurantId, Integer foodItemId, Integer quantity, Double price) {
    this.userId = userId;
    this.restaurantId = restaurantId;
    this.foodItemId = foodItemId;
    this.quantity = quantity;
    this.price = price;
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
   * Retrieves the food item ID.
   *
   * @return the food item ID
   */
  public Integer getFoodItemId() {
    return foodItemId;
  }

  /**
   * Sets the food item ID.
   *
   * @param foodItemId the food item ID to set
   */
  public void setFoodItemId(Integer foodItemId) {
    this.foodItemId = foodItemId;
  }

  /**
   * Retrieves the quantity of the food item.
   *
   * @return the quantity
   */
  public Integer getQuantity() {
    return quantity;
  }

  /**
   * Sets the quantity of the food item.
   *
   * @param quantity the quantity to set
   */
  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  /**
   * Retrieves the price of the food item.
   *
   * @return the price
   */
  public Double getPrice() {
    return price;
  }

  /**
   * Sets the price of the food item.
   *
   * @param price the price to set
   */
  public void setPrice(Double price) {
    this.price = price;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CartInDTO cartInDTO = (CartInDTO) o;
    return Objects.equals(userId, cartInDTO.userId) &&
      Objects.equals(restaurantId, cartInDTO.restaurantId) &&
      Objects.equals(foodItemId, cartInDTO.foodItemId) &&
      Objects.equals(quantity, cartInDTO.quantity) &&
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