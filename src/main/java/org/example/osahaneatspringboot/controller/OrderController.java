package org.example.osahaneatspringboot.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.example.osahaneatspringboot.dto.request.OrderStoreRequest;
import org.example.osahaneatspringboot.dto.response.ApiResponse;
import org.example.osahaneatspringboot.dto.response.OrderResponse;
import org.example.osahaneatspringboot.mapper.OrderMapper;
import org.example.osahaneatspringboot.service.order.IOrderService;
import org.example.osahaneatspringboot.utils.SecurityUtil;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequestMapping("${api.prefix}/orders")
@RestController
@AllArgsConstructor
@Validated
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Tag(name = "Quản lý hóa đơn")
public class OrderController {
    IOrderService orderService;
    OrderMapper orderMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Tạo hóa đơn")
    public ApiResponse<OrderResponse> store(@RequestBody @Valid OrderStoreRequest request) {
        return ApiResponse.ok(orderMapper.toOrderResponse(orderService.store(SecurityUtil.getUsername(), request)));
    }

    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Lấy hóa hóa đơn cá nhân")
    public ApiResponse<List<OrderResponse>> getOrdersMe() {
        return ApiResponse.ok(orderService.getOrdersByUsername(SecurityUtil.getUsername()).stream()
                .map(orderMapper::toOrderResponse)
                .toList());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Lấy hóa hóa đơn cá nhân")
    public ApiResponse<OrderResponse> getOrderDetail(@PathVariable String id) {
        return ApiResponse.ok(orderMapper.toOrderResponse(orderService.getOrderDetail(id)));
    }
}
