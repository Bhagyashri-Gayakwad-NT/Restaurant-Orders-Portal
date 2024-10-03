package com.nt.user.microservice.service;

import com.nt.user.microservice.dto.EmailRequestDTO;
import com.nt.user.microservice.dto.LoginOutDTO;
import com.nt.user.microservice.dto.UserInDTO;
import com.nt.user.microservice.dto.UserOutDTO;
import com.nt.user.microservice.dto.UserResponse;
import org.springframework.stereotype.Service;

/**
 * Service interface for managing user-related operations.
 * Provides methods for user registration, login, profile management, and deletion.
 */
@Service
public interface UserService {

  /**
   * Registers a new user in the system.
   *
   * @param userInDTO the data transfer object containing user details for registration
   * @return a response containing the status of the registration operation
   */
  UserResponse registerUser(UserInDTO userInDTO);

  /**
   * Logs in a user with the provided email and password.
   *
   * @param email the email of the user attempting to log in
   * @param password the password of the user attempting to log in
   * @return a data transfer object containing user details if login is successful
   */
  LoginOutDTO loginUser(String email, String password);

  /**
   * Retrieves the profile of a user by their ID.
   *
   * @param id the ID of the user whose profile is to be retrieved
   * @return a data transfer object containing the user's profile information
   */
  UserOutDTO getUserProfile(Integer id);

  /**
   * Updates the profile of an existing user.
   *
   * @param id the ID of the user whose profile is to be updated
   * @param userInDTO the data transfer object containing updated user details
   * @return a response containing the status of the update operation
   */
  UserResponse updateUserProfile(Integer id, UserInDTO userInDTO);

  /**
   * Deletes a user from the system.
   *
   * @param id the ID of the user to be deleted
   * @return a response containing the status of the deletion operation
   */
  UserResponse deleteUser(Integer id);

  /**
   * Sends an email with the specified subject and content.
   *
   * @param emailRequestDTO the DTO containing the email details such as subject and content
   * @return a UserResponse containing the status or result of the email operation
   */
  UserResponse sendMail(EmailRequestDTO emailRequestDTO);
}
