package com.trackAgile.service;

import org.locationtech.jts.io.ParseException;
import org.springframework.http.ResponseEntity;

import com.trackAgile.dto.ApiResponse;
import com.trackAgile.dto.AttendenceDto;
import com.trackAgile.dto.PatrollingResponseDto;
import com.trackAgile.dto.PatrollingTrackDto;

public interface AttendenceService {

	public ApiResponse markAttendence(Long id, AttendenceDto attendenceDto);

	// using geometry --using
	public ApiResponse saveLocation(Long id, AttendenceDto attendenceDto) throws ParseException;

	public ApiResponse updateLocation(Long id, AttendenceDto attendenceDto);

	// for geometry ----using
	AttendenceDto getAttendence(Long id);

	// for getting multipoint locations --using
	public ApiResponse getLocationAsWKT(Long id);

	public ApiResponse getAllLocations();

	//void startPatrolling(PatrollingTrackDto patrollingTrackDto, Long loggedInEmployeeId) throws ParseException;

	//void markOutPatrolling(Long loggedInEmployeeId, PatrollingTrackDto patrollingTrackDto) throws ParseException;

	//void markOutAttendance(Long loggedInEmployeeId, PatrollingTrackDto patrollingTrackDto) throws ParseException;

	ResponseEntity<?> startPatrolling(PatrollingTrackDto patrollingTrackDto, Long loggedInEmployeeId)
			throws ParseException;

	ResponseEntity<PatrollingResponseDto> markOutAttendance(Long loggedInEmployeeId,
			PatrollingTrackDto patrollingTrackDto) throws ParseException;

	ApiResponse updatePatrollerLocation(Long employeeId, PatrollingTrackDto patrollingTrackDto);

	ApiResponse getPattrolerInfo(Long id);

	//void startPatrolling1(PatrollingTrackDto patrollingTrackDto, Long loggedInEmployeeId) throws ParseException;

}
