package org.example.osahaneatspringboot.service.restaurant;

import static org.example.osahaneatspringboot.constant.cache.CacheKey.RESTAURANT_KEY;
import static org.example.osahaneatspringboot.constant.error.RestaurantErrorCode.RESTAURANT_NOT_FOUND;
import static org.example.osahaneatspringboot.constant.message.RestaurantErrorMessage.TITLE_DUPLICATE;

import java.util.List;
import java.util.Map;

import jakarta.transaction.Transactional;

import org.example.osahaneatspringboot.dto.request.RestaurantStoreRequest;
import org.example.osahaneatspringboot.entity.Restaurant;
import org.example.osahaneatspringboot.exception.ApiException;
import org.example.osahaneatspringboot.exception.ValidateException;
import org.example.osahaneatspringboot.mapper.RestaurantMapper;
import org.example.osahaneatspringboot.repository.IRestaurantRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class RestaurantService implements IRestaurantService {
    IRestaurantRepository repository;
    RestaurantMapper mapper;

    @Override
    @Cacheable(value = RESTAURANT_KEY)
    public List<Restaurant> findAll() {
        return repository.findAll();
    }

    @Override
    @Cacheable(value = RESTAURANT_KEY, key = "#id")
    public Restaurant findById(String id) {
        return repository.findById(id).orElseThrow(() -> new ApiException(RESTAURANT_NOT_FOUND));
    }

    @Override
    @Transactional
    @CacheEvict(value = RESTAURANT_KEY, allEntries = true)
    public Restaurant store(RestaurantStoreRequest request) {
        if (repository.existsByTitle(request.getTitle())) {
            throw new ValidateException(Map.of("title", TITLE_DUPLICATE));
        }
        Restaurant restaurant = mapper.toRestaurant(request);
        return repository.save(restaurant);
    }
}
