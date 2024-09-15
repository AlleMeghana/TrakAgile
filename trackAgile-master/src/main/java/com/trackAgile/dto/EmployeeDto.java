package com.trackAgile.dto;

import java.util.List;

import com.trackAgile.Entity.Employee;

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

public class EmployeeDto {

	private Long id;
	private String empName;
	private Long empId;
	private String email;
	private String role;
	private String empFatherName;
	private String dob;
	private String doj; // date of birth
	private String designation; // date of joining
	private String managerName;
	private String phone;
	private String aadhar;
	private String pan;
	private String userPackage; // #package package

	// current ofc locaition
	private String workLocation;
	private Boolean frtTeam;
	
	private String profilePicUrl;
	private Boolean isAttendenceMarked;

	private List<VehicleInfoDto> vehicleInfoDto;

//	private Point location;

	private List<EmployeEmergencyContactDto> empEmergencyDto;

	private List<LeaveInfoDto> leaveInfoDto;

	public EmployeeDto(Employee employee) {
		this.id=employee.getId();
		this.empName = employee.getEmpName();
		this.empId = employee.getEmpId();
		this.email = employee.getEmail();
		this.role = employee.getRole();
		this.empFatherName = employee.getEmpFatherName();
		this.dob = employee.getDob();
		this.doj = employee.getDoj();
		this.designation = employee.getDesignation();
		this.managerName = employee.getManagerName();
		this.phone = employee.getPhone();
		this.aadhar = employee.getAadhar();
		this.pan = employee.getPan();
		this.userPackage = employee.getUserPackage();
		this.workLocation = employee.getWorkLocation();
		this.frtTeam = employee.getFrtTeam();
		this.isAttendenceMarked=employee.getIsAttendenceMarked();
	}

	

}
