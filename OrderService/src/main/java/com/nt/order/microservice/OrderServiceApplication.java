package com.nt.order.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Main entry point for the Order Service microservice application.
 * <p>
 * This class bootstraps the Spring Boot application and enables the Feign Clients,
 * which allows for making HTTP requests to other services using declarative REST clients.
 * </p>
 */
@EnableFeignClients
@SpringBootApplication
public class OrderServiceApplication {

  /**
   * The main method that serves as the entry point for the Spring Boot application.
   * It initializes and runs the Order Service microservice.
   *
   * @param args command-line arguments (if any) passed when running the application.
   */
  public static void main(final String[] args) {
    SpringApplication.run(OrderServiceApplication.class, args);
  }
}
