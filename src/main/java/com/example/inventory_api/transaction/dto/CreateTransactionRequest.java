package com.example.inventory_api.transaction.dto;

public record CreateTransactionRequest(
    Long productId,
    Long transactionTypeId,
    Integer quantity,
    String notes
) {}
