package com.nt.user.microservice.indto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WalletBalanceDTOTests {

  @Test
  void testGettersAndSetters() {
    WalletBalanceDTO walletBalanceDTO = new WalletBalanceDTO();

    // Test setting and getting ID
    walletBalanceDTO.setId(1);
    assertEquals(1, walletBalanceDTO.getId(), "Expected ID to be 1");

    // Test setting and getting balance
    walletBalanceDTO.setBalance(1000.50);
    assertEquals(1000.50, walletBalanceDTO.getBalance(), "Expected balance to be 1000.50");
  }

  @Test
  void testNegativeBalance() {
    WalletBalanceDTO walletBalanceDTO = new WalletBalanceDTO();

    // Test setting and getting a negative balance
    walletBalanceDTO.setBalance(-500.00);
    assertEquals(-500.00, walletBalanceDTO.getBalance(), "Expected balance to be -500.00");
  }

  @Test
  void testZeroBalance() {
    WalletBalanceDTO walletBalanceDTO = new WalletBalanceDTO();

    // Test setting and getting a zero balance
    walletBalanceDTO.setBalance(0.00);
    assertEquals(0.00, walletBalanceDTO.getBalance(), "Expected balance to be 0.00");
  }

  @Test
  void testLargeBalance() {
    WalletBalanceDTO walletBalanceDTO = new WalletBalanceDTO();

    // Test setting and getting a large balance
    walletBalanceDTO.setBalance(1000000000.00);
    assertEquals(1000000000.00, walletBalanceDTO.getBalance(), "Expected balance to be 1000000000.00");
  }
}

