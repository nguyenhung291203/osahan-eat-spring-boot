package org.example.osahaneatspringboot.service.dish;

import static org.example.osahaneatspringboot.constant.cache.CacheKey.DISH_KEY;
import static org.example.osahaneatspringboot.constant.error.CategoryErrorCode.CATEGORY_NOT_FOUND;
import static org.example.osahaneatspringboot.constant.error.DishErrorCode.DISH_NOT_FOUND;
import static org.example.osahaneatspringboot.constant.error.RestaurantErrorCode.RESTAURANT_NOT_FOUND;
import static org.example.osahaneatspringboot.constant.message.DishErrorMessage.DISH_ALREADY_EXISTS;

import java.util.List;
import java.util.Map;

import jakarta.transaction.Transactional;

import org.example.osahaneatspringboot.dto.request.DishStoreRequest;
import org.example.osahaneatspringboot.entity.Category;
import org.example.osahaneatspringboot.entity.Dish;
import org.example.osahaneatspringboot.entity.Restaurant;
import org.example.osahaneatspringboot.exception.ApiException;
import org.example.osahaneatspringboot.exception.ValidateException;
import org.example.osahaneatspringboot.mapper.DishMapper;
import org.example.osahaneatspringboot.repository.ICategoryRepository;
import org.example.osahaneatspringboot.repository.IDishRepository;
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
public class DishService implements IDishService {
    IDishRepository repository;
    ICategoryRepository categoryRepository;
    IRestaurantRepository restaurantRepository;
    DishMapper mapper;

    Restaurant getRestaurantById(String id) {
        return restaurantRepository.findById(id).orElseThrow(() -> new ApiException(RESTAURANT_NOT_FOUND));
    }

    Category getCategoryById(String id) {
        return categoryRepository.findById(id).orElseThrow(() -> new ApiException(CATEGORY_NOT_FOUND));
    }

    Dish getDishById(String id) {
        return repository.findById(id).orElseThrow(() -> new ApiException(DISH_NOT_FOUND));
    }

    @Override
    @Transactional
    @CacheEvict(value = DISH_KEY, allEntries = true)
    public Dish store(DishStoreRequest request) {
        Category category = getCategoryById(request.getCategoryId());
        Restaurant restaurant = getRestaurantById(request.getRestaurantId());

        if (repository.existsByNameAndCategoryIdAndRestaurantId(
                request.getName(), category.getId(), restaurant.getId())) {
            throw new ValidateException(Map.of("name", DISH_ALREADY_EXISTS));
        }

        Dish dish = mapper.toDish(request);
        dish.setCategory(category);
        dish.setRestaurant(restaurant);

        return repository.save(dish);
    }

    @Override
    @Cacheable(value = DISH_KEY)
    public List<Dish> findAll() {
        return repository.findAll();
    }

    @Override
    @Cacheable(value = DISH_KEY, key = "'restaurant:'+#restaurantId")
    public List<Dish> findByRestaurantId(String restaurantId) {
        Restaurant restaurant = getRestaurantById(restaurantId);
        return repository.findByRestaurantId(restaurant.getId());
    }

    @Override
    @Cacheable(value = DISH_KEY, key = "#id")
    public Dish findById(String id) {
        return getDishById(id);
    }

    @Override
    public boolean existByIds(List<String> ids) {
        return repository.findAllById(ids).isEmpty();
    }

    @Override
    public List<Dish> findByIds(List<String> ids) {
        return repository.findAllById(ids);
    }
}
