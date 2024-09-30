package com.nt.user.microservice.exceptions;

/**
 * Exception thrown when a user tries to perform a transaction or operation that
 * requires more balance than is available in their wallet.
 * <p>
 * This exception is typically used in services managing wallet balances to
 * indicate that there are insufficient funds to complete the requested operation.
 * </p>
 */
public class InsufficientBalanceException extends RuntimeException {

  /**
   * Constructs a new {@code InsufficientBalanceException} with the specified detail message.
   *
   * @param message the detail message that explains the reason for the exception.
   */
  public InsufficientBalanceException(final String message) {
    super(message);
  }
}
