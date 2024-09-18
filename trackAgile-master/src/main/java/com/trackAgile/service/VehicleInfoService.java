package com.trackAgile.service;

import java.util.List;

import com.trackAgile.dto.UserNameDto;
import com.trackAgile.dto.VehicleInfoDto;

public interface VehicleInfoService {

	public List<VehicleInfoDto> getVehicleInfoByEmpId(UserNameDto userNameDto, VehicleInfoDto vehicleInfoDto1);

	//VehicleInfoDto getVehicleInfo(Long loggedInEmployeeId);

//	VehicleInfoDto getVehicleInfoByEmpId(Long empVehicleId);

}
