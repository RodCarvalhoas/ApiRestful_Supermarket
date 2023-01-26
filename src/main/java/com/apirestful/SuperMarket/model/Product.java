package com.apirestful.SuperMarket.model;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_PRODUCT")
public class Product extends RepresentationModel<Product> implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID productId;
	@Column(nullable = false)
	private String productName;
	@Column(nullable = false)
	private Double productValue;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name =  "category_id")
	private Category category;
	
	public Product() {
		
	}
	
	public Product(UUID productId, String productName, Double productValue, Category category) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productValue = productValue;
		this.category = category;
	}

	public UUID getProductId() {
		return productId;
	}
	public void setProductId(UUID productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Double getProductValue() {
		return productValue;
	}
	public void setProductValue(Double productValue) {
		this.productValue = productValue;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	
	
}