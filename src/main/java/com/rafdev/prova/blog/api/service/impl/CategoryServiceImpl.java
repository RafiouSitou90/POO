package com.rafdev.prova.blog.api.service.impl;

import com.rafdev.prova.blog.api.dto.CategoryDto;
import com.rafdev.prova.blog.api.entity.Category;
import com.rafdev.prova.blog.api.exception.ResourceAlreadyExistsException;
import com.rafdev.prova.blog.api.exception.ResourceNotFoundException;
import com.rafdev.prova.blog.api.repository.CategoryRepository;
import com.rafdev.prova.blog.api.request.CategoryRequest;
import com.rafdev.prova.blog.api.service.CategoryService;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final String resourceName = "Category";
    private final AtomicLong idCounter = new AtomicLong(10);
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDto saveCategory(CategoryRequest categoryRequest) {
        if (categoryRepository.existsByName(categoryRequest.getName())) {
            throw new ResourceAlreadyExistsException(resourceName, "Name", categoryRequest.getName());
        }

        Category category = new Category(idCounter.incrementAndGet(), categoryRequest.getName());
        Category categoryCreated = categoryRepository.save(category);

        return setCategoryDto(categoryCreated);
    }

    @Override
    public CategoryDto updateCategoryById(Long id, CategoryRequest categoryRequest) {
        Category categoryFound = categoryRepository.findById(id);

        if (categoryFound == null) {
            throw new ResourceNotFoundException(resourceName, "Id", id);
        }

        categoryFound.setName(categoryRequest.getName());

        Category categoryUpdated = categoryRepository.update(categoryFound);

        return setCategoryDto(categoryUpdated);
    }

    @Override
    public List<CategoryDto> getCategories() {
        List<CategoryDto> categoriesDto = new ArrayList<>();
        List<Category> categories = categoryRepository.findAll();

        for (Category category: categories) {
            CategoryDto categoryDto = new CategoryDto(category.getId(), category.getName());

            categoriesDto.add(categoryDto);
        }

        return categoriesDto;
    }

    @Override
    public CategoryDto getCategoryById(Long id) {
        Category category = categoryRepository.findById(id);

        if (category == null) {
            throw new ResourceNotFoundException(resourceName, "Id", id);
        }

        return setCategoryDto(category);
    }

    @Override
    public void deleteCategoryById(Long id) {
        Category category = categoryRepository.findById(id);

        if (category == null) {
            throw new ResourceNotFoundException(resourceName, "Id", id);
        }

        categoryRepository.delete(category.getId());
    }

    private CategoryDto setCategoryDto(Category category) {
        return new CategoryDto(category.getId(), category.getName());
    }
}
