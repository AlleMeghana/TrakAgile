package com.trackAgile.dto;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.locationtech.jts.geom.Point;

import com.fasterxml.jackson.annotation.JsonFormat;

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
public class AttendenceDto {

	private Long id;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	
	@CreationTimestamp
	private LocalDate date;
	
	private LocalTime logInTime;
	private LocalTime logoutTime;
	private String status;
	private List<Point> locationTrack;

	// last location time on 07/08/24
	private LocalTime lastKnownTime;

	// list for location track
	private Double locTrackX;
	private Double locaTrackY;

	//for getting multipoint 
	private String pointWkt;
	
	private Double lastLocX;
	private Double lastLocY;
	private String username;
	
	private LocalTime startTime;
	private LocalTime endTime;
	public void getLastLocX(double x) {
		
	}
	
	
	private int totalEmployeeCount;
	private int presentEmployeeCount;
	private int lateArrivalCount;
	private int absentEmployeeCount;
	 private int totalLeaveRequests;
	 private int totaltasksCount;
	
	public AttendenceDto(int totalEmployeeCount, int presentEmployeeCount, int lateArrivalCount, int absentEmployeeCount,int totalLeaveRequests,int totaltasksCount) {
        this.totalEmployeeCount = totalEmployeeCount;
        this.presentEmployeeCount = presentEmployeeCount;
        this.lateArrivalCount = lateArrivalCount;
        this.absentEmployeeCount = absentEmployeeCount;
        this.totalLeaveRequests= totalLeaveRequests;
        this.totaltasksCount=totaltasksCount;
    }

}
