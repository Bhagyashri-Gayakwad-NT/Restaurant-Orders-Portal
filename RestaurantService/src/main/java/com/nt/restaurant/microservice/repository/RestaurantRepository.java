package com.nt.restaurant.microservice.repository;

import com.nt.restaurant.microservice.entities.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for performing CRUD operations on {@link Restaurant} entities.
 * Extends {@link JpaRepository} to provide basic CRUD and JPA functionalities.
 */
@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

  /**
   * Finds all {@link Restaurant} entities associated with a specific user.
   *
   * @param userId the ID of the user.
   * @return a {@link List} of {@link Restaurant} entities for the specified user.
   */
  List<Restaurant> findByUserId(Integer userId);

  /**
   * Finds a {@link Restaurant} by its ID.
   *
   * @param restaurantId the ID of the restaurant.
   * @return an {@link Optional} containing the {@link Restaurant} if found, or {@link Optional#empty()} if not.
   */
  Optional<Restaurant> findById(Integer restaurantId);

  /**
   * Checks if a restaurant exists with the given name, ignoring case.
   *
   * @param normalizedRestaurantName the name of the restaurant to check for existence.
   * @return true if a restaurant with the specified name exists, false otherwise.
   */
  boolean existsByRestaurantName(String normalizedRestaurantName);
}

