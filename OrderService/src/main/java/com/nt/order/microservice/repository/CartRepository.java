package com.nt.order.microservice.repository;

import com.nt.order.microservice.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing {@link Cart} entities.
 * Provides methods to perform CRUD operations and custom queries.
 */
@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

  /**
   * Retrieves a list of cart items associated with a specific user.
   *
   * @param userId the ID of the user whose cart items are to be retrieved
   * @return a list of cart items for the given user
   */
  List<Cart> findByUserId(Integer userId);

  /**
   * Retrieves a list of cart items associated with a specific user and restaurant.
   *
   * @param userId the ID of the user whose cart items are to be retrieved
   * @param restaurantId the ID of the restaurant whose cart items are to be retrieved
   * @return a list of cart items for the given user and restaurant
   */
  List<Cart> findByUserIdAndRestaurantId(Integer userId, Integer restaurantId);

  /**
   * Deletes all cart items associated with a specific user.
   *
   * @param userId the ID of the user whose cart items are to be deleted
   */
  void deleteByUserId(Integer userId);

}
