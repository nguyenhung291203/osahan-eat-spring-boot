package org.example.osahaneatspringboot.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.example.osahaneatspringboot.dto.request.CategoryStoreRequest;
import org.example.osahaneatspringboot.dto.request.CategoryUpdateRequest;
import org.example.osahaneatspringboot.dto.response.ApiResponse;
import org.example.osahaneatspringboot.entity.Category;
import org.example.osahaneatspringboot.service.category.ICategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequestMapping("${api.prefix}/categories")
@RestController
@AllArgsConstructor
@Validated
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Tag(name = "Quản lý vụ thể loại")
public class CategoryController {
    ICategoryService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Xem toàn bộ thể loại")
    public ApiResponse<List<Category>> findAll() {
        return ApiResponse.ok(service.findAll());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Xem thể loại theo id")
    public ApiResponse<Category> findById(@PathVariable String id) {
        return ApiResponse.ok(service.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Thêm thể loại")
    public ApiResponse<Category> store(@RequestBody @Valid CategoryStoreRequest request) {
        return ApiResponse.ok(service.store(request));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "Chỉnh sửa thể loại")
    public ApiResponse<Category> update(@PathVariable String id, @RequestBody @Valid CategoryUpdateRequest request) {
        return ApiResponse.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "Xóa thể loại")
    public ApiResponse<Void> delete(@PathVariable String id) {
        return ApiResponse.ok(service.delete(id));
    }
}
