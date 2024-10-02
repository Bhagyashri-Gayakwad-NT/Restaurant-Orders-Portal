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

  /**
   * The ID of the user who is adding an item to the cart.
   * It is a mandatory field, and cannot be {@code null}.
   */
  @NotNull(message = "User ID cannot be null")
  private Integer userId;

  /**
   * The ID of the restaurant from which the item is being added.
   * It is a mandatory field, and cannot be {@code null}.
   */
  @NotNull(message = "Restaurant ID cannot be null")
  private Integer restaurantId;

  /**
   * The ID of the food item being added to the cart.
   * It is a mandatory field, and cannot be {@code null}.
   */
  @NotNull(message = "Food Item ID cannot be null")
  private Integer foodItemId;

  /**
   * The quantity of the food item being added.
   * Must be at least 1 and cannot be {@code null}.
   */
  @NotNull(message = "Quantity cannot be null")
  @Min(value = 1, message = "Quantity must be at least 1")
  private Integer quantity;

  /**
   * The price of the food item.
   * Must be greater than 0 and formatted to a maximum of 10 integer digits and 2 fraction digits.
   * It cannot be {@code null}.
   */
  @NotNull(message = "Price cannot be null")
  @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
  @Digits(integer = 10, fraction = 2, message = "Price format is invalid")
  private Double price;

  /**
   * Default constructor for CartInDTO.
   * Creates an empty CartInDTO object.
   */
  public CartInDTO() {
  }

  /**
   * Parameterized constructor to create a CartInDTO with specified values.
   *
   * @param userId       the ID of the user
   * @param restaurantId the ID of the restaurant
   * @param foodItemId   the ID of the food item
   * @param quantity     the quantity of the food item
   * @param price        the price of the food item
   */
  public CartInDTO(final Integer userId, final Integer restaurantId,
                   final Integer foodItemId, final Integer quantity, final Double price) {
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
  public void setUserId(final Integer userId) {
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
  public void setRestaurantId(final Integer restaurantId) {
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
  public void setFoodItemId(final Integer foodItemId) {
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
  public void setQuantity(final Integer quantity) {
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
  public void setPrice(final Double price) {
    this.price = price;
  }

  /**
   * Compares this CartInDTO to another object for equality.
   * Two CartInDTOs are considered equal if they have the same
   * userId, restaurantId, foodItemId, quantity, and price.
   *
   * @param o the object to compare to
   * @return {@code true} if the objects are equal, {@code false} otherwise
   */
  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CartInDTO cartInDTO = (CartInDTO) o;
    return Objects.equals(userId, cartInDTO.userId)
      && Objects.equals(restaurantId, cartInDTO.restaurantId)
      && Objects.equals(foodItemId, cartInDTO.foodItemId)
      && Objects.equals(quantity, cartInDTO.quantity)
      && Objects.equals(price, cartInDTO.price);
  }

  /**
   * Computes a hash code for this CartInDTO based on its fields.
   *
   * @return the hash code value for this object
   */
  @Override
  public int hashCode() {
    return Objects.hash(userId, restaurantId, foodItemId, quantity, price);
  }

  /**
   * Returns a string representation of the CartInDTO.
   * This includes the userId, restaurantId, foodItemId, quantity, and price.
   *
   * @return a string representation of the object
   */
  @Override
  public String toString() {
    return "CartInDTO{"
      + "userId=" + userId
      + ", restaurantId=" + restaurantId
      + ", foodItemId=" + foodItemId
      + ", quantity=" + quantity
      + ", price=" + price
      + '}';
  }
}
