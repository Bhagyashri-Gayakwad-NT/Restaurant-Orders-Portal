package com.nt.order.microservice.exception;

/**
 * Custom exception thrown when a user attempts to complete an operation that requires
 * more balance than they currently have in their account.
 */
public class InsufficientBalanceException extends RuntimeException {

  /**
   * Constructs a new InsufficientBalanceException with the specified detail message.
   *
   * @param message the detail message, saved for later retrieval by the {@link Throwable#getMessage()} method.
   */
  public InsufficientBalanceException(String message) {
    super(message);
  }
}