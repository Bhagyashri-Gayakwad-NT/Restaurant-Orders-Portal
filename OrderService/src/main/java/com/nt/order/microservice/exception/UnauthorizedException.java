package com.nt.order.microservice.exception;

/**
 * Custom exception thrown when a user is unauthorized to perform a specific action.
 * This exception is typically used for access control and authentication failures.
 */
public class UnauthorizedException extends RuntimeException {

  /**
   * Constructs a new UnauthorizedException with the specified detail message.
   *
   * @param message the detail message, saved for later retrieval by the {@link Throwable#getMessage()} method.
   */
  public UnauthorizedException(String message) {
    super(message);
  }
}