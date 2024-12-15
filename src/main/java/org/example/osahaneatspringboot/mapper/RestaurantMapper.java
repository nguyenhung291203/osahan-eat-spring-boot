package org.example.osahaneatspringboot.mapper;

import org.example.osahaneatspringboot.dto.request.RestaurantStoreRequest;
import org.example.osahaneatspringboot.dto.response.RestaurantResponse;
import org.example.osahaneatspringboot.entity.Restaurant;
import org.springframework.stereotype.Component;

@Component
public class RestaurantMapper {
    public Restaurant toRestaurant(RestaurantStoreRequest request) {
        return Restaurant.builder()
                .title(request.getTitle())
                .subtitle(request.getSubtitle())
                .address(request.getAddress())
                .description(request.getDescription())
                .isFreeShip(request.getIsFreeShip() != null ? request.getIsFreeShip() : false)
                .isActive(request.getIsActive() != null ? request.getIsActive() : true)
                .openTime(request.getOpenTime())
                .closeTime(request.getCloseTime())
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .build();
    }

    public RestaurantResponse toRestaurantResponse(Restaurant request) {
        return RestaurantResponse.builder()
                .id(request.getId())
                .title(request.getTitle())
                .subtitle(request.getSubtitle())
                .image(request.getImage())
                .address(request.getAddress())
                .description(request.getDescription())
                .isFreeShip(request.getIsFreeShip() != null ? request.getIsFreeShip() : false)
                .isActive(request.getIsActive() != null ? request.getIsActive() : true)
                .openTime(request.getOpenTime())
                .closeTime(request.getCloseTime())
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .createAt(request.getCreateAt())
                .updateAt(request.getUpdateAt())
                .build();
    }
}
