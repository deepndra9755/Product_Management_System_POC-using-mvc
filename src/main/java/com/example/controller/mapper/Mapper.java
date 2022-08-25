package com.example.controller.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.example.bo.Product;
import com.example.dto.ProductDtoImpl;
import com.example.vo.ProductVoImpl;

public class Mapper {

	public static ProductVoImpl toGetProductVo(ProductDtoImpl dtoImpl) {
		return new ProductVoImpl(dtoImpl.getId(), dtoImpl.getName(), dtoImpl.getPrice(), dtoImpl.getCategory());
	}

	public static ProductDtoImpl toGetProductDtoImpl(ProductVoImpl product) {
		return new ProductDtoImpl(product.getName(), product.getPrice(), product.getCategory());
	}
	
	

	public static List<ProductVoImpl> toGetProductVoList(List<ProductDtoImpl> list) {
		return list.stream()
				.map(lst -> new ProductVoImpl(lst.getId(), lst.getName(), lst.getPrice(), lst.getCategory()))
				.collect(Collectors.toList());

	}

}
