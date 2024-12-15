package org.example.osahaneatspringboot.service.favorite;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.example.osahaneatspringboot.entity.Restaurant;

public interface IFavoriteService {
    Restaurant store(String username, String restaurantId) throws ExecutionException, InterruptedException;

    void delete(String username, String restaurantId) throws ExecutionException, InterruptedException;

    List<Restaurant> findRestaurantFavorite(String username);
}
