package com.example.inventory_api.category.dto;

public record CreateCategoryRequest(
    String name,
    String description
) {}
