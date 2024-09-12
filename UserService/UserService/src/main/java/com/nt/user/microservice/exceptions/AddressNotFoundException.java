package com.nt.user.microservice.exceptions;

/**
 * Custom exception to be thrown when an address is not found.
 * Extends {@link RuntimeException} to indicate an exceptional condition
 * that a method should handle.
 */
public class AddressNotFoundException extends RuntimeException {

  /**
   * Constructs a new AddressNotFoundException with the specified detail message.
   *
   * @param message the detail message which is saved for later retrieval by the {@link #getMessage()} method
   */
  public AddressNotFoundException(String message) {
    super(message);
  }
}
