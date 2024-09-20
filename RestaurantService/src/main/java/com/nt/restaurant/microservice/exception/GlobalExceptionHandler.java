package com.nt.restaurant.microservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Global exception handler for handling various types of exceptions throughout the application.
 * This class centralizes the handling of validation errors, already existing entity errors,
 * entity not found errors, and other custom exceptions.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

  /**
   * Handles validation exceptions that occur when method arguments are not valid.
   * This method processes both {@link MethodArgumentNotValidException} and {@link BindException}.
   * <p>
   * It aggregates all validation error messages and returns them in an {@link ErrorResponse} object
   * with a {@code 400 Bad Request} status.
   * </p>
   *
   * @param ex the validation exception instance
   * @return an {@link ErrorResponse} object with the aggregated error details and a {@code 400 Bad Request} status
   */

  // For validation exceptions (like @Valid)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    // Extract custom validation messages from FieldError
    ex.getBindingResult().getAllErrors().forEach(error -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();  // This retrieves the message from @Pattern or other annotations
      errors.put(fieldName, errorMessage);
    });
    ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Validation failed", errors);
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(BindException.class)
  public ResponseEntity<ErrorResponse> handleBindException(BindException ex) {
    Map<String, String> errors = new HashMap<>();

    ex.getBindingResult().getAllErrors().forEach(error -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();

      if (ex.getFieldError(fieldName).getRejectedValue() != null) {
        String rejectedValue = ex.getFieldError(fieldName).getRejectedValue().toString();

        // Customize error message for specific fields
        if (fieldName.equals("foodCategoryId")) {
          errorMessage = String.format("Invalid value '%s' for field '%s'. Expected a numeric value.", rejectedValue, fieldName);
        } else if (fieldName.equals("restaurantId")) {
          errorMessage = String.format("Invalid value '%s' for field '%s'. Expected a numeric value.", rejectedValue, fieldName);
        } else if (fieldName.equals("userId")) {
          errorMessage = String.format("Invalid value '%s' for field '%s'. Expected an integer value.", rejectedValue, fieldName);
        } else if (fieldName.equals("price")) {
          errorMessage = String.format("Invalid value '%s' for field '%s'. Expected a decimal value.", rejectedValue, fieldName);
        }
      }
      errors.put(fieldName, errorMessage);
    });

    ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Validation failed", errors);
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }


  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<ErrorResponse> handleTypeMismatchException(MethodArgumentTypeMismatchException ex) {
    String fieldName = ex.getName();
    String requiredType = ex.getRequiredType() != null ? ex.getRequiredType().getSimpleName() : "unknown";
    String providedValue = ex.getValue() != null ? ex.getValue().toString() : "null";
    String errorMessage = String.format("Invalid value '%s' for field '%s'. Expected a value of type %s.", providedValue, fieldName, requiredType);

    Map<String, String> errors = new HashMap<>();
    errors.put(fieldName, errorMessage);

    ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Validation failed", errors);
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }


  /**
   * Handles exceptions thrown when trying to create an entity that already exists.
   * This method catches {@link ResourceAlreadyExistException} and returns an error response.
   * <p>
   * It returns an {@link ErrorResponse} with a {@code 409 Conflict} status.
   * </p>
   *
   * @param ex the exception instance indicating the entity already exists
   * @return a {@link ResponseEntity} containing an {@link ErrorResponse} and a {@code 409 Conflict} status
   */
  @ExceptionHandler(ResourceAlreadyExistException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  @ResponseBody
  public ResponseEntity<ErrorResponse> handleResourceAlreadyExistException(ResourceAlreadyExistException ex) {
    ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
    return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
  }

  @ExceptionHandler(UnauthorizedException.class)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  @ResponseBody
  public ResponseEntity<ErrorResponse> handleUnauthorizedException(UnauthorizedException ex) {
    ErrorResponse errorResponse = new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
    return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
  }
  /**
   * Handles exceptions thrown when an entity is not found.
   * This method catches {@link ResourceNotFoundException} and returns an error response.
   * <p>
   * It returns an {@link ErrorResponse} with a {@code 404 Not Found} status.
   * </p>
   *
   * @param ex the exception instance indicating the entity was not found
   * @return a {@link ResponseEntity} containing an {@link ErrorResponse} and a {@code 404 Not Found} status
   */
  @ExceptionHandler(ResourceNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ResponseBody
  public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
    ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
  }

  /**
   * Handles exceptions related to invalid requests, such as image file validation failures.
   * This method catches {@link InvalidRequestException} and returns an appropriate error response.
   *
   * <p>
   * It generates an {@link ErrorResponse} object and returns it with a {@code 400 Bad Request} HTTP status.
   * </p>
   *
   * @param ex the exception instance indicating the request is invalid
   * @return a {@link ResponseEntity} containing an {@link ErrorResponse} object and a {@code 400 Bad Request} status
   */
  @ExceptionHandler(InvalidRequestException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ResponseEntity<ErrorResponse> handleInvalidRequestException(InvalidRequestException ex) {
    ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
  public ResponseEntity<ErrorResponse> handleMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value());
    errorResponse.setMessage("Unsupported Media Type. Please ensure your request's Content-Type is correct.");
    errorResponse.getErrors().put("Content-Type", "Expected: multipart/form-data");

    return new ResponseEntity<>(errorResponse, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex, WebRequest request) {
    String errorMessage = "Invalid request body or format. Please ensure that the request contains valid data.";
    Map<String, Object> body = new HashMap<>();
    body.put("status", HttpStatus.BAD_REQUEST.value());
    body.put("error", "Invalid request body");
    body.put("message", errorMessage);
    return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
  }


}

