package com.rafdev.prova.blog.api.service;

import com.rafdev.prova.blog.api.dto.category.CategoryDetailsDto;
import com.rafdev.prova.blog.api.dto.category.CategoryDto;
import com.rafdev.prova.blog.api.exception.ResourceAlreadyExistsException;
import com.rafdev.prova.blog.api.exception.ResourceNotFoundException;
import com.rafdev.prova.blog.api.pagination.CategoryPagination;
import com.rafdev.prova.blog.api.request.CategoryRequest;

import org.springframework.data.domain.Page;

public interface CategoryService {

    CategoryDto saveCategory(CategoryRequest categoryRequest) throws ResourceAlreadyExistsException;

    CategoryDto updateCategoryById(Long id, CategoryRequest categoryRequest) throws ResourceNotFoundException,
            ResourceAlreadyExistsException;

    Page<CategoryDto> getCategories(CategoryPagination pagination);

    CategoryDetailsDto getCategoryById(Long id) throws ResourceNotFoundException;

    void deleteCategoryById(Long id) throws ResourceNotFoundException;
}
