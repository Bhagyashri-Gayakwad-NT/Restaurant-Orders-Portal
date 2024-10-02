package com.nt.restaurant.microservice.util;

/**
 * Enumeration representing the roles within the restaurant service.
 * This enum defines the different types of roles that a user can have.
 */
public enum Role {

  /**
   * Represents a regular user role.
   * Users with this role can interact with the restaurant service but do not have administrative privileges.
   */
  USER,

  /**
   * Represents a restaurant owner role.
   * Users with this role have administrative privileges related to managing restaurants and food items.
   */
  RESTAURANT_OWNER
}
