package com.nt.user.microservice.service.impl;

import com.nt.user.microservice.exceptions.InvalidCredentialsException;
import com.nt.user.microservice.exceptions.UserAlreadyExistsException;
import com.nt.user.microservice.exceptions.UserNotFoundException;
import com.nt.user.microservice.dto.UserResponse;
import com.nt.user.microservice.util.Constants;
import com.nt.user.microservice.util.Role;
import com.nt.user.microservice.entites.User;
import com.nt.user.microservice.entites.WalletBalance;
import com.nt.user.microservice.dto.UserInDTO;
import com.nt.user.microservice.dto.UserOutDTO;
import com.nt.user.microservice.repository.UserRepository;
import com.nt.user.microservice.repository.WalletBalanceRepository;
import com.nt.user.microservice.service.UserService;
import com.nt.user.microservice.util.Base64Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implementation of the UserService interface providing user-related operations.
 */
@Service
public class UserServiceImpl implements UserService {

  private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

  private final UserRepository userRepository;
  private final WalletBalanceRepository walletBalanceRepository;

  /**
   * Constructor for UserServiceImpl.
   *
   * @param userRepository           the UserRepository to be injected
   * @param walletBalanceRepository  the WalletBalanceRepository to be injected
   */
  @Autowired
  public UserServiceImpl(UserRepository userRepository, WalletBalanceRepository walletBalanceRepository) {
    this.userRepository = userRepository;
    this.walletBalanceRepository = walletBalanceRepository;
  }

  /**
   * Registers a new user.
   *
   * @param userInDTO the user information to register
   * @return a UserResponse indicating the result of the registration
   */
  @Override
  public UserResponse registerUser(UserInDTO userInDTO) {
    logger.info("Attempting to register user with email: {}", userInDTO.getEmail());

    // Check if a user with the same email already exists
    Optional<User> existingUser = userRepository.findByEmail(userInDTO.getEmail());
    if (existingUser.isPresent()) {
      logger.error("User with email {} already exists", userInDTO.getEmail());
      throw new UserAlreadyExistsException("User already registered with this email");
    }

    User user = new User();
    user.setFirstName(userInDTO.getFirstName());
    user.setLastName(userInDTO.getLastName());
    user.setEmail(userInDTO.getEmail());
    user.setPhoneNo(userInDTO.getPhoneNo());
    user.setPassword(Base64Util.encode(userInDTO.getPassword())); // Ensure password is encoded
    user.setRole(Role.valueOf(userInDTO.getRole().toUpperCase()));

    User savedUser = userRepository.save(user);
    logger.info("User registered successfully with ID: {}", savedUser.getId());

    if (Role.USER.equals(user.getRole())) {
      logger.info("Assigning initial wallet balance for user with ID: {}", savedUser.getId());

      WalletBalance walletBalanceEntity = new WalletBalance();
      walletBalanceEntity.setUserId(savedUser.getId());
      walletBalanceEntity.setBalance(Constants.INITIAL_WALLET_BALANCE);
      walletBalanceRepository.save(walletBalanceEntity);

      logger.info("Initial wallet balance assigned for user with ID: {}", savedUser.getId());
    }

    UserResponse response = new UserResponse();
    response.setSuccessMessage(Constants.USER_REGISTERED_SUCCESSFULLY);
    return response;
  }

  /**
   * Logs in a user by verifying their email and password.
   *
   * @param email    the user's email
   * @param password the user's password
   * @return a UserOutDTO with user details if login is successful
   */
  @Override
  public UserOutDTO loginUser(String email, String password) {
    logger.info("Attempting to log in user with email: {}", email);

    // Fetch user by email
    Optional<User> userOptional = userRepository.findByEmail(email);

    if (!userOptional.isPresent()) {
      logger.error("User with email {} not found", email);
      throw new UserNotFoundException("User not found");
    }

    User user = userOptional.get();

    // Check if the password matches
    if (!Base64Util.encode(password).equals(user.getPassword())) { // Ensure password comparison is encoded
      logger.error("Invalid credentials for user with email: {}", email);
      throw new InvalidCredentialsException("Invalid password");
    }

    logger.info("Login successful for user with email: {}", email);

    WalletBalance walletBalance = walletBalanceRepository.findByUserId(user.getId());

    UserOutDTO userOutDTO = new UserOutDTO();
    userOutDTO.setId(user.getId());
    userOutDTO.setFirstName(user.getFirstName());
    userOutDTO.setLastName(user.getLastName());
    userOutDTO.setEmail(user.getEmail());
    userOutDTO.setPhoneNo(user.getPhoneNo());
    userOutDTO.setRole(user.getRole().name());

    if (user.getRole() == Role.USER) {
      userOutDTO.setWalletBalance(walletBalance != null ? walletBalance.getBalance() : 0.0);
    }

    return userOutDTO;
  }

  /**
   * Retrieves the profile of a user by their ID.
   *
   * @param id the user's ID
   * @return a UserOutDTO with the user's profile information
   */
  @Override
  public UserOutDTO getUserProfile(Integer id) {
    logger.info("Fetching user profile for ID: {}", id);

    Optional<User> userOptional = userRepository.findById(id);
    if (userOptional.isPresent()) {
      User user = userOptional.get();
      WalletBalance walletBalance = walletBalanceRepository.findByUserId(user.getId());

      UserOutDTO userOutDTO = new UserOutDTO();
      userOutDTO.setId(user.getId());
      userOutDTO.setFirstName(user.getFirstName());
      userOutDTO.setLastName(user.getLastName());
      userOutDTO.setEmail(user.getEmail());
      userOutDTO.setPhoneNo(user.getPhoneNo());
      userOutDTO.setRole(user.getRole().name());
      userOutDTO.setWalletBalance(walletBalance != null ? walletBalance.getBalance() : 0.0);

      logger.info("User profile fetched successfully for ID: {}", id);
      return userOutDTO;
    } else {
      logger.error("User not found with ID: {}", id);
      throw new UserNotFoundException(Constants.USER_NOT_FOUND);
    }
  }

  /**
   * Updates the profile of an existing user.
   *
   * @param id        the user's ID
   * @param userInDTO the updated user information
   * @return a UserResponse indicating the result of the update
   */
  @Override
  public UserResponse updateUserProfile(Integer id, UserInDTO userInDTO) {
    logger.info("Updating user profile for ID: {}", id);

    User user = userRepository.findById(id)
      .orElseThrow(() -> {
        logger.error("User not found with ID: {}", id);
        return new UserNotFoundException(Constants.USER_NOT_FOUND);
      });

    // Update user details
    user.setFirstName(userInDTO.getFirstName());
    user.setLastName(userInDTO.getLastName());
    user.setPhoneNo(userInDTO.getPhoneNo());
    if (userInDTO.getPassword() != null && !userInDTO.getPassword().isEmpty()) {
      user.setPassword(Base64Util.encode(userInDTO.getPassword())); // Ensure password is encoded
    }
    user.setRole(Role.valueOf(userInDTO.getRole().toUpperCase()));

    userRepository.save(user);
    logger.info("User profile updated successfully for ID: {}", id);
    UserResponse userResponse = new UserResponse();
    userResponse.setSuccessMessage(Constants.USER_PROFILE_UPDATED_SUCCESSFULLY);
    return userResponse;
  }

  /**
   * Deletes a user and their associated wallet balance.
   *
   * @param id the user's ID
   * @return a UserResponse indicating the result of the deletion
   */
  @Override
  public UserResponse deleteUser(Integer id) {
    logger.info("Attempting to delete user with ID: {}", id);

    // Fetch the user, or throw UserNotFoundException if not found
    User user = userRepository.findById(id)
      .orElseThrow(() -> {
        logger.error("User not found with ID: {}", id);
        return new UserNotFoundException(Constants.USER_NOT_FOUND);
      });

    // Delete the user and wallet balance
    userRepository.delete(user);
    walletBalanceRepository.deleteByUserId(id);

    // Log success and return response
    logger.info("User and associated wallet balance deleted successfully for ID: {}", id);
    UserResponse userResponse = new UserResponse();
    userResponse.setSuccessMessage(Constants.USER_DELETED_SUCCESSFULLY);
    return userResponse;
  }
}
