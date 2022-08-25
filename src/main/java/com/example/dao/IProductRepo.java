package com.example.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.bo.Product;

@Repository
public interface IProductRepo extends JpaRepository<Product, Integer> {

	@Modifying
	@Transactional
	@Query("UPDATE com.example.bo.Product set price=?1 where id=?2")
	public void editProduct(Float price, Integer id);

	@Query("from com.example.bo.Product where category=:category")
	public List<Product> findByCategory(@Param("category") String value);

	// @Query("from com.example.bo.Product where name LIKE %:name% AND price>:price
	// AND price<:to")
	
	@Query("from com.example.bo.Product where name LIKE %:name% AND price BETWEEN :price and :to")
	public List<Product> findProduct(@Param("name") String name, @Param("price") Float price, @Param("to") Float to);

}
