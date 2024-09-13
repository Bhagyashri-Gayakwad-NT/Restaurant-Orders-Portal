package com.nt.restaurant.microservice.indto;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * Data Transfer Object (DTO) for input validation of the Food Item entity.
 * This class is used to capture and validate the input data required to create
 * or update a food item for a restaurant.
 */
public class FoodItemInDTO {

  /**
   * Represents the ID of the food category to which the food item belongs.
   * This field cannot be null.
   */
  @NotNull(message = "Food category ID cannot be null")
  private Integer foodCategoryId;

  /**
   * Represents the ID of the restaurant that offers the food item.
   * This field cannot be null.
   */
  @NotNull(message = "Restaurant ID cannot be null")
  private Integer restaurantId;

  /**
   * Represents the name of the food item.
   * The name cannot be blank, should not exceed 100 characters, and must contain only alphabets.
   */
  @NotBlank(message = "Food item name cannot be blank")
  @Size(max = 100, message = "Food item name cannot exceed 100 characters")
  @Pattern(regexp = "^[A-Za-z]+(?:\\s[A-Za-z]+)*$", message = "Food Item name must contain only alphabets")
  private String foodItemName;

  /**
   * Represents the description of the food item.
   * The description cannot be blank and should not exceed 255 characters.
   */
  @NotBlank(message = "Description cannot be blank")
  @Size(max = 255, message = "Description cannot exceed 255 characters")
  @Pattern(regexp = "^(?!\\s)(?!.*\\s$).+", message = "Description cannot contain leading or trailing spaces")
  private String description;

  /**
   * Represents the price of the food item.
   * The price cannot be null, must be greater than 0, and must have a valid format.
   */
  @NotNull(message = "Price cannot be null")
  @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
  @Digits(integer = 10, fraction = 2, message = "Price format is invalid")
  private Double price;

  /**
   * Represents the availability status of the food item.
   * A boolean value indicating whether the food item is available for order.
   */
  private boolean isAvailable;

  /**
   * Represents the image of the food item in a multipart file format.
   * This field is optional.
   */
  private MultipartFile foodItemImage;

  /**
   * Default constructor for the {@code FoodItemInDTO} class.
   * Initializes a new instance of the {@code FoodItemInDTO} class with default values.
   */
  public FoodItemInDTO() {
  }

  /**
   * Parameterized constructor to initialize {@code FoodItemInDTO} with food category ID, restaurant ID,
   * food item name, description, price, availability, and food item image.
   *
   * @param foodCategoryId the ID of the food category
   * @param restaurantId   the ID of the restaurant
   * @param foodItemName   the name of the food item
   * @param description    the description of the food item
   * @param price          the price of the food item
   * @param isAvailable    the availability status of the food item
   * @param foodItemImage  the image of the food item
   */
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

  /**
   * Gets the ID of the food category.
   *
   * @return the food category ID
   */
  public Integer getFoodCategoryId() {
    return foodCategoryId;
  }

  /**
   * Sets the ID of the food category.
   *
   * @param foodCategoryId the new food category ID
   */
  public void setFoodCategoryId(Integer foodCategoryId) {
    this.foodCategoryId = foodCategoryId;
  }

  /**
   * Gets the ID of the restaurant.
   *
   * @return the restaurant ID
   */
  public Integer getRestaurantId() {
    return restaurantId;
  }

  /**
   * Sets the ID of the restaurant.
   *
   * @param restaurantId the new restaurant ID
   */
  public void setRestaurantId(Integer restaurantId) {
    this.restaurantId = restaurantId;
  }

  /**
   * Gets the name of the food item.
   *
   * @return the food item name
   */
  public String getFoodItemName() {
    return foodItemName;
  }

  /**
   * Sets the name of the food item.
   *
   * @param foodItemName the new food item name
   */
  public void setFoodItemName(String foodItemName) {
    this.foodItemName = foodItemName;
  }

  /**
   * Gets the description of the food item.
   *
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * Sets the description of the food item.
   *
   * @param description the new description
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Gets the price of the food item.
   *
   * @return the price
   */
  public Double getPrice() {
    return price;
  }

  /**
   * Sets the price of the food item.
   *
   * @param price the new price
   */
  public void setPrice(Double price) {
    this.price = price;
  }

  /**
   * Gets the availability status of the food item.
   *
   * @return {@code true} if the food item is available, otherwise {@code false}
   */
  public boolean isAvailable() {
    return isAvailable;
  }

  /**
   * Sets the availability status of the food item.
   *
   * @param available the new availability status
   */
  public void setAvailable(boolean available) {
    isAvailable = available;
  }

  /**
   * Gets the food item image.
   *
   * @return the food item image
   */
  public MultipartFile getFoodItemImage() {
    return foodItemImage;
  }

  /**
   * Sets the food item image.
   *
   * @param foodItemImage the new food item image
   */
  public void setFoodItemImage(MultipartFile foodItemImage) {
    this.foodItemImage = foodItemImage;
  }

  /**
   * Compares this object with the specified object for equality.
   *
   * @param o the object to compare with
   * @return {@code true} if both objects are equal, otherwise {@code false}
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FoodItemInDTO that = (FoodItemInDTO) o;
    return isAvailable == that.isAvailable && Objects.equals(foodCategoryId, that.foodCategoryId) &&
      Objects.equals(restaurantId, that.restaurantId) && Objects.equals(foodItemName, that.foodItemName) &&
      Objects.equals(description, that.description) && Objects.equals(price, that.price) &&
      Objects.equals(foodItemImage, that.foodItemImage);
  }

  /**
   * Returns the hash code value for this object.
   *
   * @return the hash code value
   */
  @Override
  public int hashCode() {
    return Objects.hash(foodCategoryId, restaurantId, foodItemName, description, price, isAvailable, foodItemImage);
  }

  /**
   * Returns a string representation of the object.
   *
   * @return a string representation of the object
   */
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
