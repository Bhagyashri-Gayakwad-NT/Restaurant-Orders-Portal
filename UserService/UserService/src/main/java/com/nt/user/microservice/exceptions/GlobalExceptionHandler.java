package com.nt.user.microservice.exceptions;

import com.nt.user.microservice.util.Constants;
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
 * GlobalExceptionHandler handles exceptions across the entire application.
 * It provides specific handling for custom exceptions as well as built-in validation and method exceptions.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

  /**
   * Handles {@link NotFoundException} thrown when a requested resource is not found.
   * <p>
   * This method returns an {@link ErrorResponse} with an HTTP status code of
   * {@code CONFLICT} (409) and the exception message.
   *
   * @param ex the {@link NotFoundException} exception to handle.
   * @return a {@link ResponseEntity} containing the {@link ErrorResponse}
   *         with HTTP status code {@code CONFLICT} (409).
   */
  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException ex) {
    ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
    return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
  }

  /**
   * Handles {@link InvalidCredentialsException} thrown when login credentials are invalid.
   * <p>
   * This method returns an {@link ErrorResponse} with an HTTP status code of
   * {@code UNAUTHORIZED} (401) and the exception message.
   *
   * @param ex the {@link InvalidCredentialsException} exception to handle.
   * @return a {@link ResponseEntity} containing the {@link ErrorResponse}
   *         with HTTP status code {@code UNAUTHORIZED} (401).
   */
  @ExceptionHandler(InvalidCredentialsException.class)
  public ResponseEntity<ErrorResponse> handleInvalidCredentialsException(InvalidCredentialsException ex) {
    ErrorResponse errorResponse = new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
    return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
  }

  /**
   * Handles {@link MethodArgumentNotValidException} which occurs when validation on a method argument fails.
   * <p>
   * This method returns detailed field-specific error messages and an HTTP status code of
   * {@code BAD_REQUEST} (400).
   *
   * @param ex the {@link MethodArgumentNotValidException} exception to handle.
   * @return a {@link ResponseEntity} containing the {@link ErrorResponse}
   *         with HTTP status code {@code BAD_REQUEST} (400) and validation errors.
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
   * Handles {@link HttpRequestMethodNotSupportedException} which occurs when a request is made with an unsupported HTTP method.
   * <p>
   * This method returns an {@link ErrorResponse} with an HTTP status code of
   * {@code METHOD_NOT_ALLOWED} (405) and a generic error message.
   *
   * @param ex the {@link HttpRequestMethodNotSupportedException} exception to handle.
   * @return a {@link ResponseEntity} containing the {@link ErrorResponse}
   *         with HTTP status code {@code METHOD_NOT_ALLOWED} (405).
   */
  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public ResponseEntity<ErrorResponse> handleMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
    String errorMessage = "Method not allowed";
    ErrorResponse errorResponse = new ErrorResponse(HttpStatus.METHOD_NOT_ALLOWED.value(), errorMessage);
    return new ResponseEntity<>(errorResponse, HttpStatus.METHOD_NOT_ALLOWED);
  }

  /**
   * Handles {@link InsufficientBalanceException} which occurs when
   * a user tries to make a transaction without sufficient balance in their wallet.
   * <p>
   * This method returns an {@link ErrorResponse} with an HTTP status code of {@code BAD_REQUEST} (400) and an error message
   * indicating that there is insufficient balance to complete the transaction.
   *
   * @param ex the {@link InsufficientBalanceException} exception to handle.
   * @return a {@link ResponseEntity} containing the {@link ErrorResponse}
   *         with HTTP status code {@code BAD_REQUEST} (400).
   */
  @ExceptionHandler(InsufficientBalanceException.class)
  public ResponseEntity<ErrorResponse> handleInsufficientBalanceException(InsufficientBalanceException ex) {
    String errorMessage = Constants.INSUFFICIENT_BALANCE;
    ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), errorMessage);
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }
  /**
   * Handles all other exceptions that are not explicitly handled by the above exception handlers.
   * <p>
   * This method returns a generic error message and an HTTP status code of
   * {@code INTERNAL_SERVER_ERROR} (500).
   *
   * @param ex the {@link Exception} to handle.
   * @return a {@link ResponseEntity} containing the {@link ErrorResponse}
   *         with HTTP status code {@code INTERNAL_SERVER_ERROR} (500).
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex) {
    ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An unexpected error occurred");
    return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
