package com.eventManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eventManagement.model.Gift;

@Repository
public interface GiftRepository extends JpaRepository<Gift,Long>{
 
	@Query("SELECT g FROM Gift g WHERE g.adminId = :adminId")
	List<Gift> findAllGiftByAdminId(@Param("adminId") Long adminId);

}
