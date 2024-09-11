package com.nt.restaurant.microservice.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class FoodCategory {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer foodCategoryId;
  private Integer restaurantId;
  private String foodCategoryName;

  public FoodCategory() {
  }

  public FoodCategory(Integer foodCategoryId, Integer restaurantId, String foodCategoryName) {
    this.foodCategoryId = foodCategoryId;
    this.restaurantId = restaurantId;
    this.foodCategoryName = foodCategoryName;
  }

  public Integer getFoodCategoryId() {
    return foodCategoryId;
  }

  public void setFoodCategoryId(Integer foodCategoryId) {
    this.foodCategoryId = foodCategoryId;
  }

  public Integer getRestaurantId() {
    return restaurantId;
  }

  public void setRestaurantId(Integer restaurantId) {
    this.restaurantId = restaurantId;
  }

  public String getFoodCategoryName() {
    return foodCategoryName;
  }

  public void setFoodCategoryName(String foodCategoryName) {
    this.foodCategoryName = foodCategoryName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FoodCategory that = (FoodCategory) o;
    return Objects.equals(foodCategoryId, that.foodCategoryId) && Objects.equals(restaurantId, that.restaurantId) &&
      Objects.equals(foodCategoryName, that.foodCategoryName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(foodCategoryId, restaurantId, foodCategoryName);
  }

  @Override
  public String toString() {
    return "FoodCategory{" +
      "foodCategoryId=" + foodCategoryId +
      ", restaurantId=" + restaurantId +
      ", foodCategoryName='" + foodCategoryName + '\'' +
      '}';
  }
}
