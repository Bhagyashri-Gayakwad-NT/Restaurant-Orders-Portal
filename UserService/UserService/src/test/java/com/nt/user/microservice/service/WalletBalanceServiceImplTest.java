package com.nt.user.microservice.service;

import com.nt.user.microservice.dto.UserOutDTO;
import com.nt.user.microservice.entites.User;
import com.nt.user.microservice.entites.WalletBalance;
import com.nt.user.microservice.exceptions.InsufficientBalanceException;
import com.nt.user.microservice.exceptions.NotFoundException;
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
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class WalletBalanceServiceImplTest {

  @Mock
  private WalletBalanceRepository walletBalanceRepository;

  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private WalletBalanceServiceImpl walletBalanceService;

  private User user;
  private WalletBalance walletBalance;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);

    user = new User();
    user.setId(1);
    user.setFirstName("John");
    user.setLastName("Doe");
    user.setEmail("john.doe@example.com");
    user.setPhoneNo("1234567890");
    user.setRole(Role.USER);

    walletBalance = new WalletBalance();
    walletBalance.setUserId(user.getId());
    walletBalance.setBalance(1000.0);
  }

  @Test
  void testUpdateWalletBalance_Success() {
    when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));
    when(walletBalanceRepository.findByUserId(anyInt())).thenReturn(walletBalance);

    UserOutDTO userOutDTO = walletBalanceService.updateWalletBalance(1, 500.0);

    assertNotNull(userOutDTO);
    assertEquals(500.0, walletBalance.getBalance());
    assertEquals(user.getFirstName(), userOutDTO.getFirstName());
    assertEquals(user.getLastName(), userOutDTO.getLastName());
    assertEquals(user.getEmail(), userOutDTO.getEmail());
    assertEquals(walletBalance.getBalance(), userOutDTO.getWalletBalance());
    verify(walletBalanceRepository, times(1)).save(walletBalance);
  }

  @Test
  void testUpdateWalletBalance_UserNotFound() {
    when(userRepository.findById(anyInt())).thenReturn(Optional.empty());
    NotFoundException exception = assertThrows(NotFoundException.class,
      () -> walletBalanceService.updateWalletBalance(1, 500.0));
    assertEquals("User not found with ID: 1", exception.getMessage());
    verify(walletBalanceRepository, never()).findByUserId(anyInt());
  }

  @Test
  void testUpdateWalletBalance_WalletNotFound() {
    when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));
    when(walletBalanceRepository.findByUserId(anyInt())).thenReturn(null);
    NotFoundException exception = assertThrows(NotFoundException.class,
      () -> walletBalanceService.updateWalletBalance(1, 500.0));
    assertEquals("Wallet not found for user ID: 1", exception.getMessage());
    verify(walletBalanceRepository, never()).save(walletBalance);
  }

  @Test
  void testUpdateWalletBalance_InsufficientBalance() {
    when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));
    when(walletBalanceRepository.findByUserId(anyInt())).thenReturn(walletBalance);
    InsufficientBalanceException exception = assertThrows(InsufficientBalanceException.class,
      () -> walletBalanceService.updateWalletBalance(1, 1500.0));
    assertEquals(Constants.INSUFFICIENT_BALANCE, exception.getMessage());
    verify(walletBalanceRepository, never()).save(walletBalance);
  }
}
