package com.trackAgile.dto;

import com.trackAgile.Entity.ONTInfo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ONTInfoDto {
	
	
	private Long id;
	private String ontName;
	private String ontMake;
	private String ontModel;
	private String serialNo;
	private String tagNo;
	private String capacity;
	
	public ONTInfoDto(ONTInfo ontInfo) {
		this.ontName=ontInfo.getOntName();
		this.ontMake=ontInfo.getOntMake();
		this.ontModel=ontInfo.getOntModel();
		this.serialNo=ontInfo.getSerialNo();
		this.tagNo=ontInfo.getTagNo();
		this.capacity=ontInfo.getCapacity();
	}

}
