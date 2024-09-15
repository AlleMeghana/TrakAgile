package com.trackAgile.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.trackAgile.dto.VehicleInfoDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class VehicleInfo {
	// recors the FRT,Patroller,FE's and Ze's vehicles info

	public VehicleInfo(VehicleInfoDto vehicleInfoDto) {
		this.vehicleMake = vehicleInfoDto.getVehicleMake();
		this.vehicleName = vehicleInfoDto.getVehicleMake();
		this.vehicleType = vehicleInfoDto.getVehicleType();
		this.regNo = vehicleInfoDto.getRegNo();
		this.empId = vehicleInfoDto.getEmpId();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String vehicleName;
	private String vehicleMake;
	private String vehicleType;

	// added on 25/07
	private String regNo;
	private Integer empId;

	@JsonIgnore
	@ToString.Exclude
	@ManyToOne(targetEntity = Employee.class, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "emp_vehicle_id", referencedColumnName = "id")
	private Employee employee;

	// give realtion to frtInfo //Vehicle info that belongs to FRT team . This
	// should be null incase of employee vehicle info

	// relation to employee table

}
