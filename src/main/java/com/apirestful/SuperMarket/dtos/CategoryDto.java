package com.apirestful.SuperMarket.dtos;

import io.micrometer.common.lang.Nullable;

public class CategoryDto {

	@Nullable
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	
}
