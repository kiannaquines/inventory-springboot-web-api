package com.example.inventory_api.transaction.controller;

import java.util.List;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.inventory_api.transaction.dto.CreateTransactionTypeRequest;
import com.example.inventory_api.transaction.dto.GetTransactionTypesRequest;
import com.example.inventory_api.transaction.dto.UpdateTransactionTypeRequest;
import com.example.inventory_api.transaction.entity.TransactionType;
import com.example.inventory_api.transaction.service.TransactionTypeService;

@RestController
@RequestMapping("/transaction-types")
public class TransactionTypeController {

    private final TransactionTypeService transactionTypeService;

    public TransactionTypeController(TransactionTypeService transactionTypeService) {
        this.transactionTypeService = transactionTypeService;
    }

    @GetMapping
    public List<TransactionType> getAll(@ParameterObject GetTransactionTypesRequest request) {
        return transactionTypeService.getAllTransactionTypes(request);
    }

    @GetMapping("/{id}")
    public TransactionType getById(@PathVariable Long id) {
        return transactionTypeService.getTransactionTypeById(id);
    }

    @PostMapping
    public TransactionType create(@RequestBody CreateTransactionTypeRequest request) {
        return transactionTypeService.createTransactionType(request);
    }

    @PutMapping("/{id}")
    public TransactionType update(@PathVariable Long id, @RequestBody UpdateTransactionTypeRequest request) {
        return transactionTypeService.updateTransactionType(id, request);
    }

    @DeleteMapping("/{id}")
    public TransactionType delete(@PathVariable Long id) {
        return transactionTypeService.deleteTransactionType(id);
    }
}
