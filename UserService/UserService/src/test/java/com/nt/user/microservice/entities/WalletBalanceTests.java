package com.nt.user.microservice.entities;

import com.nt.user.microservice.entites.WalletBalance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WalletBalanceTests {

  private WalletBalance walletBalance1;
  private WalletBalance walletBalance2;

  @BeforeEach
  void setUp() {
    walletBalance1 = new WalletBalance();
    walletBalance2 = new WalletBalance();
  }

  @Test
  void testGettersAndSetters() {
    // Test ID
    Integer id = 1;
    walletBalance1.setId(id);
    assertEquals(id, walletBalance1.getId());

    // Test UserId
    Integer userId = 101;
    walletBalance1.setUserId(userId);
    assertEquals(userId, walletBalance1.getUserId());

    // Test Balance
    Double balance = 500.0;
    walletBalance1.setBalance(balance);
    assertEquals(balance, walletBalance1.getBalance());
  }

  @Test
  void testEquals_SameObject() {
    assertEquals(walletBalance1, walletBalance1);
  }

  @Test
  void testEquals_NullObject() {
    assertNotEquals(walletBalance1, null);
  }

  @Test
  void testEquals_DifferentClass() {
    assertNotEquals(walletBalance1, new Object());
  }

  @Test
  void testEquals_DifferentFields() {
    walletBalance1.setId(1);
    walletBalance1.setUserId(101);
    walletBalance1.setBalance(500.0);

    walletBalance2.setId(2);
    walletBalance2.setUserId(102);
    walletBalance2.setBalance(600.0);

    assertNotEquals(walletBalance1, walletBalance2);
  }

  @Test
  void testEquals_SameFields() {
    walletBalance1.setId(1);
    walletBalance1.setUserId(101);
    walletBalance1.setBalance(500.0);

    walletBalance2.setId(1);
    walletBalance2.setUserId(101);
    walletBalance2.setBalance(500.0);

    assertEquals(walletBalance1, walletBalance2);
  }

  @Test
  void testHashCode_SameObject() {
    walletBalance1.setId(1);
    walletBalance1.setUserId(101);
    walletBalance1.setBalance(500.0);

    walletBalance2.setId(1);
    walletBalance2.setUserId(101);
    walletBalance2.setBalance(500.0);

    assertEquals(walletBalance1.hashCode(), walletBalance2.hashCode());
  }

  @Test
  void testHashCode_DifferentObject() {
    walletBalance1.setId(1);
    walletBalance1.setUserId(101);
    walletBalance1.setBalance(500.0);

    walletBalance2.setId(2);
    walletBalance2.setUserId(102);
    walletBalance2.setBalance(600.0);

    assertNotEquals(walletBalance1.hashCode(), walletBalance2.hashCode());
  }

  @Test
  void testToString() {
    walletBalance1.setId(1);
    walletBalance1.setUserId(101);
    walletBalance1.setBalance(500.0);

    String expected = "WalletBalance{id=1, userId=101, balance=500.0}";
    assertEquals(expected, walletBalance1.toString());
  }
}