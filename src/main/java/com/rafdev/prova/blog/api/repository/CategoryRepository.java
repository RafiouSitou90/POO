package com.rafdev.prova.blog.api.repository;

import com.rafdev.prova.blog.api.database.Database;
import com.rafdev.prova.blog.api.entity.Category;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class CategoryRepository {

    private final List<Category> categories;

    public CategoryRepository(Database database) {
        this.categories = database.getCategories();
    }

    public List<Category> findAll() {
        return categories;
    }

    public Category save(Category category) {
        categories.add(category);

        return category;
    }

    public Category update(Category category) {
        Category categoryBD = findById(category.getId());

        if (categoryBD != null) {
            categories.set(categories.indexOf(categoryBD), category);
        }

        return category;
    }

    public void delete(Long id) {
        categories.removeIf(category -> Objects.equals(category.getId(), id));
    }

    public Category findById(Long id) {
        for (Category category: categories) {
            if (category.getId().equals(id)) {
                return category;
            }
        }

        return null;
    }

    public Category findByName(String name) {
        for (Category category: categories) {
            if (category.getName().equals(name)) {
                return category;
            }
        }

        return null;
    }

    public boolean existsByName(String name) {
        for (Category category: categories) {
            if (category.getName().equals(name)) {
                return true;
            }
        }

        return false;
    }

    public boolean existsById(Long id) {
        for (Category category: categories) {
            if (category.getId().equals(id)) {
                return true;
            }
        }

        return false;
    }
}
