package com.nt.order.microservice.dtos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
}
