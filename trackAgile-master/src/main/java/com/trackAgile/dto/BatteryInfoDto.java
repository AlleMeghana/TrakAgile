package com.trackAgile.dto;

import com.trackAgile.Entity.BatteryInfo;

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
public class BatteryInfoDto {

	private Long id;
	private String batteryMake;
	private String batteryModel;
	private String batterVoltage;
	private String serailNo;
	private String tagNo;
	private String photo;
	private String gpInfo;
	private byte[] photo1;

	// private String relationToSite;

	public BatteryInfoDto(BatteryInfo batteryInfo) {
		this.batteryMake = batteryInfo.getBatteryMake();
		this.batterVoltage = batteryInfo.getBatterVoltage();
		this.batteryModel = batteryInfo.getBatteryModel();
		this.serailNo = batteryInfo.getSerailNo();
		this.tagNo = batteryInfo.getTagNo();
//		this.photo=batteryInfo.getPhoto();
		this.gpInfo = batteryInfo.getGpInfo();
	}

	
}
