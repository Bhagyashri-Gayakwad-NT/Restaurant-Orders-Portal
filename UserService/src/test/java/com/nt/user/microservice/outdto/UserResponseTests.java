package com.nt.user.microservice.outdto;

import com.nt.user.microservice.dto.UserResponse;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserResponseTests {

  @Test
  public void testGettersAndSetters() {
    UserResponse userResponse = new UserResponse();
    String expectedMessage = "Operation successful";

    userResponse.setSuccessMessage(expectedMessage);

    assertEquals(expectedMessage, userResponse.getSuccessMessage());
  }

  @Test
  public void testSettersAndGettersWithDifferentValues() {

    UserResponse userResponse = new UserResponse();
    String message = "User registered successfully";

    userResponse.setSuccessMessage(message);

    assertEquals(message, userResponse.getSuccessMessage());
  }
}