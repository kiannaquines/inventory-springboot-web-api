package com.example.inventory_api.product.entity;

import com.example.inventory_api.category.entity.Category;
import com.example.inventory_api.supplier.entity.Supplier;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Double price;
    private Integer quantity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    public Product() {}

    public Product(String name, String description, Double price, Integer quantity, Category category, Supplier supplier) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.supplier = supplier;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public Double getPrice() { return price; }
    public Integer getQuantity() { return quantity; }
    public Category getCategory() { return category; }
    public Supplier getSupplier() { return supplier; }

    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setPrice(Double price) { this.price = price; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public void setCategory(Category category) { this.category = category; }
    public void setSupplier(Supplier supplier) { this.supplier = supplier; }
}
