package org.example.osahaneatspringboot.mapper;

import org.example.osahaneatspringboot.dto.request.CategoryStoreRequest;
import org.example.osahaneatspringboot.dto.request.CategoryUpdateRequest;
import org.example.osahaneatspringboot.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public Category toCategory(CategoryStoreRequest request) {
        return Category.builder()
                .description(request.getDescription())
                .name(request.getName())
                .build();
    }

    public Category toCategory(CategoryUpdateRequest request) {
        return Category.builder()
                .description(request.getDescription())
                .name(request.getName())
                .build();
    }
}
