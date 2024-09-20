package com.nt.user.microservice.contoller;

import com.nt.user.microservice.dto.AmountInDTO;
import com.nt.user.microservice.dto.LogInDTO;
import com.nt.user.microservice.dto.LoginOutDTO;
import com.nt.user.microservice.dto.UserInDTO;
import com.nt.user.microservice.dto.UserOutDTO;
import com.nt.user.microservice.dto.UserResponse;
import com.nt.user.microservice.service.UserService;
import com.nt.user.microservice.service.WalletBalanceService;
import com.nt.user.microservice.util.Constants;
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
import org.springframework.web.bind.annotation.RequestParam;
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
   * Logger for the UserController class, used for logging important information
   * such as requests, success, and error messages during execution.
   */
  private static final Logger logger = LogManager.getLogger(UserController.class);

  /**
   * Service layer responsible for handling user-related operations.
   * <p>
   * The {@link UserService} is injected via the constructor and provides methods for user
   * registration, login, profile management, and deletion. It encapsulates the business logic
   * related to user operations and interacts with the underlying data layer to perform these tasks.
   * </p>
   */
  private final UserService userService;

  @Autowired
  private WalletBalanceService walletBalanceService;


  /**
   * Constructor for UserController, initializes the UserService.
   *
   * @param userService the service layer that handles user-related operations
   */
  public UserController(UserService userService) {
    this.userService = userService;
  }

  /**
   * Registers a new user.
   *
   * @param userInDTO the user information to register.
   * @return a response entity with a success message if registration is successful.
   */
  @PostMapping("/register")
  public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody UserInDTO userInDTO) {
    logger.info("Registering user with email: {}", userInDTO.getEmail());
    UserResponse userResponse = userService.registerUser(userInDTO);
    logger.info("User registered successfully: {}", userInDTO.getEmail());
    return new ResponseEntity<UserResponse>(userResponse, HttpStatus.CREATED);
  }

  /**
   * Logs in a user.
   *
   * @param loginDTO the login credentials.
   * @return a response entity with user details if login is successful.
   */
  @PostMapping("/login")
  public ResponseEntity<LoginOutDTO> loginUser(@Valid @RequestBody LogInDTO loginDTO) {
    logger.info("Attempting to login user with email: {}", loginDTO.getEmail());
    LoginOutDTO userOutDTO = userService.loginUser(loginDTO.getEmail(), loginDTO.getPassword());
    logger.info("User logged in successfully: {}", loginDTO.getEmail());
    return ResponseEntity.ok(userOutDTO);
  }

  /**
   * Retrieves the profile of a user by ID.
   *
   * @param id the ID of the user whose profile is to be fetched.
   * @return a response entity with the user's profile details.
   */
  @GetMapping("/profile/{id}")
  public ResponseEntity<UserOutDTO> getUserProfile(@PathVariable Integer id) {
    logger.info("Fetching profile for user with ID: {}", id);
    UserOutDTO userOutDTO = userService.getUserProfile(id);
    logger.info("Profile fetched successfully for user ID: {}", id);
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
  public ResponseEntity<UserResponse> updateUserProfile(@Valid @PathVariable Integer id, @RequestBody UserInDTO userInDTO) {
    logger.info("Updating profile for user with ID: {}", id);
    UserResponse result = userService.updateUserProfile(id, userInDTO);
    logger.info("Profile updated successfully for user ID: {}", id);
    return new ResponseEntity<UserResponse>(result, HttpStatus.OK);
  }

  /**
   * Deletes a user by ID.
   *
   * @param id the ID of the user to be deleted.
   * @return a response entity with a success message if the deletion is successful.
   */
  @DeleteMapping("/delete/{id}")
  public ResponseEntity<UserResponse> deleteUser(@PathVariable Integer id) {
    logger.info("Request received to delete user with ID: {}", id);
    UserResponse userResponse = userService.deleteUser(id);
    logger.info("User deleted successfully with ID: {}", id);
    return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
  }

  @PutMapping("/addMoney/{userId}")
  public ResponseEntity<UserOutDTO> addMoney(@PathVariable Integer userId, @RequestBody AmountInDTO amountInDto) {
    logger.info("Adding {} to wallet for user ID: {}", amountInDto.getBalance(), userId);
    UserOutDTO userResponse = walletBalanceService.addMoney(userId, amountInDto.getBalance());
    return new ResponseEntity<>(userResponse, HttpStatus.OK);
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
  public ResponseEntity<UserOutDTO> updateWalletBalance(@PathVariable Integer id, @RequestBody AmountInDTO amountInDTO) {
    //logger.info("Deducting {} from user ID: {}", amount, id);
    UserOutDTO userResponse = walletBalanceService.updateWalletBalance(id, amountInDTO.getBalance());
    return new ResponseEntity<>(userResponse, HttpStatus.OK);
  }


  @PostMapping("/send")
  public ResponseEntity<UserResponse> sendEmail(@RequestParam String text, @RequestParam String subject) {
    userService.sendMail(text, subject);
    UserResponse response = new UserResponse();
    response.setSuccessMessage(Constants.EMAIL_SENT_SUCCESSFULLY);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}