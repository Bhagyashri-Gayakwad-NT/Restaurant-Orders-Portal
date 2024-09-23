package com.nt.user.microservice.exceptions;

/**
 * Custom exception thrown when an invalid request is made to the application, typically due to
 * malformed data or missing required fields.
 */
public class InvalidRequestException extends RuntimeException {

  /**
   * Constructs a new InvalidRequestException with the specified detail message.
   *
   * @param message the detail message, saved for later retrieval by the {@link Throwable#getMessage()} method.
   */
  public InvalidRequestException(String message) {
    super(message);
  }
}
