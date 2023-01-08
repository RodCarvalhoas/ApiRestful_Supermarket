package com.apirestful.SuperMarket.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.apirestful.SuperMarket.model.Product;
import com.apirestful.SuperMarket.repositories.ProductRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductService {
	
	ProductRepository productRepository;
	
	public ProductService(ProductRepository productRepository) {//Injeção de dependência
		this.productRepository = productRepository;
	}

	public Optional<Product> getProductById(UUID id) {
		return productRepository.findById(id);
	}
	
	public List<Product> getAllProducts(){
		return productRepository.findAll();
	}
	
	@Transactional
	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}
	
	@Transactional
	public void deleteProduct(UUID id) {
		productRepository.deleteById(id);
	}
	
	public boolean existsByProductName(String ProductName) {//Método p/ saber se já existe o nome
        return productRepository.existsByProductName(ProductName);
    }
	
}
