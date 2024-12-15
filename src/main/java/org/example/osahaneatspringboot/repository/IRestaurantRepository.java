package org.example.osahaneatspringboot.repository;

import org.example.osahaneatspringboot.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRestaurantRepository extends JpaRepository<Restaurant, String> {
    boolean existsByTitle(String title);
}
