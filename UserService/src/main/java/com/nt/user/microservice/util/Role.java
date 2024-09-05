package com.nt.user.microservice.util;

/**
 * Enum representing different user roles within the application.
 * Each role defines the type of user and their associated permissions.
 */
public enum Role {

  /**
   * Represents a regular user with standard permissions.
   * Users can browse restaurants, place orders, and manage their profiles.
   */
  USER,

  /**
   * Represents a restaurant owner with extended permissions.
   * Restaurant owners can manage their restaurant's menu, view orders, and update their profile.
   */
  RESTAURANT_OWNER
}
