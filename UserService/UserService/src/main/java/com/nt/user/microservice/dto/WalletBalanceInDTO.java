package com.nt.user.microservice.dto;

import javax.validation.constraints.NotNull;
import java.util.Objects;

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
  public void setId(final Integer id) {
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
  public void setBalance(final double balance) {
    this.balance = balance;
  }

  /**
   * Compares this {@code WalletBalanceInDTO} with another object for equality.
   *
   * <p>Two {@code WalletBalanceInDTO} objects are considered equal if they have
   * the same user ID and wallet balance.</p>
   *
   * @param o the reference object with which to compare.
   * @return {@code true} if this object is equal to the given object;
   *         {@code false} otherwise.
   */
  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof WalletBalanceInDTO)) {
      return false;
    }
    WalletBalanceInDTO that = (WalletBalanceInDTO) o;
    return Double.compare(balance, that.balance) == 0 && Objects.equals(id, that.id);
  }

  /**
   * Returns a hash code value for this {@code WalletBalanceInDTO}.
   *
   * @return a hash code value based on the user ID and wallet balance.
   */
  @Override
  public int hashCode() {
    return Objects.hash(id, balance);
  }

  /**
   * Returns a string representation of this {@code WalletBalanceInDTO}.
   *
   * <p>The string representation includes the class name, user ID, and balance.</p>
   *
   * @return a string representation of the object.
   */
  @Override
  public String toString() {
    return "WalletBalanceInDTO{"
      + "id=" + id
      + ", balance=" + balance
      + '}';
  }
}
