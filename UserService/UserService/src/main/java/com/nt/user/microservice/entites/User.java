package com.nt.user.microservice.entites;

import com.nt.user.microservice.util.Role;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

/**
 * Represents a user entity for the application.
 * This entity is mapped to the "users" table in the database.
 */
@Entity
@Table(name = "users")
public class User {

  /**
   * The unique identifier for the user.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
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
   * The password for the user's account.
   * Passwords should be stored in encrypted format.
   */
  private String password;

  /**
   * The phone number of the user.
   */
  private String phoneNo;

  /**
   * The role of the user, either USER or RESTAURANT_OWNER.
   * This field is stored as a string in the database.
   */
  @Enumerated(EnumType.STRING)
  @Column(name = "role")
  private Role role;

  /**
   * Gets the unique identifier of the user.
   *
   * @return the user's ID.
   */
  public Integer getId() {
    return id;
  }

  /**
   * Sets the unique identifier for the user.
   *
   * @param id the user's ID to set.
   */
  public void setId(Integer id) {
    this.id = id;
  }

  /**
   * Gets the first name of the user.
   *
   * @return the user's first name.
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Sets the first name for the user.
   *
   * @param firstName the user's first name to set.
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * Gets the last name of the user.
   *
   * @return the user's last name.
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Sets the last name for the user.
   *
   * @param lastName the user's last name to set.
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * Gets the email address of the user.
   *
   * @return the user's email.
   */
  public String getEmail() {
    return email;
  }

  /**
   * Sets the email address for the user.
   *
   * @param email the user's email to set.
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Gets the password for the user's account.
   *
   * @return the user's password.
   */
  public String getPassword() {
    return password;
  }

  /**
   * Sets the password for the user's account.
   * Ensure that passwords are encrypted before storing.
   *
   * @param password the user's password to set.
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Gets the phone number of the user.
   *
   * @return the user's phone number.
   */
  public String getPhoneNo() {
    return phoneNo;
  }

  /**
   * Sets the phone number for the user.
   *
   * @param phoneNo the user's phone number to set.
   */
  public void setPhoneNo(String phoneNo) {
    this.phoneNo = phoneNo;
  }

  /**
   * Gets the role of the user.
   *
   * @return the user's role.
   */
  public Role getRole() {
    return role;
  }

  /**
   * Sets the role for the user.
   *
   * @param role the user's role to set.
   */
  public void setRole(Role role) {
    this.role = role;
  }

  /**
   * Compares this user to the specified object. The result is true if and only if
   * the argument is not null and is a User object that has the same values for all fields.
   *
   * @param o the object to compare this user against.
   * @return true if the given object represents a User equivalent to this user, false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true; }
    if (o == null || getClass() != o.getClass()) {
      return false; }
    User user = (User) o;
    return Objects.equals(id, user.id) &&
      Objects.equals(firstName, user.firstName) &&
      Objects.equals(lastName, user.lastName) &&
      Objects.equals(email, user.email) &&
      Objects.equals(password, user.password) &&
      Objects.equals(phoneNo, user.phoneNo) &&
      role == user.role;
  }

  /**
   * Returns a hash code value for the user. This method is supported for the benefit
   * of hash tables such as those provided by HashMap.
   *
   * @return a hash code value for this user.
   */
  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, lastName, email, password, phoneNo, role);
  }

  /**
   * Returns a string representation of the user, including the values of all fields.
   *
   * @return a string representation of the user.
   */
  @Override
  public String toString() {
    return "User{" +
      "id=" + id +
      ", firstName='" + firstName + '\'' +
      ", lastName='" + lastName + '\'' +
      ", email='" + email + '\'' +
      ", password='" + password + '\'' +
      ", phoneNo='" + phoneNo + '\'' +
      ", role=" + role +
      '}';
  }
}
