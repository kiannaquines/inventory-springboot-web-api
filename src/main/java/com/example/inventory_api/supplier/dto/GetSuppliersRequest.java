package com.example.inventory_api.supplier.dto;

public record GetSuppliersRequest(
    String name,
    String email
) {}
