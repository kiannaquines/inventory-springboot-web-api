package com.example.inventory_api.transaction.dto;

public record GetTransactionsRequest(
    Long productId,
    Long transactionTypeId
) {}
