package com.nt.order.microservice.dtos;

import java.util.Objects;


/**
 * Data Transfer Object for Cart Output.
 * This class represents the details of a cart item
 * to be returned to the client, including cart ID, user ID,
 * restaurant ID, food item ID, quantity, and price.
 */
public class CartOutDTO {

  private Integer cartId;
  private Integer userId;
  private Integer restaurantId;
  private Integer foodItemId;
  private Integer quantity;
  private Double price;

  /**
   * Default constructor.
   */
  public CartOutDTO() {
  }

  /**
   * Parameterized constructor to create a CartOutDTO with specified values.
   *
   * @param cartId        the ID of the cart
   * @param userId        the ID of the user
   * @param restaurantId   the ID of the restaurant
   * @param foodItemId    the ID of the food item
   * @param quantity       the quantity of the food item
   * @param price          the price of the food item
   */
  public CartOutDTO(Integer cartId, Integer userId, Integer restaurantId, Integer foodItemId, Integer quantity, Double price) {
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
  public void setCartId(Integer cartId) {
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
  public void setUserId(Integer userId) {
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
  public void setRestaurantId(Integer restaurantId) {
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
  public void setFoodItemId(Integer foodItemId) {
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
  public void setQuantity(Integer quantity) {
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
  public void setPrice(Double price) {
    this.price = price;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CartOutDTO that = (CartOutDTO) o;
    return Objects.equals(cartId, that.cartId) &&
      Objects.equals(userId, that.userId) &&
      Objects.equals(restaurantId, that.restaurantId) &&
      Objects.equals(foodItemId, that.foodItemId) &&
      Objects.equals(quantity, that.quantity) &&
      Objects.equals(price, that.price);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cartId, userId, restaurantId, foodItemId, quantity, price);
  }

  @Override
  public String toString() {
    return "CartOutDTO{" +
      "cartId=" + cartId +
      ", userId=" + userId +
      ", restaurantId=" + restaurantId +
      ", foodItemId=" + foodItemId +
      ", quantity=" + quantity +
      ", price=" + price +
      '}';
  }
}