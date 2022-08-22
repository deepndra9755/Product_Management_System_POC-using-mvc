package com.example.bo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {
	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	private Float price;
	private String category;

	public Product(String name, Float price, String category) {
		super();
		this.name = name;
		this.price = price;
		this.category = category;
	}

}
