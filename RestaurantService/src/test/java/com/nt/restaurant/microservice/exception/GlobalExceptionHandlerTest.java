package com.nt.restaurant.microservice.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


class GlobalExceptionHandlerTest {

  @InjectMocks
  private GlobalExceptionHandler globalExceptionHandler;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void testHandleResourceAlreadyExistException() {
    ResourceAlreadyExistException ex = new ResourceAlreadyExistException("Entity already exists");
    ResponseEntity<ErrorResponse> responseEntity = globalExceptionHandler.handleResourceAlreadyExistException(ex);
    assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
    assertEquals("Entity already exists", responseEntity.getBody().getMessage());
  }

  @Test
  void testHandleNotFoundException() {
    ResourceNotFoundException ex = new ResourceNotFoundException("Entity not found");
    ResponseEntity<ErrorResponse> responseEntity = globalExceptionHandler.handleResourceNotFoundException(ex);
    assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    assertEquals("Entity not found", responseEntity.getBody().getMessage());
  }

}
