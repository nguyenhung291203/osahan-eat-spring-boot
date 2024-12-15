package org.example.osahaneatspringboot.service.category;

import java.util.List;

import org.example.osahaneatspringboot.dto.request.CategoryStoreRequest;
import org.example.osahaneatspringboot.dto.request.CategoryUpdateRequest;
import org.example.osahaneatspringboot.entity.Category;

public interface ICategoryService {
    List<Category> findAll();

    Category findById(String id);

    Category store(CategoryStoreRequest request);

    Category update(String id, CategoryUpdateRequest request);

    Void delete(String id);
}
