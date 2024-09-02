package com.nt.user.microservice.entites;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Address {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String street;
  private String city;
  private String country;
  private String state;
  private String pinCode;
  private Integer userId;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

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

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true; }
    if (o == null || getClass() != o.getClass()) {
      return false; }
    Address address = (Address) o;
    return Objects.equals(id, address.id) && Objects.equals(street, address.street) &&
      Objects.equals(city, address.city) && Objects.equals(country, address.country) &&
      Objects.equals(state, address.state) && Objects.equals(pinCode, address.pinCode) &&
      Objects.equals(userId, address.userId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, street, city, country, state, pinCode, userId);
  }

  @Override
  public String toString() {
    return "Address{" +
      "id=" + id +
      ", street='" + street + '\'' +
      ", city='" + city + '\'' +
      ", country='" + country + '\'' +
      ", state='" + state + '\'' +
      ", pinCode='" + pinCode + '\'' +
      ", userId=" + userId +
      '}';
  }
}


