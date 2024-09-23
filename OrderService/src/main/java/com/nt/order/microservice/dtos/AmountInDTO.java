package com.nt.order.microservice.dtos;

import javax.validation.constraints.NotNull;

/**
 * Data Transfer Object for handling amount input.
 * This class is used to encapsulate the balance information
 * for financial operations such as adding or updating wallet balance.
 */
public class AmountInDTO {

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
  public void setBalance(Double balance) {
    this.balance = balance;
  }
}
