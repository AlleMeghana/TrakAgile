package com.trackAgile.Entity;

import java.time.LocalDate;

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
public class EmployeeTracking {

	// emp tracking create -date ,locations[],lastlocation,vechle no,start
	// reading,end reading, total dist,
	// one record per day

	private Long id;
	private LocalDate date;
	private String location;
	private String lastLocation;
	private String startReading;
	private String endReading;
	private String totalDistance;

}
