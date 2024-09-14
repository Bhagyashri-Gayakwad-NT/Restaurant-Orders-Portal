package com.nt.restaurant.microservice.dto;

import javax.persistence.Lob;
import java.util.Arrays;
import java.util.Objects;

/**
 * Data Transfer Object (DTO) for representing a food item in the output response.
 * This class is used to send food item details as a response in the application.
 */
public class FoodItemOutDTO {

  /**
   * The unique identifier for the food item.
   */
  private Integer foodItemId;

  /**
   * The identifier of the category to which the food item belongs.
   */
  private Integer categoryId;

  /**
   * The identifier of the restaurant to which the food item belongs.
   */
  private Integer restaurantId;

  /**
   * The name of the food item.
   */
  private String FoodItemName;

  /**
   * A description of the food item.
   */
  private String description;

  /**
   * The price of the food item.
   */
  private Double Price;

  /**
   * A flag indicating whether the food item is available.
   */
  private boolean isAvailable;

  /**
   * The image of the food item stored as a byte array.
   */
  @Lob
  private byte[] foodItemImage;

  /**
   * Default constructor for creating an empty {@code FoodItemOutDTO} instance.
   */
  public FoodItemOutDTO() {
  }

  /**
   * Constructor for creating a {@code FoodItemOutDTO} instance with specified values.
   *
   * @param foodItemId    the unique identifier for the food item.
   * @param categoryId    the identifier of the category to which the food item belongs.
   * @param restaurantId  the identifier of the restaurant to which the food item belongs.
   * @param foodItemName  the name of the food item.
   * @param description   a description of the food item.
   * @param price         the price of the food item.
   * @param isAvailable   a flag indicating whether the food item is available.
   * @param foodItemImage the image of the food item stored as a byte array.
   */
  public FoodItemOutDTO(Integer foodItemId, Integer categoryId, Integer restaurantId, String foodItemName,
                        String description, Double price, boolean isAvailable, byte[] foodItemImage) {
    this.foodItemId = foodItemId;
    this.categoryId = categoryId;
    this.restaurantId = restaurantId;
    this.FoodItemName = foodItemName;
    this.description = description;
    this.Price = price;
    this.isAvailable = isAvailable;
    this.foodItemImage = foodItemImage;
  }

  /**
   * Gets the unique identifier for the food item.
   *
   * @return the food item ID.
   */
  public Integer getFoodItemId() {
    return foodItemId;
  }

  /**
   * Sets the unique identifier for the food item.
   *
   * @param foodItemId the food item ID to set.
   */
  public void setFoodItemId(Integer foodItemId) {
    this.foodItemId = foodItemId;
  }

  /**
   * Gets the identifier of the category to which the food item belongs.
   *
   * @return the category ID.
   */
  public Integer getCategoryId() {
    return categoryId;
  }

  /**
   * Sets the identifier of the category to which the food item belongs.
   *
   * @param categoryId the category ID to set.
   */
  public void setCategoryId(Integer categoryId) {
    this.categoryId = categoryId;
  }

  /**
   * Gets the identifier of the restaurant to which the food item belongs.
   *
   * @return the restaurant ID.
   */
  public Integer getRestaurantId() {
    return restaurantId;
  }

  /**
   * Sets the identifier of the restaurant to which the food item belongs.
   *
   * @param restaurantId the restaurant ID to set.
   */
  public void setRestaurantId(Integer restaurantId) {
    this.restaurantId = restaurantId;
  }

  /**
   * Gets the name of the food item.
   *
   * @return the food item name.
   */
  public String getFoodItemName() {
    return FoodItemName;
  }

  /**
   * Sets the name of the food item.
   *
   * @param foodItemName the food item name to set.
   */
  public void setFoodItemName(String foodItemName) {
    this.FoodItemName = foodItemName;
  }

  /**
   * Gets the description of the food item.
   *
   * @return the description.
   */
  public String getDescription() {
    return description;
  }

  /**
   * Sets the description of the food item.
   *
   * @param description the description to set.
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Gets the price of the food item.
   *
   * @return the price.
   */
  public Double getPrice() {
    return Price;
  }

  /**
   * Sets the price of the food item.
   *
   * @param price the price to set.
   */
  public void setPrice(Double price) {
    this.Price = price;
  }

  /**
   * Checks if the food item is available.
   *
   * @return {@code true} if the food item is available, {@code false} otherwise.
   */
  public boolean isAvailable() {
    return isAvailable;
  }

  /**
   * Sets the availability of the food item.
   *
   * @param available the availability status to set.
   */
  public void setAvailable(boolean available) {
    this.isAvailable = available;
  }

  /**
   * Gets the image of the food item as a byte array.
   *
   * @return the food item image.
   */
  public byte[] getFoodItemImage() {
    return foodItemImage;
  }

  /**
   * Sets the image of the food item as a byte array.
   *
   * @param foodItemImage the food item image to set.
   */
  public void setFoodItemImage(byte[] foodItemImage) {
    this.foodItemImage = foodItemImage;
  }

  /**
   * Compares this {@code FoodItemOutDTO} to another object for equality.
   *
   * @param o the object to compare with.
   * @return {@code true} if this object is equal to the other object, {@code false} otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FoodItemOutDTO that = (FoodItemOutDTO) o;
    return isAvailable == that.isAvailable &&
      Objects.equals(foodItemId, that.foodItemId) &&
      Objects.equals(categoryId, that.categoryId) &&
      Objects.equals(restaurantId, that.restaurantId) &&
      Objects.equals(FoodItemName, that.FoodItemName) &&
      Objects.equals(description, that.description) &&
      Objects.equals(Price, that.Price) &&
      Arrays.equals(foodItemImage, that.foodItemImage);
  }

  /**
   * Returns a hash code value for this {@code FoodItemOutDTO}.
   *
   * @return the hash code value.
   */
  @Override
  public int hashCode() {
    return Objects.hash(foodItemId, categoryId, restaurantId, FoodItemName, description, Price, isAvailable) +
      Arrays.hashCode(foodItemImage);
  }

  /**
   * Returns a string representation of this {@code FoodItemOutDTO}.
   *
   * @return a string representation of the object.
   */
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
