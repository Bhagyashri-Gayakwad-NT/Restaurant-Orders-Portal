package com.nt.order.microservice.dtos;

import java.util.Objects;


/**
 * Data Transfer Object for Cart Output.
 * This class represents the details of a cart item
 * to be returned to the client, including cart ID, user ID,
 * restaurant ID, food item ID, quantity, and price.
 */
public class CartOutDTO {

  /** The ID of the cart. */
  private Integer cartId;

  /** The ID of the user associated with the cart. */
  private Integer userId;

  /** The ID of the restaurant associated with the cart. */
  private Integer restaurantId;

  /** The ID of the food item in the cart. */
  private Integer foodItemId;

  /** The quantity of the food item in the cart. */
  private Integer quantity;

  /** The price of the food item in the cart. */
  private Double price;

  /**
   * Default constructor.
   * Initializes an empty CartOutDTO object.
   */
  public CartOutDTO() {
  }

  /**
   * Parameterized constructor to create a CartOutDTO with specified values.
   *
   * @param cartId       the ID of the cart
   * @param userId       the ID of the user
   * @param restaurantId the ID of the restaurant
   * @param foodItemId   the ID of the food item
   * @param quantity     the quantity of the food item
   * @param price        the price of the food item
   */
  public CartOutDTO(final Integer cartId, final Integer userId, final Integer restaurantId,
                    final Integer foodItemId, final Integer quantity, final Double price) {
    this.cartId = cartId;
    this.userId = userId;
    this.restaurantId = restaurantId;
    this.foodItemId = foodItemId;
    this.quantity = quantity;
    this.price = price;
  }

  /**
   * Retrieves the cart ID.
   *
   * @return the cart ID
   */
  public Integer getCartId() {
    return cartId;
  }

  /**
   * Sets the cart ID.
   *
   * @param cartId the cart ID to set
   */
  public void setCartId(final Integer cartId) {
    this.cartId = cartId;
  }

  /**
   * Retrieves the user ID.
   *
   * @return the user ID
   */
  public Integer getUserId() {
    return userId;
  }

  /**
   * Sets the user ID.
   *
   * @param userId the user ID to set
   */
  public void setUserId(final Integer userId) {
    this.userId = userId;
  }

  /**
   * Retrieves the restaurant ID.
   *
   * @return the restaurant ID
   */
  public Integer getRestaurantId() {
    return restaurantId;
  }

  /**
   * Sets the restaurant ID.
   *
   * @param restaurantId the restaurant ID to set
   */
  public void setRestaurantId(final Integer restaurantId) {
    this.restaurantId = restaurantId;
  }

  /**
   * Retrieves the food item ID.
   *
   * @return the food item ID
   */
  public Integer getFoodItemId() {
    return foodItemId;
  }

  /**
   * Sets the food item ID.
   *
   * @param foodItemId the food item ID to set
   */
  public void setFoodItemId(final Integer foodItemId) {
    this.foodItemId = foodItemId;
  }

  /**
   * Retrieves the quantity of the food item.
   *
   * @return the quantity
   */
  public Integer getQuantity() {
    return quantity;
  }

  /**
   * Sets the quantity of the food item.
   *
   * @param quantity the quantity to set
   */
  public void setQuantity(final Integer quantity) {
    this.quantity = quantity;
  }

  /**
   * Retrieves the price of the food item.
   *
   * @return the price
   */
  public Double getPrice() {
    return price;
  }

  /**
   * Sets the price of the food item.
   *
   * @param price the price to set
   */
  public void setPrice(final Double price) {
    this.price = price;
  }

  /**
   * Compares this object to another object for equality.
   *
   * @param o the other object to compare to
   * @return true if the objects are equal, false otherwise
   */
  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CartOutDTO that = (CartOutDTO) o;
    return Objects.equals(cartId, that.cartId)
      && Objects.equals(userId, that.userId)
      && Objects.equals(restaurantId, that.restaurantId)
      && Objects.equals(foodItemId, that.foodItemId)
      && Objects.equals(quantity, that.quantity)
      && Objects.equals(price, that.price);
  }

  /**
   * Generates a hash code for this object.
   *
   * @return the hash code of this object
   */
  @Override
  public int hashCode() {
    return Objects.hash(cartId, userId, restaurantId, foodItemId, quantity, price);
  }

  /**
   * Returns a string representation of this object.
   *
   * @return a string representing this CartOutDTO
   */@Override
  public String toString() {
    return "CartOutDTO{"
      + "cartId=" + cartId
      + ", userId=" + userId
      + ", restaurantId=" + restaurantId
      + ", foodItemId=" + foodItemId
      + ", quantity=" + quantity
      + ", price=" + price
      + '}';
  }
}
