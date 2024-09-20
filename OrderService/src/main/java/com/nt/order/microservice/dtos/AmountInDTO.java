package com.nt.order.microservice.dtos;

import javax.validation.constraints.NotNull;

public class AmountInDTO {

  @NotNull(message = "Balance cannot be null")
  private Double balance;


  public Double getBalance() {
    return balance;
  }

  public void setBalance(Double balance) {
    this.balance = balance;
  }
}
