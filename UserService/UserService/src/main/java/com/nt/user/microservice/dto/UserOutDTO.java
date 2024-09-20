package com.nt.user.microservice.dto;

import java.util.Objects;

/**
 * UserOutDTO is a Data Transfer Object used to send user information as a response from the server.
 * It includes user details such as ID, name, email, phone number, role, and wallet balance.
 */
public class UserOutDTO {

  /**
   * The unique identifier for the user.
   */
  private Integer id;

  /**
   * The first name of the user.
   */
  private String firstName;

  /**
   * The last name of the user.
   */
  private String lastName;

  /**
   * The email address of the user.
   */
  private String email;

  /**
   * The phone number of the user.
   */
  private String phoneNo;

  /**
   * The role of the user, e.g., "user" or "restaurant_owner".
   */
  private String role;

  /**
   * The current wallet balance of the user.
   */
  private Double walletBalance;

  /**
   * Retrieves the unique identifier for the user.
   *
   * @return the user's ID as an Integer.
   */
  public Integer getId() {
    return id;
  }

  /**
   * Sets the unique identifier for the user.
   *
   * @param id the user's ID.
   */
  public void setId(Integer id) {
    this.id = id;
  }

  /**
   * Retrieves the first name of the user.
   *
   * @return the user's first name as a String.
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Sets the first name of the user.
   *
   * @param firstName the user's first name.
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * Retrieves the last name of the user.
   *
   * @return the user's last name as a String.
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Sets the last name of the user.
   *
   * @param lastName the user's last name.
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * Retrieves the email address of the user.
   *
   * @return the user's email as a String.
   */
  public String getEmail() {
    return email;
  }

  /**
   * Sets the email address of the user.
   *
   * @param email the user's email address.
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Retrieves the phone number of the user.
   *
   * @return the user's phone number as a String.
   */
  public String getPhoneNo() {
    return phoneNo;
  }

  /**
   * Sets the phone number of the user.
   *
   * @param phoneNo the user's phone number.
   */
  public void setPhoneNo(String phoneNo) {
    this.phoneNo = phoneNo;
  }

  /**
   * Retrieves the role of the user (e.g., "user" or "restaurant_owner").
   *
   * @return the user's role as a String.
   */
  public String getRole() {
    return role;
  }

  /**
   * Sets the role of the user (e.g., "user" or "restaurant_owner").
   *
   * @param role the user's role.
   */
  public void setRole(String role) {
    this.role = role;
  }

  /**
   * Retrieves the current wallet balance of the user.
   *
   * @return the user's wallet balance as a Double.
   */
  public Double getWalletBalance() {
    return walletBalance;
  }

  /**
   * Sets the wallet balance of the user.
   *
   * @param walletBalance the user's wallet balance.
   */
  public void setWalletBalance(Double walletBalance) {
    this.walletBalance = walletBalance;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof UserOutDTO)) return false;
    UserOutDTO that = (UserOutDTO) o;
    return Objects.equals(id, that.id) && Objects.equals(firstName, that.firstName) &&
      Objects.equals(lastName, that.lastName) && Objects.equals(email, that.email) &&
      Objects.equals(phoneNo, that.phoneNo) && Objects.equals(role, that.role) &&
      Objects.equals(walletBalance, that.walletBalance);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, lastName, email, phoneNo, role, walletBalance);
  }

  @Override
  public String toString() {
    return "UserOutDTO{" +
      "id=" + id +
      ", firstName='" + firstName + '\'' +
      ", lastName='" + lastName + '\'' +
      ", email='" + email + '\'' +
      ", phoneNo='" + phoneNo + '\'' +
      ", role='" + role + '\'' +
      ", walletBalance=" + walletBalance +
      '}';
  }
}
