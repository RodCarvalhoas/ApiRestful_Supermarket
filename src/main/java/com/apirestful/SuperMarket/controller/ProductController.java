package com.apirestful.SuperMarket.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirestful.SuperMarket.dtos.ProductDto;
import com.apirestful.SuperMarket.model.Product;
import com.apirestful.SuperMarket.services.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/Product")
public class ProductController {

	final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getProductById(@PathVariable(value = "id") UUID id) {
		Optional<Product> productOptional = productService.getProductById(id);
		if (!productOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
		} else {
			productOptional.get().add(linkTo(methodOn(ProductController.class).getAllProducts()).withSelfRel());
			return ResponseEntity.status(HttpStatus.OK).body(productOptional.get());
		}
	}

	@GetMapping
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> productList = productService.getAllProducts();
		if (productList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			for (Product p : productList) {
				UUID id = p.getProductId();
				p.add(linkTo(methodOn(ProductController.class).getProductById(id)).withSelfRel());// cria o link a
																									// partir do metodo
																									// localizado
				// na classe ProductController-método getProductById- sendo único para cada
				// produto -withSelfRel-
			}
			return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProducts());
		}
	}

	@PostMapping
	public ResponseEntity<Object> saveProduct(@RequestBody @Valid ProductDto productDto) {
		if (productService.existsByProductName(productDto.getProductName())) {// Se o nome do produto já existir,
																				// retorna conflito
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Product name is already in use!");
		}
		Product product = new Product();
		BeanUtils.copyProperties(productDto, product);
		return ResponseEntity.status(HttpStatus.CREATED).body(productService.saveProduct(product));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteProduct(@PathVariable(value = "id") UUID id) {
		Optional<Product> productOptional = productService.getProductById(id);
		if (!productOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
		} else {
			productService.deleteProduct(id);
			return ResponseEntity.status(HttpStatus.OK).body("Successfully deleted product");
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> updateProduct(@PathVariable(value = "id") UUID id,
			@RequestBody @Valid ProductDto productDto) {
		Optional<Product> productOptional = productService.getProductById(id);
		if (!productOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
		} else {
			var product = new Product();
			BeanUtils.copyProperties(productDto, product);
			product.setProductId(id);
			return ResponseEntity.status(HttpStatus.OK).body(productService.saveProduct(product));
		}
	}

}