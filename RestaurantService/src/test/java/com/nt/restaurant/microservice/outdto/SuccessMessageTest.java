package com.nt.restaurant.microservice.outdto;

import com.nt.restaurant.microservice.dto.SuccessMessage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SuccessMessageTest {

  @Test
  void testDefaultConstructor() {

    SuccessMessage successMessage = new SuccessMessage();

    assertNull(successMessage.getMessage(), "Default constructor should initialize message to null");
  }

  @Test
  void testParameterizedConstructor() {

    String expectedMessage = "Operation successful";

    SuccessMessage successMessage = new SuccessMessage(expectedMessage);

    assertEquals(expectedMessage, successMessage.getMessage(), "Constructor should set the correct message");
  }

  @Test
  void testSetMessage() {

    SuccessMessage successMessage = new SuccessMessage();
    String expectedMessage = "Operation successful";

    successMessage.setMessage(expectedMessage);

    assertEquals(expectedMessage, successMessage.getMessage(), "setMessage should set the correct message");
  }

  @Test
  void testGetMessage() {

    SuccessMessage successMessage = new SuccessMessage();
    String expectedMessage = "Operation completed";

    successMessage.setMessage(expectedMessage);

    assertEquals(expectedMessage, successMessage.getMessage(), "getMessage should return the correct message");
  }
}
