package com.nt.user.microservice.exceptions;

/**
 * Custom exception to be thrown when invalid credentials are provided.
 * Extends {@link RuntimeException} to indicate an exceptional condition
 * that a method should handle.
 */
public class InvalidCredentialsException extends RuntimeException {

  /**
   * Constructs a new InvalidCredentialsException with the specified detail message.
   *
   * @param message the detail message which is saved for later retrieval by the {@link #getMessage()} method
   */
  public InvalidCredentialsException(final String message) {
    super(message);
  }
}
