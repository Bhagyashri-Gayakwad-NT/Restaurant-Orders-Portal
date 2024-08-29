package com.nt.user.microservice.entities;

import com.nt.user.microservice.entites.User;
import com.nt.user.microservice.util.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserTests {
  private User user;

  @BeforeEach
  void setUp() {
    user = new User();
    user.setId(1);
    user.setFirstName("Sarita");
    user.setLastName("Sharma");
    user.setEmail("sarita.sharma@example.com");
    user.setPassword("Secret@123");
    user.setPhoneNo("8765432109");
    user.setRole(Role.USER);
  }

  @Test
  void testGettersSetters() {
    assertEquals(1, user.getId());
    assertEquals("Sarita", user.getFirstName());
    assertEquals("Sharma", user.getLastName());
    assertEquals("sarita.sharma@example.com", user.getEmail());
    assertEquals("Secret@123", user.getPassword());
    assertEquals("8765432109", user.getPhoneNo());
    assertEquals(Role.USER, user.getRole());
  }

  @Test
  void testEquals() {
    User anotherUser = new User();
    anotherUser.setId(1);
    anotherUser.setFirstName("Sarita");
    anotherUser.setLastName("Sharma");
    anotherUser.setEmail("sarita.sharma@example.com");
    anotherUser.setPassword("Secret@123");
    anotherUser.setPhoneNo("8765432109");
    anotherUser.setRole(Role.USER);

    assertTrue(user.equals(anotherUser));
  }

  @Test
  void testHashCode() {
    User anotherUser = new User();
    anotherUser.setId(1);
    anotherUser.setFirstName("Sarita");
    anotherUser.setLastName("Sharma");
    anotherUser.setEmail("sarita.sharma@example.com");
    anotherUser.setPassword("Secret@123");
    anotherUser.setPhoneNo("8765432109");
    anotherUser.setRole(Role.USER);

    assertEquals(user.hashCode(), anotherUser.hashCode());
  }

  @Test
  void testToString() {
    String expected = "User{id=1, firstName='Sarita', lastName='Sharma', email='sarita.sharma@example.com', password='Secret@123', phoneNo='8765432109', role=USER}";
    assertEquals(expected, user.toString());
  }
}

