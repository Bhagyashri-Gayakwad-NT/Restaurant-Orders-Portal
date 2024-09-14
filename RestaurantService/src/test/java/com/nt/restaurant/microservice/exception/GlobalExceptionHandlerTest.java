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
  void testHandleAlreadyExistsException() {
    AlreadyExistException ex = new AlreadyExistException("Entity already exists");
    ResponseEntity<ErrorResponse> responseEntity = globalExceptionHandler.handleAlreadyExistsException(ex);
    assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
    assertEquals("Entity already exists", responseEntity.getBody().getMessage());
  }

  @Test
  void testHandleNotFoundException() {
    NotFoundException ex = new NotFoundException("Entity not found");
    ResponseEntity<ErrorResponse> responseEntity = globalExceptionHandler.handleNotFoundException(ex);
    assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    assertEquals("Entity not found", responseEntity.getBody().getMessage());
  }

  @Test
  void testHandleInvalidImageFileException() {
    InvalidImageFileException ex = new InvalidImageFileException("Invalid image file");
    ResponseEntity<ErrorResponse> responseEntity = globalExceptionHandler.handleInvalidImageFileException(ex);
    assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    assertEquals("Invalid image file", responseEntity.getBody().getMessage());
  }
}
