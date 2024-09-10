package com.nt.user.microservice.exceptions;

/**
 * Custom exception to be thrown when a user already exists in the system.
 * Extends {@link RuntimeException} to indicate an exceptional condition
 * that a method should handle.
 */
public class UserAlreadyExistsException extends RuntimeException {

  /**
   * Constructs a new UserAlreadyExistsException with the specified detail message.
   *
   * @param message the detail message which is saved for later retrieval by the {@link #getMessage()} method
   */
  public UserAlreadyExistsException(String message) {
    super(message);
  }
}
