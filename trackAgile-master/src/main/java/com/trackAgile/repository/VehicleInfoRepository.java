package com.trackAgile.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trackAgile.Entity.VehicleInfo;

public interface VehicleInfoRepository extends JpaRepository<VehicleInfo, Long>{
	
	 List<VehicleInfo> findByEmployee_Id(Long employeeId);

}
