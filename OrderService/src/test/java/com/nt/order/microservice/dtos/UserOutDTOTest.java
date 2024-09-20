package com.nt.order.microservice.dtos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserOutDTOTest {

  @Test
  void testSetAndGetId() {
    // Given
    UserOutDTO userOutDTO = new UserOutDTO();
    Integer id = 1;

    // When
    userOutDTO.setId(id);

    // Then
    assertEquals(id, userOutDTO.getId());
  }

  @Test
  void testSetAndGetFirstName() {
    // Given
    UserOutDTO userOutDTO = new UserOutDTO();
    String firstName = "John";

    // When
    userOutDTO.setFirstName(firstName);

    // Then
    assertEquals(firstName, userOutDTO.getFirstName());
  }

  @Test
  void testSetAndGetLastName() {
    // Given
    UserOutDTO userOutDTO = new UserOutDTO();
    String lastName = "Doe";

    // When
    userOutDTO.setLastName(lastName);

    // Then
    assertEquals(lastName, userOutDTO.getLastName());
  }

  @Test
  void testSetAndGetEmail() {
    // Given
    UserOutDTO userOutDTO = new UserOutDTO();
    String email = "test@example.com";

    // When
    userOutDTO.setEmail(email);

    // Then
    assertEquals(email, userOutDTO.getEmail());
  }

  @Test
  void testSetAndGetPhoneNo() {
    // Given
    UserOutDTO userOutDTO = new UserOutDTO();
    String phoneNo = "1234567890";

    // When
    userOutDTO.setPhoneNo(phoneNo);

    // Then
    assertEquals(phoneNo, userOutDTO.getPhoneNo());
  }

  @Test
  void testSetAndGetRole() {
    // Given
    UserOutDTO userOutDTO = new UserOutDTO();
    String role = "user";

    // When
    userOutDTO.setRole(role);

    // Then
    assertEquals(role, userOutDTO.getRole());
  }

  @Test
  void testSetAndGetWalletBalance() {
    // Given
    UserOutDTO userOutDTO = new UserOutDTO();
    Double walletBalance = 1000.00;

    // When
    userOutDTO.setWalletBalance(walletBalance);

    // Then
    assertEquals(walletBalance, userOutDTO.getWalletBalance());
  }
}
