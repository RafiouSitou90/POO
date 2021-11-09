package com.rafdev.prova.blog.api.service.impl;

import com.rafdev.prova.blog.api.dto.CategoryDto;
import com.rafdev.prova.blog.api.entity.Category;
import com.rafdev.prova.blog.api.exception.ResourceAlreadyExistsException;
import com.rafdev.prova.blog.api.exception.ResourceNotFoundException;
import com.rafdev.prova.blog.api.repository.CategoryRepository;
import com.rafdev.prova.blog.api.request.CategoryRequest;
import com.rafdev.prova.blog.api.service.CategoryService;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final String resourceName = "Category";
    private final CategoryRepository categoryRepository;
    private CategoryDto categoryDto;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDto saveCategory(CategoryRequest categoryRequest) {
        if (categoryRepository.existsByNameIgnoreCase(categoryRequest.getName())) {
            throw new ResourceAlreadyExistsException(resourceName, "Name", categoryRequest.getName());
        }

        Category category = new Category(categoryRequest.getName());

        return new CategoryDto(categoryRepository.save(category));
    }

    @Override
    public CategoryDto updateCategoryById(Long id, CategoryRequest categoryRequest) {
        Category categoryFound = getCategoryOrThrowException(id);

        if(!Objects.equals(categoryFound.getName().toLowerCase(), categoryRequest.getName().toLowerCase())) {
            if (categoryRepository.existsByNameIgnoreCase(categoryRequest.getName())) {
                throw new ResourceAlreadyExistsException(resourceName, "Name", categoryRequest.getName());
            }
        }

        categoryFound.setName(categoryRequest.getName());

        return new CategoryDto(categoryRepository.save(categoryFound));
    }

    @Override
    public List<CategoryDto> getCategories() {
        List<CategoryDto> categoriesDto = new ArrayList<>();
        List<Category> categories = categoryRepository.findAll();

        categories.forEach(category -> categoriesDto.add(new CategoryDto(category)));

        return categoriesDto;
    }

    @Override
    public CategoryDto getCategoryById(Long id) {

        return new CategoryDto(getCategoryOrThrowException(id));
    }

    @Override
    public void deleteCategoryById(Long id) {

        categoryRepository.delete(getCategoryOrThrowException(id));
    }

    private Category getCategoryOrThrowException(long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(resourceName, "Id", id));
    }
}


