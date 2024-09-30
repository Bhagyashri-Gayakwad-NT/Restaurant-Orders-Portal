package com.nt.user.microservice.dto;

import java.util.Objects;

/**
 * {@code UserResponse} is a simple Data Transfer Object (DTO) that encapsulates
 * a success message related to user operations.
 *
 * <p>
 * This class is typically used to convey the result of successful operations
 * such as registration, updating, or deletion of a user. It contains a single
 * field {@code successMessage}, which stores the message indicating the
 * outcome of the operation.
 * </p>
 *
 * <p>
 * It overrides {@code equals()}, {@code hashCode()}, and {@code toString()}
 * methods from the {@code Object} class for comparison and string
 * representation.
 * </p>
 *
 * <p>
 * Example usage:
 * <pre>
 * UserResponse response = new UserResponse();
 * response.setSuccessMessage("User registered successfully");
 * </pre>
 * </p>
 *
 * @author Sarita Sharma
 * @version 1.0
 * @since 2024-09-24
 */
public class UserResponse {

  /**
   * The message that indicates the success of an operation.
   * It conveys information to the user regarding the outcome of a process.
   */
  private String successMessage;

  /**
   * Retrieves the success message indicating the outcome of the operation.
   *
   * @return a string representing the success message.
   */
  public String getSuccessMessage() {
    return successMessage;
  }

  /**
   * Sets the success message to indicate the result of a user-related operation.
   *
   * @param successMessage the success message to be set, typically denoting
   *                       the successful completion of a process such as user registration.
   */
  public void setSuccessMessage(final String successMessage) {
    this.successMessage = successMessage;
  }

  /**
   * Checks if this {@code UserResponse} is equal to another object.
   *
   * @param o the reference object with which to compare.
   * @return {@code true} if the given object is equal to this {@code UserResponse};
   *         {@code false} otherwise.
   */
  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof UserResponse)) {
      return false;
    }
    UserResponse that = (UserResponse) o;
    return Objects.equals(successMessage, that.successMessage);
  }

  /**
   * Returns a hash code value for this {@code UserResponse}.
   *
   * @return the hash code value of the success message.
   */
  @Override
  public int hashCode() {
    return Objects.hashCode(successMessage);
  }

  /**
   * Returns a string representation of this {@code UserResponse}.
   *
   * <p>The string representation includes the class name and the value of
   * the success message.</p>
   *
   * @return a string representation of the {@code UserResponse}.
   */
  @Override
  public String toString() {
    return "UserResponse{"
      + "successMessage='" + successMessage
      + '\'' + '}';
  }
}
