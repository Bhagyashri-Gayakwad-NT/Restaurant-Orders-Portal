package com.nt.user.microservice.entites;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class WalletBalance {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private Integer userId;
  private double balance;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public double getBalance() {
    return balance;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    WalletBalance that = (WalletBalance) o;
    return Double.compare(balance, that.balance) == 0 && Objects.equals(id, that.id) &&
      Objects.equals(userId, that.userId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, userId, balance);
  }

  @Override
  public String toString() {
    return "WalletBalance{" +
      "id=" + id +
      ", userId=" + userId +
      ", balance=" + balance +
      '}';
  }
}


