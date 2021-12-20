package com.example.first.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.first.dto.CompensationDTO;
import com.example.first.dto.ResponseDTO;
import com.example.first.model.Compensation;
import com.example.first.service.CompensationService;

@RestController
@RequestMapping("compensation")
public class CompensationController {
	
	
	@Autowired
	public CompensationService service;
	
	@PostMapping("/{vocId}/{name}")
	public ResponseEntity<?> create(@RequestBody CompensationDTO dto, @PathVariable Integer vocId, @PathVariable String name){
		
		try {
			Compensation entity = CompensationDTO.toEntity(dto);
			
			List<Compensation> entities = service.create(entity, vocId, name);
			
			List<CompensationDTO> dtos = entities.stream().map(CompensationDTO::new).collect(Collectors.toList());
			
			ResponseDTO<CompensationDTO> response = ResponseDTO.<CompensationDTO>builder().data(dtos).build();
			
			return ResponseEntity.ok().body(response);
		
		} catch (Exception e) {
			
			String error = e.getMessage();
			ResponseDTO<CompensationDTO> response = ResponseDTO.<CompensationDTO>builder().error(error).build();
			return ResponseEntity.badRequest().body(response);
		
		}
		
	}
	
	@GetMapping
	public ResponseEntity<?> retrieve(){
		
		List<Compensation> entities = service.retrieve();
		List<CompensationDTO> dtos = entities.stream().map(CompensationDTO::new).collect(Collectors.toList());
		ResponseDTO<CompensationDTO> response = ResponseDTO.<CompensationDTO>builder().data(dtos).build();
		return ResponseEntity.ok().body(response);
	}
}
