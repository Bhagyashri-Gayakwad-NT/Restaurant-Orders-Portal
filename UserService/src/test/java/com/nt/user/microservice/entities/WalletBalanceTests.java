package com.nt.user.microservice.entities;

import com.nt.user.microservice.entites.WalletBalance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WalletBalanceTests {

  private WalletBalance walletBalance;

  @BeforeEach
  public void setUp() {
    walletBalance = new WalletBalance();
  }

  @Test
  public void testSetAndGetId() {
    Integer id = 1;
    walletBalance.setId(id);
    assertEquals(id, walletBalance.getId(), "The ID should be correctly set and retrieved");
  }

  @Test
  public void testSetAndGetUserId() {
    Integer userId = 1001;
    walletBalance.setUserId(userId);
    assertEquals(userId, walletBalance.getUserId(), "The User ID should be correctly set and retrieved");
  }

  @Test
  public void testSetAndGetBalance() {
    double balance = 1000.0;
    walletBalance.setBalance(balance);
    assertEquals(balance, walletBalance.getBalance(), "The balance should be correctly set and retrieved");
  }
}
