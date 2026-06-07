package com.example.inventory_api.transaction.controller;

import java.util.List;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.inventory_api.transaction.dto.CreateTransactionRequest;
import com.example.inventory_api.transaction.dto.GetTransactionsRequest;
import com.example.inventory_api.transaction.entity.InventoryTransaction;
import com.example.inventory_api.transaction.service.InventoryTransactionService;

@RestController
@RequestMapping("/transactions")
public class InventoryTransactionController {

    private final InventoryTransactionService transactionService;

    public InventoryTransactionController(InventoryTransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public List<InventoryTransaction> getAll(@ParameterObject GetTransactionsRequest request) {
        return transactionService.getAllTransactions(request);
    }

    @GetMapping("/{id}")
    public InventoryTransaction getById(@PathVariable Long id) {
        return transactionService.getTransactionById(id);
    }

    @GetMapping("/product/{productId}")
    public List<InventoryTransaction> getByProduct(@PathVariable Long productId) {
        return transactionService.getTransactionsByProductId(productId);
    }

    @PostMapping
    public InventoryTransaction create(@RequestBody CreateTransactionRequest request) {
        return transactionService.createTransaction(request);
    }
}
