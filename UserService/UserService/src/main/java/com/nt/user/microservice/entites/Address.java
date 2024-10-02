package com.nt.user.microservice.entites;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

/**
 * Represents an address entity for the application.
 * This entity is mapped to the "address" table in the database.
 */
@Entity
public class Address {

  /**
   * The unique identifier for the address.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  /**
   * The street address of the user.
   */
  private String street;

  /**
   * The city where the user resides.
   */
  private String city;

  /**
   * The country where the user resides.
   */
  private String country;

  /**
   * The state where the user resides.
   */
  private String state;

  /**
   * The postal code (ZIP or pin code) for the user's address.
   */
  private String pinCode;

  /**
   * The unique identifier for the user associated with this address.
   */
  private Integer userId;

  /**
   * Gets the unique identifier for the address.
   *
   * @return the address ID.
   */
  public Integer getId() {
    return id;
  }

  /**
   * Sets the unique identifier for the address.
   *
   * @param id the address ID to set.
   */
  public void setId(final Integer id) {
    this.id = id;
  }

  /**
   * Gets the street address of the user.
   *
   * @return the street address.
   */
  public String getStreet() {
    return street;
  }

  /**
   * Sets the street address of the user.
   *
   * @param street the street address to set.
   */
  public void setStreet(final String street) {
    this.street = street;
  }

  /**
   * Gets the city where the user resides.
   *
   * @return the city.
   */
  public String getCity() {
    return city;
  }

  /**
   * Sets the city where the user resides.
   *
   * @param city the city to set.
   */
  public void setCity(final String city) {
    this.city = city;
  }

  /**
   * Gets the country where the user resides.
   *
   * @return the country.
   */
  public String getCountry() {
    return country;
  }

  /**
   * Sets the country where the user resides.
   *
   * @param country the country to set.
   */
  public void setCountry(final String country) {
    this.country = country;
  }

  /**
   * Gets the state where the user resides.
   *
   * @return the state.
   */
  public String getState() {
    return state;
  }

  /**
   * Sets the state where the user resides.
   *
   * @param state the state to set.
   */
  public void setState(final String state) {
    this.state = state;
  }

  /**
   * Gets the postal code (ZIP or pin code) for the user's address.
   *
   * @return the postal code.
   */
  public String getPinCode() {
    return pinCode;
  }

  /**
   * Sets the postal code (ZIP or pin code) for the user's address.
   *
   * @param pinCode the postal code to set.
   */
  public void setPinCode(final String pinCode) {
    this.pinCode = pinCode;
  }

  /**
   * Gets the unique identifier for the user associated with this address.
   *
   * @return the user ID.
   */
  public Integer getUserId() {
    return userId;
  }

  /**
   * Sets the unique identifier for the user associated with this address.
   *
   * @param userId the user ID to set.
   */
  public void setUserId(final Integer userId) {
    this.userId = userId;
  }

  /**
   * Compares this address to the specified object. The result is true if and only if
   * the argument is not null and is an Address object that has the same values for all fields.
   *
   * @param o the object to compare this address against.
   * @return true if the given object represents an Address equivalent to this address, false otherwise.
   */
  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Address address = (Address) o;
    return Objects.equals(id, address.id)
      && Objects.equals(street, address.street)
      && Objects.equals(city, address.city)
      && Objects.equals(country, address.country)
      && Objects.equals(state, address.state)
      && Objects.equals(pinCode, address.pinCode)
      && Objects.equals(userId, address.userId);
  }

  /**
   * Returns a hash code value for the address. This method is supported for the benefit
   * of hash tables such as those provided by HashMap.
   *
   * @return a hash code value for this address.
   */
  @Override
  public int hashCode() {
    return Objects.hash(id, street, city, country, state, pinCode, userId);
  }

  /**
   * Returns a string representation of the address, including the values of all fields.
   *
   * @return a string representation of the address.
   */
  @Override
  public String toString() {
    return "Address{"
      + "id=" + id
      + ", street='" + street
      + '\'' + ", city='" + city
      + '\'' + ", country='" + country
      + '\'' + ", state='" + state
      + '\'' + ", pinCode='" + pinCode
      + '\'' + ", userId=" + userId
      + '}';
  }
}
