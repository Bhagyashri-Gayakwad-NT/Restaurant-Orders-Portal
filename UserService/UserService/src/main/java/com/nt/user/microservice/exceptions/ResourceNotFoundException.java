package com.nt.user.microservice.exceptions;
/**
 * Custom exception class to handle "Not Found" scenarios in the application.
 * <p>
 * This class extends {@link RuntimeException}, allowing it to be thrown
 * when a specific resource or entity is not found in the application.
 */
public class ResourceNotFoundException extends RuntimeException {

  /**
   * Constructs a new {@code NotFoundException} with the specified detail message.
   *
   * @param message the detail message that explains the reason for the exception.
   */
  public ResourceNotFoundException(final String message) {
    super(message);
  }
}
