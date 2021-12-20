package com.example.first.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.first.model.Penalty;

public interface PenaltyRepository extends JpaRepository<Penalty, Integer> {
	
	List<Penalty> findByPenaltyId(Integer penaltyId);
}
