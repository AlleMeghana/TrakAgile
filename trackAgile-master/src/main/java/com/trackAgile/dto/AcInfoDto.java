package com.trackAgile.dto;

import com.trackAgile.Entity.ACInfo;

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
public class AcInfoDto {

	private Long id;
	private String acMake;
	private String acModel;
	private String capacity;
	private String serailNo;
	private String tagno;
	private String indoorUnitSerialNo;
	private String outdoorUnitSerialNo;

	public AcInfoDto(ACInfo acInfo) {
		this.acMake = acInfo.getAcMake();
		this.acModel = acInfo.getAcModel();
		this.capacity = acInfo.getCapacity();
		this.serailNo = acInfo.getSerailNo();
		this.tagno = acInfo.getTagno();
		this.indoorUnitSerialNo = acInfo.getIndoorUnitSerialNo();
		this.outdoorUnitSerialNo = acInfo.getOutdoorUnitSerialNo();
	}

}
