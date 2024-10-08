package com.nt.order.microservice.dtos;

import java.util.Objects;

/**
 * Data Transfer Object for updating the balance of a user.
 * This class encapsulates the information required to update a user's balance.
 */
public class UpdateBalance {

  /**
   * Unique identifier for the balance update record.
   */
  private Integer id;

  /**
   * ID of the user whose balance is being updated.
   */
  private Integer userId;

  /**
   * New balance amount.
   */
  private Double balance;

  /**
   * Default constructor.
   */
  public UpdateBalance() {
  }

  /**
   * Parameterized constructor to create an UpdateBalance object with specified details.
   *
   * @param id      the unique identifier for the balance update
   * @param userId  the ID of the user
   * @param balance the new balance amount
   */
  public UpdateBalance(final Integer id, final Integer userId, final Double balance) {
    this.id = id;
    this.userId = userId;
    this.balance = balance;
  }

  /**
   * Retrieves the unique identifier for the balance update.
   *
   * @return the unique identifier
   */
  public Integer getId() {
    return id;
  }

  /**
   * Sets the unique identifier for the balance update.
   *
   * @param id the unique identifier to set
   */
  public void setId(final Integer id) {
    this.id = id;
  }

  /**
   * Retrieves the user ID.
   *
   * @return the user ID
   */
  public Integer getUserId() {
    return userId;
  }

  /**
   * Sets the user ID.
   *
   * @param userId the user ID to set
   */
  public void setUserId(final Integer userId) {
    this.userId = userId;
  }

  /**
   * Retrieves the balance.
   *
   * @return the balance
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


  /**
   * Checks if two UpdateBalance objects are equal.
   *
   * @param o the object to compare with
   * @return true if the objects are equal, false otherwise
   */
  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof UpdateBalance)) {
      return false;
    }
    UpdateBalance that = (UpdateBalance) o;
    return Objects.equals(id, that.id) && Objects.equals(userId, that.userId)
      && Objects.equals(balance, that.balance);
  }

  /**
   * Returns a hash code for the UpdateBalance object.
   *
   * @return a hash code value for this object
   */
  @Override
  public int hashCode() {
    return Objects.hash(id, userId, balance);
  }

  /**
   * Returns a string representation of the UpdateBalance object.
   *
   * @return a string representation of the UpdateBalance
   */
  @Override
  public String toString() {
    return "UpdateBalance{"
      + "id=" + id
      + ", userId=" + userId
      + ", balance=" + balance
      + '}';
  }
}
