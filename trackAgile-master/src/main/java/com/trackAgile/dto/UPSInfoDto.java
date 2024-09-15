package com.trackAgile.dto;

import com.trackAgile.Entity.RackInfo;
import com.trackAgile.Entity.RouterInfo;
import com.trackAgile.Entity.SolarPanalInfo;
import com.trackAgile.Entity.SwitchInfo;
import com.trackAgile.Entity.UPSInfo;

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
public class UPSInfoDto {

	private Long id;
	private String upsName;
	private String upsMake;
	private String upsModel;
	private String serailNo;
	private String tagNo;
	private String capacity;

	public UPSInfoDto(RackInfo rackInfo) {
		this.upsMake = rackInfo.getRackMake();
		this.upsModel = rackInfo.getRackModel();
		this.upsName = rackInfo.getRackName();
		this.serailNo = rackInfo.getSerailNO();
		this.tagNo = rackInfo.getTagNo();
		this.capacity = rackInfo.getCapacity();
	}

	public UPSInfoDto(RouterInfo routerInfo) {
		this.upsMake = routerInfo.getUpsName();
		this.upsModel = routerInfo.getModel();
		this.upsName = routerInfo.getUpsName();
		this.serailNo = routerInfo.getSerialNo();
		this.tagNo = routerInfo.getTagNo();
		this.capacity = routerInfo.getCapacity();
	}

	public UPSInfoDto(UPSInfo upsInfo) {
		this.upsMake = upsInfo.getUpsMake();
		this.upsModel = upsInfo.getUpsModel();
		this.upsName = upsInfo.getUpsName();
		this.serailNo = upsInfo.getSerailNo();
		this.tagNo = upsInfo.getTagNo();
		this.capacity = upsInfo.getCapacity();
	}

	public UPSInfoDto(SwitchInfo switchInfo) {
		this.upsMake = switchInfo.getMake();
		this.upsModel = switchInfo.getModel();
		this.upsName = switchInfo.getUpsName();
		this.serailNo = switchInfo.getSerialNo();
		this.tagNo = switchInfo.getTagNo();
		this.capacity = switchInfo.getCapacity();

	}

	public UPSInfoDto(SolarPanalInfo solarPanalInfo) {
		this.upsMake = solarPanalInfo.getMake();
		this.upsModel = solarPanalInfo.getModel();
		this.upsName = solarPanalInfo.getSolarPanelName();
		this.serailNo = solarPanalInfo.getSerialNo();
		this.tagNo = solarPanalInfo.getTagNo();
		this.capacity = solarPanalInfo.getCapacity();
	}

}
