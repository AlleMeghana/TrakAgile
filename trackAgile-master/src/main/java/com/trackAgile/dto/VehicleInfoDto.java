package com.trackAgile.dto;

import com.trackAgile.Entity.VehicleInfo;

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
public class VehicleInfoDto {

	private Long id;
	private String vehicleName;
	private String vehicleMake;
	private String vehicleType;

	// added on 25/07
	private String regNo;
	private Integer empId;

	public VehicleInfoDto(VehicleInfo vehicleInfo) {
		this.empId = vehicleInfo.getEmpId();
		this.vehicleName = vehicleInfo.getVehicleName();
		this.vehicleMake = vehicleInfo.getVehicleMake();
		this.vehicleType = vehicleInfo.getVehicleType();

	}

}
