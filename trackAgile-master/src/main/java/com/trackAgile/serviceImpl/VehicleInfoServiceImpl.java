package com.trackAgile.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trackAgile.Entity.Employee;
import com.trackAgile.Entity.VehicleInfo;
import com.trackAgile.dto.UserNameDto;
import com.trackAgile.dto.VehicleInfoDto;
import com.trackAgile.repository.EmployeeRepository;
import com.trackAgile.repository.VehicleInfoRepository;
import com.trackAgile.service.VehicleInfoService;

@Service
public class VehicleInfoServiceImpl implements VehicleInfoService {

	@Autowired
	private VehicleInfoRepository vehicleInfoRepository;

	@Autowired
	private EmployeeRepository empRepo;

	@Override
	public List<VehicleInfoDto> getVehicleInfoByEmpId(UserNameDto userNameDto, VehicleInfoDto vehicleInfoDto1) {
		List<VehicleInfo> vehicleInfoList = vehicleInfoRepository.findByEmployee_Id(userNameDto.getId());
		if (vehicleInfoList == null || vehicleInfoList.isEmpty()) {
			Optional<Employee> employee = empRepo.findById(userNameDto.getId());
			Employee emp = employee.get();
			VehicleInfo newVehicleInfo = new VehicleInfo(vehicleInfoDto1);
			newVehicleInfo.setEmployee(emp);
			vehicleInfoRepository.save(newVehicleInfo);
//				newVehicleInfo.setemployee(empVehicleId);

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
