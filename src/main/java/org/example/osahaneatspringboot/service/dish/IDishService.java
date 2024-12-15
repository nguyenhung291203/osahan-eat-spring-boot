package org.example.osahaneatspringboot.service.dish;

import java.util.List;

import org.example.osahaneatspringboot.dto.request.DishStoreRequest;
import org.example.osahaneatspringboot.entity.Dish;

public interface IDishService {
    Dish store(DishStoreRequest request);

    List<Dish> findAll();

    List<Dish> findByRestaurantId(String restaurantId);

    Dish findById(String id);

    boolean existByIds(List<String> ids);

    List<Dish> findByIds(List<String> ids);
}
