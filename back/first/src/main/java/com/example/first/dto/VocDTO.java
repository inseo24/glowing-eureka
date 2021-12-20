package com.example.first.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

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
public class VocDTO {
	
	private int vocId;
	
	@NotBlank(message = "귀책 내용이 공백입니다.")
	private String faultDescription;
	
	@Min(value = 0, message="0과 1 사이에서 입력해주세요.")
	@Max(value = 1, message="0과 1 사이에서 입력해주세요.")
	private int faultIdentifier;
	
	private List<Compensation> compensation = new ArrayList<>();
	private List<Penalty> penalty = new ArrayList<>();
	
	public VocDTO(final Voc entity) {
		this.vocId = entity.getVocId();
		this.faultDescription = entity.getFaultDescription();
		this.faultIdentifier = entity.getFaultIdentifier();
		this.compensation = entity.getCompensations();
		this.penalty = entity.getPenalties();
	}
	
	public static Voc toEntity(final VocDTO dto) {
		return Voc.builder()
				.vocId(dto.getVocId())
				.faultDescription(dto.getFaultDescription())
				.compensations(dto.getCompensation())
				.faultIdentifier(dto.getFaultIdentifier())
				.penalties(dto.getPenalty())
				.build();
	}

}
