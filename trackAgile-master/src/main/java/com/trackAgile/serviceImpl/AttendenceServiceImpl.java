package com.trackAgile.serviceImpl;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.MultiPoint;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;
import org.locationtech.jts.io.WKTWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.trackAgile.Entity.Attendence;
import com.trackAgile.Entity.Employee;
import com.trackAgile.Entity.PatrollingTrack;
import com.trackAgile.Entity.Task;
import com.trackAgile.Entity.User;
import com.trackAgile.dto.ApiResponse;
import com.trackAgile.dto.AttendenceDto;
import com.trackAgile.dto.PatrollingResponseDto;
import com.trackAgile.dto.PatrollingTrackDto;
import com.trackAgile.dto.TaskDto;
import com.trackAgile.dto.UserNameDto;
import com.trackAgile.dto.VehicleInfoDto;
import com.trackAgile.repository.AttendenceRepository;
import com.trackAgile.repository.EmployeeRepository;
import com.trackAgile.repository.PattrolerRepository;
import com.trackAgile.repository.TaskRepository;
import com.trackAgile.repository.UserRepository;
import com.trackAgile.service.AttendenceService;
import com.trackAgile.service.VehicleInfoService;

import jakarta.transaction.Transactional;

@Service
public class AttendenceServiceImpl implements AttendenceService {

	private static final Logger logger = LoggerFactory.getLogger(AttendenceService.class);

	@Autowired
	private AttendenceRepository attenedenceRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private PattrolerRepository patrollingTrackRepository;

	@Autowired
	private VehicleInfoService vehicleInfoService;
	
	private final String FOLDER_PATH = "C:\\Users\\91756\\OneDrive\\Pictures\\meghana";


	@Override
	public ApiResponse markAttendence(UserNameDto userNameDto, AttendenceDto attendenceDto) {
//		Optional<User> userOpt = userRepo.findById(id);
//		Attendence attendance = new Attendence();
//		GeometryFactory geometryFactory = new GeometryFactory();
//		if (userOpt.isPresent()) {
//			attendance.setDate(attendenceDto.getDate());
//			attendance.setStatus(attendenceDto.getStatus());
//			attendance.setLogInTime(attendenceDto.getLogInTime());
//			attendance.setLogoutTime(attendenceDto.getLogoutTime());
//
//			// Initialize the list if it's null
//			if (attendance.getLocationTrack() == null) {
//				attendance.setLocationTrack(new ArrayList<>());
//			}
//
//			// Add all Points from the DTO to the attendance location track
//			List<Point> points = new ArrayList<>();
//			Point point=null;
//			for (int i = 0; i < attendenceDto.getLastLocX().size(); i++) {
//				Double lastLocX = attendenceDto.getLastLocX().get(i);
//				Double lastLocY = attendenceDto.getLastLocY().get(i);
//				 point = geometryFactory.createPoint(new Coordinate(lastLocX, lastLocY));
//				points.add(point);
//			}
//			String pointsWKT = points.stream()
//	                .map(Point::toText)
//	                .collect(Collectors.joining(", ", "MULTIPOINT(", ")"));
//			
//			for (Point point1 : points) {
//		        String pointTrack = "POINT(" + point.getX() + " " + point.getY() + ")";
////		        attenedenceRepo.saveAttendance(attendance.getDate(), attendance.getStatus(), pointTrack);
//		    }
////			 attendance.getLocationTrack().addAll(points);
//			
//
////	        attendance.setLastNowLoaction(points.get(points.size() - 1)); // Set the last point as the current location
//	        attendance.getLocationTrack().addAll(points); // Add all points to the location track
//	        logger.info("Atten+dance saved successfully for user with ID: {}", attendance);
//	        attenedenceRepo.saveAttendance(attendance.getDate(), attendance.getStatus(), attendance.getLocationTrack());
//	    }
//
//		
//		return new ApiResponse(attendance, HttpStatus.OK);
//	}

		Optional<User> userOpt = userRepo.findById(userNameDto.getId());
		Attendence attendence = new Attendence();

		if (userOpt.isPresent()) {
			attendence.setDate(attendenceDto.getDate());

			GeometryFactory geometryFactory = new GeometryFactory();
			Point point = geometryFactory
					.createPoint(new Coordinate(attendenceDto.getLastLocX(), attendenceDto.getLastLocY()));
			attendence.setStatus(attendenceDto.getStatus());
//			attendence.setLogInTime(attendenceDto.getLogInTime());
			// attendence.setLogoutTime(attendenceDto.getLogoutTime());

			Point pointTrack = geometryFactory
					.createPoint(new Coordinate(attendenceDto.getLastLocX(), attendenceDto.getLastLocY()));

//			if (attendence.getLocationTrack() == null) {
//				attendence.setLocationTrack(new ArrayList<>());
//			}

//			attendence.getLocationTrack().add(pointTrack);

			// Ensure locationTrack is initialized
//			if (attendence.getLocationTrack() == null) {
//				attendence.setLocationTrack(new ArrayList<>());
//			}
			// Add the new point to the list
//			attendence.getLocationTrack().add(pointTrack);

			//// adding code for list of coordinates #points

			attenedenceRepo.saveAttendence(attendence.getDate(), attendence.getStatus(), point.toText(),
					pointTrack.toText());
		}
		return new ApiResponse(attendence, HttpStatus.OK);
	}

	// saving the location //using ---final
	@Override
	public ApiResponse saveLocation(UserNameDto userNameDto, AttendenceDto attendenceDto) throws ParseException {
		Optional<User> userOpt = userRepo.findById(userNameDto.getId());
		Attendence attendence = new Attendence();
		if (userOpt.isPresent()) {
			WKTReader wktReader = new WKTReader();
			Geometry geometry = wktReader
					.read("POINT(+" + attendenceDto.getLastLocX() + " " + attendenceDto.getLastLocY() + " )");

			attendence.setPoint(geometry);
			attendence.setLastKnownLocation(geometry);
			attendence.setDate(attendenceDto.getDate());
			attendence.setStatus(attendenceDto.getStatus());
			attendence.setUser(userOpt.get());

			attenedenceRepo.save(attendence);
		}
		return new ApiResponse(attendence, HttpStatus.OK);

	}

	// for updating the Locations in points
	@Override
	public ApiResponse updateLocation(UserNameDto userNameDto, AttendenceDto attendenceDto) {

		Optional<Attendence> userOpt = attenedenceRepo.findById(userNameDto.getId());
		Attendence attendence = null;

		if (userOpt.isPresent()) {
			// Check if the attendance record exists for this user
			Optional<Attendence> attendenceOpt = attenedenceRepo.findById(userNameDto.getId());
			if (attendenceOpt.isPresent()) {
				// If it exists, update it
				attendence = attendenceOpt.get();
			} else {
				// If it doesn't exist, create a new one
				attendence = new Attendence();
//		        attendence.setUser(userOpt.get()); // Set the user in the attendance record
			}

			// Create a GeometryFactory to create new points
			GeometryFactory geometryFactory = new GeometryFactory();
			Point newPoint = geometryFactory
					.createPoint(new Coordinate(attendenceDto.getLastLocX(), attendenceDto.getLastLocY()));

			Geometry existingGeometry = attendence.getPoint();

			if (existingGeometry == null) {
				// If no geometry exists, set the new point
				attendence.setPoint(newPoint);
			} else if (existingGeometry instanceof Point) {
				// If the existing geometry is a point, create a MultiPoint with the new point
				Point[] points = new Point[] { (Point) existingGeometry, newPoint };
				MultiPoint multiPoint = geometryFactory.createMultiPoint(points);
				attendence.setPoint(multiPoint);
			} else if (existingGeometry instanceof MultiPoint) {
				// If the existing geometry is a MultiPoint, add the new point to it
				MultiPoint multiPoint = (MultiPoint) existingGeometry;
				Coordinate[] newCoordinates = new Coordinate[multiPoint.getNumGeometries() + 1];
				for (int i = 0; i < multiPoint.getNumGeometries(); i++) {
					newCoordinates[i] = multiPoint.getGeometryN(i).getCoordinate();
				}
				newCoordinates[newCoordinates.length - 1] = newPoint.getCoordinate();
				attendence.setPoint(geometryFactory.createMultiPointFromCoords(newCoordinates));
			}

			// Update the last known location, date, and status
			attendence.setLastKnownLocation(newPoint);
			attendence.setDate(attendenceDto.getDate());
			attendence.setStatus(attendenceDto.getStatus());
			logger.info("Attendence saved successfully for user with ID: {}", attendence);

			// Save the updated attendance record
			attenedenceRepo.save(attendence);

		}
		return new ApiResponse(attendence, HttpStatus.OK);

	}

	@Override

	public AttendenceDto getAttendence(Long id) {
		Attendence attendence = attenedenceRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Attendence not found"));

		// Create and return AttendenceDto
		AttendenceDto attendenceDto = new AttendenceDto();
		attendenceDto.setId(attendence.getId());
		attendenceDto.setDate(attendence.getDate());
		attendenceDto.setStatus(attendence.getStatus());
		if (attendence.getUser() != null) {
			attendenceDto.setUsername(attendence.getUser().getUserName()); // Assuming getUserName() returns the
																			// username
		}

		if (attendence.getLastKnownLocation() != null) {
			attendenceDto.setLastLocX(attendence.getLastKnownLocation().getCoordinate().x);
			attendenceDto.setLastLocY(attendence.getLastKnownLocation().getCoordinate().y);
		}
		if (attendence.getPoint() != null) {
			attendenceDto.setLocTrackX(attendence.getLastKnownLocation().getCoordinate().x);
			attendenceDto.setLocaTrackY(attendence.getLastKnownLocation().getCoordinate().y);
		}

		return attendenceDto;
	}

	// for getting list of points
	@Override
	public ApiResponse getLocationAsWKT(Long id) {
		Optional<Attendence> attendenceOpt = attenedenceRepo.findById(id);

		Attendence attendence = attendenceOpt.get();
		AttendenceDto attendenceDto = new AttendenceDto();
		attendenceDto.setId(attendence.getId());
		attendenceDto.setDate(attendence.getDate());
		attendenceDto.setLogInTime(attendence.getLogInTime());
		attendenceDto.setLogoutTime(attendence.getLogoutTime());
		attendenceDto.setStatus(attendence.getStatus());
		if (attendence.getUser() != null) {
			attendenceDto.setUsername(attendence.getUser().getUserName()); // Assuming getUserName() returns the
																			// username
		}
		if (attendence.getLastKnownLocation() != null) {
			attendenceDto.setLastLocX(attendence.getLastKnownLocation().getCoordinate().x);
			attendenceDto.setLastLocY(attendence.getLastKnownLocation().getCoordinate().y);
		}

		if (attendence.getUser() != null) {
			attendenceDto.setUsername(attendence.getUser().getUserName());
		}

		if (attendence != null) {
			WKTWriter writer = new WKTWriter();
			String pointWkt = writer.write(attendence.getPoint());
			attendenceDto.setPointWkt(pointWkt);
		}
		return new ApiResponse(attendenceDto, HttpStatus.OK);
	}

	@Override
	public ApiResponse getAllLocations() {
		List<Attendence> attendecneList = attenedenceRepo.findAll();
		List<AttendenceDto> attendenceDtoList = new ArrayList<AttendenceDto>();
		for (Attendence attendence : attendecneList) {
			AttendenceDto attendenceDto = new AttendenceDto();
			attendenceDto.setId(attendence.getId());
			attendenceDto.setDate(attendence.getDate());
			attendenceDto.setStatus(attendence.getStatus());
			if (attendence.getLastKnownLocation() != null) {
				attendenceDto.setLastLocX(attendence.getLastKnownLocation().getCoordinate().x);
				attendenceDto.setLastLocY(attendence.getLastKnownLocation().getCoordinate().y);
			}
// Get the associated username from the User entity
			if (attendence.getUser() != null) {
				attendenceDto.setUsername(attendence.getUser().getUserName()); // Assuming getUserName() returns the
																				// username
			}
			if (attendence != null) {
				WKTWriter writer = new WKTWriter();
				String pointWkt = writer.write(attendence.getPoint());
				attendenceDto.setPointWkt(pointWkt);
			}
			attendenceDtoList.add(attendenceDto);

		}
		return new ApiResponse(attendenceDtoList, HttpStatus.OK);
	}

	@Transactional

	@Override
	public ResponseEntity<?> startPatrolling(PatrollingTrackDto patrollingTrackDto, MultipartFile file,
			UserNameDto userNameDto,VehicleInfoDto vehicleInfoDto) throws ParseException {
		try {
			Employee employee = employeeRepository.findById(userNameDto.getId())
					.orElseThrow(() -> new RuntimeException("Employee not found"));

			User user = employee.getUser();
			if (user == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found for the employee.");
			}

			String role = user.getRole();
			System.out.println("User Role: " + role); // Debug log

			if (!"ROLE_FRT".equals(role) && !"ROLE_FE".equals(role)) {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User role does not permit patrolling.");
			}

			List<VehicleInfoDto> vehicleInfoList = vehicleInfoService.getVehicleInfoByEmpId(userNameDto,vehicleInfoDto);
			// Create and save Attendance record
			Attendence attendence = new Attendence();
			WKTReader wktReader = new WKTReader();
			Geometry geometry = wktReader
					.read("POINT(" + patrollingTrackDto.getLastLocX() + " " + patrollingTrackDto.getLastLocY() + ")");
			attendence.setPoint(geometry);
			attendence.setLastKnownLocation(geometry);
			attendence.setDate(patrollingTrackDto.getDate());
			attendence.setStatus("Active");
			attendence.setUser(user);
			attendence.setLogInTime(LocalTime.now());

			attenedenceRepo.save(attendence);
			System.out.println("Attendance saved: " + attendence); // Debug log

			employee.setIsAttendenceMarked(true);
			employeeRepository.save(employee);

			PatrollingTrack patrollingTrack = new PatrollingTrack();
			patrollingTrack.setDate(patrollingTrackDto.getDate());
			patrollingTrack.setEmpName(employee.getEmpName());
			patrollingTrack.setOdometerStartReading(patrollingTrackDto.getOdometerStartReading());
//			patrollingTrack.setStartReadingPhotoUrl(patrollingTrackDto.getStartReadingPhotoUrl());
			String startOdoMeterPic = uploadImageToFileSystem(file);
			patrollingTrack.setStartReadingPhotoUrl(startOdoMeterPic);
			patrollingTrack.setStartTime(patrollingTrackDto.getStartTime());

			// Set point and location using WKTReader
			Geometry trackGeometry = wktReader
					.read("POINT(" + patrollingTrackDto.getLastLocX() + " " + patrollingTrackDto.getLastLocY() + ")");
			patrollingTrack.setPoint(trackGeometry);
			patrollingTrack.setLastKnownLocation(trackGeometry);
			patrollingTrack.setEmployee(employee);

			patrollingTrackRepository.save(patrollingTrack);

			TaskDto taskDto = null;

			// Only create a task for the FE role if no previous tasks are pending
			if ("ROLE_FE".equals(role)) {
				List<Task> pendingTasks = taskRepository.findByEmployeeIdAndStatus(userNameDto.getId(), "Active");
				if (pendingTasks.isEmpty()) { // No pending tasks
					Task task = new Task();
					task.setTitle("Patrolling – " + employee.getEmpName());
					task.setDescription("Nothing here");
					task.setAssignedTo(employee.getEmpName());
					task.setAssignedBy(employee.getEmpName());
					task.setCreatedBy("System - " + employee.getEmpName());
					task.setCreatedTime(LocalTime.now());
					task.setCreatedDate(LocalDate.now());
					task.setWorkStartTime(LocalTime.now());

					task.setStatus("Active");
					task.setLocation(employee.getWorkLocation());
					task.setLoc_coordinates(patrollingTrackDto.getPointWkt());
					task.setEmployee(employee);
					taskRepository.save(task);

					System.out.println("Task Created: " + task);

					taskDto = new TaskDto();
					taskDto.setTitle(task.getTitle());
					taskDto.setAssignedTo(task.getAssignedTo());
					taskDto.setAssignedBy(task.getAssignedBy());
					taskDto.setCreatedBy(task.getCreatedBy());
					taskDto.setCreatedTime(task.getCreatedTime());
					taskDto.setCreatedDate(task.getCreatedDate());
					taskDto.setWorkStartTime(task.getWorkStartTime());
					taskDto.setWorkEndTime(task.getWorkEndTime());
					taskDto.setStatus(task.getStatus());
					taskDto.setLocation(task.getLocation());
					taskDto.setLoc_coordinates(task.getLoc_coordinates());
				} else {
					return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Pending tasks exist for this employee.");
				}
			}

			AttendenceDto attendanceDto = new AttendenceDto();
			attendanceDto.setDate(attendence.getDate());
			attendanceDto.setLogInTime(attendence.getLogInTime());
			attendanceDto.setLogoutTime(attendence.getLogoutTime());
			attendanceDto.setStatus(attendence.getStatus());
			attendanceDto.setLastLocX(attendence.getPoint().getCoordinate().x);
			attendanceDto.setLastLocY(attendence.getPoint().getCoordinate().y);

			PatrollingResponseDto responseDto = new PatrollingResponseDto();
			responseDto.setVehicleInfoList(vehicleInfoList);
			responseDto.setAttendence(attendanceDto);
			responseDto.setPatrollingTrack(patrollingTrackDto);
			responseDto.setTask(taskDto);

			return ResponseEntity.ok(responseDto);

		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An error occurred while starting patrolling.");
		}
	}

	@Override
	public ResponseEntity<PatrollingResponseDto> markOutAttendance(UserNameDto userNameDto,MultipartFile file,
			PatrollingTrackDto patrollingTrackDto) throws ParseException {
		try {

			Employee employee = employeeRepository.findById(userNameDto.getId())
					.orElseThrow(() -> new RuntimeException("Employee not found"));

			User user = employee.getUser();
			if (user == null) {
				throw new RuntimeException("User not found for the employee.");
			}

			Attendence attendance = attenedenceRepo.findByUserIdAndDate(user.getId(), LocalDate.now())
					.orElseThrow(() -> new RuntimeException("No active attendance record found for today"));

			attendance.setLogoutTime(LocalTime.now());
			attendance.setLastKnownTime(LocalDateTime.now());

			// Set the last known location
			WKTReader wktReader = new WKTReader();
			Geometry lastKnownLocation = wktReader
					.read("POINT(" + patrollingTrackDto.getLastLocX() + " " + patrollingTrackDto.getLastLocY() + ")");
			attendance.setLastKnownLocation(lastKnownLocation);

			attenedenceRepo.save(attendance);

			// Debug: Check if the attendance was saved
			logger.debug("Updated Attendance: " + attendance);

			PatrollingTrack patrollingTrack = patrollingTrackRepository
					.findByEmployee_IdAndDate(userNameDto.getId(), LocalDate.now())
					.orElseThrow(() -> new RuntimeException("No active patrolling record found for today"));

			// Debug: Check if the patrollingTrack was found
			logger.debug("Patrolling Track: " + patrollingTrack);

			double travelledDistance = patrollingTrackDto.getOdometerEndReading()
					- patrollingTrack.getOdometerStartReading();
			patrollingTrack.setTravelledDistance(travelledDistance);

			patrollingTrack.setOdometerEndReading(patrollingTrackDto.getOdometerEndReading());
			String endOdometerPic = uploadImageToFileSystem(file);
			patrollingTrack.setEndReadingPhotoUrl(endOdometerPic);
			patrollingTrack.setEndTime(patrollingTrackDto.getEndTime());
			patrollingTrack.setEndDate(LocalDate.now());

			patrollingTrackRepository.save(patrollingTrack);

			employee.setIsAttendenceMarked(false);
			employeeRepository.save(employee);

			List<Task> tasks = taskRepository.findByEmployeeIdAndCreatedDate(userNameDto.getId(), LocalDate.now());
			if (tasks.isEmpty()) {
				throw new RuntimeException("No active task found for the employee");
			}
			Task task = tasks.get(0);

			task.setWorkEndTime(attendance.getLogoutTime());
			taskRepository.save(task);

			AttendenceDto attendenceDto = new AttendenceDto();
			attendenceDto.setDate(attendance.getDate());
			attendenceDto.setStatus(attendance.getStatus());
			attendenceDto.setLastLocX(patrollingTrackDto.getLastLocX());
			attendenceDto.setLastLocY(patrollingTrackDto.getLastLocY());
			attendenceDto.setLogoutTime(attendance.getLogoutTime());

			PatrollingTrackDto patrollingTrackResponseDto = new PatrollingTrackDto();
			patrollingTrackResponseDto.setOdometerEndReading(patrollingTrack.getOdometerEndReading());
			patrollingTrackResponseDto.setEndReadingPhotoUrl(patrollingTrack.getEndReadingPhotoUrl());
			patrollingTrackResponseDto.setEndTime(patrollingTrack.getEndTime());
			patrollingTrackResponseDto.setEndDate(patrollingTrack.getEndDate());
			patrollingTrackResponseDto.setTravalledDistance(travelledDistance); // Include the calculated travelled
																				// distance

			PatrollingResponseDto responseDto = new PatrollingResponseDto();
			responseDto.setAttendence(attendenceDto);
			responseDto.setPatrollingTrack(patrollingTrackResponseDto);

			return ResponseEntity.ok(responseDto);

		} catch (Exception e) {

			logger.error("Error in markOutAttendance method: ", e);

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new PatrollingResponseDto());
		}
	}

	@Override
	public ApiResponse updatePatrollerLocation(Long employeeId, PatrollingTrackDto patrollingTrackDto) {
		try {
			// Fetch the employee details
			Optional<Employee> employeeOpt = employeeRepository.findById(employeeId);
			if (!employeeOpt.isPresent()) {
				throw new RuntimeException("Employee not found");
			}
			Employee employee = employeeOpt.get();

			// Fetch the associated user from the employee
			User user = employee.getUser();
			Long userId = user.getId(); // This is the userId stored in the Attendence record

			// Fetch the attendance record for the current day using userId
			Optional<Attendence> attendanceOpt = attenedenceRepo.findByUserIdAndDate(userId, LocalDate.now());
			if (!attendanceOpt.isPresent()) {
				return new ApiResponse("Attendance record not found", HttpStatus.NOT_FOUND);
			}
			Attendence attendance = attendanceOpt.get();

			// Fetch the patrolling track for the employee and current day
			Optional<PatrollingTrack> patrollingTrackOpt = patrollingTrackRepository
					.findByEmployee_IdAndDate(employeeId, LocalDate.now());
			if (!patrollingTrackOpt.isPresent()) {
				return new ApiResponse("Patrolling track record not found", HttpStatus.NOT_FOUND);
			}
			PatrollingTrack patrollingTrack = patrollingTrackOpt.get();

			// Create a new point for the updated location
			GeometryFactory geometryFactory = new GeometryFactory();
			Point newPoint = geometryFactory
					.createPoint(new Coordinate(patrollingTrackDto.getLastLocX(), patrollingTrackDto.getLastLocY()));

			// Update the Attendence entity with the new location
			Geometry existingAttendanceGeometry = attendance.getPoint();
			if (existingAttendanceGeometry instanceof Point) {
				// If the existing geometry is a point, add the new point to create a MultiPoint
				Point[] points = new Point[] { (Point) existingAttendanceGeometry, newPoint };
				MultiPoint multiPoint = geometryFactory.createMultiPoint(points);
				attendance.setPoint(multiPoint);
			} else if (existingAttendanceGeometry instanceof MultiPoint) {
				// If the existing geometry is a MultiPoint, append the new point
				MultiPoint multiPoint = (MultiPoint) existingAttendanceGeometry;
				Coordinate[] newCoordinates = new Coordinate[multiPoint.getNumGeometries() + 1];
				for (int i = 0; i < multiPoint.getNumGeometries(); i++) {
					newCoordinates[i] = multiPoint.getGeometryN(i).getCoordinate();
				}
				newCoordinates[newCoordinates.length - 1] = newPoint.getCoordinate();
				attendance.setPoint(geometryFactory.createMultiPointFromCoords(newCoordinates));
			}

			// Update attendance with last known location and other details
			attendance.setLastKnownLocation(newPoint);
			attendance.setLastKnownTime(LocalDateTime.now());

			// Save the updated attendance record
			attenedenceRepo.save(attendance);

			// Update the PatrollingTrack entity with the new location
			Geometry existingPatrollingGeometry = patrollingTrack.getPoint();
			if (existingPatrollingGeometry instanceof Point) {
				// If the existing geometry is a point, add the new point to create a MultiPoint
				Point[] points = new Point[] { (Point) existingPatrollingGeometry, newPoint };
				MultiPoint multiPoint = geometryFactory.createMultiPoint(points);
				patrollingTrack.setPoint(multiPoint);
			} else if (existingPatrollingGeometry instanceof MultiPoint) {
				// If the existing geometry is a MultiPoint, append the new point
				MultiPoint multiPoint = (MultiPoint) existingPatrollingGeometry;
				Coordinate[] newCoordinates = new Coordinate[multiPoint.getNumGeometries() + 1];
				for (int i = 0; i < multiPoint.getNumGeometries(); i++) {
					newCoordinates[i] = multiPoint.getGeometryN(i).getCoordinate();
				}
				newCoordinates[newCoordinates.length - 1] = newPoint.getCoordinate();
				patrollingTrack.setPoint(geometryFactory.createMultiPointFromCoords(newCoordinates));
			}

			// Save the updated PatrollingTrack record
			patrollingTrackRepository.save(patrollingTrack);

			// Prepare response DTOs
			AttendenceDto attendenceDto = new AttendenceDto();
			attendenceDto.setDate(attendance.getDate());
			attendenceDto.setStatus(attendance.getStatus());
			attendenceDto.setLastLocX(patrollingTrackDto.getLastLocX());
			attendenceDto.setLastLocY(patrollingTrackDto.getLastLocY());

			PatrollingTrackDto patrollingTrackResponseDto = new PatrollingTrackDto();
			patrollingTrackResponseDto.setOdometerStartReading(patrollingTrack.getOdometerStartReading());
			patrollingTrackResponseDto.setLastLocX(patrollingTrackDto.getLastLocX());
			patrollingTrackResponseDto.setLastLocY(patrollingTrackDto.getLastLocY());

			// Create response DTO
			PatrollingResponseDto responseDto = new PatrollingResponseDto();
			responseDto.setAttendence(attendenceDto);
			responseDto.setPatrollingTrack(patrollingTrackResponseDto);

			return new ApiResponse(responseDto, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error updating patroller location: ", e);
			return new ApiResponse("Error updating patroller location", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ApiResponse getPattrolerInfo(Long id) {
		Optional<Attendence> attendenceOpt = attenedenceRepo.findById(id);

		Attendence attendence = attendenceOpt.get();
		AttendenceDto attendenceDto = new AttendenceDto();
		attendenceDto.setId(attendence.getId());
		attendenceDto.setDate(attendence.getDate());
		attendenceDto.setLogInTime(attendence.getLogInTime());
		attendenceDto.setLogoutTime(attendence.getLogoutTime());
		attendenceDto.setStatus(attendence.getStatus());
		if (attendence.getUser() != null) {
			attendenceDto.setUsername(attendence.getUser().getUserName()); // Assuming getUserName() returns the
																			// username
		}

		Employee employee = attendence.getUser().getEmployee();
		if (employee != null) {
			Long employeeId = employee.getId();

			// Retrieve PatrollingTrack using employeeId
			List<PatrollingTrack> patrollingTracks = patrollingTrackRepository.findByEmployeeId(employeeId);

			if (!patrollingTracks.isEmpty()) {
				PatrollingTrack patrollingTrack = patrollingTracks.get(0);
				attendenceDto.setStartTime(patrollingTrack.getStartTime());
				attendenceDto.setEndTime(patrollingTrack.getEndTime());
			}
		}

		if (attendence.getLastKnownLocation() != null) {
			attendenceDto.setLastLocX(attendence.getLastKnownLocation().getCoordinate().x);
			attendenceDto.setLastLocY(attendence.getLastKnownLocation().getCoordinate().y);
		}

		if (attendence.getUser() != null) {
			attendenceDto.setUsername(attendence.getUser().getUserName());
		}

		if (attendence != null) {
			WKTWriter writer = new WKTWriter();
			String pointWkt = writer.write(attendence.getPoint());
			attendenceDto.setPointWkt(pointWkt);
		}

		return new ApiResponse(attendenceDto, HttpStatus.OK);
	}
	
	public String uploadImageToFileSystem(MultipartFile file) throws IOException {
		String filePath = FOLDER_PATH + file.getOriginalFilename();
		// Transfer file to the desired location
		file.transferTo(new File(filePath));
		// Save the file metadata to the database
//		BatteryInfo fileData = siteInfoRepo.save(BatteryInfo.builder().photo(filePath).build());

		// Return success message if file data is saved successfully
//		if (fileData != null) {
//			return "File uploaded successfully: " + filePath;
//		}

		return "uploaded successfully" + filePath;
	}

}
