package com.rafdev.prova.blog.api.controller;

import com.rafdev.prova.blog.api.dto.category.CategoryDetailsDto;
import com.rafdev.prova.blog.api.dto.category.CategoryDto;
import com.rafdev.prova.blog.api.exception.ResourceAlreadyExistsException;
import com.rafdev.prova.blog.api.exception.ResourceNotFoundException;
import com.rafdev.prova.blog.api.request.CategoryRequest;
import com.rafdev.prova.blog.api.service.CategoryService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v2/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<CategoryDto> saveCategory(@RequestBody @Valid CategoryRequest categoryRequest)
            throws ResourceAlreadyExistsException {
        return new ResponseEntity<>(categoryService.saveCategory(categoryRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategoryById(@PathVariable("id") Long id,
                                                          @RequestBody @Valid CategoryRequest categoryRequest)
            throws ResourceNotFoundException, ResourceAlreadyExistsException {
        return new ResponseEntity<>(categoryService.updateCategoryById(id, categoryRequest), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getCategories() {
        return new ResponseEntity<>(categoryService.getCategories(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDetailsDto> getCategoryById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(categoryService.getCategoryById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategoryById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        categoryService.deleteCategoryById(id);

        return new ResponseEntity<>("Category deleted successfully", HttpStatus.OK);
    }
}
