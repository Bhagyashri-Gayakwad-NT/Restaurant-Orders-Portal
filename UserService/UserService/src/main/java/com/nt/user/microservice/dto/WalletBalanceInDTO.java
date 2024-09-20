package com.nt.user.microservice.dto;

import javax.validation.constraints.NotNull;

/**
 * Data Transfer Object for handling wallet balance input.
 * This class is used to pass wallet balance information, including the user's ID and balance.
 */
public class WalletBalanceInDTO {

  /**
   * The unique identifier of the user.
   */
  @NotNull(message = "User ID cannot be null")
  private Integer id;

  /**
   * The balance of the user's wallet.
   */
  @NotNull(message = "Balance cannot be null")
  private double balance;

  /**
   * Gets the unique identifier of the user.
   *
   * @return the user ID
   */
  public Integer getId() {
    return id;
  }

  /**
   * Sets the unique identifier of the user.
   *
   * @param id the user ID to set
   */
  public void setId(Integer id) {
    this.id = id;
  }

  /**
   * Gets the wallet balance of the user.
   *
   * @return the wallet balance
   */
  public double getBalance() {
    return balance;
  }

  /**
   * Sets the wallet balance of the user.
   *
   * @param balance the balance to set
   */
  public void setBalance(double balance) {
    this.balance = balance;
  }
}
