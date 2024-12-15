package org.example.osahaneatspringboot.repository;

import java.util.List;
import java.util.Optional;

import org.example.osahaneatspringboot.entity.FavoriteRestaurant;
import org.example.osahaneatspringboot.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IFavoriteRestaurantRepository extends JpaRepository<FavoriteRestaurant, String> {
    Optional<FavoriteRestaurant> findByAccountIdAndRestaurantId(String accountId, String restaurantId);

    @Query("select f.restaurant from FavoriteRestaurant f where f.account.id = :accountId")
    List<Restaurant> findAllByAccountId(@Param("accountId") String accountId);
}
