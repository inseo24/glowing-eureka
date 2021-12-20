package com.example.first.dto;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.example.first.model.Compensation;
import com.example.first.model.Penalty;
import com.example.first.model.Voc;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PenaltyDTO {
	
	private int penaltyId;
	private String penaltyDescription;
	private String name;
	private int penaltyCost;
	
	@Min(value=0, message="0과 1사이에서 입력해주세요.")
	@Max(value=1, message="0과 1사이에서 입력해주세요.")
	private int delChk;
	
	@Min(value=0, message="0과 1사이에서 입력해주세요.")
	@Max(value=1, message="0과 1사이에서 입력해주세요.")
	private int issued;
	
	private Voc voc;
	
	public PenaltyDTO(final Penalty entity) {
		this.penaltyId = entity.getPenaltyId();
		this.penaltyDescription = entity.getPenaltyDescription();
		this.delChk = entity.getDelChk();
		this.issued = entity.getIssued();
		this.name = entity.getName();
		this.penaltyCost = entity.getPenaltyCost();
		this.voc = entity.getVoc();
	}
	
	public static Penalty toEntity(final PenaltyDTO dto) {
		return Penalty.builder()
				.penaltyId(dto.getPenaltyId())
				.penaltyDescription(dto.getPenaltyDescription())
				.delChk(dto.getDelChk())
				.name(dto.getName())
				.penaltyCost(dto.getPenaltyCost())
				.issued(dto.getIssued())
				.voc(dto.getVoc())
				.build();
	}
	
	
	
}
