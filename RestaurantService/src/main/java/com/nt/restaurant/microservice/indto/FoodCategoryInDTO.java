package com.nt.restaurant.microservice.indto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

public class FoodCategoryInDTO {
  @NotNull(message = "Restaurant ID cannot be null")
  private Integer restaurantId;

  @NotBlank(message = "Food category name cannot be blank")
  @Size(max = 100, message = "Food category name cannot exceed 100 characters")
  @Pattern(regexp = "^[A-Za-z\\s]+$", message = "Category name must contain only alphabets")
  private String foodCategoryName;

  public FoodCategoryInDTO() {
  }

  public FoodCategoryInDTO(Integer restaurantId, String foodCategoryName) {
    this.restaurantId = restaurantId;
    this.foodCategoryName = foodCategoryName;
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
    FoodCategoryInDTO that = (FoodCategoryInDTO) o;
    return Objects.equals(restaurantId, that.restaurantId) && Objects.equals(foodCategoryName, that.foodCategoryName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(restaurantId, foodCategoryName);
  }

  @Override
  public String toString() {
    return "FoodCategoryInDTO{" +
      "restaurantId=" + restaurantId +
      ", foodCategoryName='" + foodCategoryName + '\'' +
      '}';
  }
}
