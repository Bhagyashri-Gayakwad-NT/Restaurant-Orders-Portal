package com.nt.user.microservice.indto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class AddressInDTO {
  @NotBlank(message = "Street is required")
  @Size(min = 4, max = 100, message = "Street must be between 4 and 100 characters")
  private String street;

  @NotBlank(message = "City is required")
  @Size(min = 3, max = 50, message = "City must be between 3 and 50 characters")
  private String city;

  @NotBlank(message = "State is required")
  @Size(min = 2, max = 50, message = "State must be between 2 and 50 characters")
  @Pattern(regexp = "^[a-zA-Z]+$", message = "State must contain only alphabetic characters")
  private String state;

  @NotBlank(message = "Country is required")
  @Size(max = 50, message = "Country cannot be longer than 50 characters")
  @Pattern(regexp = "^[a-zA-Z]+$", message = "Country must contain only alphabetic characters without spaces or special characters")
  private String country;

  @NotBlank(message = "Pin code is required")
  @Pattern(regexp = "^[0-9]{6}$", message = "Pin code must be exactly 6 digits and cannot contain spaces or characters")
  private String pinCode;

  @NotNull(message = "User ID is required")
  private Integer userId;

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getPinCode() {
    return pinCode;
  }

  public void setPinCode(String pinCode) {
    this.pinCode = pinCode;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }
}



