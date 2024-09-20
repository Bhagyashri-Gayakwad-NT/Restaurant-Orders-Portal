package com.nt.user.microservice.entities;

import com.nt.user.microservice.entites.User;
import com.nt.user.microservice.util.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTests {

  private User user1;
  private User user2;

  @BeforeEach
  void setUp() {
    user1 = new User();
    user1.setId(1);
    user1.setFirstName("TestFirstName");
    user1.setLastName("TestLastName");
    user1.setEmail("test.email@example.com");
    user1.setPassword("testPassword");
    user1.setPhoneNo("1234567890");
    user1.setRole(Role.USER);

    user2 = new User();
    user2.setId(1);
    user2.setFirstName("TestFirstName");
    user2.setLastName("TestLastName");
    user2.setEmail("test.email@example.com");
    user2.setPassword("testPassword");
    user2.setPhoneNo("1234567890");
    user2.setRole(Role.USER);
  }

  @Test
  void testGettersAndSetters() {
    assertEquals(1, user1.getId());
    assertEquals("TestFirstName", user1.getFirstName());
    assertEquals("TestLastName", user1.getLastName());
    assertEquals("test.email@example.com", user1.getEmail());
    assertEquals("testPassword", user1.getPassword());
    assertEquals("1234567890", user1.getPhoneNo());
    assertEquals(Role.USER, user1.getRole());
  }

  @Test
  void testEqualsAndHashCode() {
    assertEquals(user1, user2);
    assertEquals(user1.hashCode(), user2.hashCode());
  }

  @Test
  void testNotEquals() {
    user2.setEmail("different.email@example.com");
    assertNotEquals(user1, user2);
  }

  @Test
  void testToString() {
    String expected = "User{id=1, firstName='TestFirstName', lastName='TestLastName', " +
      "email='test.email@example.com', password='testPassword', phoneNo='1234567890', role=USER}";
    assertEquals(expected, user1.toString());
  }
}
