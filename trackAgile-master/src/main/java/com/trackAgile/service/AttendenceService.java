package com.trackAgile.service;

import org.locationtech.jts.io.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.trackAgile.dto.ApiResponse;
import com.trackAgile.dto.AttendenceDto;
import com.trackAgile.dto.PatrollingResponseDto;
import com.trackAgile.dto.PatrollingTrackDto;
import com.trackAgile.dto.UserNameDto;
import com.trackAgile.dto.VehicleInfoDto;

public interface AttendenceService {

	public ApiResponse markAttendence(UserNameDto id, AttendenceDto attendenceDto);
	// using geometry --using
	public ApiResponse saveLocation(UserNameDto userNameDto, AttendenceDto attendenceDto) throws ParseException;
	
	public ApiResponse updateLocation(UserNameDto userNameDto, AttendenceDto attendenceDto);

	// for geometry ----using
	AttendenceDto getAttendence(Long id);

	// for getting multipoint locations --using
	public ApiResponse getLocationAsWKT(Long id);

	public ApiResponse getAllLocations();

	//void startPatrolling(PatrollingTrackDto patrollingTrackDto, Long loggedInEmployeeId) throws ParseException;

	//void markOutPatrolling(Long loggedInEmployeeId, PatrollingTrackDto patrollingTrackDto) throws ParseException;

	//void markOutAttendance(Long loggedInEmployeeId, PatrollingTrackDto patrollingTrackDto) throws ParseException;

	public ResponseEntity<?> startPatrolling(PatrollingTrackDto patrollingTrackDto, MultipartFile file,
			UserNameDto userNameDto,VehicleInfoDto vehicleInfoDto) throws ParseException;

	public ResponseEntity<PatrollingResponseDto> markOutAttendance(UserNameDto userNameDto,MultipartFile file,
			PatrollingTrackDto patrollingTrackDto) throws ParseException;

	ApiResponse updatePatrollerLocation(Long employeeId, PatrollingTrackDto patrollingTrackDto);

	ApiResponse getPattrolerInfo(Long id);

	//void startPatrolling1(PatrollingTrackDto patrollingTrackDto, Long loggedInEmployeeId) throws ParseException;

}
