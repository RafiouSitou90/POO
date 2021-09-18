package com.rafdev.prova.blog.api.service;

import com.rafdev.prova.blog.api.dto.CategoryDto;
import com.rafdev.prova.blog.api.request.CategoryRequest;

import java.util.List;

public interface CategoryService {

    CategoryDto saveCategory(CategoryRequest categoryRequest);

    CategoryDto updateCategoryById(Long id, CategoryRequest categoryRequest);

    List<CategoryDto> getCategories();

    CategoryDto getCategoryById(Long id);

    void deleteCategoryById(Long id);
}
