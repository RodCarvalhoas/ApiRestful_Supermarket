package com.apirestful.SuperMarket.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apirestful.SuperMarket.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

	boolean existsByProductName(String ProductName);
}
