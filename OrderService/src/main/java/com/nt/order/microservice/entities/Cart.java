package com.nt.order.microservice.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;
/**
 * Represents a shopping cart in the system.
 */
@Entity
public class Cart {

  /**
   * The unique identifier for the cart.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer cartId;

  /**
   * The unique identifier for the user who owns the cart.
   */
  private Integer userId;

  /**
   * The unique identifier for the restaurant associated with the cart.
   */
  private Integer restaurantId;

  /**
   * The unique identifier for the food item in the cart.
   */
  private Integer foodItemId;

  /**
   * The quantity of the food item in the cart.
   */
  private Integer quantity;

  /**
   * The price of the food item.
   */
  private Double price;

  /**
   * Default constructor for Cart.
   */
  public Cart() {
  }

  /**
   * Constructs a Cart with the specified values.
   *
   * @param cartId        the unique identifier for the cart
   * @param userId       the unique identifier for the user
   * @param restaurantId  the unique identifier for the restaurant
   * @param foodItemId    the unique identifier for the food item
   * @param quantity       the quantity of the food item
   * @param price         the price of the food item
   */
  public Cart(final Integer cartId, final Integer userId, final Integer restaurantId,
              final Integer foodItemId, final Integer quantity, final Double price) {
    this.cartId = cartId;
    this.userId = userId;
    this.restaurantId = restaurantId;
    this.foodItemId = foodItemId;
    this.quantity = quantity;
    this.price = price;
  }

  /**
   * Retrieves the unique identifier for the cart.
   *
   * @return the cart ID as an Integer.
   */
  public Integer getCartId() {
    return cartId;
  }

  /**
   * Sets the unique identifier for the cart.
   *
   * @param cartId the cart ID.
   */
  public void setCartId(final Integer cartId) {
    this.cartId = cartId;
  }

  /**
   * Retrieves the unique identifier for the user.
   *
   * @return the user ID as an Integer.
   */
  public Integer getUserId() {
    return userId;
  }

  /**
   * Sets the unique identifier for the user.
   *
   * @param userId the user ID.
   */
  public void setUserId(final Integer userId) {
    this.userId = userId;
  }

  /**
   * Retrieves the unique identifier for the restaurant.
   *
   * @return the restaurant ID as an Integer.
   */
  public Integer getRestaurantId() {
    return restaurantId;
  }

  /**
   * Sets the unique identifier for the restaurant.
   *
   * @param restaurantId the restaurant ID.
   */
  public void setRestaurantId(final Integer restaurantId) {
    this.restaurantId = restaurantId;
  }

  /**
   * Retrieves the unique identifier for the food item.
   *
   * @return the food item ID as an Integer.
   */
  public Integer getFoodItemId() {
    return foodItemId;
  }

  /**
   * Sets the unique identifier for the food item.
   *
   * @param foodItemId the food item ID.
   */
  public void setFoodItemId(final Integer foodItemId) {
    this.foodItemId = foodItemId;
  }

  /**
   * Retrieves the quantity of the food item in the cart.
   *
   * @return the quantity as an Integer.
   */
  public Integer getQuantity() {
    return quantity;
  }

  /**
   * Sets the quantity of the food item in the cart.
   *
   * @param quantity the quantity.
   */
  public void setQuantity(final Integer quantity) {
    this.quantity = quantity;
  }

  /**
   * Retrieves the price of the food item.
   *
   * @return the price as a Double.
   */
  public Double getPrice() {
    return price;
  }

  /**
   * Sets the price of the food item.
   *
   * @param price the price.
   */
  public void setPrice(final Double price) {
    this.price = price;
  }

  /**
   * Checks if this cart is equal to another object.
   *
   * @param o the object to compare.
   * @return true if this cart is equal to the given object, false otherwise.
   */
  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Cart cart = (Cart) o;
    return Objects.equals(cartId, cart.cartId) && Objects.equals(userId, cart.userId)
      && Objects.equals(restaurantId, cart.restaurantId) && Objects.equals(foodItemId, cart.foodItemId)
      && Objects.equals(quantity, cart.quantity) && Objects.equals(price, cart.price);
  }

  /**
   * Returns a hash code value for this cart.
   *
   * @return a hash code value for this cart.
   */
  @Override
  public int hashCode() {
    return Objects.hash(cartId, userId, restaurantId, foodItemId, quantity, price);
  }

  /**
   * Returns a string representation of this cart.
   *
   * @return a string representation of this cart.
   */
  @Override
  public String toString() {
    return "Cart{"
      + "cartId=" + cartId
      + ", userId=" + userId
      + ", restaurantId=" + restaurantId
      + ", foodItemId=" + foodItemId
      + ", quantity=" + quantity
      + ", price=" + price
      + '}';
  }
}
