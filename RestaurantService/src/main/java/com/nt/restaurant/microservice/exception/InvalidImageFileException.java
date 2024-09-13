package com.nt.restaurant.microservice.exception;

/**
 * Exception thrown when an invalid image file is encountered.
 * This custom exception is used to signal that the provided image file
 * does not meet the expected criteria for processing.
 */
public class InvalidImageFileException extends RuntimeException {

  /**
   * Constructs a new {@code InvalidImageFileException} with the specified detail message.
   *
   * @param message the detail message. The detail message is saved for later retrieval by the {@link #getMessage()} method.
   */
  public InvalidImageFileException(String message) {
    super(message);
  }
}
