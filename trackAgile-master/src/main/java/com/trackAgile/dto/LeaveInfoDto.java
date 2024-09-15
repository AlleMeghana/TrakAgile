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
public class LeaveInfoDto {
	
	private Long id;
	private Long allotedLeaves;
	private Long availedLeaves;
	private Long balance;
	private String leaveType;
	

}
