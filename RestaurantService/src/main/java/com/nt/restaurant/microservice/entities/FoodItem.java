package com.nt.restaurant.microservice.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.util.Arrays;
import java.util.Objects;

/**
 * Entity class representing a food item in the restaurant system.
 * This class is mapped to a table in the database where each record represents a food item.
 */
@Entity
public class FoodItem {

  /**
   * The unique identifier for the food item.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer foodItemId;

  /**
   * The ID of the food category this food item belongs to.
   */
  private Integer categoryId;

  /**
   * The ID of the restaurant this food item belongs to.
   */
  private Integer restaurantId;

  /**
   * The name of the food item.
   */
  private String foodItemName;

  /**
   * A description of the food item.
   */
  private String description;

  /**
   * The price of the food item.
   */
  private Double price;

  /**
   * Availability status of the food item.
   */
  private boolean isAvailable;

  /**
   * The image of the food item stored as a byte array.
   */
  @Lob
  private byte[] foodItemImage;

  /**
   * Default constructor.
   * Required by JPA for entity creation.
   */
  public FoodItem() {
  }

  /**
   * Parameterized constructor for creating a FoodItem entity.
   *
   * @param foodItemId    the unique ID of the food item
   * @param categoryId    the ID of the associated category
   * @param restaurantId  the ID of the associated restaurant
   * @param foodItemName  the name of the food item
   * @param description   the description of the food item
   * @param price         the price of the food item
   * @param isAvailable   availability status of the food item
   * @param foodItemImage the image of the food item as a byte array
   */
  public FoodItem(final Integer foodItemId, final Integer categoryId, final Integer restaurantId, final String foodItemName,
                  final String description, final Double price, final boolean isAvailable, final byte[] foodItemImage) {
    this.foodItemId = foodItemId;
    this.categoryId = categoryId;
    this.restaurantId = restaurantId;
    this.foodItemName = foodItemName;
    this.description = description;
    this.price = price;
    this.isAvailable = isAvailable;
    this.foodItemImage = foodItemImage;
  }

  // Getters and Setters

  /**
   * Gets the food item ID.
   *
   * @return the unique ID of the food item.
   */
  public Integer getFoodItemId() {
    return foodItemId;
  }

  /**
   * Sets the food item ID.
   *
   * @param foodItemId the unique ID of the food item.
   */
  public void setFoodItemId(final Integer foodItemId) {
    this.foodItemId = foodItemId;
  }

  /**
   * Gets the category ID associated with this food item.
   *
   * @return the category ID.
   */
  public Integer getCategoryId() {
    return categoryId;
  }

  /**
   * Sets the category ID associated with this food item.
   *
   * @param categoryId the category ID.
   */
  public void setCategoryId(final Integer categoryId) {
    this.categoryId = categoryId;
  }

  /**
   * Gets the restaurant ID associated with this food item.
   *
   * @return the restaurant ID.
   */
  public Integer getRestaurantId() {
    return restaurantId;
  }

  /**
   * Sets the restaurant ID associated with this food item.
   *
   * @param restaurantId the restaurant ID.
   */
  public void setRestaurantId(final Integer restaurantId) {
    this.restaurantId = restaurantId;
  }

  /**
   * Gets the name of the food item.
   *
   * @return the food item's name.
   */
  public String getFoodItemName() {
    return foodItemName;
  }

  /**
   * Sets the name of the food item.
   *
   * @param foodItemName the name of the food item.
   */
  public void setFoodItemName(final String foodItemName) {
    this.foodItemName = foodItemName;
  }

  /**
   * Gets the description of the food item.
   *
   * @return the food item's description.
   */
  public String getDescription() {
    return description;
  }

  /**
   * Sets the description of the food item.
   *
   * @param description the description of the food item.
   */
  public void setDescription(final String description) {
    this.description = description;
  }

  /**
   * Gets the price of the food item.
   *
   * @return the price of the food item.
   */
  public Double getPrice() {
    return price;
  }

  /**
   * Sets the price of the food item.
   *
   * @param price the price of the food item.
   */
  public void setPrice(final Double price) {
    this.price = price;
  }

  /**
   * Checks if the food item is available.
   *
   * @return true if the food item is available, false otherwise.
   */
  public boolean isAvailable() {
    return isAvailable;
  }

  /**
   * Sets the availability of the food item.
   *
   * @param available the availability status.
   */
  public void setAvailable(final boolean available) {
    isAvailable = available;
  }

  /**
   * Gets the food item image as a byte array.
   *
   * @return the food item's image.
   */
  public byte[] getFoodItemImage() {
    return foodItemImage;
  }

  /**
   * Sets the food item image as a byte array.
   *
   * @param foodItemImage the food item's image.
   */
  public void setFoodItemImage(final byte[] foodItemImage) {
    this.foodItemImage = foodItemImage;
  }

  // Overrides for equality checks and representation

  /**
   * Compares this food item to another object for equality.
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
    FoodItem foodItem = (FoodItem) o;
    return isAvailable == foodItem.isAvailable
      && Objects.equals(foodItemId, foodItem.foodItemId)
      && Objects.equals(categoryId, foodItem.categoryId)
      && Objects.equals(restaurantId, foodItem.restaurantId)
      && Objects.equals(foodItemName, foodItem.foodItemName)
      && Objects.equals(description, foodItem.description)
      && Objects.equals(price, foodItem.price)
      && Arrays.equals(foodItemImage, foodItem.foodItemImage);
  }

  /**
   * Returns a hash code for this food item.
   *
   * @return a hash code based on the fields of the food item.
   */
  @Override
  public int hashCode() {
    return Objects.hash(foodItemId, categoryId, restaurantId, foodItemName, description, price, isAvailable)
      +
      Arrays.hashCode(foodItemImage);
  }

  /**
   * Returns a string representation of this food item.
   *
   * @return a string representing the food item.
   */
  @Override
  public String toString() {
    return "FoodItem{"
      + "foodItemId=" + foodItemId
      + ", categoryId=" + categoryId
      + ", restaurantId=" + restaurantId
      + ", foodItemName='" + foodItemName
      + '\'' + ", description='" + description
      + '\'' + ", price=" + price
      + ", isAvailable=" + isAvailable
      + ", foodItemImage=" + Arrays.toString(foodItemImage)
      + '}';
  }
}
