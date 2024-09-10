package com.nt.restaurant.microservice.outdto;

import javax.persistence.Lob;
import java.util.Arrays;
import java.util.Objects;

public class FoodItemOutDTO {
  private Integer foodItemId;
  private Integer categoryId;
  private Integer restaurantId;
  private String FoodItemName;
  private String description;
  private Double Price;
  private boolean isAvailable;
  @Lob
  private byte[] foodItemImage;

  public FoodItemOutDTO() {
  }

  public FoodItemOutDTO(Integer foodItemId, Integer categoryId, Integer restaurantId, String foodItemName, String description,
                        Double price, boolean isAvailable, byte[] foodItemImage) {
    this.foodItemId = foodItemId;
    this.categoryId = categoryId;
    this.restaurantId = restaurantId;
    FoodItemName = foodItemName;
    this.description = description;
    Price = price;
    this.isAvailable = isAvailable;
    this.foodItemImage = foodItemImage;
  }

  public Integer getFoodItemId() {
    return foodItemId;
  }

  public void setFoodItemId(Integer foodItemId) {
    this.foodItemId = foodItemId;
  }

  public Integer getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(Integer categoryId) {
    this.categoryId = categoryId;
  }

  public Integer getRestaurantId() {
    return restaurantId;
  }

  public void setRestaurantId(Integer restaurantId) {
    this.restaurantId = restaurantId;
  }

  public String getFoodItemName() {
    return FoodItemName;
  }

  public void setFoodItemName(String foodItemName) {
    FoodItemName = foodItemName;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Double getPrice() {
    return Price;
  }

  public void setPrice(Double price) {
    Price = price;
  }

  public boolean isAvailable() {
    return isAvailable;
  }

  public void setAvailable(boolean available) {
    isAvailable = available;
  }

  public byte[] getFoodItemImage() {
    return foodItemImage;
  }

  public void setFoodItemImage(byte[] foodItemImage) {
    this.foodItemImage = foodItemImage;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) { return true; }
    if (o == null || getClass() != o.getClass()) { return false; }
    FoodItemOutDTO that = (FoodItemOutDTO) o;
    return isAvailable == that.isAvailable && Objects.equals(foodItemId, that.foodItemId) &&
      Objects.equals(categoryId, that.categoryId) && Objects.equals(restaurantId, that.restaurantId) &&
      Objects.equals(FoodItemName, that.FoodItemName) && Objects.equals(description, that.description) &&
      Objects.equals(Price, that.Price) && Objects.deepEquals(foodItemImage, that.foodItemImage);
  }

  @Override
  public int hashCode() {
    return Objects.hash(foodItemId, categoryId, restaurantId, FoodItemName, description, Price, isAvailable,
      Arrays.hashCode(foodItemImage));
  }

  @Override
  public String toString() {
    return "FoodItemOutDTO{" +
      "foodItemId=" + foodItemId +
      ", categoryId=" + categoryId +
      ", restaurantId=" + restaurantId +
      ", FoodItemName='" + FoodItemName + '\'' +
      ", description='" + description + '\'' +
      ", Price=" + Price +
      ", isAvailable=" + isAvailable +
      ", foodItemImage=" + Arrays.toString(foodItemImage) +
      '}';
  }
}
