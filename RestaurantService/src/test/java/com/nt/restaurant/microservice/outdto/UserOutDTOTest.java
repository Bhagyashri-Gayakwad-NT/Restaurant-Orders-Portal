package com.nt.restaurant.microservice.outdto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserOutDTOTest {

  @Test
  void testUserOutDTONotEquals() {
    UserOutDTO dto1 = new UserOutDTO();
    dto1.setId(1);
    dto1.setFirstName("John");
    dto1.setLastName("Doe");
    dto1.setEmail("john.doe@example.com");
    dto1.setPhoneNo("1234567890");
    dto1.setRole("user");
    dto1.setWalletBalance(100.0);

    UserOutDTO dto2 = new UserOutDTO();
    dto2.setId(2);
    dto2.setFirstName("Jane");
    dto2.setLastName("Smith");
    dto2.setEmail("jane.smith@example.com");
    dto2.setPhoneNo("0987654321");
    dto2.setRole("restaurant_owner");
    dto2.setWalletBalance(200.0);

    // Check inequality
    assertNotEquals(dto1, dto2);
  }

//  @Test
//  void testUserOutDTOToString() {
//    UserOutDTO dto = new UserOutDTO();
//    dto.setId(1);
//    dto.setFirstName("John");
//    dto.setLastName("Doe");
//    dto.setEmail("john.doe@example.com");
//    dto.setPhoneNo("1234567890");
//    dto.setRole("user");
//    dto.setWalletBalance(100.0);
//
//    String expected = "UserOutDTO{" +
//      "id=" + 1 +
//      ", firstName='John'" +
//      ", lastName='Doe'" +
//      ", email='john.doe@example.com'" +
//      ", phoneNo='1234567890'" +
//      ", role='user'" +
//      ", walletBalance=" + 100.0 +
//      '}';
//    assertEquals(expected, dto.toString());
//  }

  @Test
  void testUserOutDTOSettersAndGetters() {
    UserOutDTO dto = new UserOutDTO();
    dto.setId(1);
    dto.setFirstName("John");
    dto.setLastName("Doe");
    dto.setEmail("john.doe@example.com");
    dto.setPhoneNo("1234567890");
    dto.setRole("user");
    dto.setWalletBalance(100.0);

    assertEquals(1, dto.getId());
    assertEquals("John", dto.getFirstName());
    assertEquals("Doe", dto.getLastName());
    assertEquals("john.doe@example.com", dto.getEmail());
    assertEquals("1234567890", dto.getPhoneNo());
    assertEquals("user", dto.getRole());
    assertEquals(100.0, dto.getWalletBalance());
  }
}