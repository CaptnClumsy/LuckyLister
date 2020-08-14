package com.clumsy.luckylister.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.clumsy.luckylister.entities.FilterEntity;

@Repository
public interface FilterRepo extends JpaRepository<FilterEntity, Long> {
	@Query("SELECT t FROM FilterEntity t WHERE t.userid = ?1")
	FilterEntity findByUserId(Long id);
}
