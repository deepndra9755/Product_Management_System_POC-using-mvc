package com.example.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Setter
@Getter
@ToString
@NoArgsConstructor
public class ProductVoImpl {
	
	private Integer id;
	private String name;
	private Float price;
	private String category;
	public ProductVoImpl(String name, Float price, String category) {
		super();
		this.name = name;
		this.price = price;
		this.category = category;
	}

}
