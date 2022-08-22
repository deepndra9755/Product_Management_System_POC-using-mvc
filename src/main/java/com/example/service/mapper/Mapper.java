package com.example.service.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.example.bo.Product;
import com.example.dto.ProductDtoImpl;

public class Mapper {

	public static Product toGetProduct(ProductDtoImpl dtoImpl) {
		return new Product(dtoImpl.getId(), dtoImpl.getName(), dtoImpl.getPrice(), dtoImpl.getCategory());
	}

	public static ProductDtoImpl toGetProductDtoImpl(Product product) {
		return new ProductDtoImpl(product.getName(), product.getPrice(), product.getCategory());
	}

	public static List<ProductDtoImpl> toGetProductDtoList(List<Product> list) {
		return list.stream()
				.map(lst -> new ProductDtoImpl(lst.getId(), lst.getName(), lst.getPrice(), lst.getCategory()))
				.collect(Collectors.toList());

	}

}
