package com.nt.restaurant.microservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
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
  @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ErrorResponse handleValidationExceptions(Exception ex) {
    List<String> errorMessages = null;

    if (ex instanceof MethodArgumentNotValidException) {
      errorMessages = ((MethodArgumentNotValidException) ex)
        .getBindingResult()
        .getFieldErrors()
        .stream()
        .map(FieldError::getDefaultMessage)
        .collect(Collectors.toList());
    } else if (ex instanceof BindException) {
      errorMessages = ((BindException) ex)
        .getBindingResult()
        .getFieldErrors()
        .stream()
        .map(FieldError::getDefaultMessage)
        .collect(Collectors.toList());
    }

    String errorMessage = errorMessages != null ? String.join(", ", errorMessages) : "Validation error";
    return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), errorMessage);
  }

  /**
   * Handles exceptions thrown when trying to create an entity that already exists.
   * This method catches {@link AlreadyExistException} and returns an error response.
   * <p>
   * It returns an {@link ErrorResponse} with a {@code 409 Conflict} status.
   * </p>
   *
   * @param ex the exception instance indicating the entity already exists
   * @return a {@link ResponseEntity} containing an {@link ErrorResponse} and a {@code 409 Conflict} status
   */
  @ExceptionHandler(AlreadyExistException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  @ResponseBody
  public ResponseEntity<ErrorResponse> handleAlreadyExistsException(AlreadyExistException ex) {
    ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
    return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
  }

  /**
   * Handles exceptions thrown when an entity is not found.
   * This method catches {@link NotFoundException} and returns an error response.
   * <p>
   * It returns an {@link ErrorResponse} with a {@code 404 Not Found} status.
   * </p>
   *
   * @param ex the exception instance indicating the entity was not found
   * @return a {@link ResponseEntity} containing an {@link ErrorResponse} and a {@code 404 Not Found} status
   */
  @ExceptionHandler(NotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ResponseBody
  public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException ex) {
    ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
  }

  /**
   * Handles exceptions related to invalid image files.
   * This method catches {@link InvalidImageFileException} and returns an error response.
   * <p>
   * It returns an {@link ErrorResponse} with a {@code 400 Bad Request} status.
   * </p>
   *
   * @param ex the exception instance indicating the image file is invalid
   * @return a {@link ResponseEntity} containing an {@link ErrorResponse} and a {@code 400 Bad Request} status
   */
  @ExceptionHandler(InvalidImageFileException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ResponseEntity<ErrorResponse> handleInvalidImageFileException(InvalidImageFileException ex) {
    ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }
}

