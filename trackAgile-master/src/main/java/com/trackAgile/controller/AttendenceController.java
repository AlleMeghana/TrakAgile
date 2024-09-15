package com.trackAgile.controller;

import org.locationtech.jts.io.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trackAgile.dto.ApiResponse;
import com.trackAgile.dto.AttendenceDto;
import com.trackAgile.dto.PatrollingResponseDto;
import com.trackAgile.dto.PatrollingTrackDto;
import com.trackAgile.service.AttendenceService;
import com.trackAgile.service.PatrollerService;

@RestController
@RequestMapping("/attendence")
public class AttendenceController {

	@Autowired
	private AttendenceService attendenceService;
	  


	//Saving locations using points ---//not 
	@PostMapping("/mark/{id}")
	public ApiResponse markAttendence(@PathVariable("id") Long id, @RequestBody AttendenceDto attendenceDto) {
		return attendenceService.markAttendence(id, attendenceDto);

	}

	// saving location #Geometry//used
	@PostMapping("/loc/{id}")
	public ApiResponse saveLocation(@PathVariable("id") Long id, @RequestBody AttendenceDto attendenceDto)
			throws ParseException {
		return attendenceService.saveLocation(id, attendenceDto);

	}

	// for updating locations #Geometry //used
	@PostMapping("/update/{id}")
	public ApiResponse updateLocation(@PathVariable("id") Long id, @RequestBody AttendenceDto attendenceDto) {
		return attendenceService.updateLocation(id, attendenceDto);

	}

	// for getting locations by id using geometry //not using 
	@GetMapping("get/{id}")
	public ResponseEntity<ApiResponse> getAttendence(@PathVariable Long id) {
		AttendenceDto attendenceDto = attendenceService.getAttendence(id);
		return new ResponseEntity<>(new ApiResponse(attendenceDto, HttpStatus.OK), HttpStatus.OK);
	}

	
	// for getting multipoint locations bu id//used
	@GetMapping("/multipoint/{id}")
	public ApiResponse getLocationAsWKT(@PathVariable("id") Long id) {
		return attendenceService.getLocationAsWKT(id);

	}
	
	//To get all locations  
	@GetMapping("/all-loc")
	public ApiResponse getAllLocations() {
		return attendenceService.getAllLocations();
		
	}
	
	
	 @PostMapping("/start/{loggedInEmployeeId}")
	    public ResponseEntity<?> startPatrolling(@RequestBody PatrollingTrackDto patrollingTrackDto,
	                                                  @PathVariable Long loggedInEmployeeId) {
		 try {
	            
	            return attendenceService.startPatrolling(patrollingTrackDto, loggedInEmployeeId);
	        } catch (Exception e) {
	           
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                    .body("An error occurred while starting patrolling: " + e.getMessage());
	        }
	    }
	 
	 
	 @PostMapping("/mark-out/{loggedInEmployeeId}")
	    public ResponseEntity<PatrollingResponseDto> markOutAttendance(
	            @PathVariable("loggedInEmployeeId") Long loggedInEmployeeId,
	            @RequestBody PatrollingTrackDto patrollingTrackDto) throws ParseException {
	        
	        
	        return attendenceService.markOutAttendance(loggedInEmployeeId, patrollingTrackDto);
	    }
	 
	 
	 @PostMapping("/update-location/{employeeId}")
	    public ResponseEntity<ApiResponse> updatePatrollerLocation(
	            @PathVariable("employeeId") Long employeeId,
	            @RequestBody PatrollingTrackDto patrollingTrackDto) {
	        ApiResponse response = attendenceService.updatePatrollerLocation(employeeId, patrollingTrackDto);
	        return new ResponseEntity<>(response, response.getStatus());
	    }
	 
	 
	 @GetMapping("/patroler/{id}")
		public ApiResponse patrolerInfo(@PathVariable("id") Long id) {
			return attendenceService.getPattrolerInfo(id);

		}
}
