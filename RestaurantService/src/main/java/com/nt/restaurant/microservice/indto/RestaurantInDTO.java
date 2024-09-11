package com.nt.restaurant.microservice.indto;

import com.sun.istack.NotNull;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

public class RestaurantInDTO {

  @NotNull
  private Integer userId;

  @NotBlank(message = "Restaurant name cannot be blank")
  @Pattern(regexp = "^[A-Za-z\\s]+$", message = "Restaurant name must contain only alphabets")
  private String restaurantName;

  @NotBlank(message = "Address cannot be blank")
  private String restaurantAddress;

  @NotBlank
  @Pattern(regexp = "^[9876]\\d{9}$", message = "Phone number must start with 9, 8, 7, or 6 and contain 10 digits")
  private String contactNumber;

  @NotBlank(message = "Description cannot be blank")
  @Size(max = 255, message = "Description cannot exceed 255 characters")
  private String description;

  private MultipartFile restaurantImage;

  public RestaurantInDTO() {
  }

  public RestaurantInDTO(Integer userId, String restaurantName, String restaurantAddress, String contactNumber, String description,
                         MultipartFile restaurantImage) {
    this.userId = userId;
    this.restaurantName = restaurantName;
    this.restaurantAddress = restaurantAddress;
    this.contactNumber = contactNumber;
    this.description = description;
    this.restaurantImage = restaurantImage;
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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public MultipartFile getRestaurantImage() {
    return restaurantImage;
  }

  public void setRestaurantImage(MultipartFile restaurantImage) {
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
    RestaurantInDTO that = (RestaurantInDTO) o;
    return Objects.equals(userId, that.userId) && Objects.equals(restaurantName, that.restaurantName) &&
      Objects.equals(restaurantAddress, that.restaurantAddress) &&
      Objects.equals(contactNumber, that.contactNumber) && Objects.equals(description, that.description) &&
      Objects.equals(restaurantImage, that.restaurantImage);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, restaurantName, restaurantAddress, contactNumber, description, restaurantImage);
  }

  @Override
  public String toString() {
    return "RestaurantInDto{" +
      "userId=" + userId +
      ", restaurantName='" + restaurantName + '\'' +
      ", restaurantAddress='" + restaurantAddress + '\'' +
      ", contactNumber='" + contactNumber + '\'' +
      ", description='" + description + '\'' +
      ", restaurantImage=" + restaurantImage +
      '}';
  }
}
