package com.nt.user.microservice.dto;

import java.util.Objects;

/**
 * Data Transfer Object (DTO) for transferring address details in API responses.
 * This class encapsulates information such as street, city, state, country, and pin code.
 */
public class AddressOutDTO {

  /**
   * The unique identifier for the address.
   */
  private Integer id;

  /**
   * The street name of the address.
   */
  private String street;

  /**
   * The city where the address is located.
   */
  private String city;

  /**
   * The state or province where the address is located.
   */
  private String state;

  /**
   * The country where the address is located.
   */
  private String country;

  /**
   * The postal pin code for the address.
   */
  private String pinCode;

  /**
   * Retrieves the unique identifier for the address.
   *
   * @return the address ID
   */
  public Integer getId() {
    return id;
  }

  /**
   * Sets the unique identifier for the address.
   *
   * @param id the address ID to set
   */
  public void setId(Integer id) {
    this.id = id;
  }

  /**
   * Retrieves the street name of the address.
   *
   * @return the street name
   */
  public String getStreet() {
    return street;
  }

  /**
   * Sets the street name of the address.
   *
   * @param street the street name to set
   */
  public void setStreet(String street) {
    this.street = street;
  }

  /**
   * Retrieves the city where the address is located.
   *
   * @return the city name
   */
  public String getCity() {
    return city;
  }

  /**
   * Sets the city where the address is located.
   *
   * @param city the city name to set
   */
  public void setCity(String city) {
    this.city = city;
  }

  /**
   * Retrieves the state or province where the address is located.
   *
   * @return the state or province name
   */
  public String getState() {
    return state;
  }

  /**
   * Sets the state or province where the address is located.
   *
   * @param state the state name to set
   */
  public void setState(String state) {
    this.state = state;
  }

  /**
   * Retrieves the country where the address is located.
   *
   * @return the country name
   */
  public String getCountry() {
    return country;
  }

  /**
   * Sets the country where the address is located.
   *
   * @param country the country name to set
   */
  public void setCountry(String country) {
    this.country = country;
  }

  /**
   * Retrieves the postal pin code for the address.
   *
   * @return the postal pin code
   */
  public String getPinCode() {
    return pinCode;
  }

  /**
   * Sets the postal pin code for the address.
   *
   * @param pinCode the postal pin code to set
   */
  public void setPinCode(String pinCode) {
    this.pinCode = pinCode;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true; }
    if (!(o instanceof AddressOutDTO)) {
      return false; }
    AddressOutDTO that = (AddressOutDTO) o;
    return Objects.equals(id, that.id) && Objects.equals(street, that.street) &&
      Objects.equals(city, that.city) && Objects.equals(state, that.state) &&
      Objects.equals(country, that.country) && Objects.equals(pinCode, that.pinCode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, street, city, state, country, pinCode);
  }

  @Override
  public String toString() {
    return "AddressOutDTO{" +
      "id=" + id +
      ", street='" + street + '\'' +
      ", city='" + city + '\'' +
      ", state='" + state + '\'' +
      ", country='" + country + '\'' +
      ", pinCode='" + pinCode + '\'' +
      '}';
  }
}
