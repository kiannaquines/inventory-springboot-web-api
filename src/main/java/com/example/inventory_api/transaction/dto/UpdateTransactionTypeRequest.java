package com.example.inventory_api.transaction.dto;

import com.example.inventory_api.transaction.entity.StockMovement;

public record UpdateTransactionTypeRequest(
    String name,
    String description,
    StockMovement stockMovement
) {}
