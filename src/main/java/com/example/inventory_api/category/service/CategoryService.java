package com.example.inventory_api.category.service;

import java.util.List;

import com.example.inventory_api.category.dto.CreateCategoryRequest;
import com.example.inventory_api.category.dto.GetCategoriesRequest;
import com.example.inventory_api.category.dto.UpdateCategoryRequest;
import com.example.inventory_api.category.entity.Category;

public interface CategoryService {
    List<Category> getAllCategories(GetCategoriesRequest request);
    Category getCategoryById(Long id);
    Category createCategory(CreateCategoryRequest request);
    Category updateCategory(Long id, UpdateCategoryRequest request);
    Category deleteCategory(Long id);
    List<Category> deleteAllCategories();
}
