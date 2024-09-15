package com.trackAgile.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trackAgile.Entity.VehicleInfo;
import com.trackAgile.dto.VehicleInfoDto;
import com.trackAgile.repository.VehicleInfoRepository;
import com.trackAgile.service.VehicleInfoService;
@Service
public class VehicleInfoServiceImpl implements VehicleInfoService{

	 @Autowired
	    private VehicleInfoRepository vehicleInfoRepository;

	   
	    @Override
	    public List<VehicleInfoDto> getVehicleInfoByEmpId(Long empVehicleId) {
	        List<VehicleInfo> vehicleInfoList = vehicleInfoRepository.findByEmployee_Id(empVehicleId);
	        if (vehicleInfoList.isEmpty()) {
	            throw new RuntimeException("No vehicles found for the given employee");
	        }

	      
	        List<VehicleInfoDto> vehicleInfoDtoList = new ArrayList<>();
	        for (VehicleInfo vehicleInfo : vehicleInfoList) {
	            VehicleInfoDto vehicleInfoDto = new VehicleInfoDto();
	            vehicleInfoDto.setVehicleMake(vehicleInfo.getVehicleMake());
	            vehicleInfoDto.setVehicleName(vehicleInfo.getVehicleName());
	            vehicleInfoDto.setVehicleType(vehicleInfo.getVehicleType());
	            vehicleInfoDto.setRegNo(vehicleInfo.getRegNo());
	            vehicleInfoDto.setEmpId(vehicleInfo.getEmpId());
	            vehicleInfoDtoList.add(vehicleInfoDto);
	        }

	        return vehicleInfoDtoList;
	    }

}
