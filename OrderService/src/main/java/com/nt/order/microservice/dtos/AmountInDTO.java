package com.nt.order.microservice.dtos;

import javax.validation.constraints.NotNull;

/**
 * Data Transfer Object for handling amount input.
 * This class is used to encapsulate the balance information
 * for financial operations such as adding or updating wallet balance.
 */
public class AmountInDTO {

  /**
   * The balance amount for financial operations.
   * <p>
   * This field represents the balance to be added or updated in the user's wallet.
   * It is a mandatory field, and cannot be {@code null}.
   * </p>
   */
  @NotNull(message = "Balance cannot be null")
  private Double balance;

  /**
   * Retrieves the balance.
   *
   * @return the current balance
   */
  public Double getBalance() {
    return balance;
  }

  /**
   * Sets the balance.
   *
   * @param balance the balance to set
   */
  public void setBalance(final Double balance) {
    this.balance = balance;
  }
}
