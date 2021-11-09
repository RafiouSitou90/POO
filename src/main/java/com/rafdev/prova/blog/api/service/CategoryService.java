package com.rafdev.prova.blog.api.service;

import com.rafdev.prova.blog.api.dto.category.CategoryDetailsDto;
import com.rafdev.prova.blog.api.dto.category.CategoryDto;
import com.rafdev.prova.blog.api.request.CategoryRequest;

import java.util.List;

public interface CategoryService {

    CategoryDto saveCategory(CategoryRequest categoryRequest);

    CategoryDto updateCategoryById(Long id, CategoryRequest categoryRequest);

    List<CategoryDto> getCategories();

    CategoryDetailsDto getCategoryById(Long id);

    void deleteCategoryById(Long id);
}
