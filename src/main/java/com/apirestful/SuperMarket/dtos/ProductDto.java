package com.apirestful.SuperMarket.dtos;

import io.micrometer.common.lang.Nullable;

public class ProductDto {

	@Nullable
	private String productName;
	@Nullable
	private Double productValue;
	

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
}
