package com.nt.restaurant.microservice.dto;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * Data Transfer Object (DTO) for creating or updating a restaurant.
 * This DTO is used for validating input data for restaurant-related operations.
 */
public class RestaurantInDTO {

  /**
   * The user ID associated with the restaurant.
   * Must not be null.
   */
  @NotNull(message = "User ID cannot be null")
  private Integer userId;

  /**
   * The name of the restaurant.
   * Must be non-blank and only contain alphabets.
   */
  @NotBlank(message = "Restaurant name cannot be blank")
  @Pattern(regexp = "^[A-Za-z0-9]{2,}(?:\\s[A-Za-z0-9]+)*$",
    message = "Restaurant name must contain at least two alphabets and can include numbers")
  private String restaurantName;

  /**
   * The address of the restaurant.
   * Must be non-blank and cannot contain leading or trailing spaces.
   */
  @NotBlank(message = "Address cannot be blank")
  private String restaurantAddress;

  /**
   * The contact number of the restaurant.
   * Must be a valid 10-digit number starting with 9, 8, 7, or 6.
   */
  @NotBlank(message = "contactNumber cannot be blank")
  @Pattern(regexp = "^[9876]\\d{9}$", message = "Phone number must start with 9, 8, 7, or 6 and contain 10 digits")
  private String contactNumber;

  /**
   * A description of the restaurant.
   * Must be non-blank and cannot exceed 255 characters.
   * Cannot contain leading or trailing spaces.
   */
  @NotBlank(message = "Description cannot be blank")
  @Size(max = 255, message = "Description cannot exceed 255 characters")
  private String description;

  /**
   * An optional image of the restaurant.
   * This field represents the image as a {@link MultipartFile}.
   */
  private MultipartFile restaurantImage;

  /**
   * Default constructor for creating an empty {@code RestaurantInDTO} instance.
   */
  public RestaurantInDTO() {
  }

  /**
   * Parameterized constructor for creating a {@code RestaurantInDTO} instance with all fields.
   *
   * @param userId            the user ID associated with the restaurant.
   * @param restaurantName    the name of the restaurant.
   * @param restaurantAddress the address of the restaurant.
   * @param contactNumber     the contact number of the restaurant.
   * @param description       the description of the restaurant.
   * @param restaurantImage   the image of the restaurant.
   */
  public RestaurantInDTO(final Integer userId, final String restaurantName, final String restaurantAddress,
                         final String contactNumber,
                         final String description, final MultipartFile restaurantImage) {
    this.userId = userId;
    this.restaurantName = restaurantName;
    this.restaurantAddress = restaurantAddress;
    this.contactNumber = contactNumber;
    this.description = description;
    this.restaurantImage = restaurantImage;
  }

  /**
   * Gets the user ID associated with the restaurant.
   *
   * @return the user ID.
   */
  public Integer getUserId() {
    return userId;
  }

  /**
   * Sets the user ID associated with the restaurant.
   *
   * @param userId the user ID to set.
   */
  public void setUserId(final Integer userId) {
    this.userId = userId;
  }

  /**
   * Gets the name of the restaurant.
   *
   * @return the restaurant name.
   */
  public String getRestaurantName() {
    return restaurantName;
  }

  /**
   * Sets the name of the restaurant.
   *
   * @param restaurantName the restaurant name to set.
   */
  public void setRestaurantName(final String restaurantName) {
    this.restaurantName = restaurantName;
  }

  /**
   * Gets the address of the restaurant.
   *
   * @return the restaurant address.
   */
  public String getRestaurantAddress() {
    return restaurantAddress;
  }

  /**
   * Sets the address of the restaurant.
   *
   * @param restaurantAddress the restaurant address to set.
   */
  public void setRestaurantAddress(final String restaurantAddress) {
    this.restaurantAddress = restaurantAddress;
  }

  /**
   * Gets the contact number of the restaurant.
   *
   * @return the contact number.
   */
  public String getContactNumber() {
    return contactNumber;
  }

  /**
   * Sets the contact number of the restaurant.
   *
   * @param contactNumber the contact number to set.
   */
  public void setContactNumber(final String contactNumber) {
    this.contactNumber = contactNumber;
  }

  /**
   * Gets the description of the restaurant.
   *
   * @return the description.
   */
  public String getDescription() {
    return description;
  }

  /**
   * Sets the description of the restaurant.
   *
   * @param description the description to set.
   */
  public void setDescription(final String description) {
    this.description = description;
  }

  /**
   * Gets the image of the restaurant.
   *
   * @return the restaurant image as a {@link MultipartFile}.
   */
  public MultipartFile getRestaurantImage() {
    return restaurantImage;
  }

  /**
   * Sets the image of the restaurant.
   *
   * @param restaurantImage the restaurant image to set.
   */
  public void setRestaurantImage(final MultipartFile restaurantImage) {
    this.restaurantImage = restaurantImage;
  }

  /**
   * Compares this object with another object for equality.
   *
   * @param o the other object to compare to.
   * @return true if both objects are equal, false otherwise.
   */
  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RestaurantInDTO that = (RestaurantInDTO) o;
    return Objects.equals(userId, that.userId)
      && Objects.equals(restaurantName, that.restaurantName)
      && Objects.equals(restaurantAddress, that.restaurantAddress)
      && Objects.equals(contactNumber, that.contactNumber)
      && Objects.equals(description, that.description)
      && Objects.equals(restaurantImage, that.restaurantImage);
  }

  /**
   * Generates the hash code for this object.
   *
   * @return the hash code of this object.
   */
  @Override
  public int hashCode() {
    return Objects.hash(userId, restaurantName, restaurantAddress, contactNumber, description, restaurantImage);
  }

  /**
   * Returns a string representation of this object.
   *
   * @return a string representing the object.
   */
  @Override
  public String toString() {
    return "RestaurantInDto{"
      + "userId=" + userId
      + ", restaurantName='" + restaurantName
      + '\'' + ", restaurantAddress='" + restaurantAddress
      + '\'' + ", contactNumber='" + contactNumber
      + '\'' + ", description='" + description
      + '\'' + ", restaurantImage=" + restaurantImage
      + '}';
  }
}



