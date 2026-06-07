package com.example.inventory_api.transaction.entity;

import java.time.LocalDateTime;

import com.example.inventory_api.product.entity.Product;

import jakarta.persistence.*;

@Entity
@Table(name = "inventory_transactions")
public class InventoryTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "transaction_type_id", nullable = false)
    private TransactionType transactionType;

    @Column(nullable = false)
    private Integer quantity;

    private String notes;

    @Column(nullable = false)
    private LocalDateTime transactionDate;

    public InventoryTransaction() {}

    public InventoryTransaction(Product product, TransactionType transactionType, Integer quantity, String notes, LocalDateTime transactionDate) {
        this.product = product;
        this.transactionType = transactionType;
        this.quantity = quantity;
        this.notes = notes;
        this.transactionDate = transactionDate;
    }

    public Long getId() { return id; }
    public Product getProduct() { return product; }
    public TransactionType getTransactionType() { return transactionType; }
    public Integer getQuantity() { return quantity; }
    public String getNotes() { return notes; }
    public LocalDateTime getTransactionDate() { return transactionDate; }

    public void setProduct(Product product) { this.product = product; }
    public void setTransactionType(TransactionType transactionType) { this.transactionType = transactionType; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public void setNotes(String notes) { this.notes = notes; }
    public void setTransactionDate(LocalDateTime transactionDate) { this.transactionDate = transactionDate; }
}
