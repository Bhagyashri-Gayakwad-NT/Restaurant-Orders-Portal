package com.nt.restaurant.microservice.dto;


import java.util.Objects;

/**
 * Data Transfer Object (DTO) for representing a food category in the output response.
 * This class is used to send food category details as a response in the application.
 */
public class FoodCategoryOutDTO {

  /**
   * The unique identifier for the food category.
   */
  private Integer foodCategoryId;

  /**
   * The identifier of the restaurant to which the food category belongs.
   */
  private Integer restaurantId;

  /**
   * The name of the food category.
   */
  private String foodCategoryName;

  /**
   * Default constructor for creating an empty {@code FoodCategoryOutDTO} instance.
   */
  public FoodCategoryOutDTO() {
  }

  /**
   * Constructor for creating a {@code FoodCategoryOutDTO} instance with specified values.
   *
   * @param foodCategoryId   the unique identifier for the food category.
   * @param restaurantId     the identifier of the restaurant to which the food category belongs.
   * @param foodCategoryName the name of the food category.
   */
  public FoodCategoryOutDTO(final Integer foodCategoryId, final Integer restaurantId, final String foodCategoryName) {
    this.foodCategoryId = foodCategoryId;
    this.restaurantId = restaurantId;
    this.foodCategoryName = foodCategoryName;
  }

  /**
   * Gets the unique identifier for the food category.
   *
   * @return the food category ID.
   */
  public Integer getFoodCategoryId() {
    return foodCategoryId;
  }

  /**
   * Sets the unique identifier for the food category.
   *
   * @param foodCategoryId the food category ID to set.
   */
  public void setFoodCategoryId(final Integer foodCategoryId) {
    this.foodCategoryId = foodCategoryId;
  }

  /**
   * Gets the identifier of the restaurant to which the food category belongs.
   *
   * @return the restaurant ID.
   */
  public Integer getRestaurantId() {
    return restaurantId;
  }

  /**
   * Sets the identifier of the restaurant to which the food category belongs.
   *
   * @param restaurantId the restaurant ID to set.
   */
  public void setRestaurantId(final Integer restaurantId) {
    this.restaurantId = restaurantId;
  }

  /**
   * Gets the name of the food category.
   *
   * @return the food category name.
   */
  public String getFoodCategoryName() {
    return foodCategoryName;
  }

  /**
   * Sets the name of the food category.
   *
   * @param foodCategoryName the food category name to set.
   */
  public void setFoodCategoryName(final String foodCategoryName) {
    this.foodCategoryName = foodCategoryName;
  }

  /**
   * Compares this {@code FoodCategoryOutDTO} to another object for equality.
   *
   * @param o the object to compare with.
   * @return {@code true} if this object is equal to the other object, {@code false} otherwise.
   */
  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FoodCategoryOutDTO that = (FoodCategoryOutDTO) o;
    return Objects.equals(foodCategoryId, that.foodCategoryId)
      && Objects.equals(restaurantId, that.restaurantId)
      && Objects.equals(foodCategoryName, that.foodCategoryName);
  }

  /**
   * Returns a hash code value for this {@code FoodCategoryOutDTO}.
   *
   * @return the hash code value.
   */
  @Override
  public int hashCode() {
    return Objects.hash(foodCategoryId, restaurantId, foodCategoryName);
  }

  /**
   * Returns a string representation of this {@code FoodCategoryOutDTO}.
   *
   * @return a string representation of the object.
   */
  @Override
  public String toString() {
    return "FoodCategoryOutDTO{"
      + "foodCategoryId=" + foodCategoryId
      + ", restaurantId=" + restaurantId
      + ", foodCategoryName='" + foodCategoryName + '\''
      + '}';
  }
}
