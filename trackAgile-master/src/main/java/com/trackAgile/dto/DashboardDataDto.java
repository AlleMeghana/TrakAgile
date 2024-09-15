package com.trackAgile.dto;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DashboardDataDto {

	    private Double totalDistanceCurrentMonth;
	    private Double distanceLast7Days;
	    private Double distanceLastDay;
//	    private Long totalIncidents;
	    private Long totalAvailedLeaves;
	    private Long totalLeavesCurrentMonth;
	    private Long totalLeaveRequestsCurrentMonth;
	  
	    

	}


