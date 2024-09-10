package com.nt.user.microservice.contoller;

import com.nt.user.microservice.dto.LogInDTO;
import com.nt.user.microservice.dto.UserInDTO;
import com.nt.user.microservice.dto.UserOutDTO;
import com.nt.user.microservice.dto.UserResponse;
import com.nt.user.microservice.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

  private static final Logger logger = LogManager.getLogger(UserController.class);

  private final UserService userService;

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
    try {
      UserResponse userResponse = userService.registerUser(userInDTO);
      logger.info("User registered successfully: {}", userInDTO.getEmail());
      return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    } catch (Exception e) {
      logger.error("Error registering user with email: {}. Error: {}", userInDTO.getEmail(), e.getMessage());
      throw e;
    }
  }

  /**
   * Logs in a user.
   *
   * @param loginDTO the login credentials.
   * @return a response entity with user details if login is successful.
   */
  @PostMapping("/login")
  public ResponseEntity<UserOutDTO> loginUser(@Valid @RequestBody LogInDTO loginDTO) {
    logger.info("Attempting to login user with email: {}", loginDTO.getEmail());
    try {
      UserOutDTO userOutDTO = userService.loginUser(loginDTO.getEmail(), loginDTO.getPassword());
      logger.info("User logged in successfully: {}", loginDTO.getEmail());
      return ResponseEntity.ok(userOutDTO);
    } catch (Exception e) {
      logger.error("Error logging in user with email: {}. Error: {}", loginDTO.getEmail(), e.getMessage());
      throw e;
    }
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
    try {
      UserOutDTO userOutDTO = userService.getUserProfile(id);
      logger.info("Profile fetched successfully for user ID: {}", id);
      return new ResponseEntity<>(userOutDTO, HttpStatus.OK);
    } catch (Exception e) {
      logger.error("Error fetching profile for user ID: {}. Error: {}", id, e.getMessage());
      throw e;
    }
  }

  /**
   * Updates the profile of a user by ID.
   *
   * @param id the ID of the user to be updated.
   * @param userInDTO the updated user information.
   * @return a response entity with a success message if the update is successful.
   */
  @PutMapping("/update/{id}")
  public ResponseEntity<?> updateUserProfile(@Valid @PathVariable Integer id, @RequestBody UserInDTO userInDTO) {
    logger.info("Updating profile for user with ID: {}", id);
    try {
      UserResponse result = userService.updateUserProfile(id, userInDTO);
      logger.info("Profile updated successfully for user ID: {}", id);
      return new ResponseEntity<>(result, HttpStatus.OK);
    } catch (Exception e) {
      logger.error("Error updating profile for user ID: {}. Error: {}", id, e.getMessage());
      throw e;
    }
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
    try {
      UserResponse userResponse = userService.deleteUser(id);
      logger.info("User deleted successfully with ID: {}", id);
      return new ResponseEntity<>(userResponse, HttpStatus.OK);
    } catch (Exception e) {
      logger.error("Error deleting user with ID: {}. Error: {}", id, e.getMessage());
      throw e;
    }
  }
}
