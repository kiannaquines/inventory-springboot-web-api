package com.example.inventory_api.product.dto;

public record UpdateProductRequest(
    String name,
    String description,
    Double price,
    Integer quantity,
    Long categoryId,
    Long supplierId
) {}
