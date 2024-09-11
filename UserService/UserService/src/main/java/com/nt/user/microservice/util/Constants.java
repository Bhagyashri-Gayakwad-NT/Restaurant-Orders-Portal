package com.nt.user.microservice.util;

/**
 * A utility class containing constant values used across the application.
 * These constants include default values, success messages, and error messages for various operations.
 */
public class Constants {

  /**
   * The initial wallet balance assigned to a new user upon registration.
   */
  public static final double INITIAL_WALLET_BALANCE = 1000.0;

  /**
   * Message indicating successful user registration.
   */
  public static final String USER_REGISTERED_SUCCESSFULLY = "User Registered Successfully";

  /**
   * Message indicating that a user was not found.
   */
  public static final String USER_NOT_FOUND = "User not found";

  /**
   * Message indicating successful update of user profile.
   */
  public static final String USER_PROFILE_UPDATED_SUCCESSFULLY = "User profile updated successfully";

  /**
   * Message indicating successful user deletion.
   */
  public static final String USER_DELETED_SUCCESSFULLY = "User deleted successfully";

  /**
   * Message indicating an unexpected error occurred during user deletion.
   */
  public static final String UNEXPECTED_ERROR_DURING_DELETION = "An unexpected error occurred during deletion";

  /**
   * Message indicating successful addition of an address.
   */
  public static final String ADDRESS_ADDED_SUCCESSFULLY = "Address added successfully";

  /**
   * Message indicating successful update of an address.
   */
  public static final String ADDRESS_UPDATED_SUCCESS = "Address updated successfully";

  /**
   * Message indicating failure to add an address.
   */
  public static final String ADDRESS_ADD_FAILURE = "Failed to add address";

  public static final String USER_ALREADY_REGISTERED = "User already registered with this email";

  public static final String INVALID_CREDENTIALS = "Invalid password";

  public static final String ADDRESS_NOT_FOUND = "No addresses found for this user.";
}
