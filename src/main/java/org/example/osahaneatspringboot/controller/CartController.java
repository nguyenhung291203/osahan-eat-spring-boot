package org.example.osahaneatspringboot.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.example.osahaneatspringboot.dto.request.CartItemStoreRequest;
import org.example.osahaneatspringboot.dto.request.CartItemUpdateRequest;
import org.example.osahaneatspringboot.dto.response.ApiResponse;
import org.example.osahaneatspringboot.dto.response.CartItemResponse;
import org.example.osahaneatspringboot.mapper.CartMapper;
import org.example.osahaneatspringboot.service.cart.ICartService;
import org.example.osahaneatspringboot.utils.SecurityUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequestMapping("${api.prefix}/cart")
@RestController
@AllArgsConstructor
@Validated
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Tag(name = "Quản lý giỏ hàng")
public class CartController {
    ICartService service;
    CartMapper mapper;

    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Lấy thông tin giỏ hàng")
    public ApiResponse<List<CartItemResponse>> getCartMe() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return ApiResponse.ok(service.getCartMe(username).stream()
                .map(mapper::toCartItemResponse)
                .toList());
    }

    @PostMapping("/me")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Thêm sản phẩm giỏ hàng")
    public ApiResponse<CartItemResponse> store(@Valid @RequestBody CartItemStoreRequest request) {
        return ApiResponse.ok(mapper.toCartItemResponse(service.store(SecurityUtil.getUsername(), request)));
    }

    @PutMapping("/{id}/me")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "Chỉnh sửa giỏ hàng")
    public ApiResponse<CartItemResponse> update(
            @PathVariable String id, @Valid @RequestBody CartItemUpdateRequest request) {
        return ApiResponse.ok(mapper.toCartItemResponse(service.update(id, SecurityUtil.getUsername(), request)));
    }

    @DeleteMapping("/{id}/me")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Xóa sản phẩm trong giỏ hàng")
    public ApiResponse<Void> remove(@PathVariable String id) {
        return ApiResponse.ok(service.remove(id, SecurityUtil.getUsername()));
    }

    @DeleteMapping("/me")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Xóa nhiều sản phẩm trong giỏ hàng")
    public ApiResponse<Void> removeAll(@RequestParam List<String> ids) {
        return ApiResponse.ok(service.removeAll(ids, SecurityUtil.getUsername()));
    }
}
