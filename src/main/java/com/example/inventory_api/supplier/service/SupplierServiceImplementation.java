package com.example.inventory_api.supplier.service;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.inventory_api.supplier.dto.CreateSupplierRequest;
import com.example.inventory_api.supplier.dto.GetSuppliersRequest;
import com.example.inventory_api.supplier.dto.UpdateSupplierRequest;
import com.example.inventory_api.supplier.entity.Supplier;
import com.example.inventory_api.supplier.exception.SupplierNotFoundException;
import com.example.inventory_api.supplier.repository.SupplierRepository;

@Service
public class SupplierServiceImplementation implements SupplierService {

    private final SupplierRepository supplierRepository;

    public SupplierServiceImplementation(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public List<Supplier> getAllSuppliers(GetSuppliersRequest request) {
        Specification<Supplier> spec = (root, query, cb) -> cb.conjunction();

        if (request.name() != null && !request.name().isBlank()) {
            spec = spec.and((root, query, cb) ->
                    cb.like(cb.lower(root.get("name")), "%" + request.name().toLowerCase() + "%")
            );
        }

        if (request.email() != null && !request.email().isBlank()) {
            spec = spec.and((root, query, cb) ->
                    cb.like(cb.lower(root.get("email")), "%" + request.email().toLowerCase() + "%")
            );
        }

        return supplierRepository.findAll(spec);
    }

    @Override
    public Supplier getSupplierById(Long id) {
        return supplierRepository.findById(id)
                .orElseThrow(() -> new SupplierNotFoundException("Supplier not found with id: " + id));
    }

    @Override
    public Supplier createSupplier(CreateSupplierRequest request) {
        Supplier supplier = new Supplier(
                request.name(),
                request.contactName(),
                request.email(),
                request.phone(),
                request.address()
        );
        return supplierRepository.save(supplier);
    }

    @Override
    public Supplier updateSupplier(Long id, UpdateSupplierRequest request) {
        Supplier existing = getSupplierById(id);
        if (request.name() != null) existing.setName(request.name());
        if (request.contactName() != null) existing.setContactName(request.contactName());
        if (request.email() != null) existing.setEmail(request.email());
        if (request.phone() != null) existing.setPhone(request.phone());
        if (request.address() != null) existing.setAddress(request.address());
        return supplierRepository.save(existing);
    }

    @Override
    public Supplier deleteSupplier(Long id) {
        Supplier existing = getSupplierById(id);
        supplierRepository.delete(existing);
        return existing;
    }

    @Override
    public List<Supplier> deleteAllSuppliers() {
        List<Supplier> suppliers = supplierRepository.findAll();
        supplierRepository.deleteAll();
        return suppliers;
    }
}
