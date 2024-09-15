package com.trackAgile.dto;

import com.trackAgile.Entity.EmployeEmergencyContact;

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

public class EmployeEmergencyContactDto {

	private Long id;
	private String name;
	private String phone;
	private String email;
	private String relation;

	public EmployeEmergencyContactDto(EmployeEmergencyContact empEmrgncy) {
		this.email = empEmrgncy.getEmail();
		this.phone = empEmrgncy.getName();
		this.phone = empEmrgncy.getPhone();
		this.relation = empEmrgncy.getRelation();
	}

}
