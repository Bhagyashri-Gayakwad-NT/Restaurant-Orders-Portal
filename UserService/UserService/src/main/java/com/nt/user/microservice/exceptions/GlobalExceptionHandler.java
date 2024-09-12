package com.nt.user.microservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * GlobalExceptionHandler handles all exceptions across the application.
 * It provides specific handling for custom exceptions as well as built-in validation and method exceptions.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

  /**
   * Handles UserAlreadyExistsException, which occurs when a user tries to register with an email that is already taken.
   *
   * @param ex the exception to handle
   * @return a ResponseEntity containing the error message and HTTP status CONFLICT (409)
   */
  @ExceptionHandler(UserAlreadyExistsException.class)
  public ResponseEntity<ErrorResponse> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
    ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
    return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
  }

  /**
   * Handles UserNotFoundException, which occurs when a user is not found in the database.
   *
   * @param ex the exception to handle
   * @return a ResponseEntity containing the error message and HTTP status NOT FOUND (404)
   */
  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException ex) {
    ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
  }

  /**
   * Handles InvalidCredentialsException, which occurs when the user's login credentials are invalid.
   *
   * @param ex the exception to handle
   * @return a ResponseEntity containing the error message and HTTP status UNAUTHORIZED (401)
   */
  @ExceptionHandler(InvalidCredentialsException.class)
  public ResponseEntity<ErrorResponse> handleInvalidCredentialsException(InvalidCredentialsException ex) {
    ErrorResponse errorResponse = new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
    return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
  }

  /**
   * Handles MethodArgumentNotValidException, which occurs when validation on a method argument fails.
   * Returns detailed field-specific error messages.
   *
   * @param ex the exception to handle
   * @return a ResponseEntity containing validation errors and HTTP status BAD REQUEST (400)
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    for (FieldError error : ex.getBindingResult().getFieldErrors()) {
      errors.put(error.getField(), error.getDefaultMessage());
    }
    ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Validation failed", errors);
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  /**
   * Handles IllegalArgumentException, which occurs when an illegal argument is passed to a method.
   *
   * @param ex the exception to handle
   * @return a ResponseEntity containing the error message and HTTP status BAD REQUEST (400)
   */
  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
    ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  /**
   * Handles HttpRequestMethodNotSupportedException, which occurs when a request is made with an unsupported HTTP method.
   *
   * @param ex the exception to handle
   * @return a ResponseEntity containing the error message and HTTP status METHOD NOT ALLOWED (405)
   */
  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public ResponseEntity<ErrorResponse> handleMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
    String errorMessage = "Method not allowed";
    ErrorResponse errorResponse = new ErrorResponse(HttpStatus.METHOD_NOT_ALLOWED.value(), errorMessage);
    return new ResponseEntity<>(errorResponse, HttpStatus.METHOD_NOT_ALLOWED);
  }

  /**
   * Handles all other exceptions that are not explicitly handled by the above exception handlers.
   *
   * @param ex the exception to handle
   * @return a ResponseEntity containing a generic error message and HTTP status INTERNAL SERVER ERROR (500)
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex) {
    ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An unexpected error occurred");
    return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
