package com.trackAgile.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Setter
@Getter
@ToString
@NoArgsConstructor
public class PatrollingResponseDto {

	
	private List<VehicleInfoDto> vehicleInfoList;
	    private AttendenceDto attendence;
	    private PatrollingTrackDto patrollingTrack;
	    private TaskDto task; // Only if FRT role requires tasks
	    // Getters and setters
	    
	    
	   

	    public PatrollingResponseDto(AttendenceDto attendence, PatrollingTrackDto patrollingTrack) {
	        this.attendence = attendence;
	        this.patrollingTrack = patrollingTrack;
	    }
	}

