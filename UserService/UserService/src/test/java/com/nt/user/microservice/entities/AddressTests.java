package com.nt.user.microservice.entities;

import com.nt.user.microservice.entites.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

  @Test
  public void testEqualsAndHashCode() {
    Address address1 = new Address();
    address1.setId(1);
    address1.setStreet("123 Main St");
    address1.setCity("Springfield");
    address1.setCountry("USA");
    address1.setState("Illinois");
    address1.setPinCode("62704");
    address1.setUserId(10);

    Address address2 = new Address();
    address2.setId(1);
    address2.setStreet("123 Main St");
    address2.setCity("Springfield");
    address2.setCountry("USA");
    address2.setState("Illinois");
    address2.setPinCode("62704");
    address2.setUserId(10);

    Address address3 = new Address();
    address3.setId(2);
    address3.setStreet("456 Elm St");
    address3.setCity("Springfield");
    address3.setCountry("USA");
    address3.setState("Illinois");
    address3.setPinCode("62705");
    address3.setUserId(20);

    assertTrue(address1.equals(address2));
    assertFalse(address1.equals(address3));
    assertFalse(address2.equals(address3));

    assertEquals(address1.hashCode(), address2.hashCode());
    assertNotEquals(address1.hashCode(), address3.hashCode());
  }

  @Test
  public void testToString() {
    address.setId(1);
    address.setStreet("123 Main St");
    address.setCity("Springfield");
    address.setCountry("USA");
    address.setState("Illinois");
    address.setPinCode("62704");
    address.setUserId(10);

    String expectedString = "Address{id=1, street='123 Main St', city='Springfield', " +
      "country='USA', state='Illinois', pinCode='62704', userId=10}";
    assertEquals(expectedString, address.toString());
  }
}
