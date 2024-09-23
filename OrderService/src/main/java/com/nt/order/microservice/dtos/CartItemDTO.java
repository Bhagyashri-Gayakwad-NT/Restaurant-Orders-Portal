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
  public CartItemDTO() {
  }

  /**
   * Parameterized constructor to create a CartItemDTO with specified values.
   *
   * @param foodItemId the ID of the food item
   * @param quantity    the quantity of the food item
   * @param price       the price of the food item
   */
  public CartItemDTO(Integer foodItemId, Integer quantity, Double price) {
    this.foodItemId = foodItemId;
    this.quantity = quantity;
    this.price = price;
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
    if (!(o instanceof CartItemDTO)) {
      return false;
    }
    CartItemDTO that = (CartItemDTO) o;
    return Objects.equals(foodItemId, that.foodItemId) &&
      Objects.equals(quantity, that.quantity) &&
      Objects.equals(price, that.price);
  }

  @Override
  public int hashCode() {
    return Objects.hash(foodItemId, quantity, price);
  }

  @Override
  public String toString() {
    return "CartItemDTO{" +
      "foodItemId=" + foodItemId +
      ", quantity=" + quantity +
      ", price=" + price +
      '}';
  }
}