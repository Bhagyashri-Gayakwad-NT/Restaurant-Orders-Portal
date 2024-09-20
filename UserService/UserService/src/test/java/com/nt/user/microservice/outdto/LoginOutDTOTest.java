package com.nt.user.microservice.outdto;

import com.nt.user.microservice.dto.LoginOutDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class LoginOutDTOTest {

  private LoginOutDTO loginOutDTO;

  @BeforeEach
  void setUp() {
    loginOutDTO = new LoginOutDTO();
  }

  @Test
  void testGettersAndSetters() {
    loginOutDTO.setId(1);
    loginOutDTO.setRole("USER");

    assertEquals(1, loginOutDTO.getId(), "Expected ID to be 1");
    assertEquals("USER", loginOutDTO.getRole(), "Expected role to be USER");
  }

  @Test
  void testEqualsSameObject() {
    assertEquals(loginOutDTO, loginOutDTO, "Same object should be equal");
  }

  @Test
  void testEqualsDifferentObjectSameValues() {
    LoginOutDTO anotherDTO = new LoginOutDTO();
    anotherDTO.setId(1);
    anotherDTO.setRole("USER");

    loginOutDTO.setId(1);
    loginOutDTO.setRole("USER");

    assertEquals(loginOutDTO, anotherDTO, "Objects with the same values should be equal");
  }

  @Test
  void testEqualsDifferentObjectDifferentValues() {
    LoginOutDTO anotherDTO = new LoginOutDTO();
    anotherDTO.setId(2);
    anotherDTO.setRole("ADMIN");

    loginOutDTO.setId(1);
    loginOutDTO.setRole("USER");

    assertNotEquals(loginOutDTO, anotherDTO, "Objects with different values should not be equal");
  }

  @Test
  void testEqualsNull() {
    assertNotEquals(loginOutDTO, null, "Object should not be equal to null");
  }

  @Test
  void testHashCodeSameValues() {
    LoginOutDTO anotherDTO = new LoginOutDTO();
    anotherDTO.setId(1);
    anotherDTO.setRole("USER");

    loginOutDTO.setId(1);
    loginOutDTO.setRole("USER");

    assertEquals(loginOutDTO.hashCode(), anotherDTO.hashCode(), "Hash codes should be equal for the same values");
  }

  @Test
  void testHashCodeDifferentValues() {
    LoginOutDTO anotherDTO = new LoginOutDTO();
    anotherDTO.setId(2);
    anotherDTO.setRole("ADMIN");

    loginOutDTO.setId(1);
    loginOutDTO.setRole("USER");

    assertNotEquals(loginOutDTO.hashCode(), anotherDTO.hashCode(), "Hash codes should be different for different values");
  }

  @Test
  void testToString() {
    loginOutDTO.setId(1);
    loginOutDTO.setRole("USER");

    String expectedString = "LoginOutDTO{id=1, role='USER'}";
    assertEquals(expectedString, loginOutDTO.toString(), "Expected toString output to match the format");
  }
}
