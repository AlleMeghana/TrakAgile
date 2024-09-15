package com.trackAgile.Entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.trackAgile.dto.EmployeeDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
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
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String empName;
	private Long empId;
	private String email; // added for users email
	private String role; // added for users role
	private String empFatherName;
	private String dob;
	private String doj; // date of birth
	private String designation; // date of joining
	private String managerName;
	private String phone;
	private String aadhar;
	private String pan;
	private String userPackage; // #package package
	private String workLocation;
	private Boolean frtTeam; // If FRT team and designation is Driver, track locations in FRT Tripinfo table

	// newly added -25/07/24
	private String profilePicUrl;
	private String bloodGroup;
	private String licenceNo;
	private String address;
	private String countryState;
	private String Mandal;
	private String city;
	private String userObild; // URL to the user's image
	private Boolean isAttendenceMarked;

	// vehicle details create and relate to emple

//	private userObjId userObjId # need to give realtion with Uder table

	@OneToOne(targetEntity = User.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	@JsonIgnore
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	 @JsonBackReference
	private User user;
	
	
	@JsonIgnore
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(targetEntity = EmployeEmergencyContact.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "emp_id", referencedColumnName = "id")
	private List<EmployeEmergencyContact> empEmergencyContact = new ArrayList<>();
	
	@JsonIgnore
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(targetEntity = VehicleInfo.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "emp_vehicle_id", referencedColumnName = "id")
	private List<VehicleInfo> vehicleInfo = new ArrayList<>();
	

	public Employee(EmployeeDto employeeDto) {
		this.id=employeeDto.getId();
		this.empName = employeeDto.getEmpName();
		this.empId = employeeDto.getEmpId();
		this.empFatherName = employeeDto.getEmpFatherName();
		this.dob = employeeDto.getDob();
		this.doj = employeeDto.getDoj();
		this.designation = employeeDto.getDesignation();
		this.managerName = employeeDto.getManagerName();
		this.phone = employeeDto.getPhone();
		this.aadhar = employeeDto.getAadhar();
		this.pan = employeeDto.getPan();
		this.userPackage = employeeDto.getUserPackage();
		this.workLocation = employeeDto.getWorkLocation();
		this.frtTeam = employeeDto.getFrtTeam();
		this.isAttendenceMarked=employeeDto.getIsAttendenceMarked();

	}

	@JsonIgnore
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(targetEntity = LeaveInfo.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "emp_id", referencedColumnName = "id")
	private List<LeaveInfo> leaveInfo = new ArrayList<>();
	
	@JsonIgnore
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(targetEntity = LeaveRequest.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "emp_id", referencedColumnName = "id")
	private List<LeaveRequest> leaveRequest = new ArrayList<>();
	
	
	
	

} 
