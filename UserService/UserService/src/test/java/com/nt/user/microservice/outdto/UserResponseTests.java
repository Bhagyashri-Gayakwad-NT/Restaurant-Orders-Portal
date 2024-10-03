package com.nt.user.microservice.outdto;

import com.nt.user.microservice.dto.UserResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserResponseTests {

  @Test
  void testGettersAndSetters() {
    UserResponse response = new UserResponse();

    response.setSuccessMessage("Operation successful");

    assertEquals("Operation successful", response.getSuccessMessage());
  }

  @Test
  void testEquals() {
    UserResponse response1 = new UserResponse();
    response1.setSuccessMessage("Operation successful");

    UserResponse response2 = new UserResponse();
    response2.setSuccessMessage("Operation successful");

    UserResponse response3 = new UserResponse();
    response3.setSuccessMessage("Another operation successful");

    assertEquals(response1, response2, "Same messages should be equal.");
    assertNotEquals(response1, response3, "Different messages should not be equal.");
    assertNotEquals(response1, null, "Should not be equal to null.");
    assertNotEquals(response1, new Object(), "Should not be equal to an object of a different class.");
  }

  @Test
  void testHashCode() {
    UserResponse response1 = new UserResponse();
    response1.setSuccessMessage("Operation successful");

    UserResponse response2 = new UserResponse();
    response2.setSuccessMessage("Operation successful");

    UserResponse response3 = new UserResponse();
    response3.setSuccessMessage("Another operation successful");

    assertEquals(response1.hashCode(), response2.hashCode(), "Equal objects should have the same hash code.");
    assertNotEquals(response1.hashCode(), response3.hashCode(), "Different objects should have different hash codes.");
  }

  @Test
  void testToString() {
    UserResponse response = new UserResponse();
    response.setSuccessMessage("Operation successful");

    String expectedString = "UserResponse{successMessage='Operation successful'}";
    assertEquals(expectedString, response.toString(), "toString() should return the correct string representation.");
  }
}
