package com.nt.user.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main class for the User Service microservice application.
 * <p>
 * This class serves as the entry point for the Spring Boot application.
 * It configures the component scanning for the application, ensuring that
 * all components within the `com.nt.user.microservice` package are detected and registered.
 * </p>
 */
@SpringBootApplication
public class UserServiceApplication {

  /**
   * The main method that serves as the entry point for the Spring Boot application.
   * It bootstraps the application, starting the embedded web server and initializing
   * all the Spring components.
   *
   * @param args command-line arguments passed to the application
   */
  public static void main(final String[] args) {
    SpringApplication.run(UserServiceApplication.class, args);
  }
}
