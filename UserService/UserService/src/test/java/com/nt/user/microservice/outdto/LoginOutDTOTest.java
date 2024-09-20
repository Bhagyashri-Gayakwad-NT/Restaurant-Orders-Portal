package com.nt.user.microservice.outdto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.nt.user.microservice.dto.LoginOutDTO;
import org.junit.jupiter.api.Test;

public class LoginOutDTOTest {

  @Test
  void testGettersAndSetters() {
    // Create an instance of LoginOutDTO
    LoginOutDTO loginOutDTO = new LoginOutDTO();

    // Set values
    loginOutDTO.setId(1);
    loginOutDTO.setRole("USER");

    // Verify the values are set correctly
    assertEquals(1, loginOutDTO.getId(), "ID should be 1");
    assertEquals("USER", loginOutDTO.getRole(), "Role should be 'USER'");
  }

  @Test
  void testSetId() {
    // Create an instance of LoginOutDTO
    LoginOutDTO loginOutDTO = new LoginOutDTO();

    // Set ID
    loginOutDTO.setId(10);

    // Verify ID
    assertEquals(10, loginOutDTO.getId(), "ID should be 10");
  }

  @Test
  void testSetRole() {
    // Create an instance of LoginOutDTO
    LoginOutDTO loginOutDTO = new LoginOutDTO();

    // Set Role
    loginOutDTO.setRole("ADMIN");

    // Verify Role
    assertEquals("ADMIN", loginOutDTO.getRole(), "Role should be 'ADMIN'");
  }
}
