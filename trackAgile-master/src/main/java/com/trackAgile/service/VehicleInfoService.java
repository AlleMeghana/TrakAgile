package com.trackAgile.service;

import java.util.List;

import com.trackAgile.dto.VehicleInfoDto;

public interface VehicleInfoService {

	List<VehicleInfoDto> getVehicleInfoByEmpId(Long empVehicleId);

	//VehicleInfoDto getVehicleInfo(Long loggedInEmployeeId);

//	VehicleInfoDto getVehicleInfoByEmpId(Long empVehicleId);

}
