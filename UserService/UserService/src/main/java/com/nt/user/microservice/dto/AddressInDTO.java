package com.nt.user.microservice.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * Data Transfer Object (DTO) for Address input.
 * This DTO is used to capture address details from the user.
 */
public class AddressInDTO {

  /**
   * The street address of the user.
   * Must be between 4 and 100 characters.
   */
  @NotBlank(message = "Street is required")
  @Size(min = 4, max = 100, message = "Street must be between 4 and 100 characters")
  private String street;

  /**
   * The city of the user.
   * Must contain only alphabetic characters and be between 3 and 50 characters.
   */
  @NotBlank(message = "City is required")
  @Size(min = 3, max = 50, message = "City must be between 3 and 50 characters")
  @Pattern(regexp = "^[a-zA-Z]+$", message = "City must contain only alphabetic characters")
  private String city;

  /**
   * The state of the user.
   * Must contain only alphabetic characters and be between 2 and 50 characters.
   */
  @NotBlank(message = "State is required")
  @Size(min = 2, max = 50, message = "State must be between 2 and 50 characters")
  @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "State must contain only alphabetic characters")
  private String state;

  /**
   * The country of the user.
   * Must contain only alphabetic characters and be no longer than 50 characters.
   */
  @NotBlank(message = "Country is required")
  @Size(max = 50, message = "Country cannot be longer than 50 characters")
  @Pattern(regexp = "^[a-zA-Z]+$", message = "Country must contain only alphabetic characters")
  private String country;

  /**
   * The pin code of the user's address.
   * Must be exactly 6 digits.
   */
  @NotBlank(message = "Pin code is required")
  @Pattern(regexp = "^[0-9]{6}$", message = "Pin code must be exactly 6 digits")
  private String pinCode;

  /**
   * The unique identifier of the user associated with the address.
   */
  @NotNull(message = "User ID is required")
  private Integer userId;

  /**
   * Gets the street address.
   *
   * @return the street address.
   */
  public String getStreet() {
    return street;
  }

  /**
   * Sets the street address.
   *
   * @param street the street address to set.
   */
  public void setStreet(final String street) {
    this.street = street;
  }

  /**
   * Gets the city of the user.
   *
   * @return the city.
   */
  public String getCity() {
    return city;
  }

  /**
   * Sets the city of the user.
   *
   * @param city the city to set.
   */
  public void setCity(final String city) {
    this.city = city;
  }

  /**
   * Gets the state of the user.
   *
   * @return the state.
   */
  public String getState() {
    return state;
  }

  /**
   * Sets the state of the user.
   *
   * @param state the state to set.
   */
  public void setState(final String state) {
    this.state = state;
  }

  /**
   * Gets the country of the user.
   *
   * @return the country.
   */
  public String getCountry() {
    return country;
  }

  /**
   * Sets the country of the user.
   *
   * @param country the country to set.
   */
  public void setCountry(final String country) {
    this.country = country;
  }

  /**
   * Gets the pin code of the user's address.
   *
   * @return the pin code.
   */
  public String getPinCode() {
    return pinCode;
  }

  /**
   * Sets the pin code of the user's address.
   *
   * @param pinCode the pin code to set.
   */
  public void setPinCode(final String pinCode) {
    this.pinCode = pinCode;
  }

  /**
   * Gets the user ID associated with this address.
   *
   * @return the user ID.
   */
  public Integer getUserId() {
    return userId;
  }

  /**
   * Sets the user ID associated with this address.
   *
   * @param userId the user ID to set.
   */
  public void setUserId(final Integer userId) {
    this.userId = userId;
  }

  /**
   * Compares this address input DTO to another object.
   * The result is true if the fields of both objects are equal.
   *
   * @param o the object to compare this DTO against.
   * @return true if the given object represents an AddressInDTO equivalent to this DTO, false otherwise.
   */
  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AddressInDTO that = (AddressInDTO) o;
    return Objects.equals(street, that.street)
      && Objects.equals(city, that.city)
      && Objects.equals(state, that.state)
      && Objects.equals(country, that.country)
      && Objects.equals(pinCode, that.pinCode)
      && Objects.equals(userId, that.userId);
  }

  /**
   * Returns a hash code value for the AddressInDTO.
   *
   * @return a hash code value for this DTO.
   */
  @Override
  public int hashCode() {
    return Objects.hash(street, city, state, country, pinCode, userId);
  }

  /**
   * Returns a string representation of the AddressInDTO.
   *
   * @return a string representation of the address input DTO.
   */
  @Override
  public String toString() {
    return "AddressInDTO{"
      + "street='" + street
      + '\'' + ", city='" + city
      + '\'' + ", state='" + state
      + '\'' + ", country='" + country
      + '\'' + ", pinCode='" + pinCode
      + '\'' + ", userId=" + userId
      + '}';
  }
}
