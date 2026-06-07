package com.example.inventory_api.transaction.service;

import java.util.List;

import com.example.inventory_api.transaction.dto.CreateTransactionRequest;
import com.example.inventory_api.transaction.dto.GetTransactionsRequest;
import com.example.inventory_api.transaction.entity.InventoryTransaction;

public interface InventoryTransactionService {
    List<InventoryTransaction> getAllTransactions(GetTransactionsRequest request);
    InventoryTransaction getTransactionById(Long id);
    InventoryTransaction createTransaction(CreateTransactionRequest request);
    List<InventoryTransaction> getTransactionsByProductId(Long productId);
}
