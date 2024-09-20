package com.nt.user.microservice.indto;

import com.nt.user.microservice.dto.UserInDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserInDTOTests {

  private UserInDTO userInDTO;

  @BeforeEach
  void setUp() {
    userInDTO = new UserInDTO();
  }

  @Test
  void testGettersAndSetters() {
    // Setting values
    userInDTO.setFirstName("TestFirst");
    userInDTO.setLastName("TestLast");
    userInDTO.setEmail("test@nucleusteq.com");
    userInDTO.setPassword("Password123!");
    userInDTO.setPhoneNo("9876543210");
    userInDTO.setRole("USER");

    // Asserting getters
    assertEquals("TestFirst", userInDTO.getFirstName(), "Expected firstName to be 'TestFirst'");
    assertEquals("TestLast", userInDTO.getLastName(), "Expected lastName to be 'TestLast'");
    assertEquals("test@nucleusteq.com", userInDTO.getEmail(), "Expected email to be 'test@nucleusteq.com'");
    assertEquals("Password123!", userInDTO.getPassword(), "Expected password to be 'Password123!'");
    assertEquals("9876543210", userInDTO.getPhoneNo(), "Expected phone number to be '9876543210'");
    assertEquals("USER", userInDTO.getRole(), "Expected role to be 'USER'");
  }

  @Test
  void testEqualsSameObject() {
    // Asserting that the same object is equal
    assertEquals(userInDTO, userInDTO, "The same object should be equal to itself");
  }

  @Test
  void testEqualsDifferentObjectSameValues() {
    // Create another object with the same values
    UserInDTO anotherDTO = new UserInDTO();
    anotherDTO.setFirstName("TestFirst");
    anotherDTO.setLastName("TestLast");
    anotherDTO.setEmail("test@nucleusteq.com");
    anotherDTO.setPassword("Password123!");
    anotherDTO.setPhoneNo("9876543210");
    anotherDTO.setRole("USER");

    // Set the same values for the original object
    userInDTO.setFirstName("TestFirst");
    userInDTO.setLastName("TestLast");
    userInDTO.setEmail("test@nucleusteq.com");
    userInDTO.setPassword("Password123!");
    userInDTO.setPhoneNo("9876543210");
    userInDTO.setRole("USER");

    // Assert equality between the two objects
    assertEquals(userInDTO, anotherDTO, "Objects with the same values should be equal");
  }

  @Test
  void testEqualsDifferentObjectDifferentValues() {
    // Create another object with different values
    UserInDTO anotherDTO = new UserInDTO();
    anotherDTO.setFirstName("DifferentFirst");
    anotherDTO.setLastName("DifferentLast");
    anotherDTO.setEmail("diff@nucleusteq.com");
    anotherDTO.setPassword("DiffPass123!");
    anotherDTO.setPhoneNo("8765432109");
    anotherDTO.setRole("RESTAURANT_OWNER");

    // Set different values for the original object
    userInDTO.setFirstName("TestFirst");
    userInDTO.setLastName("TestLast");
    userInDTO.setEmail("test@nucleusteq.com");
    userInDTO.setPassword("Password123!");
    userInDTO.setPhoneNo("9876543210");
    userInDTO.setRole("USER");

    // Assert that the objects are not equal
    assertNotEquals(userInDTO, anotherDTO, "Objects with different values should not be equal");
  }

  @Test
  void testEqualsNull() {
    // Asserting that the object is not equal to null
    assertNotEquals(userInDTO, null, "Object should not be equal to null");
  }

  @Test
  void testHashCodeSameValues() {
    // Create another object with the same values
    UserInDTO anotherDTO = new UserInDTO();
    anotherDTO.setFirstName("TestFirst");
    anotherDTO.setLastName("TestLast");
    anotherDTO.setEmail("test@nucleusteq.com");
    anotherDTO.setPassword("Password123!");
    anotherDTO.setPhoneNo("9876543210");
    anotherDTO.setRole("USER");

    // Set the same values for the original object
    userInDTO.setFirstName("TestFirst");
    userInDTO.setLastName("TestLast");
    userInDTO.setEmail("test@nucleusteq.com");
    userInDTO.setPassword("Password123!");
    userInDTO.setPhoneNo("9876543210");
    userInDTO.setRole("USER");

    // Asserting that the hash codes are the same for the same values
    assertEquals(userInDTO.hashCode(), anotherDTO.hashCode(), "Hash codes should match for same values");
  }

  @Test
  void testHashCodeDifferentValues() {
    // Create another object with different values
    UserInDTO anotherDTO = new UserInDTO();
    anotherDTO.setFirstName("DifferentFirst");
    anotherDTO.setLastName("DifferentLast");
    anotherDTO.setEmail("diff@nucleusteq.com");
    anotherDTO.setPassword("DiffPass123!");
    anotherDTO.setPhoneNo("8765432109");
    anotherDTO.setRole("RESTAURANT_OWNER");

    // Set different values for the original object
    userInDTO.setFirstName("TestFirst");
    userInDTO.setLastName("TestLast");
    userInDTO.setEmail("test@nucleusteq.com");
    userInDTO.setPassword("Password123!");
    userInDTO.setPhoneNo("9876543210");
    userInDTO.setRole("USER");

    // Assert that the hash codes are different for different values
    assertNotEquals(userInDTO.hashCode(), anotherDTO.hashCode(), "Hash codes should differ for different values");
  }

  @Test
  void testToString() {
    userInDTO.setFirstName("TestFirst");
    userInDTO.setLastName("TestLast");
    userInDTO.setEmail("test@nucleusteq.com");
    userInDTO.setPassword("Password123!");
    userInDTO.setPhoneNo("9876543210");
    userInDTO.setRole("USER");

    String expectedString = "UserInDTO{firstName='TestFirst', lastName='TestLast', " +
      "email='test@nucleusteq.com', password='Password123!', phoneNo='9876543210', role='USER'}";

    assertEquals(expectedString, userInDTO.toString(), "Expected toString output to match the format");
  }
}
