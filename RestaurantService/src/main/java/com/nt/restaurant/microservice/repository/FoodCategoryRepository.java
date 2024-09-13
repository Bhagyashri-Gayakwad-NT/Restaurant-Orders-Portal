package com.nt.restaurant.microservice.repository;

import com.nt.restaurant.microservice.entities.FoodCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for performing CRUD operations on {@link FoodCategory} entities.
 * Extends {@link JpaRepository} to provide basic CRUD and JPA functionalities.
 */
@Repository
public interface FoodCategoryRepository extends JpaRepository<FoodCategory, Integer> {

  /**
   * Finds a {@link FoodCategory} by its name.
   *
   * @param foodCategoryName the name of the food category.
   * @return an {@link Optional} containing the {@link FoodCategory} if found, or {@link Optional#empty()} if not.
   */
  Optional<FoodCategory> findByFoodCategoryName(String foodCategoryName);

  /**
   * Finds all {@link FoodCategory} entities associated with a specific restaurant.
   *
   * @param restaurantId the ID of the restaurant.
   * @return a {@link List} of {@link FoodCategory} entities for the specified restaurant.
   */
  List<FoodCategory> findByRestaurantId(Integer restaurantId);

  /**
   * Finds a {@link FoodCategory} by its restaurant ID and category name.
   *
   * @param restaurantId     the ID of the restaurant.
   * @param foodCategoryName the name of the food category.
   * @return an {@link Optional} containing the {@link FoodCategory} if found, or {@link Optional#empty()} if not.
   */
  Optional<FoodCategory> findByRestaurantIdAndFoodCategoryName(Integer restaurantId, String foodCategoryName);
}
