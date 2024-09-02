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

@Entity
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String firstName;
  private String lastName;
  private String email;
  private String password;
  private String phoneNo;
  @Enumerated(EnumType.STRING)
  @Column(name = "role")
  private Role role;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPhoneNo() {
    return phoneNo;
  }

  public void setPhoneNo(String phoneNo) {
    this.phoneNo = phoneNo;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true; }
    if (o == null || getClass() != o.getClass()) {
      return false; }
    User user = (User) o;
    return Objects.equals(id, user.id) && Objects.equals(firstName, user.firstName) &&
      Objects.equals(lastName, user.lastName) && Objects.equals(email, user.email) &&
      Objects.equals(password, user.password) && Objects.equals(phoneNo, user.phoneNo) && role == user.role;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, lastName, email, password, phoneNo, role);
  }

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


