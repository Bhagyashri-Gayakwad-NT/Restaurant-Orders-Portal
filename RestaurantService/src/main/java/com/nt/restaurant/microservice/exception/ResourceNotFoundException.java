package com.nt.restaurant.microservice.exception;

/**
 * Custom exception class that represents a scenario where an entity or resource
 * is not found in the system. This exception is typically thrown when an entity
 * being searched for does not exist in the database.
 */
public class ResourceNotFoundException extends RuntimeException {

  /**
   * Constructs a new {@code NotFoundException} with the specified detail message.
   *
   * @param message the detail message which explains why the exception was thrown
   */
  public ResourceNotFoundException(String message) {
    super(message);
  }
}
