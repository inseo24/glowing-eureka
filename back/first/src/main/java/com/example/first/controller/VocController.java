package com.example.first.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.first.dto.ResponseDTO;
import com.example.first.dto.VocDTO;
import com.example.first.model.Voc;
import com.example.first.service.VocService;

@RestController
@RequestMapping("voc")
public class VocController {
	
	@Autowired
	public VocService service;
	
	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody VocDTO dto){
		
		try {
			
			Voc entity = VocDTO.toEntity(dto);
			List<Voc> entities = service.create(entity);
			List<VocDTO> dtos = entities.stream().map(VocDTO::new).collect(Collectors.toList());
			ResponseDTO<VocDTO> response = ResponseDTO.<VocDTO>builder().data(dtos).build();
			
			return ResponseEntity.ok().body(response);
			
		} catch (Exception e) {
			String error = e.getMessage();
			ResponseDTO<VocDTO> response = ResponseDTO.<VocDTO>builder().error(error).build();
			
			return ResponseEntity.badRequest().body(response);
			
		}
		
		
	}
	
	
	@GetMapping
	public ResponseEntity<?> retrieve() {
		
		List<Voc> entities = service.retreive();
		List<VocDTO> dtos = entities.stream().map(VocDTO::new).collect(Collectors.toList());
		ResponseDTO<VocDTO> response = ResponseDTO.<VocDTO>builder().data(dtos).build();
		
		return ResponseEntity.ok().body(response);
	}
}
