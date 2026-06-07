package com.example.inventory_api.product.dto;

public record CreateProductRequest(
    String name,
    String description,
    Double price,
    Integer quantity,
    Long categoryId,
    Long supplierId
) {}
