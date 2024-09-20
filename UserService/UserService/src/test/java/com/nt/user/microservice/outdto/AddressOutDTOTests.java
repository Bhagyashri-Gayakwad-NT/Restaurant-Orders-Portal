package com.nt.user.microservice.outdto;

import com.nt.user.microservice.dto.AddressOutDTO;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AddressOutDTOTests {

  @Test
  void testGettersAndSetters() {
    AddressOutDTO address = new AddressOutDTO();

    address.setId(1);
    address.setStreet("Street Name");
    address.setCity("City Name");
    address.setState("State Name");
    address.setCountry("Country Name");
    address.setPinCode("PinCode");

    assertEquals(1, address.getId());
    assertEquals("Street Name", address.getStreet());
    assertEquals("City Name", address.getCity());
    assertEquals("State Name", address.getState());
    assertEquals("Country Name", address.getCountry());
    assertEquals("PinCode", address.getPinCode());
  }

  @Test
  void testAddressOutDTOConstructor() {
    AddressOutDTO address = new AddressOutDTO();
    address.setId(1);
    address.setStreet("Street Name");
    address.setCity("City Name");
    address.setState("State Name");
    address.setCountry("Country Name");
    address.setPinCode("PinCode");

    assertNotNull(address.getId());
    assertNotNull(address.getStreet());
    assertNotNull(address.getCity());
    assertNotNull(address.getState());
    assertNotNull(address.getCountry());
    assertNotNull(address.getPinCode());
  }

  @Test
  void testEquals() {
    AddressOutDTO address1 = new AddressOutDTO();
    address1.setId(1);
    address1.setStreet("Street Name");
    address1.setCity("City Name");
    address1.setState("State Name");
    address1.setCountry("Country Name");
    address1.setPinCode("PinCode");

    AddressOutDTO address2 = new AddressOutDTO();
    address2.setId(1);
    address2.setStreet("Street Name");
    address2.setCity("City Name");
    address2.setState("State Name");
    address2.setCountry("Country Name");
    address2.setPinCode("PinCode");

    AddressOutDTO address3 = new AddressOutDTO();
    address3.setId(2);
    address3.setStreet("Another Street");
    address3.setCity("Another City");
    address3.setState("Another State");
    address3.setCountry("Another Country");
    address3.setPinCode("Another PinCode");

    assertEquals(address1, address2, "Same attributes should be equal.");
    assertNotEquals(address1, address3, "Different attributes should not be equal.");
    assertNotEquals(address1, null, "Should not be equal to null.");
    assertNotEquals(address1, new Object(), "Should not be equal to an object of a different class.");
  }

  @Test
  void testHashCode() {
    AddressOutDTO address1 = new AddressOutDTO();
    address1.setId(1);
    address1.setStreet("Street Name");
    address1.setCity("City Name");
    address1.setState("State Name");
    address1.setCountry("Country Name");
    address1.setPinCode("PinCode");

    AddressOutDTO address2 = new AddressOutDTO();
    address2.setId(1);
    address2.setStreet("Street Name");
    address2.setCity("City Name");
    address2.setState("State Name");
    address2.setCountry("Country Name");
    address2.setPinCode("PinCode");

    AddressOutDTO address3 = new AddressOutDTO();
    address3.setId(2);
    address3.setStreet("Another Street");
    address3.setCity("Another City");
    address3.setState("Another State");
    address3.setCountry("Another Country");
    address3.setPinCode("Another PinCode");

    assertEquals(address1.hashCode(), address2.hashCode(), "Equal objects should have the same hash code.");
    assertNotEquals(address1.hashCode(), address3.hashCode(), "Different objects should have different hash codes.");
  }

  @Test
  void testToString() {
    AddressOutDTO address = new AddressOutDTO();
    address.setId(1);
    address.setStreet("Street Name");
    address.setCity("City Name");
    address.setState("State Name");
    address.setCountry("Country Name");
    address.setPinCode("PinCode");

    String expectedString = "AddressOutDTO{id=1, street='Street Name', city='City Name', " +
      "state='State Name', country='Country Name', pinCode='PinCode'}";
    assertEquals(expectedString, address.toString(), "toString() should return the correct string representation.");
  }
}
