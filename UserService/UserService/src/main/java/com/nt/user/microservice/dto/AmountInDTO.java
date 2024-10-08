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
  public AmountInDTO(final Double balance) {
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
  public void setBalance(final Double balance) {
    this.balance = balance;
  }

  /**
   * Compares this AmountInDTO object to the specified object for equality.
   * Two AmountInDTO objects are considered equal if they have the same value
   * for the balance field.
   *
   * @param o the object to compare this AmountInDTO against
   * @return true if the specified object is equal to this AmountInDTO; false otherwise
   */
  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof AmountInDTO)) {
      return false;
    }
    AmountInDTO that = (AmountInDTO) o;
    return Objects.equals(balance, that.balance);
  }

  /**
   * Returns a hash code value for this AmountInDTO object.
   * The hash code is computed based on the value of the balance field.
   *
   * @return a hash code value for this AmountInDTO object
   */
  @Override
  public int hashCode() {
    return Objects.hashCode(balance);
  }

  /**
   * Returns a string representation of this AmountInDTO object.
   * The string includes the class name and the value of the balance field.
   *
   * @return a string representation of this AmountInDTO object
   */
  @Override
  public String toString() {
    return "AmountInDTO{"
      + "balance=" + balance
      + '}';
  }
}
