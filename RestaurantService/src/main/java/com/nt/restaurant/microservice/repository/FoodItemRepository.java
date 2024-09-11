package com.nt.restaurant.microservice.repository;

import com.nt.restaurant.microservice.entities.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FoodItemRepository extends JpaRepository<FoodItem, Integer> {
  List<FoodItem> findByCategoryId(Integer categoryId);

  List<FoodItem> findByRestaurantId(Integer restaurantId);

  Optional<FoodItem> findByFoodItemName(String foodItemName);
  //FoodItem findFoodItemsById(Integer foodItemId);
  //Optional<FoodItem> findByFoodItemNameAndRestaurantIdAndFoodCategoryId(String foodItemName, Integer restaurantId, Integer foodCategoryId);

  Optional<FoodItem> findByFoodItemNameAndRestaurantId(String foodItemName, Integer restaurantId);

}
