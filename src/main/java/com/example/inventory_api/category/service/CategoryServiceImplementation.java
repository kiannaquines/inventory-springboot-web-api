package com.example.inventory_api.category.service;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.inventory_api.category.dto.CreateCategoryRequest;
import com.example.inventory_api.category.dto.GetCategoriesRequest;
import com.example.inventory_api.category.dto.UpdateCategoryRequest;
import com.example.inventory_api.category.entity.Category;
import com.example.inventory_api.category.exception.CategoryNotFoundException;
import com.example.inventory_api.category.repository.CategoryRepository;

@Service
public class CategoryServiceImplementation implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImplementation(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategories(GetCategoriesRequest request) {
        Specification<Category> spec = (root, query, cb) -> cb.conjunction();

        if (request.name() != null && !request.name().isBlank()) {
            spec = spec.and((root, query, cb) ->
                    cb.like(cb.lower(root.get("name")), "%" + request.name().toLowerCase() + "%")
            );
        }

        return categoryRepository.findAll(spec);
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + id));
    }

    @Override
    public Category createCategory(CreateCategoryRequest request) {
        Category category = new Category(request.name(), request.description());
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Long id, UpdateCategoryRequest request) {
        Category existing = getCategoryById(id);
        if (request.name() != null) existing.setName(request.name());
        if (request.description() != null) existing.setDescription(request.description());
        return categoryRepository.save(existing);
    }

    @Override
    public Category deleteCategory(Long id) {
        Category existing = getCategoryById(id);
        categoryRepository.delete(existing);
        return existing;
    }

    @Override
    public List<Category> deleteAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        categoryRepository.deleteAll();
        return categories;
    }
}
