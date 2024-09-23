package com.nt.order.microservice.exception;

/**
 * Custom exception thrown when a requested resource is not found.
 * This exception is typically used when a specific entity or data cannot be found in the system.
 */
public class ResourceNotFoundException extends RuntimeException {

  /**
   * Constructs a new ResourceNotFoundException with the specified detail message.
   *
   * @param message the detail message, saved for later retrieval by the {@link Throwable#getMessage()} method.
   */
  public ResourceNotFoundException(String message) {
    super(message);
  }
}