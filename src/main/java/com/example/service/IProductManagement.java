package com.example.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.example.controller.Exceptions.RecordNotFoundExceptions;
import com.example.dto.ProductDtoImpl;

public interface IProductManagement {
	
	public ProductDtoImpl insertProduct(ProductDtoImpl dto)throws Exception;
    public  void deleteRecord(Integer id)throws Exception;
    public List<ProductDtoImpl> findAllRecords()throws Exception;
    public List<ProductDtoImpl> findAllRecords(Pageable page);
    public ProductDtoImpl findByID(Integer id)throws Exception;
    public void throwIfNotExist(Integer id)throws Exception;
    public Long getAllRecodsCount(Pageable page);
    public Long getTotalPages(Pageable page);
    public List<ProductDtoImpl> findElementsByCategory(String category)throws RecordNotFoundExceptions;
}
