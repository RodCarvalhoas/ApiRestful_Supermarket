package com.apirestful.SuperMarket.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.apirestful.SuperMarket.model.Category;
import com.apirestful.SuperMarket.model.Product;
import com.apirestful.SuperMarket.repositories.CategoryRepository;
import com.apirestful.SuperMarket.repositories.ProductRepository;

@Configuration
public class Config implements CommandLineRunner{

	@Autowired
	private CategoryRepository categorieRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		Category cat1 = new Category(null,"Eletronico");
		Category cat2 = new Category(null,"Livros");
		Category cat3 = new Category(null, "Futebol");
		
		categorieRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		
		Product p1 = new Product(null, "Iphone", 5000.0, cat1);
		Product p2 = new Product(null, "Harry Potter", 75.0, cat2);
		Product p3 = new Product(null, "Bola", 20.0, cat3);
		Product p4 = new Product(null, "Luva de Goleiro", 50.0, cat3);
		Product p5 = new Product(null, "Apito", 35.0, cat3);
		Product p6 = new Product(null, "Notebook Gamer", 7430.00, cat1);
		Product p7 = new Product(null, "Fada do dente", 20.0, cat2);
		
		
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7));
	
	}
}
