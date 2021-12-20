package com.example.first.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.first.model.Voc;

public interface VocRepository extends JpaRepository<Voc, Integer>{
	
	List<Voc> findByVocId(Integer vocId);
}
