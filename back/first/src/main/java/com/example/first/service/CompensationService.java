package com.example.first.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.first.dto.PenaltyDTO;
import com.example.first.model.Compensation;
import com.example.first.model.Penalty;
import com.example.first.model.Voc;
import com.example.first.persistence.CompensationRepository;
import com.example.first.persistence.VocRepository;

@Service
public class CompensationService {
	
	@Autowired
	private CompensationRepository comRepo;
	
	@Autowired
	private VocRepository vocRepo;
	
	@Autowired
	public PenaltyService penaltyservice;
	
	private void validate(final Compensation entity) {
		if (entity == null) {
			throw new RuntimeException("entity cannot be null");
		}
		
	}
	
	public List<Compensation> create(final Compensation entity, Integer vocId, String name){
			
			validate(entity);
			
			Voc vocEntity = vocRepo.getById(vocId);
			
			vocEntity.setVocId(vocId);
			vocEntity.setCompensations(entity);
			
			entity.setVoc(vocEntity);
			entity.setCompensationValue(entity.getCompensationValue());
			 
			if (vocEntity.getFaultIdentifier() == 1) {
				PenaltyDTO dto = new PenaltyDTO();
				dto.setDelChk(0);
				dto.setIssued(0);
				dto.setName(name);
				dto.setPenaltyCost(entity.getCompensationValue());
				dto.setVoc(vocEntity);
				Penalty penaltyEntity = PenaltyDTO.toEntity(dto);
				
				List<Penalty> entities = penaltyservice.create(penaltyEntity, vocEntity);
		
				vocEntity.setPenalties(entities);
			} else {
				
			}
			
			comRepo.save(entity);
			
			return comRepo.findByCompensationId(entity.getCompensationId());
			
	}
		
	
	public List<Compensation> retrieve() {
		return comRepo.findAll();
	}
	
}
