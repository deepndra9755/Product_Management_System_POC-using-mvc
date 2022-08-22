package com.example.dto;

import com.sun.istack.Nullable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDtoImpl {
	
	private Integer id;
	private String name;
	private Float price;
	private String category;
	public ProductDtoImpl(String name, Float price, String category) {
		super();
		this.name = name;
		this.price = price;
		this.category = category;
	}
	
	
}
