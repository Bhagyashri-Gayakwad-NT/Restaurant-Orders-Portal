package com.nt.user.microservice.indto;

import com.nt.user.microservice.dto.WalletBalanceInDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WalletBalanceDTOTests {

  @Test
  void testGettersAndSetters() {
    WalletBalanceInDTO walletBalanceDTO = new WalletBalanceInDTO();

    // Test setting and getting ID
    walletBalanceDTO.setId(1);
    assertEquals(1, walletBalanceDTO.getId(), "Expected ID to be 1");

    // Test setting and getting balance
    walletBalanceDTO.setBalance(1000.50);
    assertEquals(1000.50, walletBalanceDTO.getBalance(), "Expected balance to be 1000.50");
  }

  @Test
  void testNegativeBalance() {
    WalletBalanceInDTO walletBalanceDTO = new WalletBalanceInDTO();

    // Test setting and getting a negative balance
    walletBalanceDTO.setBalance(-500.00);
    assertEquals(-500.00, walletBalanceDTO.getBalance(), "Expected balance to be -500.00");
  }

  @Test
  void testZeroBalance() {
    WalletBalanceInDTO walletBalanceDTO = new WalletBalanceInDTO();

    // Test setting and getting a zero balance
    walletBalanceDTO.setBalance(0.00);
    assertEquals(0.00, walletBalanceDTO.getBalance(), "Expected balance to be 0.00");
  }

  @Test
  void testLargeBalance() {
    WalletBalanceInDTO walletBalanceDTO = new WalletBalanceInDTO();

    // Test setting and getting a large balance
    walletBalanceDTO.setBalance(1000000000.00);
    assertEquals(1000000000.00, walletBalanceDTO.getBalance(), "Expected balance to be 1000000000.00");
  }
}

