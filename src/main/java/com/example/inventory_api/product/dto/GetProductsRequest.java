package com.example.inventory_api.product.dto;

public record GetProductsRequest(
    String name,
    Double price,
    Long categoryId,
    Long supplierId
) {}
