package com.trackAgile.dto;

import com.trackAgile.Entity.CablesInfo;

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
public class CablesInfoDto {

	private Long id;
	private String cableName;
	private String cableType;
	private String length;
	private String tagNo;

	public CablesInfoDto(CablesInfo cablesInfo) {
		this.cableName = cablesInfo.getCableName();
		this.cableType = cablesInfo.getCableType();
		this.length = cablesInfo.getLength();
		this.tagNo = cablesInfo.getTagNo();
	}

}
