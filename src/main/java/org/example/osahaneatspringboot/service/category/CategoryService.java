package org.example.osahaneatspringboot.service.category;

import static org.example.osahaneatspringboot.constant.cache.CacheKey.CATEGORY_KEY;
import static org.example.osahaneatspringboot.constant.error.CategoryErrorCode.CATEGORY_NOT_FOUND;

import java.util.List;
import java.util.Map;

import org.example.osahaneatspringboot.dto.request.CategoryStoreRequest;
import org.example.osahaneatspringboot.dto.request.CategoryUpdateRequest;
import org.example.osahaneatspringboot.entity.Category;
import org.example.osahaneatspringboot.exception.ApiException;
import org.example.osahaneatspringboot.exception.ValidateException;
import org.example.osahaneatspringboot.mapper.CategoryMapper;
import org.example.osahaneatspringboot.repository.ICategoryRepository;
import org.example.osahaneatspringboot.utils.NullPropertyHandler;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CategoryService implements ICategoryService {
    ICategoryRepository repository;
    CategoryMapper mapper;

    Category getCategoryById(String id) {
        return repository.findById(id).orElseThrow(() -> new ApiException(CATEGORY_NOT_FOUND));
    }

    @Override
    @Cacheable(value = CATEGORY_KEY)
    public List<Category> findAll() {
        return repository.findAll();
    }

    @Override
    @Cacheable(value = CATEGORY_KEY, key = "#id")
    public Category findById(String id) {
        return getCategoryById(id);
    }

    @Override
    @CacheEvict(value = CATEGORY_KEY, allEntries = true)
    public Category store(CategoryStoreRequest request) {
        if (repository.existsByName(request.getName())) {
            throw new ValidateException(Map.of("name", "Tên danh mục đã tồn tại"));
        }
        Category category = mapper.toCategory(request);
        return repository.save(category);
    }

    @Override
    @CacheEvict(value = CATEGORY_KEY, allEntries = true)
    public Category update(String id, CategoryUpdateRequest request) {
        Category category = getCategoryById(id);
        if (repository.existsByNameAndIdNot(request.getName(), id)) {
            throw new ValidateException(Map.of("name", "Tên danh mục đã tồn tại"));
        }
        Category update = mapper.toCategory(request);
        NullPropertyHandler.copyNullProperties(category, update);
        return repository.save(category);
    }

    @Override
    @CacheEvict(value = CATEGORY_KEY, allEntries = true)
    public Void delete(String id) {
        Category category = getCategoryById(id);
        repository.delete(category);
        return null;
    }
}
