package com.nt.restaurant.microservice.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * Data Transfer Object (DTO) for input validation of the Food Category entity.
 * This class is used to capture and validate the input data required to create
 * or update a food category for a restaurant.
 */
public class FoodCategoryInDTO {

  /**
   * Represents the ID of the restaurant that owns the food category.
   * This field cannot be null.
   */
  @NotNull(message = "Restaurant ID cannot be null")
  private Integer restaurantId;

  /**
   * Represents the name of the food category.
   * The name cannot be blank, should not exceed 100 characters, and must contain only alphabets.
   */
  @NotBlank(message = "Food category name cannot be blank")
  @Size(max = 100, message = "Food category name cannot exceed 100 characters")
  @Pattern(regexp = "^[A-Za-z]+(?:\\s[A-Za-z]+)*$", message = "Category name must contain only alphabets")
  private String foodCategoryName;

  /**
   * Default constructor for the {@code FoodCategoryInDTO} class.
   */
  public FoodCategoryInDTO() {
  }

  /**
   * Parameterized constructor to initialize {@code FoodCategoryInDTO} with restaurant ID and food category name.
   *
   * @param restaurantId     the ID of the restaurant
   * @param foodCategoryName the name of the food category
   */
  public FoodCategoryInDTO(Integer restaurantId, String foodCategoryName) {
    this.restaurantId = restaurantId;
    this.foodCategoryName = foodCategoryName;
  }

  /**
   * Gets the ID of the restaurant.
   *
   * @return the restaurant ID
   */
  public Integer getRestaurantId() {
    return restaurantId;
  }

  /**
   * Sets the ID of the restaurant.
   *
   * @param restaurantId the new restaurant ID
   */
  public void setRestaurantId(Integer restaurantId) {
    this.restaurantId = restaurantId;
  }

  /**
   * Gets the name of the food category.
   *
   * @return the food category name
   */
  public String getFoodCategoryName() {
    return foodCategoryName;
  }

  /**
   * Sets the name of the food category.
   *
   * @param foodCategoryName the new food category name
   */
  public void setFoodCategoryName(String foodCategoryName) {
    this.foodCategoryName = foodCategoryName;
  }

  /**
   * Compares this object with the specified object for equality.
   *
   * @param o the object to compare with
   * @return {@code true} if both objects are equal, otherwise {@code false}
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FoodCategoryInDTO that = (FoodCategoryInDTO) o;
    return Objects.equals(restaurantId, that.restaurantId) && Objects.equals(foodCategoryName, that.foodCategoryName);
  }

  /**
   * Returns the hash code value for this object.
   *
   * @return the hash code value
   */
  @Override
  public int hashCode() {
    return Objects.hash(restaurantId, foodCategoryName);
  }

  /**
   * Returns a string representation of the object.
   *
   * @return a string representation of the object
   */
  @Override
  public String toString() {
    return "FoodCategoryInDTO{" +
      "restaurantId=" + restaurantId +
      ", foodCategoryName='" + foodCategoryName + '\'' +
      '}';
  }
}
