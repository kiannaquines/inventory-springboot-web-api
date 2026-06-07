package com.example.inventory_api.product.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.inventory_api.product.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> , JpaSpecificationExecutor<Product>{}
