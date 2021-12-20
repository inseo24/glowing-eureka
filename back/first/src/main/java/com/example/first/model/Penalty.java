package com.example.first.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "penalty")
public class Penalty {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int penaltyId;
	
	// 기사 이름
	@NotNull
	private String name;
	
	// 청구 비용
	private int penaltyCost;
	
	// 패널티 내용
	private String penaltyDescription;
	
	// 기사 확인 여부 : 안함 0, 함 1
	private int delChk;
	
	// 이의 제기 여부 : 안함 0, 함 1
	private int issued;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "voc_id")
	@JsonIgnore
	private Voc voc;
	
}
