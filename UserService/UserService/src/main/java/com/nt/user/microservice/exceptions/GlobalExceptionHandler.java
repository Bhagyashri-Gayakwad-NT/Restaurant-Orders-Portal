package com.nt.user.microservice.exceptions;

import com.nt.user.microservice.util.Constants;
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
 * GlobalExceptionHandler handles exceptions across the entire application.
 * It provides specific handling for custom exceptions as well as built-in validation and method exceptions.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

  /**
   * Handles {@link ResourceNotFoundException} thrown when a requested resource is not found.
   * <p>
   * This method returns an {@link ErrorResponse} with an HTTP status code of
   * {@code CONFLICT} (409) and the exception message.
   *
   * @param ex the {@link ResourceNotFoundException} exception to handle.
   * @return a {@link ResponseEntity} containing the {@link ErrorResponse}
   *         with HTTP status code {@code CONFLICT} (409).
   */
  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleNotFoundException(ResourceNotFoundException ex) {
    ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(ResourceAlreadyExistException.class)
  public ResponseEntity<ErrorResponse> AlreadyExistException(ResourceAlreadyExistException ex) {
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
    String errorMessage = Constants.METHODE_NOT_ALLOWED;
    ErrorResponse errorResponse = new ErrorResponse(HttpStatus.METHOD_NOT_ALLOWED.value(), errorMessage);
    return new ResponseEntity<>(errorResponse, HttpStatus.METHOD_NOT_ALLOWED);
  }

  @ExceptionHandler(InvalidRequestException.class)
  public ResponseEntity<ErrorResponse> handleInvalidRequestException(InvalidRequestException ex) {
    ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
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
    ErrorResponse errorResponse = new ErrorResponse(HttpStatus.PAYMENT_REQUIRED.value(), errorMessage);
    return new ResponseEntity<>(errorResponse, HttpStatus.PAYMENT_REQUIRED);
  }

  /**
   * Handles exceptions when the request body is not readable or is invalid.
   *
   * This method catches {@link HttpMessageNotReadableException} and provides a custom response
   * indicating that the request body is invalid or empty.
   *
   * @param ex the exception that was thrown when the request body could not be read
   * @param request the {@link WebRequest} object that contains the details of the current request
   * @return a {@link ResponseEntity} containing a map with error details and HTTP status BAD_REQUEST
   */
  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex, WebRequest request) {
    Map<String, Object> body = new HashMap<>();
    body.put("status", HttpStatus.BAD_REQUEST.value());
    body.put("error", Constants.INVALID_REQUEST_BODY_ERROR);
    body.put("message", Constants.EMPTY_CONTENT_ERROR);
    return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
  }

}
