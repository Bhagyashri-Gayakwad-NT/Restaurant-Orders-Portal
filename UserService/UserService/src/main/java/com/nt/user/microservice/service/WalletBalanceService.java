package com.nt.user.microservice.service;

import com.nt.user.microservice.dto.WalletBalanceInDTO;
import org.springframework.stereotype.Service;

/**
 * Service interface for managing wallet balance operations.
 * Provides methods for retrieving and updating wallet balances.
 */
@Service
public interface WalletBalanceService {

  /**
   * Retrieves the current wallet balance for a specific user.
   *
   * @param userId the ID of the user whose wallet balance is to be retrieved
   * @return a data transfer object containing the wallet balance of the specified user
   */
  WalletBalanceInDTO getBalance(Long userId);

  /**
   * Adds a specified amount to the wallet balance of a user.
   *
   * @param userId the ID of the user whose wallet balance is to be updated
   * @param amount the amount to be added to the user's wallet balance
   */
  void addBalance(Long userId, double amount);
}
