package com.example.inventory_api.product.service;

import java.util.List;

import com.example.inventory_api.product.dto.CreateProductRequest;
import com.example.inventory_api.product.dto.GetProductsRequest;
import com.example.inventory_api.product.dto.UpdateProductRequest;
import com.example.inventory_api.product.entity.Product;

public interface ProductService {
    List<Product> getAllProducts(GetProductsRequest request);
    Product createProduct(CreateProductRequest request);
    Product getProductById(Long id);
    Product updateProduct(Long id, UpdateProductRequest request);
    Product deleteProduct(Long id);
    List<Product> deleteAllProducts();
}
