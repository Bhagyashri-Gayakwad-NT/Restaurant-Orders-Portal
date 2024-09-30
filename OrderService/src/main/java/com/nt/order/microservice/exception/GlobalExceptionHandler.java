package com.nt.order.microservice.exception;

import com.nt.order.microservice.util.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Global Exception Handler for managing exceptions thrown in the application.
 * This class handles various exceptions and provides standardized error responses.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

  /**
   * Handles validation exceptions when method arguments are not valid.
   *
   * @param ex the validation exception
   * @return ResponseEntity with error details
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleValidationExceptions(final MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();

    for (FieldError error : ex.getBindingResult().getFieldErrors()) {
      String fieldName = error.getField().contains("[") ? error.getField().substring(error.getField().indexOf("]") + 2)
        : error.getField();
      errors.put(fieldName, error.getDefaultMessage());
    }
    ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Validation failed", errors);
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  /**
   * Handles exceptions thrown when an unsupported HTTP method is used.
   *
   * @param ex the method not supported exception
   * @return ResponseEntity with error details
   */
  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public ResponseEntity<ErrorResponse> handleMethodNotSupportedException(final HttpRequestMethodNotSupportedException ex) {
    String errorMessage = Constants.METHODE_NOT_ALLOWED;
    ErrorResponse errorResponse = new ErrorResponse(HttpStatus.METHOD_NOT_ALLOWED.value(), errorMessage);
    return new ResponseEntity<>(errorResponse, HttpStatus.METHOD_NOT_ALLOWED);
  }

  /**
   * Handles custom invalid request exceptions.
   *
   * @param ex the invalid request exception
   * @return ResponseEntity with error details
   */
  @ExceptionHandler(InvalidRequestException.class)
  public ResponseEntity<ErrorResponse> handleInvalidRequestException(final InvalidRequestException ex) {
    ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  /**
   * Handles resource not found exceptions.
   *
   * @param ex the resource not found exception
   * @return ResponseEntity with error details
   */
  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleUserNotFoundException(final ResourceNotFoundException ex) {
    ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
  }

  /**
   * Handles unauthorized access exceptions.
   *
   * @param ex the unauthorized exception
   * @return ResponseEntity with error details
   */
  @ExceptionHandler(UnauthorizedException.class)
  public ResponseEntity<ErrorResponse> handleUnauthorizedException(final UnauthorizedException ex) {
    ErrorResponse errorResponse = new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
    return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
  }

  /**
   * Handles exceptions thrown when a resource already exists.
   *
   * @param ex the resource already exists exception
   * @return ResponseEntity with error details
   */
  @ExceptionHandler(ResourceAlreadyExistException.class)
  public ResponseEntity<ErrorResponse> handleAlreadyExistException(final ResourceAlreadyExistException ex) {
    ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
    return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
  }

  /**
   * Handles insufficient balance exceptions.
   *
   * @param ex the insufficient balance exception
   * @return ResponseEntity with error details
   */
  @ExceptionHandler(InsufficientBalanceException.class)
  public ResponseEntity<ErrorResponse> handleInsufficientBalanceException(final InsufficientBalanceException ex) {
    String errorMessage = Constants.INSUFFICIENT_BALANCE;
    ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), errorMessage);
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  /**
   * Handles exceptions related to unreadable HTTP messages.
   *
   * @param ex      the HTTP message not readable exception
   * @param request the web request
   * @return ResponseEntity with error details
   */
  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<Object> handleHttpMessageNotReadableException(
    final HttpMessageNotReadableException ex, final WebRequest request) {
    Map<String, Object> body = new HashMap<>();
    body.put("status", HttpStatus.BAD_REQUEST.value());
    body.put("error", "Invalid request body");
    body.put("message", "No content was provided in the request body or the format is invalid");

    return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
  }

}
