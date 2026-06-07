package com.example.inventory_api.transaction.service;

import java.util.List;

import com.example.inventory_api.transaction.dto.CreateTransactionTypeRequest;
import com.example.inventory_api.transaction.dto.GetTransactionTypesRequest;
import com.example.inventory_api.transaction.dto.UpdateTransactionTypeRequest;
import com.example.inventory_api.transaction.entity.TransactionType;

public interface TransactionTypeService {
    List<TransactionType> getAllTransactionTypes(GetTransactionTypesRequest request);
    TransactionType getTransactionTypeById(Long id);
    TransactionType createTransactionType(CreateTransactionTypeRequest request);
    TransactionType updateTransactionType(Long id, UpdateTransactionTypeRequest request);
    TransactionType deleteTransactionType(Long id);
}
