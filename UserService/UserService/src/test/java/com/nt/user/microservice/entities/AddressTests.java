package com.nt.user.microservice.entities;

import com.nt.user.microservice.entites.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AddressTests {

  private Address address1;
  private Address address2;

  @BeforeEach
  void setUp() {
    address1 = new Address();
    address1.setId(1);
    address1.setStreet("123 Test St");
    address1.setCity("Test City");
    address1.setCountry("Test Country");
    address1.setState("Test State");
    address1.setPinCode("12345");
    address1.setUserId(1);

    address2 = new Address();
    address2.setId(1);
    address2.setStreet("123 Test St");
    address2.setCity("Test City");
    address2.setCountry("Test Country");
    address2.setState("Test State");
    address2.setPinCode("12345");
    address2.setUserId(1);
  }

  @Test
  void testGettersAndSetters() {
    assertEquals(1, address1.getId());
    assertEquals("123 Test St", address1.getStreet());
    assertEquals("Test City", address1.getCity());
    assertEquals("Test Country", address1.getCountry());
    assertEquals("Test State", address1.getState());
    assertEquals("12345", address1.getPinCode());
    assertEquals(1, address1.getUserId());
  }

  @Test
  void testEqualsAndHashCode() {
    assertEquals(address1, address2);
    assertEquals(address1.hashCode(), address2.hashCode());
  }

  @Test
  void testNotEquals() {
    address2.setStreet("456 Different St");
    assertNotEquals(address1, address2);
  }

  @Test
  void testToString() {
    String expected = "Address{id=1, street='123 Test St', city='Test City', " +
      "country='Test Country', state='Test State', pinCode='12345', userId=1}";
    assertEquals(expected, address1.toString());
  }
}
