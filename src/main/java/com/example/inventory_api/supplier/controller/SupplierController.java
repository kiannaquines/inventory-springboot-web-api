package com.example.inventory_api.supplier.controller;

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

import com.example.inventory_api.supplier.dto.CreateSupplierRequest;
import com.example.inventory_api.supplier.dto.GetSuppliersRequest;
import com.example.inventory_api.supplier.dto.UpdateSupplierRequest;
import com.example.inventory_api.supplier.entity.Supplier;
import com.example.inventory_api.supplier.service.SupplierService;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping
    public List<Supplier> getAll(@ParameterObject GetSuppliersRequest request) {
        return supplierService.getAllSuppliers(request);
    }

    @GetMapping("/{id}")
    public Supplier getById(@PathVariable Long id) {
        return supplierService.getSupplierById(id);
    }

    @PostMapping
    public Supplier create(@RequestBody CreateSupplierRequest request) {
        return supplierService.createSupplier(request);
    }

    @PutMapping("/{id}")
    public Supplier update(@PathVariable Long id, @RequestBody UpdateSupplierRequest request) {
        return supplierService.updateSupplier(id, request);
    }

    @DeleteMapping("/{id}")
    public Supplier delete(@PathVariable Long id) {
        return supplierService.deleteSupplier(id);
    }

    @DeleteMapping
    public List<Supplier> deleteAll() {
        return supplierService.deleteAllSuppliers();
    }
}
