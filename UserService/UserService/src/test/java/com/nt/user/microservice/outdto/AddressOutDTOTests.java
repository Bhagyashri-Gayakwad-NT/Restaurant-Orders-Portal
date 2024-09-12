package com.nt.user.microservice.outdto;

import com.nt.user.microservice.dto.AddressOutDTO;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AddressOutDTOTests {

  @Test
  void testGettersAndSetters() {
    AddressOutDTO address = new AddressOutDTO();

    address.setId(1);
    address.setStreet("123 Main St");
    address.setCity("Springfield");
    address.setState("IL");
    address.setCountry("USA");
    address.setPinCode("62701");

    assertEquals(1, address.getId());
    assertEquals("123 Main St", address.getStreet());
    assertEquals("Springfield", address.getCity());
    assertEquals("IL", address.getState());
    assertEquals("USA", address.getCountry());
    assertEquals("62701", address.getPinCode());
  }

  @Test
  void testAddressOutDTOConstructor() {
    AddressOutDTO address = new AddressOutDTO();
    address.setId(1);
    address.setStreet("123 Main St");
    address.setCity("Springfield");
    address.setState("IL");
    address.setCountry("USA");
    address.setPinCode("62701");

    assertNotNull(address.getId());
    assertNotNull(address.getStreet());
    assertNotNull(address.getCity());
    assertNotNull(address.getState());
    assertNotNull(address.getCountry());
    assertNotNull(address.getPinCode());
  }
}
