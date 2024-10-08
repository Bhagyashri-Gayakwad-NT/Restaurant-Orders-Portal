package com.nt.user.microservice.util;

/**
 * A utility class containing constant values used throughout the application.
 * <p>
 * This class defines various constant values including default settings, success messages,
 * error messages, and other string literals and numeric values that are used in different parts
 * of the application to ensure consistency and avoid hard-coding values directly in the code.
 * </p>
 */
public class Constants {

  /**
   * The initial wallet balance assigned to a new user upon registration.
   * <p>
   * This constant represents the default amount of money that is credited to a new user's wallet
   * when they are first registered in the system.
   * </p>
   */
  public static final Double INITIAL_WALLET_BALANCE = 1000.0;

  /**
   * Message indicating that a user has been successfully registered.
   * <p>
   * This constant is used as a response message to indicate that the user registration process
   * was completed successfully.
   * </p>
   */
  public static final String USER_REGISTERED_SUCCESSFULLY = "User Registered Successfully";

  /**
   * Message indicating that a user was not found in the system.
   * <p>
   * This constant is used when an operation cannot be completed because the specified user
   * does not exist in the database.
   * </p>
   */
  public static final String USER_NOT_FOUND = "User not found";

  /**
   * Message indicating that a user's profile has been successfully updated.
   * <p>
   * This constant is used as a response message to signify that the user's profile information
   * has been successfully modified and saved.
   * </p>
   */
  public static final String USER_PROFILE_UPDATED_SUCCESSFULLY = "User profile updated successfully";

  /**
   * Message indicating that a user has been successfully deleted.
   * <p>
   * This constant is used as a confirmation message after a user has been removed from the system
   * and all associated data has been cleaned up.
   * </p>
   */
  public static final String USER_DELETED_SUCCESSFULLY = "User deleted successfully";

  /**
   * Message indicating that an unexpected error occurred during the user deletion process.
   * <p>
   * This constant is used to report errors that are not specifically handled but occur during
   * the user deletion process.
   * </p>
   */
  public static final String UNEXPECTED_ERROR_DURING_DELETION = "An unexpected error occurred during deletion";

  /**
   * Message indicating that an address has been successfully added.
   * <p>
   * This constant is used as a response message when a new address is added to a user's profile.
   * </p>
   */
  public static final String ADDRESS_ADDED_SUCCESSFULLY = "Address added successfully";

  /**
   * Message indicating that an address has been successfully updated.
   * <p>
   * This constant is used as a response message when an existing address is modified and saved.
   * </p>
   */
  public static final String ADDRESS_UPDATED_SUCCESS = "Address updated successfully";

  /**
   * Message indicating that there was a failure in adding an address.
   * <p>
   * This constant is used when an attempt to add a new address fails, possibly due to validation
   * errors or system issues.
   * </p>
   */
  public static final String ADDRESS_ADD_FAILURE = "Failed to add address";

  /**
   * Message indicating that a user is already registered with the provided email.
   * <p>
   * This constant is used to notify that the registration attempt failed because the email is
   * already associated with an existing user.
   * </p>
   */
  public static final String USER_ALREADY_REGISTERED = "User already registered with this email";

  /**
   * Message indicating that the provided credentials are invalid.
   * <p>
   * This constant is used when a login attempt fails due to incorrect password or other credential
   * issues.
   * </p>
   */
  public static final String INVALID_CREDENTIALS = "Invalid password";

  /**
   * Message indicating that no addresses were found for the specified user.
   * <p>
   * This constant is used when a request to retrieve addresses for a user returns an empty result,
   * meaning no addresses are associated with the user.
   * </p>
   */
  public static final String ADDRESS_NOT_FOUND = "No addresses found for this user.";

  /**
   * Message indicating insufficient balance in the user's wallet.
   * <p>
   * This constant is used when an operation fails due to the user not having enough funds in their wallet.
   * </p>
   */
  public static final String INSUFFICIENT_BALANCE = "Insufficient balance in wallet.";

  /**
   * Message indicating that the HTTP method is not allowed for the requested resource.
   * <p>
   * This constant is used when a client attempts to use a method that is not supported by the server for the specific endpoint.
   * </p>
   */
  public static final String METHOD_NOT_ALLOWED = "Method not allowed";

  /**
   * Error message indicating that the subject of an email cannot be null or empty.
   * <p>
   * This constant is used in validation scenarios to ensure that the subject field is provided.
   * </p>
   */
  public static final String SUBJECT_EMPTY_ERROR = "Subject cannot be null or empty.";

  /**
   * Error message indicating that the recipient list for an email cannot be null or empty.
   * <p>
   * This constant is used in validation scenarios to ensure that at least one recipient is specified.
   * </p>
   */
  public static final String RECIPIENT_LIST_EMPTY_ERROR = "Recipient list cannot be null or empty.";

  /**
   * Error message indicating that the recipient email cannot be null or empty.
   * <p>
   * This constant is used in validation scenarios to ensure that the recipient's email address is provided.
   * </p>
   */
  public static final String RECIPIENT_EMAIL_EMPTY_ERROR = "Recipient email cannot be null or empty: ";

  /**
   * Error message indicating that the provided email format is invalid.
   * <p>
   * This constant is used in validation scenarios to notify users of incorrect email formats.
   * </p>
   */
  public static final String INVALID_EMAIL_FORMAT_ERROR = "Invalid email format: ";

  /**
   * Error message indicating that the email body cannot be null or empty.
   * <p>
   * This constant is used in validation scenarios to ensure that an email body is provided.
   * </p>
   */
  public static final String EMAIL_BODY_EMPTY_ERROR = "Email body cannot be null or empty.";

  /**
   * Message indicating that the email has been sent successfully.
   * <p>
   * This constant is used to provide feedback to users upon successful email transmission.
   * </p>
   */
  public static final String EMAIL_SENT_SUCCESSFULLY = "Email sent successfully.";

  /**
   * The sender's email address used for sending emails.
   * <p>
   * This constant defines the email address that will be displayed as the sender when emails are sent.
   * </p>
   */
  public static final String SENDER = "bhagyashrigayakwad23@gmail.com";

  /**
   * Error message indicating that the request body is invalid.
   * <p>
   * This constant is used to inform clients when the provided request body does not conform to expected formats.
   * </p>
   */
  public static final String INVALID_REQUEST_BODY_ERROR = "Invalid request body";

  /**
   * Error message indicating that no content was provided in the request body or that the format is invalid.
   * <p>
   * This constant is used in scenarios where the request body is either empty or not formatted correctly.
   * </p>
   */
  public static final String EMPTY_CONTENT_ERROR = "No content was provided in the request body or the format is invalid";

  /**
   * Error message indicating that the wallet was not found for a specific user.
   * <p>
   * This constant is used when attempting to retrieve a wallet balance for a user, and no corresponding wallet is found.
   * </p>
   */
  public static final String WALLET_NOT_FOUND = "Wallet not found for this user";

}

