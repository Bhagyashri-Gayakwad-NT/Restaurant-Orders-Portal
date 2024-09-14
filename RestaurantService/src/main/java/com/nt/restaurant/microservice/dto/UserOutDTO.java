package com.nt.restaurant.microservice.dto;


/**
 * Data Transfer Object (DTO) for representing user information in the response.
 * This class is used to convey user details from the server to the client.
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
   * Default constructor for creating an empty {@code UserOutDTO} instance.
   */
  public UserOutDTO() {
  }

  /**
   * Constructor for creating a {@code UserOutDTO} instance with specified values.
   *
   * @param id            the unique identifier for the user.
   * @param firstName     the first name of the user.
   * @param lastName      the last name of the user.
   * @param email         the email address of the user.
   * @param phoneNo       the phone number of the user.
   * @param role          the role of the user (e.g., "user" or "restaurant_owner").
   * @param walletBalance the current wallet balance of the user.
   */
  public UserOutDTO(Integer id, String firstName, String lastName, String email, String phoneNo, String role, Double walletBalance) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phoneNo = phoneNo;
    this.role = role;
    this.walletBalance = walletBalance;
  }

  /**
   * Retrieves the unique identifier for the user.
   *
   * @return the user's ID as an {@code Integer}.
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
   * @return the user's first name as a {@code String}.
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
   * @return the user's last name as a {@code String}.
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
   * @return the user's email as a {@code String}.
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
   * @return the user's phone number as a {@code String}.
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
   * @return the user's role as a {@code String}.
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
   * @return the user's wallet balance as a {@code Double}.
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
}
