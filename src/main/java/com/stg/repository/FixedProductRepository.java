package com.stg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.stg.entity.FixedProducts;
import com.stg.entity.RationCard;

public interface FixedProductRepository extends JpaRepository<FixedProducts, Integer> {

	
	
	public abstract FixedProducts findByProductName(String productName);

}
