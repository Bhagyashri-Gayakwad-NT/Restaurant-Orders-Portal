package com.nt.user.microservice.serviceimpl;

import com.nt.user.microservice.dto.EmailRequestDTO;
import com.nt.user.microservice.dto.LoginOutDTO;
import com.nt.user.microservice.dto.UserInDTO;
import com.nt.user.microservice.dto.UserOutDTO;
import com.nt.user.microservice.dto.UserResponse;
import com.nt.user.microservice.entites.User;
import com.nt.user.microservice.entites.WalletBalance;
import com.nt.user.microservice.exceptions.InvalidCredentialsException;
import com.nt.user.microservice.exceptions.ResourceAlreadyExistException;
import com.nt.user.microservice.exceptions.ResourceNotFoundException;
import com.nt.user.microservice.repository.UserRepository;
import com.nt.user.microservice.repository.WalletBalanceRepository;
import com.nt.user.microservice.service.EmailService;
import com.nt.user.microservice.service.UserService;
import com.nt.user.microservice.util.Base64Util;
import com.nt.user.microservice.util.Constants;
import com.nt.user.microservice.util.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of the UserService interface providing user-related operations.
 */
@Service
public class UserServiceImpl implements UserService {
  /**
   * LOGGER for the UserServiceImpl class, used for logging important information
   * such as user registration attempts, login attempts, profile updates, and deletions.
   * <p>
   * The {@link Logger} instance helps in tracking execution flow, capturing success and error messages,
   * and facilitating debugging and auditing of user-related operations.
   * </p>
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

  /**
   * Repository for performing CRUD operations on {@link User} entities.
   * <p>
   * The {@link UserRepository} is used to interact with the database for user-related data operations.
   * It provides methods for saving, retrieving, updating, and deleting user records.
   * </p>
   */
  @Autowired
  private  UserRepository userRepository;

  /**
   * Service for handling email-related operations.
   * <p>
   * The {@link EmailService} is used to send emails to recipients for various notifications such as
   * registration confirmation, password reset, or other important communication. It provides methods for
   * composing and sending emails.
   * </p>
   */
  @Autowired
  private EmailService emailService;

  /**
   * Repository for performing CRUD operations on {@link WalletBalance} entities.
   * <p>
   * The {@link WalletBalanceRepository} is used to interact with the database for wallet balance-related operations.
   * It provides methods for saving, retrieving, and deleting wallet balance records associated with users.
   * </p>
   */
  @Autowired
  private  WalletBalanceRepository walletBalanceRepository;

  /**
   * Registers a new user.
   *
   * @param userInDTO the user information to register
   * @return a UserResponse indicating the result of the registration
   */
  @Override
  public UserResponse registerUser(final UserInDTO userInDTO) {
    LOGGER.info("Attempting to register user with email: {}", userInDTO.getEmail());

    Optional<User> existingUser = userRepository.findByEmail(userInDTO.getEmail().toLowerCase());
    if (existingUser.isPresent()) {
      LOGGER.error("User with email {} already exists", userInDTO.getEmail());
      throw new ResourceAlreadyExistException(Constants.USER_ALREADY_REGISTERED);
    }

    User user = new User();
    user.setFirstName(userInDTO.getFirstName().trim());
    user.setLastName(userInDTO.getLastName().trim());
    user.setEmail(userInDTO.getEmail().toLowerCase());
    user.setPhoneNo(userInDTO.getPhoneNo());
    user.setPassword(Base64Util.encode(userInDTO.getPassword())); // Ensure password is encoded
    user.setRole(Role.valueOf(userInDTO.getRole().toUpperCase()));

    User savedUser = userRepository.save(user);
    LOGGER.info("User registered successfully with ID: {}", savedUser.getId());

    if (Role.USER.equals(user.getRole())) {
      LOGGER.info("Assigning initial wallet balance for user with ID: {}", savedUser.getId());

      WalletBalance walletBalanceEntity = new WalletBalance();
      walletBalanceEntity.setUserId(savedUser.getId());
      walletBalanceEntity.setBalance(Constants.INITIAL_WALLET_BALANCE);
      walletBalanceRepository.save(walletBalanceEntity);

      LOGGER.info("Initial wallet balance assigned for user with ID: {}", savedUser.getId());
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
  public LoginOutDTO loginUser(final String email, final String password) {
    LOGGER.info("Attempting to log in user with email: {}", email);

    Optional<User> userOptional = userRepository.findByEmail(email.toLowerCase());

    if (!userOptional.isPresent()) {
      LOGGER.error("User with email {} not found", email);
      throw new ResourceNotFoundException(Constants.USER_NOT_FOUND);
    }

    User user = userOptional.get();

    if (!Base64Util.encode(password).equals(user.getPassword())) {
      LOGGER.error("Invalid credentials for user with email: {}", email);
      throw new InvalidCredentialsException(Constants.INVALID_CREDENTIALS);
    }

    LOGGER.info("Login successful for user with email: {}", email);

    LoginOutDTO loginOutDTO = new LoginOutDTO();
    loginOutDTO.setId(user.getId());
    loginOutDTO.setRole(user.getRole().name());

    return loginOutDTO;
  }

  /**
   * Retrieves the profile of a user by their ID.
   *
   * @param id the user's ID
   * @return a UserOutDTO with the user's profile information
   */
  @Override
  public UserOutDTO getUserProfile(final Integer id) {
    LOGGER.info("Fetching user profile for ID: {}", id);

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
      userOutDTO.setWalletBalance(walletBalance != null ? walletBalance.getBalance() : null);

      LOGGER.info("User profile fetched successfully for ID: {}", id);
      return userOutDTO;
    } else {
      LOGGER.error("User not found with ID: {}", id);
      throw new ResourceNotFoundException(Constants.USER_NOT_FOUND);
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
  public UserResponse updateUserProfile(final Integer id, final UserInDTO userInDTO) {
    LOGGER.info("Updating user profile for ID: {}", id);

    User user = userRepository.findById(id)
      .orElseThrow(() -> {
        LOGGER.error("User not found with ID: {}", id);
        return new ResourceNotFoundException(Constants.USER_NOT_FOUND);
      });

    user.setFirstName(userInDTO.getFirstName());
    user.setLastName(userInDTO.getLastName());
    user.setPhoneNo(userInDTO.getPhoneNo());
    if (userInDTO.getPassword() != null && !userInDTO.getPassword().isEmpty()) {
      user.setPassword(Base64Util.encode(userInDTO.getPassword()));
    }
    user.setRole(Role.valueOf(userInDTO.getRole().toUpperCase()));

    userRepository.save(user);
    LOGGER.info("User profile updated successfully for ID: {}", id);
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
  public UserResponse deleteUser(final Integer id) {
    LOGGER.info("Attempting to delete user with ID: {}", id);

    User user = userRepository.findById(id)
      .orElseThrow(() -> {
        LOGGER.error(Constants.USER_NOT_FOUND);
        return new ResourceNotFoundException(Constants.USER_NOT_FOUND);
      });

    userRepository.delete(user);
    walletBalanceRepository.deleteByUserId(id);

    LOGGER.info("User and associated wallet balance deleted successfully for ID: {}", id);
    UserResponse userResponse = new UserResponse();
    userResponse.setSuccessMessage(Constants.USER_DELETED_SUCCESSFULLY);
    return userResponse;
  }

  /**
   * Sends an email to a predefined list of recipients.
   *
   * @param emailRequestDTO the details of the email, including subject and body
   * @return a {@link UserResponse} containing a success message if the email was sent successfully
   */
  public UserResponse sendMail(final EmailRequestDTO emailRequestDTO) {
    List<String> recipients = Arrays.asList(
      "bhagyashrigayakwad23@gmail.com",
      "bhagyashrigayakwad26@gmail.com",
      "rajkumargayakwad222@gmail.com"
    );
    emailService.sendMail(Constants.SENDER, emailRequestDTO, recipients);
    UserResponse response = new UserResponse();
    response.setSuccessMessage(Constants.EMAIL_SENT_SUCCESSFULLY);
    return response;
  }
}
