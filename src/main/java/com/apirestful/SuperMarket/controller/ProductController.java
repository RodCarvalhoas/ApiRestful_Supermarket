package com.apirestful.SuperMarket.controller;

import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirestful.SuperMarket.model.Product;
import com.apirestful.SuperMarket.services.ProductService;

@RestController
@RequestMapping("/Product")
public class ProductController {
	
	final ProductService productService;
	
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getProductById(@PathVariable(value = "id")UUID id){
		Optional<Product> productOptional = productService.getProductById(id);
		if(!productOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
		}else {
			return ResponseEntity.status(HttpStatus.OK).body(productOptional.get());
		}
		
	}
	
}
