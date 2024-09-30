package com.nt.order.microservice.repository;

import com.nt.order.microservice.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Repository interface for managing {@link Order} entities.
 * Provides methods to perform CRUD operations and custom queries.
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

  /**
   * Retrieves a list of orders associated with a specific user.
   *
   * @param userId the ID of the user whose orders are to be retrieved
   * @return a list of orders for the given user
   */
  List<Order> findByUserId(Integer userId);

  /**
   * Retrieves a list of orders associated with a specific restaurant.
   *
   * @param restaurantId the ID of the restaurant whose orders are to be retrieved
   * @return a list of orders for the given restaurant
   */
  List<Order> findByRestaurantId(Integer restaurantId);
}
