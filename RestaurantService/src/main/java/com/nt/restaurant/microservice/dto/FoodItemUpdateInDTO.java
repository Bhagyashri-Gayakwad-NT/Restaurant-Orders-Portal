package com.nt.restaurant.microservice.dto;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * Data Transfer Object (DTO) for updating a food item in the system.
 * <p>
 * This DTO is used for validating input data when updating food item details such as
 * the food item name, description, price, and optional image.
 * </p>
 *
 * <p>Validation Annotations:
 * <ul>
 *   <li>{@link NotBlank} - Ensures the field is not null or empty.</li>
 *   <li>{@link Size} - Ensures the length of the field is within the specified range.</li>
 *   <li>{@link Pattern} - Ensures the field matches the given regular expression.</li>
 *   <li>{@link DecimalMin} - Ensures the field has a minimum value.</li>
 *   <li>{@link Digits} - Ensures the field follows a valid numeric format.</li>
 *   <li>{@link NotNull} - Ensures the field is not null.</li>
 * </ul>
 * </p>
 *
 * <p>This class provides getter and setter methods for all fields, along with methods
 * for equals, hashCode, and toString.
 * </p>
 *
 * @author [Your Name]
 * @version 1.0
 */
public class FoodItemUpdateInDTO {

  /**
   * The name of the food item.
   * <p>Must be non-blank and can contain only alphabets with a maximum length of 100 characters.</p>
   */
  @NotBlank(message = "Food item name cannot be blank")
  @Size(max = 100, message = "Food item name cannot exceed 100 characters")
  @Pattern(regexp = "^[A-Za-z]+(?:\\s[A-Za-z]+)*$", message = "Food Item name must contain only alphabets")
  private String foodItemName;

  /**
   * The description of the food item.
   * <p>Must be non-blank and should not exceed 255 characters. Leading or trailing spaces are not allowed.</p>
   */
  @NotBlank(message = "Description cannot be blank")
  @Size(max = 255, message = "Description cannot exceed 255 characters")
  @Pattern(regexp = "^(?!\\s)(?!.*\\s$).+", message = "Description cannot contain leading or trailing spaces")
  private String description;

  /**
   * The price of the food item.
   * <p>Must be greater than 0 and follow a valid numeric format with up to 10 digits before the decimal and 2 digits after.</p>
   */
  @NotNull(message = "Price cannot be null")
  @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
  @Digits(integer = 10, fraction = 2, message = "Price format is invalid")
  private Double price;

  /**
   * The image of the food item.
   * <p>This is an optional field represented as a {@link MultipartFile}.</p>
   */
  private MultipartFile foodItemImage;

  /**
   * Default constructor for creating an empty {@code FoodItemUpdateInDTO} instance.
   */
  public FoodItemUpdateInDTO() {
  }

  /**
   * Parameterized constructor for creating an instance of {@code FoodItemUpdateInDTO} with all fields.
   *
   * @param foodItemName  the name of the food item.
   * @param description   the description of the food item.
   * @param price         the price of the food item.
   * @param foodItemImage the image of the food item (optional).
   */
  public FoodItemUpdateInDTO(String foodItemName, String description, Double price, MultipartFile foodItemImage) {
    this.foodItemName = foodItemName;
    this.description = description;
    this.price = price;
    this.foodItemImage = foodItemImage;
  }

  /**
   * Gets the name of the food item.
   *
   * @return the name of the food item.
   */
  public String getFoodItemName() {
    return foodItemName;
  }

  /**
   * Sets the name of the food item.
   *
   * @param foodItemName the name of the food item to set.
   */
  public void setFoodItemName(String foodItemName) {
    this.foodItemName = foodItemName;
  }

  /**
   * Gets the description of the food item.
   *
   * @return the description of the food item.
   */
  public String getDescription() {
    return description;
  }

  /**
   * Sets the description of the food item.
   *
   * @param description the description of the food item to set.
   */
  public void setDescription(String description) {
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
   * @param price the price of the food item to set.
   */
  public void setPrice(Double price) {
    this.price = price;
  }

  /**
   * Gets the image of the food item.
   *
   * @return the image of the food item as a {@link MultipartFile}.
   */
  public MultipartFile getFoodItemImage() {
    return foodItemImage;
  }

  /**
   * Sets the image of the food item.
   *
   * @param foodItemImage the image of the food item to set.
   */
  public void setFoodItemImage(MultipartFile foodItemImage) {
    this.foodItemImage = foodItemImage;
  }

  /**
   * Compares this object to another object for equality.
   *
   * @param o the other object to compare to.
   * @return true if both objects are equal, false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FoodItemUpdateInDTO that = (FoodItemUpdateInDTO) o;
    return Objects.equals(foodItemName, that.foodItemName) &&
      Objects.equals(description, that.description) &&
      Objects.equals(price, that.price) &&
      Objects.equals(foodItemImage, that.foodItemImage);
  }

  /**
   * Generates the hash code for this object.
   *
   * @return the hash code of this object.
   */
  @Override
  public int hashCode() {
    return Objects.hash(foodItemName, description, price, foodItemImage);
  }

  /**
   * Returns a string representation of the object.
   *
   * @return a string representing the object.
   */
  @Override
  public String toString() {
    return "FoodItemUpdateInDTO{" +
      "foodItemName='" + foodItemName + '\'' +
      ", description='" + description + '\'' +
      ", price=" + price +
      ", foodItemImage=" + foodItemImage +
      '}';
  }
}
