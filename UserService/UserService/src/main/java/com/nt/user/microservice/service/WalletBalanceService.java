package com.nt.user.microservice.service;

import com.nt.user.microservice.dto.UserOutDTO;
import com.nt.user.microservice.exceptions.InsufficientBalanceException;
import com.nt.user.microservice.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

/**
 * Service interface for managing wallet balance operations.
 * Provides methods for retrieving and updating wallet balances.
 */
@Service
public interface WalletBalanceService {

  /**
   * Updates the wallet balance for a specified user by deducting the given amount.
   *
   * @param userId the ID of the user whose wallet balance is to be updated
   * @param amount the amount to deduct from the user's wallet balance
   * @return {@link UserOutDTO} containing the updated user information and new wallet balance
   * @throws NotFoundException if the user or their wallet is not found
   * @throws InsufficientBalanceException if the user's wallet does not have enough balance
   */
  UserOutDTO updateWalletBalance(Integer userId,  Double amount);
}
