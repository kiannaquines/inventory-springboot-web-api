package com.example.inventory_api.supplier.dto;

public record UpdateSupplierRequest(
    String name,
    String contactName,
    String email,
    String phone,
    String address
) {}
