package com.nt.user.microservice.service;

import com.nt.user.microservice.dto.UserOutDTO;
import com.nt.user.microservice.entites.User;
import com.nt.user.microservice.entites.WalletBalance;
import com.nt.user.microservice.exceptions.InsufficientBalanceException;
import com.nt.user.microservice.exceptions.ResourceNotFoundException;
import com.nt.user.microservice.repository.UserRepository;
import com.nt.user.microservice.repository.WalletBalanceRepository;
import com.nt.user.microservice.serviceimpl.WalletBalanceServiceImpl;
import com.nt.user.microservice.util.Constants;
import com.nt.user.microservice.util.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class WalletBalanceServiceImplTest {

  @InjectMocks
  private WalletBalanceServiceImpl walletBalanceService;

  @Mock
  private WalletBalanceRepository walletBalanceRepository;

  @Mock
  private UserRepository userRepository;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testUpdateWalletBalance_Success() {
    Integer userId = 1;
    Double amountToDeduct = 100.0;

    User user = new User();
    user.setId(userId);
    user.setFirstName("FirstName");
    user.setLastName("LastName");
    user.setEmail("test@example.com");
    user.setRole(Role.USER);

    WalletBalance walletBalance = new WalletBalance();
    walletBalance.setUserId(userId);
    walletBalance.setBalance(500.0);

    when(userRepository.findById(userId)).thenReturn(Optional.of(user));
    when(walletBalanceRepository.findByUserId(userId)).thenReturn(walletBalance);

    UserOutDTO result = walletBalanceService.updateWalletBalance(userId, amountToDeduct);

    assertNotNull(result);
    assertEquals(400.0, walletBalance.getBalance());
  }


  @Test
  public void testUpdateWalletBalance_UserNotFound() {
    Integer userId = 1;
    Double amountToDeduct = 100.0;

    when(userRepository.findById(userId)).thenReturn(Optional.empty());

    ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
      () -> walletBalanceService.updateWalletBalance(userId, amountToDeduct));

    assertEquals(Constants.USER_NOT_FOUND, exception.getMessage());
  }

  @Test
  public void testUpdateWalletBalance_WalletNotFound() {
    Integer userId = 1;
    Double amountToDeduct = 100.0;

    User user = new User();
    user.setId(userId);

    when(userRepository.findById(userId)).thenReturn(Optional.of(user));
    when(walletBalanceRepository.findByUserId(userId)).thenReturn(null);

    ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
      () -> walletBalanceService.updateWalletBalance(userId, amountToDeduct));

    assertEquals(Constants.WALLET_NOT_FOUND, exception.getMessage());
  }

  @Test
  public void testUpdateWalletBalance_InsufficientBalance() {
    Integer userId = 1;
    Double amountToDeduct = 600.0;

    User user = new User();
    user.setId(userId);

    WalletBalance walletBalance = new WalletBalance();
    walletBalance.setUserId(userId);
    walletBalance.setBalance(500.0);

    when(userRepository.findById(userId)).thenReturn(Optional.of(user));
    when(walletBalanceRepository.findByUserId(userId)).thenReturn(walletBalance);

    InsufficientBalanceException exception = assertThrows(InsufficientBalanceException.class,
      () -> walletBalanceService.updateWalletBalance(userId, amountToDeduct));

    assertEquals(Constants.INSUFFICIENT_BALANCE, exception.getMessage());
    verify(walletBalanceRepository, never()).save(any(WalletBalance.class));
  }

  @Test
  public void testAddMoney_Success() {
    Integer userId = 1;
    Double amountToAdd = 200.0;

    User user = new User();
    user.setId(userId);
    user.setFirstName("FirstName");
    user.setLastName("LastName");
    user.setEmail("test@example.com");
    user.setRole(Role.USER);

    WalletBalance walletBalance = new WalletBalance();
    walletBalance.setUserId(userId);
    walletBalance.setBalance(500.0);

    when(userRepository.findById(userId)).thenReturn(Optional.of(user));
    when(walletBalanceRepository.findByUserId(userId)).thenReturn(walletBalance);

    UserOutDTO result = walletBalanceService.addMoney(userId, amountToAdd);

    assertNotNull(result);
    assertEquals(700.0, walletBalance.getBalance());
    verify(walletBalanceRepository, times(1)).save(walletBalance);
  }


  @Test
  public void testAddMoney_UserNotFound() {
    Integer userId = 1;
    Double amountToAdd = 200.0;

    when(userRepository.findById(userId)).thenReturn(Optional.empty());

    ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
      () -> walletBalanceService.addMoney(userId, amountToAdd));

    assertEquals(Constants.USER_NOT_FOUND, exception.getMessage());
    verify(walletBalanceRepository, never()).save(any(WalletBalance.class));
  }

  @Test
  public void testAddMoney_WalletNotFound() {
    Integer userId = 1;
    Double amountToAdd = 200.0;

    User user = new User();
    user.setId(userId);

    when(userRepository.findById(userId)).thenReturn(Optional.of(user));
    when(walletBalanceRepository.findByUserId(userId)).thenReturn(null);

    ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
      () -> walletBalanceService.addMoney(userId, amountToAdd));

    assertEquals(Constants.WALLET_NOT_FOUND, exception.getMessage());
  }
}
