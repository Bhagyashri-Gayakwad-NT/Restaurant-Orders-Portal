package com.nt.user.microservice.serviceimpl;

import com.nt.user.microservice.dto.UserOutDTO;
import com.nt.user.microservice.entites.User;
import com.nt.user.microservice.entites.WalletBalance;
import com.nt.user.microservice.exceptions.InsufficientBalanceException;
import com.nt.user.microservice.exceptions.ResourceNotFoundException;
import com.nt.user.microservice.repository.UserRepository;
import com.nt.user.microservice.repository.WalletBalanceRepository;
import com.nt.user.microservice.service.WalletBalanceService;
import com.nt.user.microservice.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service implementation for managing wallet balance operations.
 * <p>
 * This service provides functionality to update the wallet balance of a user.
 * It handles exceptions for cases such as user or wallet not found and insufficient balance.
 * </p>
 */
@Service
public class WalletBalanceServiceImpl implements WalletBalanceService {
  /**
   * LOGGER for logging information, warnings, and errors related to wallet balance operations.
   * <p>
   * The LOGGER is used to record events and errors that occur during the execution of methods in this class.
   * This helps in debugging and tracking the application's behavior.
   * </p>
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(WalletBalanceServiceImpl.class);

  /**
   * Repository for performing CRUD operations on the WalletBalance entity.
   * <p>
   * This repository is used to interact with the database to fetch, save, and update wallet balance records.
   * It is injected into this service to perform operations related to wallet balances.
   * </p>
   */
  @Autowired
  private WalletBalanceRepository walletBalanceRepository;

  /**
   * Repository for performing CRUD operations on the User entity.
   * <p>
   * This repository is used to interact with the database to fetch user details.
   * It is injected into this service to retrieve user information required for wallet balance updates.
   * </p>
   */
  @Autowired
  private UserRepository userRepository;


  /**
   * Updates the wallet balance for a given user.
   * <p>
   * This method retrieves the user and their wallet balance, checks if the current balance
   * is sufficient for the requested amount, and updates the wallet balance accordingly.
   * If the user or wallet is not found or if there is insufficient balance, appropriate exceptions are thrown.
   * </p>
   *
   * @param userId the ID of the user whose wallet balance is to be updated
   * @param amount the amount to be deducted from the wallet balance
   * @return a {@link UserOutDTO} object containing updated user information and wallet balance
   * @throws ResourceNotFoundException if the user or wallet is not found
   * @throws InsufficientBalanceException if the wallet balance is insufficient for the requested amount
   */
  @Override
  public UserOutDTO updateWalletBalance(final Integer userId, final Double amount) {
    LOGGER.info("Updating wallet balance for user ID: {}", userId);

    User user = userRepository.findById(userId)
      .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));
    WalletBalance walletBalance = walletBalanceRepository.findByUserId(userId);
    if (walletBalance == null) {
      throw new ResourceNotFoundException("Wallet not found for user ID: " + userId);
    }

    Double currentBalance = walletBalance.getBalance();
    if (currentBalance < amount) {
      LOGGER.error("Insufficient funds for user ID: {}", userId);
      throw new InsufficientBalanceException(Constants.INSUFFICIENT_BALANCE);
    }
    walletBalance.setBalance(currentBalance - amount);
    walletBalanceRepository.save(walletBalance);

    LOGGER.info("Wallet balance updated successfully for user ID: {}", userId);

    UserOutDTO userOutDTO = new UserOutDTO();
    userOutDTO.setId(user.getId());
    userOutDTO.setFirstName(user.getFirstName());
    userOutDTO.setLastName(user.getLastName());
    userOutDTO.setEmail(user.getEmail());
    userOutDTO.setPhoneNo(user.getPhoneNo());
    userOutDTO.setWalletBalance(walletBalance.getBalance());
    userOutDTO.setRole(user.getRole().name());
    return userOutDTO;
  }

  /**
   * Adds money to the wallet balance for a given user.
   * <p>
   * This method retrieves the user and their wallet balance, adds the specified amount
   * to the current balance, and saves the updated wallet balance.
   * If the user or wallet is not found, an appropriate exception is thrown.
   * </p>
   *
   * @param userId the ID of the user whose wallet balance is to be updated
   * @param amount the amount to be added to the wallet balance
   * @return a {@link UserOutDTO} object containing updated user information and wallet balance
   * @throws ResourceNotFoundException if the user or wallet is not found
   */
  @Override
  public UserOutDTO addMoney(final Integer userId, final Double amount) {
    User user = userRepository.findById(userId)
      .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));

    WalletBalance walletBalance = walletBalanceRepository.findByUserId(userId);
    if (walletBalance == null) {
      throw new ResourceNotFoundException("Wallet not found for user ID: " + userId);
    }

    Double currentBalance = walletBalance.getBalance();
    walletBalance.setBalance(currentBalance + amount);
    walletBalanceRepository.save(walletBalance);

    return mapToUserOutDTO(user, walletBalance);
  }

  /**
   * Maps a User and WalletBalance to a UserOutDTO.
   *
   * @param user the user to be mapped
   * @param walletBalance the wallet balance to be included in the DTO
   * @return a {@link UserOutDTO} object containing user information and wallet balance
   */
  private UserOutDTO mapToUserOutDTO(final User user, final WalletBalance walletBalance) {
    UserOutDTO userOutDTO = new UserOutDTO();
    userOutDTO.setId(user.getId());
    userOutDTO.setFirstName(user.getFirstName());
    userOutDTO.setLastName(user.getLastName());
    userOutDTO.setEmail(user.getEmail());
    userOutDTO.setPhoneNo(user.getPhoneNo());
    userOutDTO.setWalletBalance(walletBalance.getBalance());
    userOutDTO.setRole(user.getRole().name());
    return userOutDTO;
  }
}
