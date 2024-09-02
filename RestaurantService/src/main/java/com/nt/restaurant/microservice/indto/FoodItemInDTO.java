package com.nt.restaurant.microservice.indto;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

public class FoodItemInDTO {
  @NotNull(message = "Food category ID cannot be null")
  private Integer foodCategoryId;

  @NotNull(message = "Restaurant ID cannot be null")
  private Integer restaurantId;

  @NotBlank(message = "Food item name cannot be blank")
  @Size(max = 100, message = "Food item name cannot exceed 100 characters")
  private String foodItemName;

  @NotBlank(message = "Description cannot be blank")
  @Size(max = 255, message = "Description cannot exceed 255 characters")
  private String description;

  @NotNull(message = "Price cannot be null")
  @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
  @Digits(integer = 10, fraction = 2, message = "Price format is invalid")
  private Double price;

  private boolean isAvailable;

  @NotNull(message = "Food item image cannot be null")
  private MultipartFile foodItemImage;

  public FoodItemInDTO() {
  }

  public FoodItemInDTO(Integer foodCategoryId, Integer restaurantId, String foodItemName, String description, Double price,
                       boolean isAvailable, MultipartFile foodItemImage) {
    this.foodCategoryId = foodCategoryId;
    this.restaurantId = restaurantId;
    this.foodItemName = foodItemName;
    this.description = description;
    this.price = price;
    this.isAvailable = isAvailable;
    this.foodItemImage = foodItemImage;
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

  public String getFoodItemName() {
    return foodItemName;
  }

  public void setFoodItemName(String foodItemName) {
    this.foodItemName = foodItemName;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public boolean isAvailable() {
    return isAvailable;
  }

  public void setAvailable(boolean available) {
    isAvailable = available;
  }

  public MultipartFile getFoodItemImage() {
    return foodItemImage;
  }

  public void setFoodItemImage(MultipartFile foodItemImage) {
    this.foodItemImage = foodItemImage;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) { return true; }
    if (o == null || getClass() != o.getClass()) { return false; }
    FoodItemInDTO that = (FoodItemInDTO) o;
    return isAvailable == that.isAvailable && Objects.equals(foodCategoryId, that.foodCategoryId) &&
      Objects.equals(restaurantId, that.restaurantId) && Objects.equals(foodItemName, that.foodItemName) &&
      Objects.equals(description, that.description) && Objects.equals(price, that.price) &&
      Objects.equals(foodItemImage, that.foodItemImage);
  }

  @Override
  public int hashCode() {
    return Objects.hash(foodCategoryId, restaurantId, foodItemName, description, price, isAvailable, foodItemImage);
  }

  @Override
  public String toString() {
    return "FoodItemInDTO{" +
      "foodCategoryId=" + foodCategoryId +
      ", restaurantId=" + restaurantId +
      ", foodItemName='" + foodItemName + '\'' +
      ", description='" + description + '\'' +
      ", price=" + price +
      ", isAvailable=" + isAvailable +
      ", foodItemImage=" + foodItemImage +
      '}';
  }
}
