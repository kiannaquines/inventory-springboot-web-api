package com.example.inventory_api.category.controller;

import java.util.List;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.inventory_api.category.dto.CreateCategoryRequest;
import com.example.inventory_api.category.dto.GetCategoriesRequest;
import com.example.inventory_api.category.dto.UpdateCategoryRequest;
import com.example.inventory_api.category.entity.Category;
import com.example.inventory_api.category.service.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> getAll(@ParameterObject GetCategoriesRequest request) {
        return categoryService.getAllCategories(request);
    }

    @GetMapping("/{id}")
    public Category getById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }

    @PostMapping
    public Category create(@RequestBody CreateCategoryRequest request) {
        return categoryService.createCategory(request);
    }

    @PutMapping("/{id}")
    public Category update(@PathVariable Long id, @RequestBody UpdateCategoryRequest request) {
        return categoryService.updateCategory(id, request);
    }

    @DeleteMapping("/{id}")
    public Category delete(@PathVariable Long id) {
        return categoryService.deleteCategory(id);
    }

    @DeleteMapping
    public List<Category> deleteAll() {
        return categoryService.deleteAllCategories();
    }
}
