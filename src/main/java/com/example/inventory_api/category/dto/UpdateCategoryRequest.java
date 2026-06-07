package com.example.inventory_api.category.dto;

public record UpdateCategoryRequest(
    String name,
    String description
) {}
