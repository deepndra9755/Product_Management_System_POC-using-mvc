package com.example.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.bo.Product;

public interface IProductRepo extends JpaRepository<Product, Integer>{

	@Modifying
	@Transactional
	@Query("UPDATE com.example.bo.Product set price=?1 where id=?2")
	public void editProduct(Float price,Integer id);
	
	@Query("from com.example.bo.Product where category=:category")
	public List<Product> findByCategory(@Param("category") String valuewe);
	
}
