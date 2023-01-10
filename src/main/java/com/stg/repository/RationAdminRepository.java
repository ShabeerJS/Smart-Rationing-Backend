package com.stg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.stg.entity.RationAdmin;
import com.stg.entity.RationCard;

public interface RationAdminRepository extends JpaRepository<RationAdmin, Integer> {

	
	public abstract RationAdmin findByRationAdminName(String rationAdminName);

	public abstract RationAdmin findByEmailId(String emailId);
	
}


//@Query(value = "select * from ration_card where ration_card_no=?", nativeQuery = true)