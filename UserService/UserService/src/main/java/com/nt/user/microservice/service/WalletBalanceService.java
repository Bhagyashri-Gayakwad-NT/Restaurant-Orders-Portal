package com.nt.user.microservice.service;

import com.nt.user.microservice.dto.UserOutDTO;
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
   */
  UserOutDTO updateWalletBalance(Integer userId,  Double amount);

  /**
   * Adds a specified amount to a user's wallet balance.
   *
   * @param userId the ID of the user
   * @param amount the amount to add
   * @return updated user information with new wallet balance
   */
  UserOutDTO addMoney(Integer userId,  Double amount);

}
