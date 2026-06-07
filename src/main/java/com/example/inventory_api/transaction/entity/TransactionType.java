package com.example.inventory_api.transaction.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "transaction_types")
public class TransactionType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StockMovement stockMovement;

    public TransactionType() {}

    public TransactionType(String name, String description, StockMovement stockMovement) {
        this.name = name;
        this.description = description;
        this.stockMovement = stockMovement;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public StockMovement getStockMovement() { return stockMovement; }

    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setStockMovement(StockMovement stockMovement) { this.stockMovement = stockMovement; }
}
