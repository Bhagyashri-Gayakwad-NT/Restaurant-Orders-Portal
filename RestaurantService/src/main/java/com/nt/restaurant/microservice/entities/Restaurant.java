package com.nt.restaurant.microservice.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;

/**
 * Represents a Restaurant entity in the Restaurant microservice.
 * This class holds the data related to a restaurant including its name,
 * address, contact information, registration date, and more.
 */
@Entity
public class Restaurant {

  /**
   * Unique identifier for the restaurant. This ID is automatically generated
   * when a new restaurant is created.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer restaurantId;

  /**
   * The ID of the user who owns the restaurant.
   */
  private Integer userId;

  /**
   * The name of the restaurant.
   */
  private String restaurantName;

  /**
   * The address of the restaurant.
   */
  private String restaurantAddress;

  /**
   * The contact number of the restaurant.
   */
  private String contactNumber;

  /**
   * The date the restaurant was registered.
   */
  private LocalDate registrationDate;

  /**
   * The description of the restaurant.
   */
  private String description;

  /**
   * Whether the restaurant is currently open.
   */
  private boolean isOpen;

  /**
   * The image of the restaurant stored as byte array.
   */
  @Lob
  private byte[] restaurantImage;

  /**
   * Default constructor for Restaurant.
   */
  public Restaurant() {
  }

  /**
   * Parameterized constructor for creating a Restaurant object.
   *
   * @param restaurantId      the unique ID of the restaurant
   * @param userId            the ID of the user who owns the restaurant
   * @param restaurantName    the name of the restaurant
   * @param restaurantAddress the address of the restaurant
   * @param contactNumber     the contact number of the restaurant
   * @param registrationDate  the date the restaurant was registered
   * @param description       the description of the restaurant
   * @param isOpen            whether the restaurant is currently open
   * @param restaurantImage   the image of the restaurant stored as byte array
   */
  public Restaurant(final Integer restaurantId, final Integer userId, final String restaurantName,
                    final String restaurantAddress, final String contactNumber,
                    final LocalDate registrationDate, final String description,
                    final boolean isOpen, final byte[] restaurantImage) {
    this.restaurantId = restaurantId;
    this.userId = userId;
    this.restaurantName = restaurantName;
    this.restaurantAddress = restaurantAddress;
    this.contactNumber = contactNumber;
    this.registrationDate = registrationDate;
    this.description = description;
    this.isOpen = isOpen;
    this.restaurantImage = restaurantImage;
  }

  // Getter and Setter methods with Javadoc comments
  /**
   * Gets the unique ID of the restaurant.
   *
   * @return the restaurant ID
   */
  public Integer getRestaurantId() {
    return restaurantId;
  }

  /**
   * Sets the unique ID of the restaurant.
   *
   * @param restaurantId the restaurant ID to set
   */
  public void setRestaurantId(final Integer restaurantId) {
    this.restaurantId = restaurantId;
  }

  /**
   * Gets the user ID of the restaurant owner.
   *
   * @return the user ID
   */
  public Integer getUserId() {
    return userId;
  }

  /**
   * Sets the user ID of the restaurant owner.
   *
   * @param userId the user ID to set
   */
  public void setUserId(final Integer userId) {
    this.userId = userId;
  }

  /**
   * Gets the name of the restaurant.
   *
   * @return the restaurant name
   */
  public String getRestaurantName() {
    return restaurantName;
  }

  /**
   * Sets the name of the restaurant.
   *
   * @param restaurantName the restaurant name to set
   */
  public void setRestaurantName(final String restaurantName) {
    this.restaurantName = restaurantName;
  }

  /**
   * Gets the address of the restaurant.
   *
   * @return the restaurant address
   */
  public String getRestaurantAddress() {
    return restaurantAddress;
  }

  /**
   * Sets the address of the restaurant.
   *
   * @param restaurantAddress the restaurant address to set
   */
  public void setRestaurantAddress(final String restaurantAddress) {
    this.restaurantAddress = restaurantAddress;
  }

  /**
   * Gets the contact number of the restaurant.
   *
   * @return the contact number
   */
  public String getContactNumber() {
    return contactNumber;
  }

  /**
   * Sets the contact number of the restaurant.
   *
   * @param contactNumber the contact number to set
   */
  public void setContactNumber(final String contactNumber) {
    this.contactNumber = contactNumber;
  }

  /**
   * Gets the registration date of the restaurant.
   *
   * @return the registration date
   */
  public LocalDate getRegistrationDate() {
    return registrationDate;
  }

  /**
   * Sets the registration date of the restaurant.
   *
   * @param registrationDate the registration date to set
   */
  public void setRegistrationDate(final LocalDate registrationDate) {
    this.registrationDate = registrationDate;
  }

  /**
   * Gets the description of the restaurant.
   *
   * @return the description of the restaurant
   */
  public String getDescription() {
    return description;
  }

  /**
   * Sets the description of the restaurant.
   *
   * @param description the description to set
   */
  public void setDescription(final String description) {
    this.description = description;
  }

  /**
   * Checks if the restaurant is currently open.
   *
   * @return true if the restaurant is open, false otherwise
   */
  public boolean isOpen() {
    return isOpen;
  }

  /**
   * Sets whether the restaurant is currently open.
   *
   * @param open true if the restaurant is open, false otherwise
   */
  public void setOpen(final boolean open) {
    isOpen = open;
  }

  /**
   * Gets the image of the restaurant stored as a byte array.
   *
   * @return the restaurant image
   */
  public byte[] getRestaurantImage() {
    return restaurantImage;
  }

  /**
   * Sets the image of the restaurant stored as a byte array.
   *
   * @param restaurantImage the restaurant image to set
   */
  public void setRestaurantImage(final byte[] restaurantImage) {
    this.restaurantImage = restaurantImage;
  }

  /**
   * Generates the hash code for this Restaurant object.
   *
   * @return the hash code of this object
   */  @Override
  public int hashCode() {
    return Objects.hash(restaurantId, userId, restaurantName, restaurantAddress,
      contactNumber, registrationDate, description, isOpen,
      Arrays.hashCode(restaurantImage));
  }

  /**
   * Checks if this Restaurant object is equal to another object.
   *
   * @param o the object to compare with
   * @return true if the objects are equal, false otherwise
   */
  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Restaurant)) {
      return false;
    }
    Restaurant that = (Restaurant) o;
    return isOpen == that.isOpen
      && Objects.equals(restaurantId, that.restaurantId)
      && Objects.equals(userId, that.userId)
      && Objects.equals(restaurantName, that.restaurantName)
      && Objects.equals(restaurantAddress, that.restaurantAddress)
      && Objects.equals(contactNumber, that.contactNumber)
      && Objects.equals(registrationDate, that.registrationDate)
      && Objects.equals(description, that.description)
      && Objects.deepEquals(restaurantImage, that.restaurantImage);
  }

  /**
   * Returns a string representation of the restaurant object.
   *
   * @return a string representation of the restaurant
   */
  @Override
  public String toString() {
    return "Restaurant{"
      + "restaurantId=" + restaurantId
      + ", userId=" + userId
      + ", restaurantName='" + restaurantName
      + '\'' + ", restaurantAddress='" + restaurantAddress
      + '\'' + ", contactNumber='" + contactNumber
      + '\'' + ", registrationDate=" + registrationDate
      + ", description='" + description
      + '\'' + ", isOpen=" + isOpen
      + ", restaurantImage=" + Arrays.toString(restaurantImage)
      + '}';
  }
}
