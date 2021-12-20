package com.example.first.dto;

import com.example.first.model.Compensation;
import com.example.first.model.Voc;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CompensationDTO {
	
	private int compensationId;
	private int compensationValue;
	private Voc voc;
	
	public CompensationDTO(final Compensation entity) {
		this.compensationId = entity.getCompensationId();
		this.compensationValue = entity.getCompensationValue();
		this.voc = entity.getVoc();
	}
	
	public static Compensation toEntity(final CompensationDTO dto) {
		return Compensation.builder()
				.compensationId(dto.getCompensationId())
				.compensationValue(dto.getCompensationValue())
				.voc(dto.getVoc())
				.build();
	}
}
