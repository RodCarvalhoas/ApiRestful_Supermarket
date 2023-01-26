package com.apirestful.SuperMarket.controller;

import java.util.List;
import java.util.Optional;

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

import com.apirestful.SuperMarket.dtos.CategoryDto;
import com.apirestful.SuperMarket.model.Category;
import com.apirestful.SuperMarket.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/Category")
public class CategoryController {

	final CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getCategoryById(@PathVariable(value = "id") Long id) {
		Optional<Category> CategoryOptional = categoryService.getcategoryById(id);
		if (!CategoryOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found");
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(CategoryOptional.get());
		}
	}

	@GetMapping
	public ResponseEntity<List<Category>> getAllCategories() {
		List<Category> CategoryList = categoryService.getAllcategorys();
		if (CategoryList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} 
			return ResponseEntity.status(HttpStatus.OK).body(categoryService.getAllcategorys());
		}
	

	@PostMapping
	public ResponseEntity<Object> saveCategory(@RequestBody @Valid CategoryDto CategoryDto) {
		Category category = new Category();
		BeanUtils.copyProperties(CategoryDto, category);
		return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.saveCategory(category));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteCategory(@PathVariable(value = "id") Long id) {
		Optional<Category> CategoryOptional = categoryService.getcategoryById(id);
		if (!CategoryOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found");
		} else {
			categoryService.deletecategory(id);
			return ResponseEntity.status(HttpStatus.OK).body("Successfully deleted Category");
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> updateCategory(@PathVariable(value = "id") Long id,
			@RequestBody @Valid CategoryDto CategoryDto) {
		Optional<Category> CategoryOptional = categoryService.getcategoryById(id);
		if (!CategoryOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found");
		} else {
			var category = new Category();
			BeanUtils.copyProperties(CategoryDto, category);
			category.setId(id);
			return ResponseEntity.status(HttpStatus.OK).body(categoryService.saveCategory(category));
		}
	}

}