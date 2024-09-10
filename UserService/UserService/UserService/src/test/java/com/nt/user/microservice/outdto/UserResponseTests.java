package com.nt.user.microservice.outdto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserResponseTests {

  @Test
  public void testGettersAndSetters() {
    // Arrange
    UserResponse userResponse = new UserResponse();
    String expectedMessage = "Operation successful";

    // Act
    userResponse.setSuccessMessage(expectedMessage);

    // Assert
    assertEquals(expectedMessage, userResponse.getSuccessMessage());
  }

  @Test
  public void testSettersAndGettersWithDifferentValues() {
    // Arrange
    UserResponse userResponse = new UserResponse();
    String message = "User registered successfully";

    // Act
    userResponse.setSuccessMessage(message);

    // Assert
    assertEquals(message, userResponse.getSuccessMessage());
  }
}