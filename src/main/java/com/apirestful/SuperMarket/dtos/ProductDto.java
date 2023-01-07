package com.apirestful.SuperMarket.dtos;

import io.micrometer.common.lang.Nullable;

public class ProductDto {

	@Nullable
	private String productGroup;
	@Nullable
	private String productName;
	@Nullable
	private Double productValue;

	public String getProductGroup() {
		return productGroup;
	}

	public void setProductGroup(String productGroup) {
		this.productGroup = productGroup;
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

}
