package com.nt.order.microservice.util;

/**
 * Constants class contains all the constant string messages used throughout the application.
 * These constants are used for error messages, success responses, and validation messages.
 */
public class Constants {

  // Cart-related messages
  /**
   * Success message when an item is removed from the cart.
   */
  public static final String CART_ITEM_REMOVED_SUCCESSFULLY = "Cart item removed successfully";
  /**
   * Error message when a cart item is not found.
   */
  public static final String CART_ITEM_NOT_FOUND = "Cart item not found";
  /**
   * Error message when a cart is not found.
   */
  public static final String CART_NOT_FOUND = "Cart not found";
  /**
   * Success message when a cart is cleared after placing an order.
   */
  public static final String CART_CLEARED_SUCCESSFULLY = "Cart cleared successfully after placing the order.";
  /**
   * Error message when there is a price mismatch for a food item in the cart.
   */
  public static final String PRICE_MISMATCH = "Price mismatch between request and actual food item price.";
  /**
   * Success message when the quantity of an item is updated in the cart.
   */
  public static final String ITEM_QUANTITY_UPDATED_SUCCESS = "Item quantity updated successfully in the cart.";
  /**
   * Success message when an item is removed from the cart.
   */
  public static final String ITEM_REMOVED_SUCCESSFULLY = "Item removed from cart successfully.";
  /**
   * Success message when an item is added to the cart.
   */
  public static final String ITEM_ADDED_TO_CART_SUCCESS = "Item added to cart successfully.";

  // Order-related messages
  /**
   * Success message when an order is cancelled.
   */
  public static final String ORDER_CANCELLED_SUCCESSFULLY = "Order cancelled successfully.";
  /**
   * Success message when an order is marked as completed.
   */
  public static final String ORDER_COMPLETED_SUCCESSFULLY = "Order has been marked as completed successfully";
  /**
   * Error message when a user is not found.
   */
  public static final String USER_NOT_FOUND = "User not found";
  /**
   * Error message when an order is not found.
   */
  public static final String ORDER_NOT_FOUND = "Order not found";
  /**
   * Error message when a restaurant ID is invalid.
   */
  public static final String INVALID_RESTAURANT_ID = "Invalid restaurant ID.";
  /**
   * Error message when a food item ID is invalid.
   */
  public static final String INVALID_FOOD_ITEM_ID = "Invalid food item ID.";
  /**
   * Error message when a cart quantity is less than 1.
   */
  public static final String INVALID_CART_QUANTITY = "Quantity cannot be less than 1.";
  /**
   * Error message when a user's cart is empty.
   */
  public static final String CART_EMPTY_FOR_USER = "No items found in the cart for this User";

  // Authorization messages
  /**
   * Error message when a user is unauthorized to perform an action.
   */
  public static final String UNAUTHORIZED_USER = "Unauthorized: Only the restaurant owner can mark the order as completed.";
  /**
   * Error message when an order is already completed.
   */
  public static final String ALREADY_COMPLETED = "Order is already completed.";
  /**
   * Error message when a restaurant owner is attempting to perform a user-only action.
   */
  public static final String RESTAURANT_OWNER = "User is Restaurant Owner";

  // Order cancellation-related messages
  /**
   * Error message when the time limit for order cancellation has been exceeded.
   */
  public static final String ORDER_CANCELLATION_TIME_LIMIT_EXCEEDED = "Order cancellation time limit exceeded";

  // Cart restrictions for restaurant owners
  /**
   * Error message when a restaurant owner attempts to add items to the cart.
   */
  public static final String RESTAURANT_OWNER_CART_ERROR = "Restaurant owners cannot add items to the cart.";
  /**
   * Error message when a restaurant owner attempts to place an order.
   */
  public static final String RESTAURANT_OWNER_ORDER_ERROR = "Restaurant owners cannot place an order";

  // General error messages
  /**
   * Error message for unsupported methods.
   */
  public static final String METHODE_NOT_ALLOWED = "Method not allowed";
  /**
   * Error message when no addresses are found for a user.
   */
  public static final String ADDRESS_NOT_FOUND = "No addresses found for this user.";
  /**
   * Error message when a food item is not found.
   */
  public static final String FOODITEM_NOT_FOUND = "Food Item not found for this id.";
  /**
   * Error message when an address ID is invalid.
   */
  public static final String INVALID_ADDRESS_ID = "Invalid address Id.";

  // Order placement-related messages
  /**
   * Success message when an order is placed successfully.
   */
  public static final String ORDER_PLACED_SUCCESSFULLY = "Order placed successfully!";
  /**
   * Error message when a food item does not belong to a specified restaurant.
   */
  public static final String FOOD_ITEM_DOES_NOT_BELONG_TO_RESTAURANT = "Food item does not belong to the specified restaurant";

  // Wallet-related messages
  /**
   * Error message when a wallet has insufficient balance.
   */
  public static final String INSUFFICIENT_BALANCE = "Insufficient balance in wallet.";

}

