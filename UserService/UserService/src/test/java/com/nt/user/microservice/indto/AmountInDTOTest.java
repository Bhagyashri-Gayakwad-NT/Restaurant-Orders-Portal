package com.nt.user.microservice.indto;

import com.nt.user.microservice.dto.AmountInDTO;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AmountInDTOTests {

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

  @Test
  void testEquals() {
    AmountInDTO amountInDTO1 = new AmountInDTO(150.0);
    AmountInDTO amountInDTO2 = new AmountInDTO(150.0);
    AmountInDTO amountInDTO3 = new AmountInDTO(200.0);

    assertEquals(amountInDTO1, amountInDTO2, "Equal amounts should be considered equal.");
    assertNotEquals(amountInDTO1, amountInDTO3, "Different amounts should not be considered equal.");
    assertNotEquals(amountInDTO1, null, "Object should not be equal to null.");
    assertNotEquals(amountInDTO1, new Object(), "Object should not be equal to a different class.");
  }

  @Test
  void testHashCode() {
    AmountInDTO amountInDTO1 = new AmountInDTO(100.0);
    AmountInDTO amountInDTO2 = new AmountInDTO(100.0);
    AmountInDTO amountInDTO3 = new AmountInDTO(200.0);

    assertEquals(amountInDTO1.hashCode(), amountInDTO2.hashCode(), "Equal objects should have the same hash code.");
    assertNotEquals(amountInDTO1.hashCode(), amountInDTO3.hashCode(), "Different objects should have different hash codes.");
  }

  @Test
  void testToString() {
    AmountInDTO amountInDTO = new AmountInDTO(500.0);
    String expectedString = "AmountInDTO{balance=500.0}";

    assertEquals(expectedString, amountInDTO.toString(), "toString() should return the correct string representation.");
  }
}