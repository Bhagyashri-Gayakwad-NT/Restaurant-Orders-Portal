package com.nt.order.microservice.dtos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CommonResponseTest {

  @Test
  void testNoArgsConstructor() {
    CommonResponse commonResponse = new CommonResponse();
    assertNull(commonResponse.getMessage(), "Message should be null with no-args constructor");
  }

  @Test
  void testAllArgsConstructor() {
    String testMessage = "Test message";

    // When
    CommonResponse commonResponse = new CommonResponse(testMessage);

    // Then
    assertEquals(testMessage, commonResponse.getMessage(), "Message should be set with all-args constructor");
  }

  @Test
  void testSetAndGetMessage() {
    // Given
    CommonResponse commonResponse = new CommonResponse();
    String testMessage = "Test message";

    // When
    commonResponse.setMessage(testMessage);

    // Then
    assertEquals(testMessage, commonResponse.getMessage(), "Message should match the one set using setMessage");
  }
}

