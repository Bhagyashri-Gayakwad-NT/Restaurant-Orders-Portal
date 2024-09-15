package com.nt.user.microservice.entites;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

/**
 * Represents a wallet balance entity for the application.
 * This entity is mapped to the "wallet_balance" table in the database.
 */
@Entity
public class WalletBalance {

  /**
   * The unique identifier for the wallet balance.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  /**
   * The unique identifier for the user associated with this wallet balance.
   */
  private Integer userId;

  /**
   * The current balance of the user's wallet.
   */
  private Double balance;

  /**
   * Gets the unique identifier for the wallet balance.
   *
   * @return the wallet balance ID.
   */
  public Integer getId() {
    return id;
  }

  /**
   * Sets the unique identifier for the wallet balance.
   *
   * @param id the wallet balance ID to set.
   */
  public void setId(Integer id) {
    this.id = id;
  }

  /**
   * Gets the unique identifier for the user associated with this wallet balance.
   *
   * @return the user ID.
   */
  public Integer getUserId() {
    return userId;
  }

  /**
   * Sets the unique identifier for the user associated with this wallet balance.
   *
   * @param userId the user ID to set.
   */
  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  /**
   * Gets the current balance of the user's wallet.
   *
   * @return the current wallet balance.
   */
  public double getBalance() {
    return balance;
  }

  /**
   * Sets the current balance of the user's wallet.
   *
   * @param balance the wallet balance to set.
   */
  public void setBalance(Double balance) {
    this.balance = balance;
  }

  /**
   * Compares this wallet balance to the specified object. The result is true if and only if
   * the argument is not null and is a WalletBalance object that has the same values for all fields.
   *
   * @param o the object to compare this wallet balance against.
   * @return true if the given object represents a WalletBalance equivalent to this wallet balance, false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true; }
    if (o == null || getClass() != o.getClass()) {
      return false; }
    WalletBalance that = (WalletBalance) o;
    return Double.compare(that.balance, balance) == 0 &&
      Objects.equals(id, that.id) &&
      Objects.equals(userId, that.userId);
  }

  /**
   * Returns a hash code value for the wallet balance. This method is supported for the benefit
   * of hash tables such as those provided by HashMap.
   *
   * @return a hash code value for this wallet balance.
   */
  @Override
  public int hashCode() {
    return Objects.hash(id, userId, balance);
  }

  /**
   * Returns a string representation of the wallet balance, including the values of all fields.
   *
   * @return a string representation of the wallet balance.
   */
  @Override
  public String toString() {
    return "WalletBalance{" +
      "id=" + id +
      ", userId=" + userId +
      ", balance=" + balance +
      '}';
  }
}
