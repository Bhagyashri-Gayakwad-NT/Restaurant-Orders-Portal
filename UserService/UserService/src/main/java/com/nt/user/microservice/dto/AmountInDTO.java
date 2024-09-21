package com.nt.user.microservice.dto;

import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Data Transfer Object (DTO) for representing the amount to be deducted from the wallet balance.
 * <p>
 * This class is used to encapsulate the balance amount in incoming HTTP requests when updating the wallet balance.
 * </p>
 */
public class AmountInDTO {

  /**
   * The amount to be deducted from the user's wallet balance.
   */
  @NotNull(message = "Balance cannot be null")
  private Double balance;

  /**
   * Default constructor for {@link AmountInDTO}.
   * Initializes the object without setting any balance.
   */
  public AmountInDTO() {
  }

  /**
   * Constructor for {@link AmountInDTO} that sets the balance.
   *
   * @param balance the amount to be deducted from the wallet.
   */
  public AmountInDTO(Double balance) {
    this.balance = balance;
  }

  /**
   * Retrieves the balance to be deducted.
   *
   * @return the balance amount.
   */
  public Double getBalance() {
    return balance;
  }

  /**
   * Sets the balance to be deducted.
   *
   * @param balance the amount to set.
   */
  public void setBalance(Double balance) {
    this.balance = balance;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true; }
    if (!(o instanceof AmountInDTO)) {
      return false; }
    AmountInDTO that = (AmountInDTO) o;
    return Objects.equals(balance, that.balance);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(balance);
  }

  @Override
  public String toString() {
    return "AmountInDTO{" +
      "balance=" + balance +
      '}';
  }
}
