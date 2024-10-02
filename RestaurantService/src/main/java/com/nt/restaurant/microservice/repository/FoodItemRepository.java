package com.nt.restaurant.microservice.repository;

import com.nt.restaurant.microservice.entities.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for performing CRUD operations on {@link FoodItem} entities.
 * Extends {@link JpaRepository} to provide basic CRUD and JPA functionalities.
 */
@Repository
public interface FoodItemRepository extends JpaRepository<FoodItem, Integer> {

  /**
   * Finds all {@link FoodItem} entities associated with a specific category.
   *
   * @param categoryId the ID of the food category.
   * @return a {@link List} of {@link FoodItem} entities for the specified category.
   */
  List<FoodItem> findByCategoryId(Integer categoryId);

  /**
   * Finds all {@link FoodItem} entities associated with a specific restaurant.
   *
   * @param restaurantId the ID of the restaurant.
   * @return a {@link List} of {@link FoodItem} entities for the specified restaurant.
   */
  List<FoodItem> findByRestaurantId(Integer restaurantId);

  /**
   * Finds a {@link FoodItem} by its name.
   *
   * @param foodItemName the name of the food item.
   * @return an {@link Optional} containing the {@link FoodItem} if found, or {@link Optional#empty()} if not.
   */
  Optional<FoodItem> findByFoodItemName(String foodItemName);

  /**
   * Finds a {@link FoodItem} by its name and associated restaurant.
   *
   * @param foodItemName the name of the food item.
   * @param restaurantId the ID of the restaurant.
   * @return an {@link Optional} containing the {@link FoodItem} if found, or {@link Optional#empty()} if not.
   */
  Optional<FoodItem> findByFoodItemNameAndRestaurantId(String foodItemName, Integer restaurantId);
}

