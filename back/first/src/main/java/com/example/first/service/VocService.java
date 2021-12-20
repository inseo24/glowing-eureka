package com.example.first.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.first.model.Compensation;
import com.example.first.model.Voc;
import com.example.first.persistence.VocRepository;

@Service
public class VocService {
	
	@Autowired
	private VocRepository vocRepo;
	
	private void validate(final Voc entity) {
		if (entity == null) {
			throw new RuntimeException("entity cannot be null");
		}
	}
	
	public List<Voc> create(final Voc entity){
		
		validate(entity);
		
		Compensation compensation = new Compensation();
		compensation.setCompensationValue(0);
		compensation.setVoc(entity);
		
		vocRepo.save(entity);
		return vocRepo.findByVocId(entity.getVocId());
	}
	
	public List<Voc> retreive() {
		return vocRepo.findAll();
	}
}
