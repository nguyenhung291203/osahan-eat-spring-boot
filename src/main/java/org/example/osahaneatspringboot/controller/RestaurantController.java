package org.example.osahaneatspringboot.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.example.osahaneatspringboot.dto.request.RestaurantPaginationRequest;
import org.example.osahaneatspringboot.dto.request.RestaurantStoreRequest;
import org.example.osahaneatspringboot.dto.response.ApiResponse;
import org.example.osahaneatspringboot.dto.response.RestaurantPaginationResponse;
import org.example.osahaneatspringboot.dto.response.RestaurantResponse;
import org.example.osahaneatspringboot.mapper.RestaurantMapper;
import org.example.osahaneatspringboot.service.restaurant.IRestaurantService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequestMapping("${api.prefix}/restaurants")
@RestController
@AllArgsConstructor
@Validated
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Tag(name = "Quản lý vụ nhà hàng")
public class RestaurantController {
    IRestaurantService service;
    RestaurantMapper mapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("permitAll()")
    @Operation(summary = "Xem toàn bộ nhà hàng")
    public ApiResponse<List<RestaurantResponse>> findAll() {
        return ApiResponse.ok(
                service.findAll().stream().map(mapper::toRestaurantResponse).toList());
    }

    @PostMapping("/filter")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("permitAll()")
    @Operation(summary = "Phân trang với nhà hàng")
    public ApiResponse<RestaurantPaginationResponse> filter(@RequestBody RestaurantPaginationRequest request) {
        return ApiResponse.ok(
                new RestaurantPaginationResponse(service.findAll(), request, mapper::toRestaurantResponse));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("permitAll()")
    @Operation(summary = "Xem nhà hàng theo id")
    public ApiResponse<RestaurantResponse> findById(@PathVariable String id) {
        return ApiResponse.ok(mapper.toRestaurantResponse(service.findById(id)));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Thêm nhà hàng")
    public ApiResponse<RestaurantResponse> store(@RequestBody @Valid RestaurantStoreRequest request) {
        return ApiResponse.ok(mapper.toRestaurantResponse(service.store(request)));
    }
}
