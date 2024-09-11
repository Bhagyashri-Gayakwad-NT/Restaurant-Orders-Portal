package com.nt.restaurant.microservice.outdto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CommonResponseTest {

  @Test
  void testNoArgsConstructor() {
    // Create an object using the no-args constructor
    CommonResponse response = new CommonResponse();

    // Verify that the message is null by default
    assertNull(response.getMessage(), "Message should be null when no-args constructor is used.");
  }

  @Test
  void testAllArgsConstructor() {
    // Create an object using the constructor with a message
    CommonResponse response = new CommonResponse("Operation successful");

    // Verify that the message is set correctly
    assertEquals("Operation successful", response.getMessage(), "Message should match the passed value.");
  }

  @Test
  void testSetMessage() {
    // Create an object and set a message
    CommonResponse response = new CommonResponse();
    response.setMessage("Error occurred");

    // Verify that the message is updated
    assertEquals("Error occurred", response.getMessage(), "Message should be updated after using setMessage.");
  }

  @Test
  void testGetMessage() {
    // Create an object with an initial message
    CommonResponse response = new CommonResponse("Initial Message");

    // Verify the message
    assertEquals("Initial Message", response.getMessage(), "Message should return the correct value.");
  }

  @Test
  void testMessageMutability() {
    // Create an object with an initial message
    CommonResponse response = new CommonResponse("Initial Message");

    // Update the message using the setter
    response.setMessage("Updated Message");

    // Verify that the message was updated
    assertEquals("Updated Message", response.getMessage(), "Message should be updated after setter call.");
  }
}