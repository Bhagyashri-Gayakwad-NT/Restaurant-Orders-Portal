package com.nt.user.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * The entry point for the User Service application.
 * This class is responsible for bootstrapping the Spring Boot application.
 * <p>
 * It uses the {@link SpringBootApplication} annotation to enable auto-configuration,
 * component scanning, and other Spring Boot configuration features.
 * <p>
 * The {@link ComponentScan} annotation ensures that Spring scans for components
 * (e.g., services, controllers) within the specified base package.
 */

@SpringBootApplication
@ComponentScan(basePackages = "com.nt.user.microservice")
public class UserServiceApplication {

  /**
   * The main method that serves as the entry point of the Spring Boot application.
   *
   * @param args command-line arguments passed during the startup of the application.
   */

  public static void main(String[] args) {
    SpringApplication.run(UserServiceApplication.class, args);
  }

}




