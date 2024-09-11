package com.nt.restaurant.microservice.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.util.Arrays;
import java.util.Objects;

@Entity
public class FoodItem {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer foodItemId;
  private Integer categoryId;
  private Integer restaurantId;
  private String foodItemName;
  private String description;
  private Double Price;
  private boolean isAvailable;
  @Lob
  private byte[] foodItemImage;

  public FoodItem() {
  }

  public FoodItem(Integer foodItemId, Integer categoryId, Integer restaurantId, String foodItemName, String description, Double price,
                  boolean isAvailable, byte[] foodItemImage) {
    this.foodItemId = foodItemId;
    this.categoryId = categoryId;
    this.restaurantId = restaurantId;
    this.foodItemName = foodItemName;
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
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FoodItem foodItem = (FoodItem) o;
    return isAvailable == foodItem.isAvailable && Objects.equals(foodItemId, foodItem.foodItemId) &&
      Objects.equals(categoryId, foodItem.categoryId) && Objects.equals(restaurantId, foodItem.restaurantId) &&
      Objects.equals(foodItemName, foodItem.foodItemName) && Objects.equals(description, foodItem.description) &&
      Objects.equals(Price, foodItem.Price) && Objects.deepEquals(foodItemImage, foodItem.foodItemImage);
  }

  @Override
  public int hashCode() {
    return Objects.hash(foodItemId, categoryId, restaurantId, foodItemName, description, Price, isAvailable,
      Arrays.hashCode(foodItemImage));
  }

  @Override
  public String toString() {
    return "FoodItem{" +
      "foodItemId=" + foodItemId +
      ", categoryId=" + categoryId +
      ", restaurantId=" + restaurantId +
      ", foodItemName='" + foodItemName + '\'' +
      ", description='" + description + '\'' +
      ", Price=" + Price +
      ", isAvailable=" + isAvailable +
      ", foodItemImage=" + Arrays.toString(foodItemImage) +
      '}';
  }
}

