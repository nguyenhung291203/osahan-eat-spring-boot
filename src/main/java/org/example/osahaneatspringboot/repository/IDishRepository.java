package org.example.osahaneatspringboot.repository;

import java.util.List;

import org.example.osahaneatspringboot.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDishRepository extends JpaRepository<Dish, String> {
    boolean existsByNameAndCategoryIdAndRestaurantId(String name, String categoryId, String restaurantId);

    List<Dish> findByRestaurantId(String restaurantId);
}
