package com.nt.restaurant.microservice.repository;

import com.nt.restaurant.microservice.entities.FoodCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FoodCategoryRepository extends JpaRepository<FoodCategory, Integer> {
  Optional<FoodCategory> findByFoodCategoryName(String foodCategoryName);

  List<FoodCategory> findByRestaurantId(Integer restaurantId);

  // List<FoodCategory> findByRestaurantIdAndFoodCategoryName(Integer restaurantId, String foodCategoryName);
  Optional<FoodCategory> findByRestaurantIdAndFoodCategoryName(Integer restaurantId, String foodCategoryName);
}
