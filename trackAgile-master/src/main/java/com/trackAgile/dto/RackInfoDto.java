package com.trackAgile.dto;

import com.trackAgile.Entity.RackInfo;

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
public class RackInfoDto {

	private Long id;
	private String rackName;
	private String rackMake;
	private String rackModel;
	private String serailNO;
	private String tagNo;
	private String capacity;

	public RackInfoDto(RackInfo rackInfo) {
		// TODO Auto-generated constructor stub
	}

}
