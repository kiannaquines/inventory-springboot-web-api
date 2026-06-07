package com.example.inventory_api.transaction.service;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.inventory_api.transaction.dto.CreateTransactionTypeRequest;
import com.example.inventory_api.transaction.dto.GetTransactionTypesRequest;
import com.example.inventory_api.transaction.dto.UpdateTransactionTypeRequest;
import com.example.inventory_api.transaction.entity.TransactionType;
import com.example.inventory_api.transaction.exception.TransactionTypeNotFoundException;
import com.example.inventory_api.transaction.repository.TransactionTypeRepository;

@Service
public class TransactionTypeServiceImplementation implements TransactionTypeService {

    private final TransactionTypeRepository transactionTypeRepository;

    public TransactionTypeServiceImplementation(TransactionTypeRepository transactionTypeRepository) {
        this.transactionTypeRepository = transactionTypeRepository;
    }

    @Override
    public List<TransactionType> getAllTransactionTypes(GetTransactionTypesRequest request) {
        Specification<TransactionType> spec = (root, query, cb) -> cb.conjunction();

        if (request.name() != null && !request.name().isBlank()) {
            spec = spec.and((root, query, cb) ->
                    cb.like(cb.lower(root.get("name")), "%" + request.name().toLowerCase() + "%")
            );
        }

        if (request.stockMovement() != null) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("stockMovement"), request.stockMovement())
            );
        }

        return transactionTypeRepository.findAll(spec);
    }

    @Override
    public TransactionType getTransactionTypeById(Long id) {
        return transactionTypeRepository.findById(id)
                .orElseThrow(() -> new TransactionTypeNotFoundException("Transaction type not found with id: " + id));
    }

    @Override
    public TransactionType createTransactionType(CreateTransactionTypeRequest request) {
        TransactionType transactionType = new TransactionType(
                request.name(),
                request.description(),
                request.stockMovement()
        );
        return transactionTypeRepository.save(transactionType);
    }

    @Override
    public TransactionType updateTransactionType(Long id, UpdateTransactionTypeRequest request) {
        TransactionType existing = getTransactionTypeById(id);
        if (request.name() != null) existing.setName(request.name());
        if (request.description() != null) existing.setDescription(request.description());
        if (request.stockMovement() != null) existing.setStockMovement(request.stockMovement());
        return transactionTypeRepository.save(existing);
    }

    @Override
    public TransactionType deleteTransactionType(Long id) {
        TransactionType existing = getTransactionTypeById(id);
        transactionTypeRepository.delete(existing);
        return existing;
    }
}
