package com.nt.order.microservice.exception;

public class UnauthorizedException extends RuntimeException{
  public UnauthorizedException(String message) {
    super(message);
  }
}
