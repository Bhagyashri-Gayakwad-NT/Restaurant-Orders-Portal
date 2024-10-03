package com.nt.user.microservice.outdto;

import com.nt.user.microservice.exceptions.ErrorResponse;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ErrorResponseTest {

  @Test
  void testNoArgsConstructor() {
    ErrorResponse errorResponse = new ErrorResponse();
    assertNotNull(errorResponse.getErrors(), "Errors map should not be null");
    assertEquals(0, errorResponse.getErrors().size(), "Errors map should be empty");
  }

  @Test
  void testConstructorWithStatusAndMessage() {
    ErrorResponse errorResponse = new ErrorResponse(404, "Not Found");
    assertEquals(404, errorResponse.getStatus(), "Status should be 404");
    assertEquals("Not Found", errorResponse.getMessage(), "Message should be 'Not Found'");
    assertNotNull(errorResponse.getErrors(), "Errors map should not be null");
    assertEquals(0, errorResponse.getErrors().size(), "Errors map should be empty");
  }

  @Test
  void testConstructorWithStatusMessageAndErrors() {
    Map<String, String> errors = new HashMap<>();
    errors.put("field1", "Error 1");
    errors.put("field2", "Error 2");

    ErrorResponse errorResponse = new ErrorResponse(400, "Validation Failed", errors);

    assertEquals(400, errorResponse.getStatus(), "Status should be 400");
    assertEquals("Validation Failed", errorResponse.getMessage(), "Message should be 'Validation Failed'");
    assertEquals(2, errorResponse.getErrors().size(), "Errors map should contain 2 entries");
    assertEquals("Error 1", errorResponse.getErrors().get("field1"), "Error for 'field1' should be 'Error 1'");
    assertEquals("Error 2", errorResponse.getErrors().get("field2"), "Error for 'field2' should be 'Error 2'");
  }

  @Test
  void testConstructorWithNullErrorsMap() {
    ErrorResponse errorResponse = new ErrorResponse(400, "Validation Failed", null);
    assertEquals(400, errorResponse.getStatus(), "Status should be 400");
    assertEquals("Validation Failed", errorResponse.getMessage(), "Message should be 'Validation Failed'");
    assertNotNull(errorResponse.getErrors(), "Errors map should not be null");
    assertEquals(0, errorResponse.getErrors().size(), "Errors map should be empty");
  }

  @Test
  void testSetAndGetStatus() {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setStatus(500);
    assertEquals(500, errorResponse.getStatus(), "Status should be 500");
  }

  @Test
  void testSetAndGetMessage() {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setMessage("Internal Server Error");
    assertEquals("Internal Server Error", errorResponse.getMessage(), "Message should be 'Internal Server Error'");
  }

  @Test
  void testSetAndGetErrors() {
    Map<String, String> errors = new HashMap<>();
    errors.put("field1", "Error 1");

    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setErrors(errors);

    assertEquals(1, errorResponse.getErrors().size(), "Errors map should contain 1 entry");
    assertEquals("Error 1", errorResponse.getErrors().get("field1"), "Error for 'field1' should be 'Error 1'");
  }

  @Test
  void testSetErrorsWithNullMap() {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setErrors(null);

    assertNotNull(errorResponse.getErrors(), "Errors map should not be null");
    assertEquals(0, errorResponse.getErrors().size(), "Errors map should be empty");
  }
}
