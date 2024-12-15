package org.example.osahaneatspringboot.service.restaurant;

import java.util.List;

import org.example.osahaneatspringboot.dto.request.RestaurantStoreRequest;
import org.example.osahaneatspringboot.entity.Restaurant;

public interface IRestaurantService {
    List<Restaurant> findAll();

    Restaurant findById(String id);

    Restaurant store(RestaurantStoreRequest request);
}
