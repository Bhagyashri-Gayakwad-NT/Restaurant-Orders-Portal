package com.nt.restaurant.microservice.outdto;

import com.nt.restaurant.microservice.dto.CommonResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CommonResponseTest {

  @Test
  void testNoArgsConstructor() {

    CommonResponse response = new CommonResponse();

    assertNull(response.getMessage(), "Message should be null when no-args constructor is used.");
  }

  @Test
  void testAllArgsConstructor() {

    CommonResponse response = new CommonResponse("Operation successful");

    assertEquals("Operation successful", response.getMessage(), "Message should match the passed value.");
  }

  @Test
  void testSetMessage() {

    CommonResponse response = new CommonResponse();
    response.setMessage("Error occurred");

    assertEquals("Error occurred", response.getMessage(), "Message should be updated after using setMessage.");
  }

  @Test
  void testGetMessage() {

    CommonResponse response = new CommonResponse("Initial Message");

    assertEquals("Initial Message", response.getMessage(), "Message should return the correct value.");
  }

  @Test
  void testMessageMutability() {

    CommonResponse response = new CommonResponse("Initial Message");

    response.setMessage("Updated Message");

    assertEquals("Updated Message", response.getMessage(), "Message should be updated after setter call.");
  }
}
