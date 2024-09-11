package com.nt.restaurant.microservice.outdto;

import java.util.Objects;

public class FoodCategoryOutDTO {
  private Integer foodCategoryId;
  private Integer restaurantId;
  private String foodCategoryName;

  public FoodCategoryOutDTO() {
  }

  public FoodCategoryOutDTO(Integer foodCategoryId, Integer restaurantId, String foodCategoryName) {
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
    FoodCategoryOutDTO that = (FoodCategoryOutDTO) o;
    return Objects.equals(foodCategoryId, that.foodCategoryId) && Objects.equals(restaurantId, that.restaurantId) &&
      Objects.equals(foodCategoryName, that.foodCategoryName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(foodCategoryId, restaurantId, foodCategoryName);
  }

  @Override
  public String toString() {
    return "FoodCategoryOutDTO{" +
      "foodCategoryId=" + foodCategoryId +
      ", restaurantId=" + restaurantId +
      ", foodCategoryName='" + foodCategoryName + '\'' +
      '}';
  }
}
