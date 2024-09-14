package com.nt.restaurant.microservice.dto;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Data Transfer Object (DTO) for representing a restaurant in the output response.
 * This class is used to send restaurant details as a response in the application.
 */
public class RestaurantOutDTO {

  /**
   * The unique identifier for the restaurant.
   */
  private Integer restaurantId;

  /**
   * The name of the restaurant.
   */
  private String restaurantName;

  /**
   * The address of the restaurant.
   */
  private String restaurantAddress;

  /**
   * The contact number for the restaurant.
   */
  private String contactNumber;

  /**
   * The registration date of the restaurant.
   */
  private LocalDate registrationDate;

  /**
   * A description of the restaurant.
   */
  private String description;

  /**
   * A flag indicating whether the restaurant is open.
   */
  private boolean isOpen;

  /**
   * The image of the restaurant.
   */
  private String restaurantImage;

  /**
   * Default constructor for creating an empty {@code RestaurantOutDTO} instance.
   */
  public RestaurantOutDTO() {
  }

  /**
   * Constructor for creating a {@code RestaurantOutDTO} instance with specified values.
   *
   * @param restaurantId      the unique identifier for the restaurant.
   * @param restaurantName    the name of the restaurant.
   * @param restaurantAddress the address of the restaurant.
   * @param contactNumber     the contact number for the restaurant.
   * @param registrationDate  the registration date of the restaurant.
   * @param description       a description of the restaurant.
   * @param isOpen            a flag indicating whether the restaurant is open.
   * @param restaurantImage   the image of the restaurant.
   */
  public RestaurantOutDTO(Integer restaurantId, String restaurantName, String restaurantAddress, String contactNumber,
                          LocalDate registrationDate, String description, boolean isOpen, String restaurantImage) {
    this.restaurantId = restaurantId;
    this.restaurantName = restaurantName;
    this.restaurantAddress = restaurantAddress;
    this.contactNumber = contactNumber;
    this.registrationDate = registrationDate;
    this.description = description;
    this.isOpen = isOpen;
    this.restaurantImage = restaurantImage;
  }

  /**
   * Gets the unique identifier for the restaurant.
   *
   * @return the restaurant ID.
   */
  public Integer getRestaurantId() {
    return restaurantId;
  }

  /**
   * Sets the unique identifier for the restaurant.
   *
   * @param restaurantId the restaurant ID to set.
   */
  public void setRestaurantId(Integer restaurantId) {
    this.restaurantId = restaurantId;
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
  public void setRestaurantName(String restaurantName) {
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
  public void setRestaurantAddress(String restaurantAddress) {
    this.restaurantAddress = restaurantAddress;
  }

  /**
   * Gets the contact number for the restaurant.
   *
   * @return the contact number.
   */
  public String getContactNumber() {
    return contactNumber;
  }

  /**
   * Sets the contact number for the restaurant.
   *
   * @param contactNumber the contact number to set.
   */
  public void setContactNumber(String contactNumber) {
    this.contactNumber = contactNumber;
  }

  /**
   * Gets the registration date of the restaurant.
   *
   * @return the registration date.
   */
  public LocalDate getRegistrationDate() {
    return registrationDate;
  }

  /**
   * Sets the registration date of the restaurant.
   *
   * @param registrationDate the registration date to set.
   */
  public void setRegistrationDate(LocalDate registrationDate) {
    this.registrationDate = registrationDate;
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
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Checks if the restaurant is open.
   *
   * @return {@code true} if the restaurant is open, {@code false} otherwise.
   */
  public boolean isOpen() {
    return isOpen;
  }

  /**
   * Sets the open status of the restaurant.
   *
   * @param open the open status to set.
   */
  public void setOpen(boolean open) {
    isOpen = open;
  }

  /**
   * Gets the image of the restaurant.
   *
   * @return the restaurant image.
   */
  public String getRestaurantImage() {
    return restaurantImage;
  }

  /**
   * Sets the image of the restaurant.
   *
   * @param restaurantImage the restaurant image to set.
   */
  public void setRestaurantImage(String restaurantImage) {
    this.restaurantImage = restaurantImage;
  }

  /**
   * Compares this {@code RestaurantOutDTO} to another object for equality.
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
    RestaurantOutDTO that = (RestaurantOutDTO) o;
    return isOpen == that.isOpen &&
      Objects.equals(restaurantId, that.restaurantId) &&
      Objects.equals(restaurantName, that.restaurantName) &&
      Objects.equals(restaurantAddress, that.restaurantAddress) &&
      Objects.equals(contactNumber, that.contactNumber) &&
      Objects.equals(registrationDate, that.registrationDate) &&
      Objects.equals(description, that.description) &&
      Objects.equals(restaurantImage, that.restaurantImage);
  }

  /**
   * Returns a hash code value for this {@code RestaurantOutDTO}.
   *
   * @return the hash code value.
   */
  @Override
  public int hashCode() {
    return Objects.hash(restaurantId, restaurantName, restaurantAddress, contactNumber, registrationDate, description, isOpen,
      restaurantImage);
  }

  /**
   * Returns a string representation of this {@code RestaurantOutDTO}.
   *
   * @return a string representation of the object.
   */
  @Override
  public String toString() {
    return "RestaurantOutDTO{" +
      "restaurantId=" + restaurantId +
      ", restaurantName='" + restaurantName + '\'' +
      ", restaurantAddress='" + restaurantAddress + '\'' +
      ", contactNumber='" + contactNumber + '\'' +
      ", registrationDate=" + registrationDate +
      ", description='" + description + '\'' +
      ", isOpen=" + isOpen +
      ", restaurantImage='" + restaurantImage + '\'' +
      '}';
  }
}

