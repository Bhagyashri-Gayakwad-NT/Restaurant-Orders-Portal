package com.nt.user.microservice.contoller;

import com.nt.user.microservice.dto.AmountInDTO;
import com.nt.user.microservice.dto.EmailRequestDTO;
import com.nt.user.microservice.dto.LogInDTO;
import com.nt.user.microservice.dto.LoginOutDTO;
import com.nt.user.microservice.dto.UserInDTO;
import com.nt.user.microservice.dto.UserOutDTO;
import com.nt.user.microservice.dto.UserResponse;
import com.nt.user.microservice.service.UserService;
import com.nt.user.microservice.service.WalletBalanceService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Controller for managing user-related operations such as registration, login, profile management, and deletion.
 */
@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {

  /**
   * LOGGER for the UserController class, used for logging important information
   * such as requests, success, and error messages during execution.
   */
  private static final Logger LOGGER = LogManager.getLogger(UserController.class);

  /**
   * Service layer responsible for handling user-related operations.
   * <p>
   * The {@link UserService} is injected via the constructor and provides methods for user
   * registration, login, profile management, and deletion. It encapsulates the business logic
   * related to user operations and interacts with the underlying data layer to perform these tasks.
   * </p>
   */
  @Autowired
  private  UserService userService;

  /**
   * Service layer responsible for managing user wallet balance operations.
   * <p>
   * The {@link WalletBalanceService} is injected via the constructor and provides methods for
   * retrieving and updating the wallet balance of users. It encapsulates the business logic
   * related to wallet operations and interacts with the underlying data layer to perform these tasks.
   * </p>
   */
  @Autowired
  private WalletBalanceService walletBalanceService;

  /**
   * Registers a new user.
   *
   * @param userInDTO the user information to register.
   * @return a response entity with a success message if registration is successful.
   */
  @PostMapping("/register")
  public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody final UserInDTO userInDTO) {
    LOGGER.info("Registering user with email: {}", userInDTO.getEmail());
    UserResponse userResponse = userService.registerUser(userInDTO);
    LOGGER.info("User registered successfully: {}", userInDTO.getEmail());
    return new ResponseEntity<UserResponse>(userResponse, HttpStatus.CREATED);
  }

  /**
   * Logs in a user.
   *
   * @param loginDTO the login credentials.
   * @return a response entity with user details if login is successful.
   */
  @PostMapping("/login")
  public ResponseEntity<LoginOutDTO> loginUser(@Valid @RequestBody final LogInDTO loginDTO) {
    LOGGER.info("Attempting to login user with email: {}", loginDTO.getEmail());
    LoginOutDTO userOutDTO = userService.loginUser(loginDTO.getEmail(), loginDTO.getPassword());
    LOGGER.info("User logged in successfully: {}", loginDTO.getEmail());
    return ResponseEntity.ok(userOutDTO);
  }

  /**
   * Retrieves the profile of a user by ID.
   *
   * @param id the ID of the user whose profile is to be fetched.
   * @return a response entity with the user's profile details.
   */
  @GetMapping("/profile/{id}")
  public ResponseEntity<UserOutDTO> getUserProfile(@PathVariable final Integer id) {
    LOGGER.info("Fetching profile for user with ID: {}", id);
    UserOutDTO userOutDTO = userService.getUserProfile(id);
    LOGGER.info("Profile fetched successfully for user ID: {}", id);
    return new ResponseEntity<UserOutDTO>(userOutDTO, HttpStatus.OK);
  }

  /**
   * Updates the profile of a user by ID.
   *
   * @param id the ID of the user to be updated.
   * @param userInDTO the updated user information.
   * @return a response entity with a success message if the update is successful.
   */
  @PutMapping("/update/{id}")
  public ResponseEntity<UserResponse> updateUserProfile(@Valid @PathVariable final Integer id,
                                                        @RequestBody final UserInDTO userInDTO) {
    LOGGER.info("Updating profile for user with ID: {}", id);
    UserResponse result = userService.updateUserProfile(id, userInDTO);
    LOGGER.info("Profile updated successfully for user ID: {}", id);
    return new ResponseEntity<UserResponse>(result, HttpStatus.OK);
  }

  /**
   * Deletes a user by ID.
   *
   * @param id the ID of the user to be deleted.
   * @return a response entity with a success message if the deletion is successful.
   */
  @DeleteMapping("/delete/{id}")
  public ResponseEntity<UserResponse> deleteUser(@PathVariable final Integer id) {
    LOGGER.info("Request received to delete user with ID: {}", id);
    UserResponse userResponse = userService.deleteUser(id);
    LOGGER.info("User deleted successfully with ID: {}", id);
    return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
  }

  /**
   * Adds money to the user's wallet.
   *
   * @param userId the ID of the user whose wallet will be updated.
   * @param amountInDto the amount to be added to the user's wallet, encapsulated in an {@link AmountInDTO} object.
   * @return a response entity containing the updated user information after adding the money.
   */
  @PutMapping("/addMoney/{userId}")
  public ResponseEntity<UserOutDTO> addMoney(@PathVariable final Integer userId, @RequestBody final AmountInDTO amountInDto) {
    LOGGER.info("Adding {} to wallet for user ID: {}", amountInDto.getBalance(), userId);
    UserOutDTO userResponse = walletBalanceService.addMoney(userId, amountInDto.getBalance());
    return new ResponseEntity<UserOutDTO>(userResponse, HttpStatus.OK);
  }
  /**
   * Updates the wallet balance for a user.
   * <p>
   * This method handles HTTP PUT requests to deduct a specific amount from a user's wallet balance.
   * The amount to be deducted is provided in the request body.
   * </p>
   *
   * @param id the ID of the user whose wallet balance will be updated.
   * @param amountInDTO the amount to be deducted from the user's wallet balance.
   * @return a {@link ResponseEntity} containing the updated {@link UserOutDTO} with the new wallet balance and HTTP status 200.
   */
  @PutMapping("/walletBalance/{id}")
  public ResponseEntity<UserOutDTO> updateWalletBalance(@PathVariable final Integer id,
                                                        @RequestBody final AmountInDTO amountInDTO) {
    UserOutDTO userResponse = walletBalanceService.updateWalletBalance(id, amountInDTO.getBalance());
    return new ResponseEntity<UserOutDTO>(userResponse, HttpStatus.OK);
  }

  /**
   * Sends an email with the specified text and subject.
   *
   * <p>This method processes a POST request to send an email using the provided {@code EmailRequestDTO}.
   * If successful, it returns a success message and an HTTP status of {@code 200 OK}.</p>
   *
   * @param emailInDTO the email details (subject, recipients, body)
   * @return a {@code ResponseEntity<UserResponse>} with a success message and HTTP status OK
   */
  @PostMapping("/send")
  public ResponseEntity<UserResponse> sendEmail(@Valid @RequestBody final EmailRequestDTO emailInDTO) {
    UserResponse response = userService.sendMail(emailInDTO);
    return new ResponseEntity<UserResponse>(response, HttpStatus.OK);
  }
}
