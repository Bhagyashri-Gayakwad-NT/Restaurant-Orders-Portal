package com.nt.restaurant.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * The entry point of the Restaurant Service Application.
 *
 * <p>This class contains the main method that bootstraps the Spring Boot
 * application. It also enables Feign clients to communicate with other
 * microservices.
 *
 * <p>Annotations:
 * <ul>
 *   <li>{@link SpringBootApplication} - Indicates this is a Spring Boot application.
 *   <li>{@link EnableFeignClients} - Enables Feign clients for remote communication between microservices.
 * </ul>
 *
 * <p>Feign is a declarative HTTP client that simplifies invoking RESTful services.
 *
 * <p>Usage: Run this class to start the Restaurant microservice.
 *
 * @author [Your Name]
 * @version 1.0
 */
@EnableFeignClients
@SpringBootApplication
public class RestaurantServiceApplication {
  /**
   * The main method that serves as the entry point for the Spring Boot application.
   *
   * @param args Command-line arguments passed during the execution of the application.
   */
  public static void main(String[] args) {
    SpringApplication.run(RestaurantServiceApplication.class, args);
  }

}

