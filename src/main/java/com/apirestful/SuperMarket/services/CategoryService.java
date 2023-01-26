package com.apirestful.SuperMarket.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.apirestful.SuperMarket.model.Category;
import com.apirestful.SuperMarket.repositories.CategoryRepository;

import jakarta.transaction.Transactional;

@Service
public class CategoryService {

	CategoryRepository categoryRepository;

	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	public Optional<Category> getcategoryById(Long id) {
		return categoryRepository.findById(id);
	}

	public List<Category> getAllcategorys() {
		return categoryRepository.findAll();
	}

	@Transactional
	public Category saveCategory(Category category) {
		return categoryRepository.save(category);
	}

	@Transactional
	public void deletecategory(Long id) {
		categoryRepository.deleteById(id);

	}
}
