package com.nt.order.microservice.dtos;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Data Transfer Object for Cart Item.
 * This class represents the details of an item in the cart,
 * including food item ID, quantity, and price.
 */
public class CartItemDTO {

  /**
   * The ID of the food item.
   * It cannot be {@code null}.
   */
  @NotNull(message = "Food Item ID cannot be null")
  private Integer foodItemId;

  /**
   * The quantity of the food item.
   * It cannot be {@code null} and must be at least 1.
   */
  @NotNull(message = "Quantity cannot be null")
  @Min(value = 1, message = "Quantity must be at least 1")
  private Integer quantity;

  /**
   * The price of the food item.
   * It cannot be {@code null}, must be greater than 0, and should be valid
   * with up to 10 digits in the integer part and 2 digits in the fractional part.
   */
  @NotNull(message = "Price cannot be null")
  @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
  @Digits(integer = 10, fraction = 2, message = "Price format is invalid")
  private Double price;

  /**
   * Default constructor.
   * Creates an empty instance of CartItemDTO.
   */
  public CartItemDTO() {
  }

  /**
   * Parameterized constructor to create a CartItemDTO with specified values.
   *
   * @param foodItemId the ID of the food item
   * @param quantity   the quantity of the food item
   * @param price      the price of the food item
   */
  public CartItemDTO(final Integer foodItemId, final Integer quantity, final Double price) {
    this.foodItemId = foodItemId;
    this.quantity = quantity;
    this.price = price;
  }

  /**
   * Retrieves the food item ID.
   *
   * @return the ID of the food item
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
   * @return the quantity of the food item
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
   * @return the price of the food item
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
   * Compares this CartItemDTO to another object for equality.
   * Two CartItemDTOs are considered equal if their food item ID, quantity,
   * and price are equal.
   *
   * @param o the object to compare this CartItemDTO to
   * @return {@code true} if the objects are equal, otherwise {@code false}
   */
  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof CartItemDTO)) {
      return false;
    }
    CartItemDTO that = (CartItemDTO) o;
    return Objects.equals(foodItemId, that.foodItemId)
      && Objects.equals(quantity, that.quantity)
      && Objects.equals(price, that.price);
  }

  /**
   * Returns a hash code value for the CartItemDTO.
   *
   * @return a hash code value for this object
   */
  @Override
  public int hashCode() {
    return Objects.hash(foodItemId, quantity, price);
  }

  /**
   * Returns a string representation of the CartItemDTO.
   * This includes the food item ID, quantity, and price.
   *
   * @return a string representation of the CartItemDTO
   */
  @Override
  public String toString() {
    return "CartItemDTO{"
      + "foodItemId=" + foodItemId
      + ", quantity=" + quantity
      + ", price=" + price
      + '}';
  }
}
