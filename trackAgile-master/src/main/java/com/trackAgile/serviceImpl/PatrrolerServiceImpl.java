//package com.trackAgile.serviceImpl;
//
//import java.time.LocalDateTime;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Service;
//
//import com.trackAgile.Entity.Attendence;
//import com.trackAgile.Entity.Employee;
//import com.trackAgile.Entity.PatrollingTrack;
//import com.trackAgile.Entity.Task;
//import com.trackAgile.Entity.User;
//import com.trackAgile.dto.ApiResponse;
//import com.trackAgile.dto.AttendenceDto;
//import com.trackAgile.dto.PatrollingTrackDto;
//import com.trackAgile.dto.VehicleInfoDto;
//import com.trackAgile.repository.AttendenceRepository;
//import com.trackAgile.repository.EmployeeRepository;
//import com.trackAgile.repository.PattrolerRepository;
//import com.trackAgile.repository.TaskRepository;
//import com.trackAgile.repository.UserRepository;
//import com.trackAgile.service.PatrollerService;
//import com.trackAgile.service.VehicleInfoService;
//import com.vividsolutions.jts.geom.Geometry;
//import com.vividsolutions.jts.io.ParseException;
//import com.vividsolutions.jts.io.WKTReader;
//
//@Service
//public class PatrrolerServiceImpl implements PatrollerService {
//	
//	 @Autowired
//	    private PattrolerRepository patrollingTrackRepository;
//	    
//	    @Autowired
//	    private EmployeeRepository employeeRepository;
//	    
//	    @Autowired
//	    private TaskRepository taskRepository;
//	    
//	    @Autowired
//		private AttendenceRepository attenedenceRepo;
//
//		@Autowired
//		private UserRepository userRepo;
//		
//	    @Autowired
//	    private VehicleInfoService vehicleInfoService; // Service to get Vehicle Info
//
//	    @Override
//	    public void startPatrolling(PatrollingTrackDto patrollingTrackDto, Long loggedInEmployeeId) {
//	        // Fetch logged-in employee details
//	        Employee employee = employeeRepository.findById(loggedInEmployeeId).orElseThrow(() -> new RuntimeException("Employee not found"));
//
//	        // Fetch vehicle information
//	        VehicleInfoDto vehicleInfo = vehicleInfoService.getVehicleInfo(loggedInEmployeeId);
//
//	     // Create and save Attendance record
//	        AttendenceDto attendanceDto = new AttendenceDto();
//	        attendanceDto.setDate(patrollingTrackDto.getDate());
//	        attendanceDto.setStatus("Active");
//	        attendanceDto.setLastLocX(patrollingTrackDto.getLastLocX());
//	        attendanceDto.setLastLocY(patrollingTrackDto.getLastLocY());
//	        saveLocation(loggedInEmployeeId, attendanceDto);
//	        // Create PatrollingTrack record
//	        PatrollingTrack patrollingTrack = new PatrollingTrack();
//	        patrollingTrack.setDate(patrollingTrackDto.getDate());
//	        patrollingTrack.setEmpName(employee.getEmpName());
//	        patrollingTrack.setOdometerStartReading(patrollingTrackDto.getOdometerStartReading());
//	        patrollingTrack.setStartReadingPhotoUrl(patrollingTrackDto.getStartReadingPhotoUrl());
//	        patrollingTrack.setOdometerEndReading(patrollingTrackDto.getOdometerEndReading());
//	        patrollingTrack.setEndReadingPhotoUrl(patrollingTrackDto.getEndReadingPhotoUrl());
//	        patrollingTrack.setStartTime(patrollingTrackDto.getStartTime());
//	        patrollingTrack.setEndTime(patrollingTrackDto.getEndTime());
//	        patrollingTrack.setEndDate(patrollingTrackDto.getEndDate());
//	        patrollingTrack.setTravelledDistance(patrollingTrackDto.getTravalledDistance());
////	        patrollingTrack.setPoint(parseGeometry(patrollingTrackDto.getPointWkt())); // Convert WKT to Geometry
//	        WKTReader wktReader = new WKTReader();
//			Geometry geometry = wktReader
//					.read("POINT(+" + patrollingTrackDto.getLastLocX() + " " + patrollingTrackDto.getLastLocY() + " )");
//
//			patrollingTrack.setPoint(geometry);
//			patrollingTrack.setLastKnownLocation(geometry);
//	        //patrollingTrack.setLastKnownLocation(parseGeometry(patrollingTrackDto.getLastLocWkt())); // Convert WKT to Geometry
//	        patrollingTrack.setEmployee(employee);
//	       // patrollingTrack.setVehicleInfo(vehicleInfo); // Set vehicle info in PatrollingTrack
//	        patrollingTrackRepository.save(patrollingTrack);
//
//	        // Check if employee is FRT Team
////	        if (employee.getFrtTeam() == null || !employee.getFrtTeam()) {
//	            // Create Task record
//	            Task task = new Task();
//	            task.setTitle("Patrolling – " + employee.getEmpName());
//	            task.setDescription("Nothing here");
//	            task.setAssignedTo(employee.getEmpName());
//	            task.setAssignedBy(employee.getEmpName());
//	            task.setCreatedBy("System - " + employee.getEmpName());
//	            task.setCreateTime(LocalDateTime.now().toString());
//	            task.setWorkStartTime(LocalDateTime.now().toString());
//	            task.setWorkEndTime(null); // To be set later
//	            task.setStatus("Active");
//	            task.setLocation(employee.getWorkLocation());
//	            task.setLoc_coordinates(patrollingTrackDto.getPointWkt()); // Assuming coordinates are in WKT
//	            taskRepository.save(task);
//	        }
//	    
//	    
//	    // Helper method to parse WKT to Geometry
//	   
//	   
//
//	    // Existing saveLocation method
//	 
//	    public ApiResponse saveLocation(Long id, AttendenceDto attendenceDto) throws ParseException {
//			Optional<User> userOpt = userRepo.findById(id);
//			Attendence attendence = new Attendence();
//			if (userOpt.isPresent()) {
//				WKTReader wktReader = new WKTReader();
//				Geometry geometry = wktReader
//						.read("POINT(+" + attendenceDto.getLastLocX() + " " + attendenceDto.getLastLocY() + " )");
//
//				attendence.setPoint(geometry);
//				attendence.setLastKnownLocation(geometry);
//				attendence.setDate(attendenceDto.getDate());
//				attendence.setStatus(attendenceDto.getStatus());
//				attendence.setUser(userOpt.get());
//
//				attenedenceRepo.save(attendence);
//			}
//			return new ApiResponse(attendence, HttpStatus.OK);
//
//		}
//	    
//	    
//	    
//}
