package org.example.osahaneatspringboot.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.example.osahaneatspringboot.dto.request.DishStoreRequest;
import org.example.osahaneatspringboot.dto.response.ApiResponse;
import org.example.osahaneatspringboot.dto.response.DishResponse;
import org.example.osahaneatspringboot.mapper.DishMapper;
import org.example.osahaneatspringboot.service.dish.IDishService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequestMapping("${api.prefix}/dishes")
@RestController
@AllArgsConstructor
@Validated
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Tag(name = "Quản lý món ăn")
public class DishController {
    IDishService service;
    DishMapper mapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Xem toàn bộ món ăn")
    public ApiResponse<List<DishResponse>> findAll() {
        return ApiResponse.ok(
                service.findAll().stream().map(mapper::toDishResponse).toList());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Xem món ăn theo id")
    public ApiResponse<DishResponse> findById(@PathVariable String id) {
        return ApiResponse.ok(mapper.toDishResponse(service.findById(id)));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Thêm món ăn")
    public ApiResponse<DishResponse> store(@RequestBody @Valid DishStoreRequest request) {
        return ApiResponse.ok(mapper.toDishResponse(service.store(request)));
    }

    @GetMapping("/restaurants/{restaurantId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Xem toàn bộ món ăn")
    public ApiResponse<List<DishResponse>> findByRestaurantId(@PathVariable String restaurantId) {
        return ApiResponse.ok(service.findByRestaurantId(restaurantId).stream()
                .map(mapper::toDishResponse)
                .toList());
    }
}
