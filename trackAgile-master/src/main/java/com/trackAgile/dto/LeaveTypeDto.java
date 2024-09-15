package com.trackAgile.dto;

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
public class LeaveTypeDto {
	
	private Long id; 
	private String cl; //casual leave
	private String el; //
	private String pl; //personal leave
	private String maternityLeave;
	private String sickLeave;
	private String paternityLeave;
	private String lop; //loss of pay
	private String other;

}
