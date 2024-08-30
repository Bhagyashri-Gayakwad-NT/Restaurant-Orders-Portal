package com.nt.user.microservice.contoller;

import com.nt.user.microservice.indto.LogInDTO;
import com.nt.user.microservice.indto.UserInDTO;
import com.nt.user.microservice.outdto.UserOutDTO;
import com.nt.user.microservice.outdto.UserResponse;
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

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {

  private static final Logger logger = LogManager.getLogger(UserController.class);

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/register")
  public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody UserInDTO userInDTO) {
    logger.info("Registering user with email: {}", userInDTO.getEmail());
    System.out.println("Password=" + userInDTO.getPassword());
    String userAdded = userService.registerUser(userInDTO);
    UserResponse successMessage = new UserResponse();
    successMessage.setSuccessMessage(userAdded);
    logger.info("User registered successfully: {}", userInDTO.getEmail());
    return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
  }

  @PostMapping("/login")
  public ResponseEntity<UserOutDTO> loginUser(@Valid @RequestBody LogInDTO loginDTO) {
    logger.info("Attempting to login user with email: {}", loginDTO.getEmail());
    UserOutDTO userOutDTO = userService.loginUser(loginDTO.getEmail(), loginDTO.getPassword());
    logger.info("User logged in successfully: {}", loginDTO.getEmail());
    return ResponseEntity.ok(userOutDTO);
  }

  @GetMapping("/profile/{id}")
  public ResponseEntity<UserOutDTO> getUserProfile(@PathVariable Integer id) {
    logger.info("Fetching profile for user with ID: {}", id);
    UserOutDTO userOutDTO = userService.getUserProfile(id);
    logger.info("Profile fetched successfully for user ID: {}", id);
    return new ResponseEntity<>(userOutDTO, HttpStatus.OK);
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<?> updateUserProfile(@PathVariable Integer id, @RequestBody UserInDTO userInDTO) {
    logger.info("Updating profile for user with ID: {}", id);
    UserResponse result = userService.updateUserProfile(id, userInDTO);
    logger.info("Profile updated successfully for user ID: {}", id);
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
    logger.info("Deleting user with ID: {}", id);
    userService.deleteUser(id);
    logger.info("User deleted successfully with ID: {}", id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}




