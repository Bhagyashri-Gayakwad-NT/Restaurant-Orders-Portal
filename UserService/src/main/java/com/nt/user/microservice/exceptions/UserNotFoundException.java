package com.nt.user.microservice.exceptions;

/**
 * Custom exception to be thrown when a user is not found in the system.
 * Extends {@link RuntimeException} to indicate an exceptional condition
 * that a method should handle.
 */
public class UserNotFoundException extends RuntimeException {

  /**
   * Constructs a new UserNotFoundException with the specified detail message.
   *
   * @param message the detail message which is saved for later retrieval by the {@link #getMessage()} method
   */
  public UserNotFoundException(String message) {
    super(message);
  }
}
