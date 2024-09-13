package com.nt.restaurant.microservice.exception;

/**
 * Exception thrown when an entity already exists in the system.
 * This exception is typically used in scenarios where a unique constraint
 * (like a restaurant or user already existing) is violated during creation or update operations.
 */
public class AlreadyExistException extends RuntimeException {

  /**
   * Constructs a new AlreadyExistException with the specified detail message.
   *
   * @param message the detail message explaining the reason for the exception
   */
  public AlreadyExistException(String message) {

    super(message);
  }
}
