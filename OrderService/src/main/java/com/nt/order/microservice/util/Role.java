package com.nt.order.microservice.util;

/**
 * Enum representing the roles available in the application.
 * <p>
 * The application supports two types of roles:
 * </p>
 * <ul>
 *   <li>{@link #USER} - Represents a regular user who can place orders.</li>
 *   <li>{@link #RESTAURANT_OWNER} - Represents a restaurant owner who manages restaurants and menus.</li>
 * </ul>
 */
public enum Role {

  /**
   * Role for regular users of the application, typically customers who place orders.
   */
  USER,

  /**
   * Role for restaurant owners, responsible for managing restaurants, categories, and food items.
   */
  RESTAURANT_OWNER
}
