package com.nt.restaurant.microservice.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

/**
 * Entity class representing the food category in the restaurant system.
 * This class is mapped to a table in the database where each record represents a food category.
 */
@Entity
public class FoodCategory {

  /**
   * The unique identifier for the food category.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer foodCategoryId;

  /**
   * The ID of the restaurant this food category belongs to.
   */
  private Integer restaurantId;

  /**
   * The name of the food category.
   */
  private String foodCategoryName;

  /**
   * Default constructor.
   * Required by JPA for entity creation.
   */
  public FoodCategory() {
  }

  /**
   * Parameterized constructor for creating a FoodCategory entity.
   *
   * @param foodCategoryId   the unique ID of the food category
   * @param restaurantId     the ID of the associated restaurant
   * @param foodCategoryName the name of the food category
   */
  public FoodCategory(final Integer foodCategoryId, final Integer restaurantId, final String foodCategoryName) {
    this.foodCategoryId = foodCategoryId;
    this.restaurantId = restaurantId;
    this.foodCategoryName = foodCategoryName;
  }

  /**
   * Parameterized constructor for creating a FoodCategory entity without an ID.
   * Typically used when creating new categories where the ID is auto-generated.
   *
   * @param restaurantId     the ID of the associated restaurant
   * @param foodCategoryName the name of the food category
   */
  public FoodCategory(final Integer restaurantId, final String foodCategoryName) {
    this.restaurantId = restaurantId;
    this.foodCategoryName = foodCategoryName;
  }

  // Getters and Setters

  /**
   * Gets the food category ID.
   *
   * @return the unique ID of the food category.
   */
  public Integer getFoodCategoryId() {
    return foodCategoryId;
  }

  /**
   * Sets the food category ID.
   *
   * @param foodCategoryId the unique ID of the food category.
   */
  public void setFoodCategoryId(final Integer foodCategoryId) {
    this.foodCategoryId = foodCategoryId;
  }

  /**
   * Gets the restaurant ID associated with this food category.
   *
   * @return the restaurant ID.
   */
  public Integer getRestaurantId() {
    return restaurantId;
  }

  /**
   * Sets the restaurant ID associated with this food category.
   *
   * @param restaurantId the restaurant ID.
   */
  public void setRestaurantId(final Integer restaurantId) {
    this.restaurantId = restaurantId;
  }

  /**
   * Gets the name of the food category.
   *
   * @return the name of the food category.
   */
  public String getFoodCategoryName() {
    return foodCategoryName;
  }

  /**
   * Sets the name of the food category.
   *
   * @param foodCategoryName the name of the food category.
   */
  public void setFoodCategoryName(final String foodCategoryName) {
    this.foodCategoryName = foodCategoryName;
  }

  // Overrides for equality checks and representation

  /**
   * Compares this food category to another object for equality.
   *
   * @param o the object to compare.
   * @return true if the objects are equal, false otherwise.
   */
  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FoodCategory that = (FoodCategory) o;
    return Objects.equals(foodCategoryId, that.foodCategoryId)
      && Objects.equals(restaurantId, that.restaurantId)
      && Objects.equals(foodCategoryName, that.foodCategoryName);
  }

  /**
   * Returns a hash code for this food category.
   *
   * @return a hash code based on the fields of the food category.
   */
  @Override
  public int hashCode() {
    return Objects.hash(foodCategoryId, restaurantId, foodCategoryName);
  }

  /**
   * Returns a string representation of this food category.
   *
   * @return a string representing the food category.
   */
  @Override
  public String toString() {
    return "FoodCategory{"
      + "foodCategoryId=" + foodCategoryId
      + ", restaurantId=" + restaurantId
      + ", foodCategoryName='" + foodCategoryName
      + '\'' + '}';
  }
}
