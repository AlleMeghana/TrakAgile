package com.trackAgile.dto;

import com.trackAgile.Entity.OLTInfo;

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
public class OLTInfoDto {
	private Long id;
	private String oltName;
	private String oltMake;
	private String oltModel;
	private String serialNo;
	private String tagNo;
	private String capacity;

	public OLTInfoDto(OLTInfo oltInfo) {
		this.oltMake = oltInfo.getOltMake();
		this.oltName = oltInfo.getOltName();
		this.oltModel = oltInfo.getOltModel();
		this.serialNo = oltInfo.getSerialNo();
		this.tagNo = oltInfo.getTagNo();
		this.capacity = oltInfo.getCapacity();
	}
}
