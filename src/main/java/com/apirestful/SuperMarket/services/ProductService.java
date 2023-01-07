package com.apirestful.SuperMarket.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.apirestful.SuperMarket.model.Product;
import com.apirestful.SuperMarket.repositories.ProductRepository;

@Service
public class ProductService {
	
	ProductRepository productRepository;
	
	public ProductService(ProductRepository productRepository) {//Injeção de dependência
		this.productRepository = productRepository;
	}

	public Optional<Product> getProductById(UUID id) {
		return productRepository.findById(id);
	}
	
	
}
