package com.example.inventory_api.supplier.service;

import java.util.List;

import com.example.inventory_api.supplier.dto.CreateSupplierRequest;
import com.example.inventory_api.supplier.dto.GetSuppliersRequest;
import com.example.inventory_api.supplier.dto.UpdateSupplierRequest;
import com.example.inventory_api.supplier.entity.Supplier;

public interface SupplierService {
    List<Supplier> getAllSuppliers(GetSuppliersRequest request);
    Supplier getSupplierById(Long id);
    Supplier createSupplier(CreateSupplierRequest request);
    Supplier updateSupplier(Long id, UpdateSupplierRequest request);
    Supplier deleteSupplier(Long id);
    List<Supplier> deleteAllSuppliers();
}
