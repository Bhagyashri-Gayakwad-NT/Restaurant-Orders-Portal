package com.nt.order.microservice.dtos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class UpdateBalanceTest {

  @Test
  public void testNoArgsConstructor() {
    // Arrange & Act
    UpdateBalance updateBalance = new UpdateBalance();

    // Assert
    assertNull(updateBalance.getId());
    assertNull(updateBalance.getUserId());
    assertNull(updateBalance.getBalance());
  }

  @Test
  public void testAllArgsConstructor() {
    // Arrange
    Integer id = 1;
    Integer userId = 101;
    Double balance = 500.75;

    // Act
    UpdateBalance updateBalance = new UpdateBalance(id, userId, balance);

    // Assert
    assertEquals(id, updateBalance.getId());
    assertEquals(userId, updateBalance.getUserId());
    assertEquals(balance, updateBalance.getBalance());
  }

  @Test
  public void testSettersAndGetters() {
    // Arrange
    UpdateBalance updateBalance = new UpdateBalance();

    Integer id = 1;
    Integer userId = 101;
    Double balance = 500.75;

    // Act
    updateBalance.setId(id);
    updateBalance.setUserId(userId);
    updateBalance.setBalance(balance);

    // Assert
    assertEquals(id, updateBalance.getId());
    assertEquals(userId, updateBalance.getUserId());
    assertEquals(balance, updateBalance.getBalance());
  }

  @Test
  public void testSetId() {
    // Arrange
    UpdateBalance updateBalance = new UpdateBalance();
    Integer id = 2;

    // Act
    updateBalance.setId(id);

    // Assert
    assertEquals(id, updateBalance.getId());
  }

  @Test
  public void testSetUserId() {
    // Arrange
    UpdateBalance updateBalance = new UpdateBalance();
    Integer userId = 102;

    // Act
    updateBalance.setUserId(userId);

    // Assert
    assertEquals(userId, updateBalance.getUserId());
  }

  @Test
  public void testSetBalance() {
    // Arrange
    UpdateBalance updateBalance = new UpdateBalance();
    Double balance = 800.50;

    // Act
    updateBalance.setBalance(balance);

    // Assert
    assertEquals(balance, updateBalance.getBalance());
  }

  @Test
  public void testEquals() {
    // Arrange
    Integer id = 1;
    Integer userId = 101;
    Double balance = 500.75;

    UpdateBalance updateBalance1 = new UpdateBalance(id, userId, balance);
    UpdateBalance updateBalance2 = new UpdateBalance(id, userId, balance);

    // Act & Assert
    assertEquals(updateBalance1, updateBalance2);
    assertEquals(updateBalance1, updateBalance1); // Reflexive
    assertNotEquals(updateBalance1, new Object()); // Different class

    // Changing a field should result in inequality
    updateBalance2.setBalance(1000.00);
    assertNotEquals(updateBalance1, updateBalance2);
  }

  @Test
  public void testHashCode() {
    // Arrange
    Integer id = 1;
    Integer userId = 101;
    Double balance = 500.75;

    UpdateBalance updateBalance1 = new UpdateBalance(id, userId, balance);
    UpdateBalance updateBalance2 = new UpdateBalance(id, userId, balance);

    // Act & Assert
    assertEquals(updateBalance1.hashCode(), updateBalance2.hashCode());

    // Changing a field should result in different hashcodes
    updateBalance2.setBalance(1000.00);
    assertNotEquals(updateBalance1.hashCode(), updateBalance2.hashCode());
  }

  @Test
  public void testToString() {
    // Arrange
    Integer id = 1;
    Integer userId = 101;
    Double balance = 500.75;

    UpdateBalance updateBalance = new UpdateBalance(id, userId, balance);

    // Act & Assert
    assertEquals("UpdateBalance{id=1, userId=101, balance=500.75}", updateBalance.toString());
  }
}
