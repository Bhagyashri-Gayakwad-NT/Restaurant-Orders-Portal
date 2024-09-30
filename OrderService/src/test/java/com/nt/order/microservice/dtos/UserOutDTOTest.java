package com.nt.order.microservice.dtos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class UserOutDTOTest {

  @Test
  void testSetAndGetId() {
    // Given
    UserOutDTO userOutDTO = new UserOutDTO();
    Integer id = 100;

    // When
    userOutDTO.setId(id);

    // Then
    assertEquals(id, userOutDTO.getId());
  }

  @Test
  void testSetAndGetFirstName() {
    // Given
    UserOutDTO userOutDTO = new UserOutDTO();
    String firstName = "First";

    // When
    userOutDTO.setFirstName(firstName);

    // Then
    assertEquals(firstName, userOutDTO.getFirstName());
  }

  @Test
  void testSetAndGetLastName() {
    // Given
    UserOutDTO userOutDTO = new UserOutDTO();
    String lastName = "Last";

    // When
    userOutDTO.setLastName(lastName);

    // Then
    assertEquals(lastName, userOutDTO.getLastName());
  }

  @Test
  void testSetAndGetEmail() {
    // Given
    UserOutDTO userOutDTO = new UserOutDTO();
    String email = "test@domain.com";

    // When
    userOutDTO.setEmail(email);

    // Then
    assertEquals(email, userOutDTO.getEmail());
  }

  @Test
  void testSetAndGetPhoneNo() {
    // Given
    UserOutDTO userOutDTO = new UserOutDTO();
    String phoneNo = "9999999999";

    // When
    userOutDTO.setPhoneNo(phoneNo);

    // Then
    assertEquals(phoneNo, userOutDTO.getPhoneNo());
  }

  @Test
  void testSetAndGetRole() {
    // Given
    UserOutDTO userOutDTO = new UserOutDTO();
    String role = "role";

    // When
    userOutDTO.setRole(role);

    // Then
    assertEquals(role, userOutDTO.getRole());
  }

  @Test
  void testSetAndGetWalletBalance() {
    // Given
    UserOutDTO userOutDTO = new UserOutDTO();
    Double walletBalance = 500.00;

    // When
    userOutDTO.setWalletBalance(walletBalance);

    // Then
    assertEquals(walletBalance, userOutDTO.getWalletBalance());
  }

  @Test
  void testEqualsAndHashCode() {
    // Given
    UserOutDTO userOutDTO1 = buildUserOutDTO(100, "First", "Last", "test@domain.com", "9999999999", "role", 500.00);
    UserOutDTO userOutDTO2 = buildUserOutDTO(100, "First", "Last", "test@domain.com", "9999999999", "role", 500.00);

    // When & Then
    assertEquals(userOutDTO1, userOutDTO2);
    assertEquals(userOutDTO1.hashCode(), userOutDTO2.hashCode());

    // Different objects
    userOutDTO2 = buildUserOutDTO(101, "First", "Last", "test@domain.com", "9999999999", "role", 500.00);
    assertNotEquals(userOutDTO1, userOutDTO2);
    assertNotEquals(userOutDTO1.hashCode(), userOutDTO2.hashCode());
  }

  @Test
  void testToString() {
    // Given
    UserOutDTO userOutDTO = buildUserOutDTO(100, "First", "Last", "test@domain.com", "9999999999", "role", 500.00);

    // When & Then
    assertEquals("UserOutDTO{id=100, firstName='First'," +
        " lastName='Last', email='test@domain.com', phoneNo='9999999999', role='role', walletBalance=500.0}",
      userOutDTO.toString());
  }

  private UserOutDTO buildUserOutDTO(Integer id, String firstName, String lastName,
                                     String email, String phoneNo, String role, Double walletBalance) {
    UserOutDTO userOutDTO = new UserOutDTO();
    userOutDTO.setId(id);
    userOutDTO.setFirstName(firstName);
    userOutDTO.setLastName(lastName);
    userOutDTO.setEmail(email);
    userOutDTO.setPhoneNo(phoneNo);
    userOutDTO.setRole(role);
    userOutDTO.setWalletBalance(walletBalance);
    return userOutDTO;
  }
}
