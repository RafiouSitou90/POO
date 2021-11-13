package com.rafdev.prova.blog.api.controller;

import com.rafdev.prova.blog.api.dto.category.CategoryDetailsDto;
import com.rafdev.prova.blog.api.dto.category.CategoryDto;
import com.rafdev.prova.blog.api.exception.ResourceAlreadyExistsException;
import com.rafdev.prova.blog.api.exception.ResourceNotFoundException;
import com.rafdev.prova.blog.api.pagination.CategoryPagination;
import com.rafdev.prova.blog.api.request.CategoryRequest;
import com.rafdev.prova.blog.api.service.CategoryService;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v2/categories")
@Api(tags = "Categories")
@Tags(value = @Tag(name = "Categories", description = "Categories Resources"))
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    @Operation(summary = "Add new Category", description = "Add a single category resource in the API.")
    public ResponseEntity<CategoryDto> saveCategory(@RequestBody @Valid CategoryRequest categoryRequest)
            throws ResourceAlreadyExistsException {
        return new ResponseEntity<>(categoryService.saveCategory(categoryRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Updated Category by Id", description = "Update a single category resource in the API.")
    public ResponseEntity<CategoryDto> updateCategoryById(@PathVariable("id") Long id,
                                                          @RequestBody @Valid CategoryRequest categoryRequest)
            throws ResourceNotFoundException, ResourceAlreadyExistsException {
        return new ResponseEntity<>(categoryService.updateCategoryById(id, categoryRequest), HttpStatus.OK);
    }

    @GetMapping
    @Operation(summary = "Get Categories list", description = "Get the full list paginated of categories provided by the API.")
    public ResponseEntity<Page<CategoryDto>> getCategories(CategoryPagination pagination) {
        return new ResponseEntity<>(categoryService.getCategories(pagination), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Category by Id", description = "Get single category resource provided by the API.")
    public ResponseEntity<CategoryDetailsDto> getCategoryById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(categoryService.getCategoryById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Category by Id", description = "Delete a single category resource in the API.")
    public ResponseEntity<String> deleteCategoryById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        categoryService.deleteCategoryById(id);

        return new ResponseEntity<>("Category deleted successfully", HttpStatus.OK);
    }
}
