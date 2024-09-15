package com.nt.user.microservice.indto;

import com.nt.user.microservice.dto.AmountInDTO;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AmountInDTOTest {

  @Test
  void testNoArgsConstructor() {
    AmountInDTO amountInDTO = new AmountInDTO();
    assertNull(amountInDTO.getBalance(), "Default constructor should set balance to null.");
  }

  @Test
  void testAllArgsConstructor() {
    Double balance = 100.0;
    AmountInDTO amountInDTO = new AmountInDTO(balance);
    assertEquals(balance, amountInDTO.getBalance(), "Constructor with balance should set the correct balance.");
  }

  @Test
  void testGetBalance() {
    Double balance = 200.0;
    AmountInDTO amountInDTO = new AmountInDTO(balance);
    assertEquals(balance, amountInDTO.getBalance(), "getBalance() should return the correct balance.");
  }

  @Test
  void testSetBalance() {
    AmountInDTO amountInDTO = new AmountInDTO();
    Double newBalance = 300.0;
    amountInDTO.setBalance(newBalance);
    assertEquals(newBalance, amountInDTO.getBalance(), "setBalance() should update the balance.");
  }
}
