package com.example.inventory_api.transaction.dto;

import com.example.inventory_api.transaction.entity.StockMovement;

public record GetTransactionTypesRequest(
    String name,
    StockMovement stockMovement
) {}
