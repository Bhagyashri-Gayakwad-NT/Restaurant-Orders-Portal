package com.nt.user.microservice.outdto;

public class UserOutDTO {
  private Integer id;
  private String firstName;
  private String lastName;
  private String email;
  private String phoneNo;
  private String role;
  private Double walletBalance;

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

  public String getPhoneNo() {
    return phoneNo;
  }

  public void setPhoneNo(String phoneNo) {
    this.phoneNo = phoneNo;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public Double getWalletBalance() {
    return walletBalance;
  }

  public void setWalletBalance(Double walletBalance) {
    this.walletBalance = walletBalance;
  }

}




