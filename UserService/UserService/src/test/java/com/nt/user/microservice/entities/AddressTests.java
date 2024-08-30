package com.nt.user.microservice.entities;

import com.nt.user.microservice.entites.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class AddressTests {
  private Address address;

  @BeforeEach
  public void setUp() {
    address = new Address();
  }

  @Test
  public void testIdGetterAndSetter() {
    Integer id = 1;
    address.setId(id);
    assertEquals(id, address.getId());
  }

  @Test
  public void testStreetGetterAndSetter() {
    String street = "123 Main St";
    address.setStreet(street);
    assertEquals(street, address.getStreet());
  }

  @Test
  public void testCityGetterAndSetter() {
    String city = "Springfield";
    address.setCity(city);
    assertEquals(city, address.getCity());
  }

  @Test
  public void testCountryGetterAndSetter() {
    String country = "USA";
    address.setCountry(country);
    assertEquals(country, address.getCountry());
  }

  @Test
  public void testStateGetterAndSetter() {
    String state = "Illinois";
    address.setState(state);
    assertEquals(state, address.getState());
  }

  @Test
  public void testPinCodeGetterAndSetter() {
    String pinCode = "62704";
    address.setPinCode(pinCode);
    assertEquals(pinCode, address.getPinCode());
  }

  @Test
  public void testUserIdGetterAndSetter() {
    Integer userId = 10;
    address.setUserId(userId);
    assertEquals(userId, address.getUserId());
  }

  @Test
  public void testDefaultValues() {
    assertNull(address.getId());
    assertNull(address.getStreet());
    assertNull(address.getCity());
    assertNull(address.getCountry());
    assertNull(address.getState());
    assertNull(address.getPinCode());
    assertNull(address.getUserId());
  }
}
