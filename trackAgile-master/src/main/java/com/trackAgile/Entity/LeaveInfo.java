package com.trackAgile.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class LeaveInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long allotedLeaves; //allotedLeaves
	private Long availedLeaves;
	private Long balance;
	private String leaveType;
	
	@JsonIgnore
	@ToString.Exclude
	@ManyToOne(targetEntity = Employee.class, cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
	@JoinColumn(name ="emp_id", referencedColumnName = "id")
	private Employee employee;

}
