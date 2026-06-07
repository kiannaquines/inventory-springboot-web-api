package com.example.inventory_api.product.service;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.inventory_api.category.entity.Category;
import com.example.inventory_api.category.service.CategoryService;
import com.example.inventory_api.product.dto.CreateProductRequest;
import com.example.inventory_api.product.dto.GetProductsRequest;
import com.example.inventory_api.product.dto.UpdateProductRequest;
import com.example.inventory_api.product.entity.Product;
import com.example.inventory_api.product.exception.ProductNotFoundException;
import com.example.inventory_api.product.repository.ProductRepository;
import com.example.inventory_api.supplier.entity.Supplier;
import com.example.inventory_api.supplier.service.SupplierService;

@Service
public class ProductServiceImplementation implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final SupplierService supplierService;

    public ProductServiceImplementation(
            ProductRepository productRepository,
            CategoryService categoryService,
            SupplierService supplierService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.supplierService = supplierService;
    }

    @Override
    public List<Product> getAllProducts(GetProductsRequest request) {
        Specification<Product> spec = (root, query, cb) -> cb.conjunction();

        if (request.name() != null && !request.name().isBlank()) {
            spec = spec.and((root, query, cb) ->
                    cb.like(cb.lower(root.get("name")), "%" + request.name().toLowerCase() + "%")
            );
        }

        if (request.price() != null) {
            spec = spec.and((root, query, cb) ->
                    cb.lessThanOrEqualTo(root.get("price"), request.price())
            );
        }

        if (request.categoryId() != null) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("category").get("id"), request.categoryId())
            );
        }

        if (request.supplierId() != null) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("supplier").get("id"), request.supplierId())
            );
        }

        return productRepository.findAll(spec);
    }

    @Override
    public Product createProduct(CreateProductRequest request) {
        Category category = request.categoryId() != null
                ? categoryService.getCategoryById(request.categoryId())
                : null;
        Supplier supplier = request.supplierId() != null
                ? supplierService.getSupplierById(request.supplierId())
                : null;

        Product product = new Product(
                request.name(),
                request.description(),
                request.price(),
                request.quantity(),
                category,
                supplier
        );
        return productRepository.save(product);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
    }

    @Override
    public Product updateProduct(Long id, UpdateProductRequest request) {
        Product existing = getProductById(id);

        if (request.name() != null) existing.setName(request.name());
        if (request.description() != null) existing.setDescription(request.description());
        if (request.price() != null) existing.setPrice(request.price());
        if (request.quantity() != null) existing.setQuantity(request.quantity());

        if (request.categoryId() != null) {
            existing.setCategory(categoryService.getCategoryById(request.categoryId()));
        }
        if (request.supplierId() != null) {
            existing.setSupplier(supplierService.getSupplierById(request.supplierId()));
        }

        return productRepository.save(existing);
    }

    @Override
    public Product deleteProduct(Long id) {
        Product existing = getProductById(id);
        productRepository.delete(existing);
        return existing;
    }

    @Override
    public List<Product> deleteAllProducts() {
        List<Product> products = productRepository.findAll();
        productRepository.deleteAll();
        return products;
    }
}
