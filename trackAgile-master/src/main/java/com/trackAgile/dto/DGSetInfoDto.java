package com.trackAgile.dto;

import com.trackAgile.Entity.DGSetInfo;

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
public class DGSetInfoDto {

	private Long id;
	private String dgSetMake;
	private String dgSetModel;
	private String dgSetName;
	private String dgSetCapacity;
	private String dgSetSerialNo;
	private String tagNo;
	private String dgSetStatus;

	public DGSetInfoDto(DGSetInfo dgSetInfo) {
		this.dgSetMake = dgSetInfo.getDgSetMake();
		this.dgSetCapacity = dgSetInfo.getDgSetCapacity();
		this.dgSetModel = dgSetInfo.getDgSetModel();
		this.dgSetName = dgSetInfo.getDgSetName();
		this.dgSetSerialNo = dgSetInfo.getDgSetSerialNo();
		this.tagNo = dgSetInfo.getTagNo();
		this.dgSetStatus = dgSetInfo.getDgSetStatus();
	}

}
