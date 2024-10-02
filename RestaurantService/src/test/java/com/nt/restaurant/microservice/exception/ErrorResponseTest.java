package com.nt.restaurant.microservice.exception;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ErrorResponseTest {

  @Test
  void testConstructorWithStatusAndMessage() {

    int expectedStatus = 404;
    String expectedMessage = "Resource not found";

    ErrorResponse errorResponse = new ErrorResponse(expectedStatus, expectedMessage);

    assertEquals(expectedStatus, errorResponse.getStatus());
    assertEquals(expectedMessage, errorResponse.getMessage());
    assertNotNull(errorResponse.getErrors());
    assertEquals(0, errorResponse.getErrors().size());
  }

  @Test
  void testConstructorWithStatusMessageAndErrors() {
    int expectedStatus = 400;
    String expectedMessage = "Validation failed";
    Map<String, String> expectedErrors = new HashMap<>();
    expectedErrors.put("username", "Username is required");

    ErrorResponse errorResponse = new ErrorResponse(expectedStatus, expectedMessage, expectedErrors);

    assertEquals(expectedStatus, errorResponse.getStatus());
    assertEquals(expectedMessage, errorResponse.getMessage());
    assertEquals(expectedErrors, errorResponse.getErrors());
    assertEquals(1, errorResponse.getErrors().size());
  }

  @Test
  void testConstructorWithNullErrors() {

    int expectedStatus = 500;
    String expectedMessage = "Internal Server Error";

    ErrorResponse errorResponse = new ErrorResponse(expectedStatus, expectedMessage, null);

    assertEquals(expectedStatus, errorResponse.getStatus());
    assertEquals(expectedMessage, errorResponse.getMessage());
    assertNotNull(errorResponse.getErrors());
    assertEquals(0, errorResponse.getErrors().size());
  }

  @Test
  void testDefaultConstructor() {
    ErrorResponse errorResponse = new ErrorResponse();
    assertNotNull(errorResponse.getErrors());
    assertEquals(0, errorResponse.getErrors().size());
  }

  @Test
  void testSettersAndGetters() {
    ErrorResponse errorResponse = new ErrorResponse();
    int expectedStatus = 401;
    String expectedMessage = "Unauthorized access";
    Map<String, String> expectedErrors = new HashMap<>();
    expectedErrors.put("email", "Email is invalid");
    errorResponse.setStatus(expectedStatus);
    errorResponse.setMessage(expectedMessage);
    errorResponse.setErrors(expectedErrors);
    assertEquals(expectedStatus, errorResponse.getStatus());
    assertEquals(expectedMessage, errorResponse.getMessage());
    assertEquals(expectedErrors, errorResponse.getErrors());
  }

  @Test
  void testSetErrorsWithNull() {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setErrors(null);
    assertNotNull(errorResponse.getErrors());
    assertEquals(0, errorResponse.getErrors().size());
  }
}

