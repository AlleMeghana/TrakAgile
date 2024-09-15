package com.trackAgile.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.trackAgile.dto.EmployeEmergencyContactDto;

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

public class EmployeEmergencyContact {
	// holds the information about the names and contact who need to be informed
	// incase of emergency

	public EmployeEmergencyContact(EmployeEmergencyContactDto emergncyContDto) {
		this.email=emergncyContDto.getEmail();
		this.name=emergncyContDto.getName();
		this.phone=emergncyContDto.getPhone();
		this.relation=emergncyContDto.getRelation();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String phone;
	private String email;
	private String relation;

	@JsonIgnore
	@ToString.Exclude
	@ManyToOne(targetEntity = Employee.class, cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinColumn(name = "emp_id", referencedColumnName = "id")
	private Employee employee;
	// give relation to employee table

}
