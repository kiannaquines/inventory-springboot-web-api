package com.example.inventory_api.transaction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.inventory_api.transaction.entity.TransactionType;

public interface TransactionTypeRepository extends JpaRepository<TransactionType, Long>, JpaSpecificationExecutor<TransactionType> {}
