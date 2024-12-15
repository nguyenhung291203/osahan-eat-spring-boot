package org.example.osahaneatspringboot.mapper;

import org.example.osahaneatspringboot.dto.request.DishStoreRequest;
import org.example.osahaneatspringboot.dto.response.DishResponse;
import org.example.osahaneatspringboot.entity.Dish;
import org.springframework.stereotype.Component;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@Component
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class DishMapper {
    RestaurantMapper restaurantMapper;

    public Dish toDish(DishStoreRequest request) {
        return Dish.builder()
                .name(request.getName())
                .description(request.getDescription())
                .isFreeShip(request.getIsFreeShip())
                .price(request.getPrice())
                .build();
    }

    public DishResponse toDishResponse(Dish request) {
        return DishResponse.builder()
                .id(request.getId())
                .name(request.getName())
                .description(request.getDescription())
                .isFreeShip(request.getIsFreeShip())
                .price(request.getPrice())
                .image(request.getImage())
                .category(request.getCategory())
                .restaurant(restaurantMapper.toRestaurantResponse(request.getRestaurant()))
                .updateAt(request.getUpdateAt())
                .createAt(request.getCreateAt())
                .build();
    }
}
