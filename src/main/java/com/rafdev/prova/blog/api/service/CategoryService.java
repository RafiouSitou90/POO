package com.rafdev.prova.blog.api.service;

import com.rafdev.prova.blog.api.dto.category.CategoryDetailsDto;
import com.rafdev.prova.blog.api.dto.category.CategoryDto;
import com.rafdev.prova.blog.api.exception.ResourceAlreadyExistsException;
import com.rafdev.prova.blog.api.exception.ResourceNotFoundException;
import com.rafdev.prova.blog.api.request.CategoryRequest;

import java.util.List;

public interface CategoryService {

    CategoryDto saveCategory(CategoryRequest categoryRequest) throws ResourceAlreadyExistsException;

    CategoryDto updateCategoryById(Long id, CategoryRequest categoryRequest) throws ResourceNotFoundException,
            ResourceAlreadyExistsException;

    List<CategoryDto> getCategories();

    CategoryDetailsDto getCategoryById(Long id) throws ResourceNotFoundException;

    void deleteCategoryById(Long id) throws ResourceNotFoundException;
}
