package com.example.first.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.first.model.Compensation;

public interface CompensationRepository extends JpaRepository<Compensation, Integer> {
	
	List<Compensation> findByCompensationId(Integer compensationId);
	
	@Query(value = "SELECT * FROM compensation WHERE voc_id = :vocId", nativeQuery = true)
	List<Compensation> retrieve(@Param("vocId") Integer vocId);

}