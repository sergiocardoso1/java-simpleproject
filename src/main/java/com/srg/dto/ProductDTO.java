package com.srg.dto;

import java.io.Serializable;

public class ProductDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String name;
	private Double price;
	
	private Long categoryId;
	
	public ProductDTO() {
		
	}

	public ProductDTO(String name, Double price, Long categoryId) {
		super();
		this.name = name;
		this.price = price;
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	
}
