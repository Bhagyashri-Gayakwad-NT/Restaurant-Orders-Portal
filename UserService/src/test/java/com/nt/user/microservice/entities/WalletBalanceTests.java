package com.nt.user.microservice.entities;

import com.nt.user.microservice.entites.WalletBalance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

  @Test
  public void testEqualsAndHashCode() {
    WalletBalance walletBalance1 = new WalletBalance();
    walletBalance1.setId(1);
    walletBalance1.setUserId(1001);
    walletBalance1.setBalance(1000.0);

    WalletBalance walletBalance2 = new WalletBalance();
    walletBalance2.setId(1);
    walletBalance2.setUserId(1001);
    walletBalance2.setBalance(1000.0);

    WalletBalance walletBalance3 = new WalletBalance();
    walletBalance3.setId(2);
    walletBalance3.setUserId(1002);
    walletBalance3.setBalance(500.0);

    assertTrue(walletBalance1.equals(walletBalance2), "walletBalance1 should be equal to walletBalance2");
    assertFalse(walletBalance1.equals(walletBalance3), "walletBalance1 should not be equal to walletBalance3");
    assertFalse(walletBalance2.equals(walletBalance3), "walletBalance2 should not be equal to walletBalance3");

    assertEquals(walletBalance1.hashCode(), walletBalance2.hashCode(), "hashCode of walletBalance1 should be" +
      " equal to hashCode of walletBalance2");
    assertNotEquals(walletBalance1.hashCode(), walletBalance3.hashCode(), "hashCode of walletBalance1 should not be equal to h" +
      "ashCode of walletBalance3");
  }

  @Test
  public void testToString() {
    walletBalance.setId(1);
    walletBalance.setUserId(1001);
    walletBalance.setBalance(1000.0);

    String expectedString = "WalletBalance{id=1, userId=1001, balance=1000.0}";
    assertEquals(expectedString, walletBalance.toString(), "The toString method should return the expected string representation");
  }
}
