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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trackAgile.dto.ApiResponse;
import com.trackAgile.dto.AttendenceDto;
import com.trackAgile.dto.PatrollingTrackDto;
import com.trackAgile.dto.UserNameDto;
import com.trackAgile.dto.VehicleInfoDto;
import com.trackAgile.service.AttendenceService;

@RestController
@RequestMapping("/attendence")
public class AttendenceController {

	@Autowired
	private AttendenceService attendenceService;

	// Saving locations using points ---//not
	@PostMapping("/mark/{id}")
	public ApiResponse markAttendence(@RequestBody UserNameDto id, @RequestBody AttendenceDto attendenceDto) {
		return attendenceService.markAttendence(id, attendenceDto);

	}

	// saving location #Geometry//used
	@PostMapping("/loc")
	public ApiResponse saveLocation(@RequestBody UserNameDto userNameDto, @RequestBody AttendenceDto attendenceDto)
			throws ParseException {
		return attendenceService.saveLocation(userNameDto, attendenceDto);

	}

	// for updating locations #Geometry //used
	@PostMapping("/update")
	public ApiResponse updateLocation(@RequestBody UserNameDto userNameDto, @RequestBody AttendenceDto attendenceDto) {
		return attendenceService.updateLocation(userNameDto, attendenceDto);

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

	// To get all locations
	@GetMapping("/all-loc")
	public ApiResponse getAllLocations() {
		return attendenceService.getAllLocations();

	}

	@PostMapping("/start")
	public ResponseEntity<?> startPatrolling(@RequestPart("patrollingTrackDto") String patrollingTrackDto,
			@RequestPart("file") MultipartFile file, @RequestPart("userNameDto") String userNameDto,
			@RequestPart("vehicleInfoDto") String vehicleInfoDto) {
		try {
			// Call the service method to handle the patrolling start logic

			ObjectMapper objectMapper = new ObjectMapper();
			PatrollingTrackDto patrollerDto = objectMapper.readValue(patrollingTrackDto, PatrollingTrackDto.class);
			VehicleInfoDto vehicleInfoDto1 = objectMapper.readValue(vehicleInfoDto, VehicleInfoDto.class);
			UserNameDto userNameDto1 = objectMapper.readValue(userNameDto, UserNameDto.class);
			return attendenceService.startPatrolling(patrollerDto, file, userNameDto1, vehicleInfoDto1);
		} catch (Exception e) {
			// Return an internal server error if something unexpected happens
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An error occurred while starting patrolling: " + e.getMessage());
		}
	}

	@PostMapping("/mark-out")
	public ResponseEntity<?> markOutAttendance(@RequestPart("userNameDto") String userNameDto,
			@RequestPart("file") MultipartFile file, @RequestPart("patrollingTrackDto") String patrollingTrackDto)
			throws ParseException {
		try {
			// Call the service method to handle the patrolling start logic

			ObjectMapper objectMapper = new ObjectMapper();
			PatrollingTrackDto patrollerDto = objectMapper.readValue(patrollingTrackDto, PatrollingTrackDto.class);
			UserNameDto userNameDto1 = objectMapper.readValue(userNameDto, UserNameDto.class);
			return attendenceService.markOutAttendance(userNameDto1, file, patrollerDto);
		} catch (Exception e) {
			// Return an internal server error if something unexpected happens
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An error occurred while starting patrolling: " + e.getMessage());
		}

	}

	@PostMapping("/update-location/{employeeId}")
	public ResponseEntity<ApiResponse> updatePatrollerLocation(@PathVariable("employeeId") Long employeeId,
			@RequestBody PatrollingTrackDto patrollingTrackDto) {
		ApiResponse response = attendenceService.updatePatrollerLocation(employeeId, patrollingTrackDto);
		return new ResponseEntity<>(response, response.getStatus());
	}

	@GetMapping("/patroler/{id}")
	public ApiResponse patrolerInfo(@PathVariable("id") Long id) {
		return attendenceService.getPattrolerInfo(id);

	}
}
