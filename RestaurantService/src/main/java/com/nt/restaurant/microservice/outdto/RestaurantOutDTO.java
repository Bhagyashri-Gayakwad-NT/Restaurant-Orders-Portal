package com.nt.restaurant.microservice.outdto;

import java.time.LocalDate;
import java.util.Objects;

public class RestaurantOutDTO {

  private Integer restaurantId;
  private String restaurantName;
  private String restaurantAddress;
  private String contactNumber;
  private LocalDate registrationDate;
  private String description;
  private boolean isOpen;
  private String restaurantImage;

  public RestaurantOutDTO() {
  }

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

  public Integer getRestaurantId() {
    return restaurantId;
  }

  public void setRestaurantId(Integer restaurantId) {
    this.restaurantId = restaurantId;
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

  public String getRestaurantImage() {
    return restaurantImage;
  }

  public void setRestaurantImage(String restaurantImage) {
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
    RestaurantOutDTO that = (RestaurantOutDTO) o;
    return isOpen == that.isOpen && Objects.equals(restaurantId, that.restaurantId) &&
      Objects.equals(restaurantName, that.restaurantName) &&
      Objects.equals(restaurantAddress, that.restaurantAddress) &&
      Objects.equals(contactNumber, that.contactNumber) && Objects.equals(registrationDate, that.registrationDate) &&
      Objects.equals(description, that.description) && Objects.equals(restaurantImage, that.restaurantImage);
  }

  @Override
  public int hashCode() {
    return Objects.hash(restaurantId, restaurantName, restaurantAddress, contactNumber, registrationDate, description, isOpen,
      restaurantImage);
  }

  @Override
  public String toString() {
    return "RestaurantOutDto{" +
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
