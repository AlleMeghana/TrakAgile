package com.trackAgile.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.locationtech.jts.geom.Point;

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
public class PatrollingTrackDto {
	
	
	private Long id;
	private LocalDate date;
	private String empName;
	private Double odometerStartReading;
	private String startReadingPhotoUrl;
	private Double OdometerEndReading;
	private String endReadingPhotoUrl;
	private LocalTime startTime;
	private LocalTime endTime;
	private LocalDate endDate;
	private Double travalledDistance;
	
	private List<Point> locationTrack;



	// list for location track
	private Double locTrackX;
	private Double locaTrackY;

	//for getting multipoint 
	private String pointWkt;
	
	private Double lastLocX;
	private Double lastLocY;
	private String username;
	public void getLastLocX(double x) {
		
	}

}
