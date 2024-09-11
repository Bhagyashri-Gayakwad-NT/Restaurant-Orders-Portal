package com.nt.restaurant.microservice.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;

@Entity
public class Restaurant {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer restaurantId;

  private Integer userId;

  private String restaurantName;

  private String restaurantAddress;

  private String contactNumber;

  private LocalDate registrationDate;

  private String description;

  private boolean isOpen;
  @Lob
  private byte[] restaurantImage;

  public Restaurant() {
  }

  public Restaurant(Integer restaurantId, Integer userId, String restaurantName, String restaurantAddress, String contactNumber,
                    LocalDate registrationDate, String description, boolean isOpen, byte[] restaurantImage) {
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

  public Integer getRestaurantId() {
    return restaurantId;
  }

  public void setRestaurantId(Integer restaurantId) {
    this.restaurantId = restaurantId;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public String getRestaurantName() {
    return restaurantName;
  }

  public void setRestaurantName(String restaurantName) {
    this.restaurantName = restaurantName;
  }

  public String getRestaurantAddress() {
    return restaurantAddress;
  }

  public void setRestaurantAddress(String restaurantAddress) {
    this.restaurantAddress = restaurantAddress;
  }

  public String getContactNumber() {
    return contactNumber;
  }

  public void setContactNumber(String contactNumber) {
    this.contactNumber = contactNumber;
  }

  public LocalDate getRegistrationDate() {
    return registrationDate;
  }

  public void setRegistrationDate(LocalDate registrationDate) {
    this.registrationDate = registrationDate;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public boolean isOpen() {
    return isOpen;
  }

  public void setOpen(boolean open) {
    isOpen = open;
  }

  public byte[] getRestaurantImage() {
    return restaurantImage;
  }

  public void setRestaurantImage(byte[] restaurantImage) {
    this.restaurantImage = restaurantImage;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Restaurant that = (Restaurant) o;
    return isOpen == that.isOpen && Objects.equals(restaurantId, that.restaurantId) &&
      Objects.equals(userId, that.userId) && Objects.equals(restaurantName, that.restaurantName) &&
      Objects.equals(restaurantAddress, that.restaurantAddress) &&
      Objects.equals(contactNumber, that.contactNumber) && Objects.equals(registrationDate, that.registrationDate) &&
      Objects.equals(description, that.description) && Objects.deepEquals(restaurantImage, that.restaurantImage);
  }

  @Override
  public int hashCode() {
    return Objects.hash(restaurantId, userId, restaurantName, restaurantAddress, contactNumber, registrationDate, description, isOpen,
      Arrays.hashCode(restaurantImage));
  }

  @Override
  public String toString() {
    return "Restaurant{" +
      "restaurantId=" + restaurantId +
      ", userId=" + userId +
      ", restaurantName='" + restaurantName + '\'' +
      ", restaurantAddress='" + restaurantAddress + '\'' +
      ", contactNumber='" + contactNumber + '\'' +
      ", registrationDate=" + registrationDate +
      ", description='" + description + '\'' +
      ", isOpen=" + isOpen +
      ", restaurantImage=" + Arrays.toString(restaurantImage) +
      '}';
  }
}
