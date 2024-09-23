package com.nt.order.microservice.dtos;

import java.util.Objects;

/**
 * Data Transfer Object for updating the balance of a user.
 * This class encapsulates the information required to update a user's balance.
 */
public class UpdateBalance {

  private Integer id;      // Unique identifier for the balance update record
  private Integer userId;  // ID of the user whose balance is being updated
  private Double balance;   // New balance amount

  /**
   * Default constructor.
   */
  public UpdateBalance() {
  }

  /**
   * Parameterized constructor to create an UpdateBalance object with specified details.
   *
   * @param id       the unique identifier for the balance update
   * @param userId   the ID of the user
   * @param balance  the new balance amount
   */
  public UpdateBalance(Integer id, Integer userId, Double balance) {
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
  public void setId(Integer id) {
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
  public void setUserId(Integer userId) {
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
  public void setBalance(Double balance) {
    this.balance = balance;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof UpdateBalance)) {
      return false;
    }
    UpdateBalance that = (UpdateBalance) o;
    return Objects.equals(id, that.id) && Objects.equals(userId, that.userId) &&
      Objects.equals(balance, that.balance);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, userId, balance);
  }

  @Override
  public String toString() {
    return "UpdateBalance{" +
      "id=" + id +
      ", userId=" + userId +
      ", balance=" + balance +
      '}';
  }
}