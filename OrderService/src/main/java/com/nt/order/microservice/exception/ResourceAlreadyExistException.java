package com.nt.order.microservice.exception;

/**
 * Custom exception thrown when attempting to create a resource that already exists.
 * This is commonly used for scenarios like duplicate entries in the database.
 */
public class ResourceAlreadyExistException extends RuntimeException {

  /**
   * Constructs a new ResourceAlreadyExistException with the specified detail message.
   *
   * @param message the detail message, saved for later retrieval by the {@link Throwable#getMessage()} method.
   */
  public ResourceAlreadyExistException(String message) {
    super(message);
  }
}