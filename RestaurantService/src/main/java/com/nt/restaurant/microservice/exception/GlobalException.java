package com.nt.restaurant.microservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalException {
//  @ExceptionHandler(MethodArgumentNotValidException.class)
//  @ResponseStatus(HttpStatus.BAD_REQUEST)
//  @ResponseBody
//  public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
//    Map<String, String> errors = new HashMap<>();
//    ex.getBindingResult().getAllErrors().forEach(error -> {
//      String fieldName = ((org.springframework.validation.FieldError) error).getField();
//      String errorMessage = error.getDefaultMessage();
//      errors.put(fieldName, errorMessage);
//    });
//
//    ErrorResponse errorResponse = new ErrorResponse(
//      HttpStatus.BAD_REQUEST.value(),
//      "Validation failed",
//      errors
//    );
//    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
//  }
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

  @ExceptionHandler(AlreadyExistException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  @ResponseBody
  public ResponseEntity<ErrorResponse> handleAlreadyExistsException(AlreadyExistException ex) {
    ErrorResponse errorResponse = new ErrorResponse(
      HttpStatus.CONFLICT.value(),
      ex.getMessage()
    );
    return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
  }

  @ExceptionHandler(NotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ResponseBody
  public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException ex) {
    ErrorResponse errorResponse = new ErrorResponse(
      HttpStatus.NOT_FOUND.value(),
      ex.getMessage()
    );
    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
  }
  @ExceptionHandler(UserNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ResponseBody
  public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException ex) {
    ErrorResponse errorResponse = new ErrorResponse(
      HttpStatus.NOT_FOUND.value(),
      ex.getMessage()
    );
    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
  }
  @ExceptionHandler(UserNotRestaurantOwnerException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ResponseBody
  public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotRestaurantOwnerException ex) {
    ErrorResponse errorResponse = new ErrorResponse(
      HttpStatus.NOT_FOUND.value(),
      ex.getMessage()
    );
    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
  }

//  @ExceptionHandler(Exception.class)
//  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//  @ResponseBody
//  public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex) {
//    ErrorResponse errorResponse = new ErrorResponse(
//      HttpStatus.INTERNAL_SERVER_ERROR.value(),
//      "An unexpected error occurred"
//    );
//    return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
//  }
}
