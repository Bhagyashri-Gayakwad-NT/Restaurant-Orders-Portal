package com.nt.order.microservice.util;

/**
 * Enum representing the status of an order in the system.
 * <p>
 * The order can go through the following statuses:
 * </p>
 * <ul>
 *   <li>{@link #PLACED} - The order has been placed by the user.</li>
 *   <li>{@link #CANCELLED} - The order has been cancelled by the user or system.</li>
 *   <li>{@link #COMPLETED} - The order has been successfully completed.</li>
 * </ul>
 */
public enum OrderStatus {

  /**
   * Status indicating the order has been placed by the user and is awaiting processing.
   */
  PLACED,

  /**
   * Status indicating the order has been cancelled either by the user or due to some issue.
   */
  CANCELLED,

  /**
   * Status indicating the order has been successfully processed and completed.
   */
  COMPLETED
}