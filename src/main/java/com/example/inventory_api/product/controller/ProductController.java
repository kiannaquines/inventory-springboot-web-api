package com.example.inventory_api.product.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.inventory_api.product.dto.CreateProductRequest;
import com.example.inventory_api.product.dto.GetProductsRequest;
import com.example.inventory_api.product.dto.UpdateProductRequest;
import com.example.inventory_api.product.entity.Product;
import com.example.inventory_api.product.service.ProductService;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAll(@ParameterObject GetProductsRequest request) {
        return productService.getAllProducts(request);
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PostMapping
    public Product create(@RequestBody CreateProductRequest request) {
        return productService.createProduct(request);
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @RequestBody UpdateProductRequest request) {
        return productService.updateProduct(id, request);
    }

    @DeleteMapping("/{id}")
    public Product delete(@PathVariable Long id) {
        return productService.deleteProduct(id);
    }

    @DeleteMapping
    public List<Product> deleteAll() {
        return productService.deleteAllProducts();
    }
}
