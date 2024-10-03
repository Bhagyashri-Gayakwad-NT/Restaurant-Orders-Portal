package com.nt.order.microservice.dtos;


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
  public FoodItemOutDTO(final Integer foodItemId, final Integer categoryId, final Integer restaurantId, final String foodItemName,
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
  public void setFoodItemId(final Integer foodItemId) {
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
  public void setCategoryId(final Integer categoryId) {
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
  public void setRestaurantId(final Integer restaurantId) {
    this.restaurantId = restaurantId;
  }

  /**
   * Gets the name of the food item.
   *
   * @return the food item name.
   */
  public String getFoodItemName() {
    return foodItemName;
  }

  /**
   * Sets the name of the food item.
   *
   * @param foodItemName the food item name to set.
   */
  public void setFoodItemName(final String foodItemName) {
    this.foodItemName = foodItemName;
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
  public void setDescription(final String description) {
    this.description = description;
  }

  /**
   * Gets the price of the food item.
   *
   * @return the price.
   */
  public Double getPrice() {
    return price;
  }

  /**
   * Sets the price of the food item.
   *
   * @param price the price to set.
   */
  public void setPrice(final Double price) {
    this.price = price;
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
  public void setAvailable(final boolean available) {
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
  public void setFoodItemImage(final byte[] foodItemImage) {
    this.foodItemImage = foodItemImage;
  }

  /**
   * Compares this {@code FoodItemOutDTO} to another object for equality.
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
    FoodItemOutDTO that = (FoodItemOutDTO) o;
    return isAvailable == that.isAvailable
      && Objects.equals(foodItemId, that.foodItemId)
      && Objects.equals(categoryId, that.categoryId)
      && Objects.equals(restaurantId, that.restaurantId)
      && Objects.equals(foodItemName, that.foodItemName)
      && Objects.equals(description, that.description)
      && Objects.equals(price, that.price)
      && Arrays.equals(foodItemImage, that.foodItemImage);
  }

  /**
   * Returns a hash code value for this {@code FoodItemOutDTO}.
   *
   * @return the hash code value.
   */
  @Override
  public int hashCode() {
    return Objects.hash(foodItemId, categoryId, restaurantId, foodItemName, description, price, isAvailable)
      + Arrays.hashCode(foodItemImage);
  }

  /**
   * Returns a string representation of this {@code FoodItemOutDTO}.
   *
   * @return a string representation of the object.
   */
  @Override
  public String toString() {
    return "FoodItemOutDTO{"
      + "foodItemId=" + foodItemId
      + ", categoryId=" + categoryId
      + ", restaurantId=" + restaurantId
      + ", FoodItemName='" + foodItemName
      + '\'' + ", description='" + description
      + '\'' + ", Price=" + price
      + ", isAvailable=" + isAvailable
      + ", foodItemImage=" + Arrays.toString(foodItemImage)
      + '}';
  }
}
