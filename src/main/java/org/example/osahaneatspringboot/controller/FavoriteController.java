package org.example.osahaneatspringboot.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.example.osahaneatspringboot.dto.response.ApiResponse;
import org.example.osahaneatspringboot.dto.response.RestaurantResponse;
import org.example.osahaneatspringboot.mapper.RestaurantMapper;
import org.example.osahaneatspringboot.service.favorite.IFavoriteService;
import org.example.osahaneatspringboot.utils.SecurityUtil;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequestMapping("${api.prefix}/favorite")
@RestController
@AllArgsConstructor
@Validated
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Tag(name = "Quản lý danh sách yêu thích")
public class FavoriteController {
    IFavoriteService favoriteService;
    RestaurantMapper restaurantMapper;

    @GetMapping("/restaurants")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Xem danh sách nhà hàng yêu thích")
    public ApiResponse<List<RestaurantResponse>> findRestaurantFavorite() {
        return ApiResponse.ok(favoriteService.findRestaurantFavorite(SecurityUtil.getUsername()).stream()
                .map(restaurantMapper::toRestaurantResponse)
                .toList());
    }

    @PostMapping("/restaurants/{restaurantId}")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Thêm nhà hàng yêu thích vào danh sách")
    public ApiResponse<RestaurantResponse> storeRestaurantFavorite(@PathVariable String restaurantId)
            throws ExecutionException, InterruptedException {
        return ApiResponse.ok(
                restaurantMapper.toRestaurantResponse(favoriteService.store(SecurityUtil.getUsername(), restaurantId)));
    }

    @DeleteMapping("/restaurants/{restaurantId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Gỡ nhà hàng yêu thích vào danh sách")
    public ApiResponse<Void> removeRestaurantFavorite(@PathVariable String restaurantId)
            throws ExecutionException, InterruptedException {
        favoriteService.delete(SecurityUtil.getUsername(), restaurantId);
        return null;
    }
}
