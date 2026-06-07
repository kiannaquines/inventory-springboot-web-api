package com.example.inventory_api.transaction.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.inventory_api.product.entity.Product;
import com.example.inventory_api.product.service.ProductService;
import com.example.inventory_api.transaction.dto.CreateTransactionRequest;
import com.example.inventory_api.transaction.dto.GetTransactionsRequest;
import com.example.inventory_api.transaction.entity.InventoryTransaction;
import com.example.inventory_api.transaction.entity.TransactionType;
import com.example.inventory_api.transaction.exception.TransactionNotFoundException;
import com.example.inventory_api.transaction.repository.InventoryTransactionRepository;

@Service
public class InventoryTransactionServiceImplementation implements InventoryTransactionService {

    private final InventoryTransactionRepository transactionRepository;
    private final ProductService productService;
    private final TransactionTypeService transactionTypeService;

    public InventoryTransactionServiceImplementation(
            InventoryTransactionRepository transactionRepository,
            ProductService productService,
            TransactionTypeService transactionTypeService) {
        this.transactionRepository = transactionRepository;
        this.productService = productService;
        this.transactionTypeService = transactionTypeService;
    }

    @Override
    public List<InventoryTransaction> getAllTransactions(GetTransactionsRequest request) {
        Specification<InventoryTransaction> spec = (root, query, cb) -> cb.conjunction();

        if (request.productId() != null) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("product").get("id"), request.productId())
            );
        }

        if (request.transactionTypeId() != null) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("transactionType").get("id"), request.transactionTypeId())
            );
        }

        return transactionRepository.findAll(spec);
    }

    @Override
    public InventoryTransaction getTransactionById(Long id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new TransactionNotFoundException("Transaction not found with id: " + id));
    }

    @Override
    @Transactional
    public InventoryTransaction createTransaction(CreateTransactionRequest request) {
        Product product = productService.getProductById(request.productId());
        TransactionType transactionType = transactionTypeService.getTransactionTypeById(request.transactionTypeId());

        int currentQuantity = product.getQuantity() != null ? product.getQuantity() : 0;
        int delta = request.quantity();

        int newQuantity = switch (transactionType.getStockMovement()) {
            case IN -> currentQuantity + delta;
            case OUT -> {
                if (currentQuantity < delta) {
                    throw new IllegalArgumentException(
                            "Insufficient stock. Available: " + currentQuantity + ", requested: " + delta
                    );
                }
                yield currentQuantity - delta;
            }
            case ADJUSTMENT -> delta;
        };

        product.setQuantity(newQuantity);

        InventoryTransaction transaction = new InventoryTransaction(
                product,
                transactionType,
                request.quantity(),
                request.notes(),
                LocalDateTime.now()
        );

        return transactionRepository.save(transaction);
    }

    @Override
    public List<InventoryTransaction> getTransactionsByProductId(Long productId) {
        productService.getProductById(productId);
        Specification<InventoryTransaction> spec = (root, query, cb) ->
                cb.equal(root.get("product").get("id"), productId);
        return transactionRepository.findAll(spec);
    }
}
