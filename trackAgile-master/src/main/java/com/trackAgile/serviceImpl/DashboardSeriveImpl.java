package com.trackAgile.serviceImpl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.trackAgile.Entity.Attendence;
import com.trackAgile.Entity.Employee;
import com.trackAgile.Entity.LeaveRequest;
import com.trackAgile.Entity.Task;
import com.trackAgile.dto.ApiResponse;
import com.trackAgile.dto.AttendenceDto;
import com.trackAgile.dto.DashboardDataDto;
import com.trackAgile.repository.AttendenceRepository;
import com.trackAgile.repository.ConfigurationRepository;
import com.trackAgile.repository.EmployeeRepository;
import com.trackAgile.repository.LeaveInfoRepository;
import com.trackAgile.repository.LeaveRequestInfoRepository;
import com.trackAgile.repository.PattrolerRepository;
import com.trackAgile.repository.TaskRepository;
import com.trackAgile.service.ConfigItemService;
import com.trackAgile.service.DashboardService;

	
	@Service
	public class DashboardSeriveImpl implements DashboardService {
		
		
		    @Autowired
		    private EmployeeRepository employeeRepository;

		    @Autowired
		    private AttendenceRepository attendanceRepository;
		    
		    @Autowired
		    private ConfigurationRepository configurationRepository;
		    
		    @Autowired
		    private ConfigItemService  configItemService;
		    
		    @Autowired
		    private LeaveRequestInfoRepository leaveRequestRepository;
		    
		    @Autowired
		    private LeaveInfoRepository  leaveInfoRepository;
		    
		    @Autowired
		    private PattrolerRepository patrollingTrackRepository;
		    
		    @Autowired
		    private TaskRepository taskRepository;
		    
		    
		    @Override
		    public ApiResponse getAttendanceSummary(LocalDate date) {
		        if (date == null) {
		            date = LocalDate.now();
		        }

		        try {
		            
		            List<Employee> totalEmployees = employeeRepository.findAll();
		            List<Attendence> attendances = attendanceRepository.findByDate(date);

		            int totalEmployeeCount = totalEmployees.size();
		            int presentEmployeeCount = 0;
		            int lateArrivalCount = 0;
		            int absentEmployeeCount = 0;
		            int totalLeaveRequestsToday = 0;
		            int totalTasksCount = 0; 

		            // Fetch dynamic configuration values
		            String shiftStartTimeStr = configItemService.getConfigValue("shiftStartTime", "09:30:00.000000");
		            String gracePeriodEndStr = configItemService.getConfigValue("gracePeriodEnd", "09:45:00.000000");
		            String minWorkDurationStr = configItemService.getConfigValue("minWorkDuration", "03:00:00.000000");

		            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSSSSS");
		            LocalTime gracePeriodEnd = LocalTime.parse(gracePeriodEndStr, timeFormatter);
		            LocalTime minWorkDuration = LocalTime.parse(minWorkDurationStr, timeFormatter);

		            
		            for (Employee employee : totalEmployees) {
		                if (employee.getUser() != null) { 
		                    Optional<Attendence> attendanceOpt = attendances.stream()
		                            .filter(a -> a.getUser().getId().equals(employee.getUser().getId()))
		                            .findFirst();

		                    if (attendanceOpt.isPresent()) {
		                        Attendence attendance = attendanceOpt.get();
		                        LocalTime loginTime = attendance.getLogInTime();
		                        LocalTime logoutTime = attendance.getLogoutTime();

		                        LocalTime endTime = (logoutTime != null) ? logoutTime : LocalTime.now();

		                        long workedSeconds = endTime.toSecondOfDay() - loginTime.toSecondOfDay();

		                        if (!loginTime.isAfter(gracePeriodEnd)) {
		                            presentEmployeeCount++;
		                        } else if (loginTime.isAfter(gracePeriodEnd) && workedSeconds >= minWorkDuration.toSecondOfDay()) {
		                            lateArrivalCount++;
		                        } else {
		                            absentEmployeeCount++;
		                        }
		                    } else {
		                        absentEmployeeCount++;
		                    }

		                    
		                    List<LeaveRequest> leaveRequestsToday = leaveRequestRepository.findByEmployeeAndRequestDate(employee, date);
		                    totalLeaveRequestsToday += leaveRequestsToday.size();
		                    
		                   
		                    List<Task> tasks = taskRepository.findByCreatedDate(date);
		                    totalTasksCount = tasks.size();
		                } else {
		                    absentEmployeeCount++;
		                }
		            }

		            
		            AttendenceDto summaryDto = new AttendenceDto(
		                    totalEmployeeCount,
		                    presentEmployeeCount,
		                    lateArrivalCount,
		                    absentEmployeeCount,
		                    totalLeaveRequestsToday,
		                    totalTasksCount 
		            );

		            
		            return new ApiResponse(HttpStatus.OK, summaryDto, "Attendance summary, today's leave requests, and total tasks fetched successfully.");

		        } catch (Exception e) {
		            e.printStackTrace();
		            return new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR, null, "An error occurred while fetching attendance, today's leave requests, and total tasks.");
		        }
		    }


			@Override
			public ApiResponse getAttendanceSummary(LocalDate date, String packageName) {
				
				return null;
			}
		    
		    
		    
//		    @Override
//		    public ApiResponse getAttendanceSummary(LocalDate date, String packageName) {
//		        if (date == null) {
//		            date = LocalDate.now();
//		        }
//
//		        try {
//		            // Fetch all employees within the given package and attendance records for the given date
//		            List<Employee> totalEmployees = employeeRepository.findByPackage(packageName);
//		            List<Attendence> attendances = attendanceRepository.findByUser_Employee_UserPackageAndDate( packageName,  date);
//
//
//		            int totalEmployeeCount = totalEmployees.size();
//		            int presentEmployeeCount = 0;
//		            int lateArrivalCount = 0;
//		            int absentEmployeeCount = 0;
//		            int totalLeaveRequestsToday = 0; // Track leave requests applied today
//
//		            // Fetch dynamic configuration values
//		            String shiftStartTimeStr = configItemService.getConfigValue("shiftStartTime", "09:30:00.000000");
//		            String gracePeriodEndStr = configItemService.getConfigValue("gracePeriodEnd", "09:45:00.000000");
//		            String minWorkDurationStr = configItemService.getConfigValue("minWorkDuration", "03:00:00.000000");
//
//		            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSSSSS");
//		            LocalTime gracePeriodEnd = LocalTime.parse(gracePeriodEndStr, timeFormatter);
//		            LocalTime minWorkDuration = LocalTime.parse(minWorkDurationStr, timeFormatter);
//
//		            // Process each employee in the package
//		            for (Employee employee : totalEmployees) {
//		                if (employee.getUser() != null) { 
//		                    Optional<Attendence> attendanceOpt = attendances.stream()
//		                            .filter(a -> a.getUser().getId().equals(employee.getUser().getId()))
//		                            .findFirst();
//
//		                    if (attendanceOpt.isPresent()) {
//		                        Attendence attendance = attendanceOpt.get();
//		                        LocalTime loginTime = attendance.getLogInTime();
//		                        LocalTime logoutTime = attendance.getLogoutTime();
//
//		                        LocalTime endTime = (logoutTime != null) ? logoutTime : LocalTime.now();
//
//		                        long workedSeconds = endTime.toSecondOfDay() - loginTime.toSecondOfDay();
//
//		                        if (!loginTime.isAfter(gracePeriodEnd)) {
//		                            presentEmployeeCount++;
//		                        } else if (loginTime.isAfter(gracePeriodEnd) && workedSeconds >= minWorkDuration.toSecondOfDay()) {
//		                            lateArrivalCount++;
//		                        } else {
//		                            absentEmployeeCount++;
//		                        }
//		                    } else {
//		                        absentEmployeeCount++;
//		                    }
//
//		                    // Fetch today's leave requests for the employee within the package
//		                    List<LeaveRequest> leaveRequestsToday = leaveRequestRepository.findByUser_PackageAndRequestDate(packageName, date);
//		                    totalLeaveRequestsToday += leaveRequestsToday.size();
//		                } else {
//		                    absentEmployeeCount++;
//		                }
//		            }
//
//		            // Create summary DTO with today's leave requests
//		            AttendenceDto summaryDto = new AttendenceDto(
//		                    totalEmployeeCount,
//		                    presentEmployeeCount,
//		                    lateArrivalCount,
//		                    absentEmployeeCount,
//		                    totalLeaveRequestsToday // Count of today's leave requests
//		            );
//
//		            // Return the ApiResponse
//		            return new ApiResponse(HttpStatus.OK, summaryDto, "Attendance summary and today's leave requests fetched successfully.");
//
//		        } catch (Exception e) {
//		            e.printStackTrace();
//		            return new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR, null, "An error occurred while fetching attendance and today's leave requests.");
//		        }
//		    }
			
			                                                                                                                                                                                                                
			    @Override
			    public ApiResponse getDashboardDataForFE(Long empId) {
			        try {
			            Double totalDistanceCurrentMonth = patrollingTrackRepository.findTotalDistanceByEmpIdAndMonth(empId, LocalDate.now().getMonthValue());
			            Double distanceLast7Days = patrollingTrackRepository.findTotalDistanceByEmpIdAndDateRange(empId, LocalDate.now().minusDays(7), LocalDate.now());
			            Double distanceLastDay = patrollingTrackRepository.findDistanceByEmpIdAndDate(empId, LocalDate.now().minusDays(1));

			            
			            Long totalAvailedLeaves = leaveInfoRepository.findTotalAvailedLeavesByEmpId(empId);
			            Long totalLeavesCurrentMonth = leaveRequestRepository.findTotalLeavesCurrentMonthByEmpId(empId);
			            Long totalLeaveRequestsCurrentMonth = leaveRequestRepository.findTotalLeaveRequestsCurrentMonthByEmpId(empId);

			            DashboardDataDto dashboardData = new DashboardDataDto(
			                totalDistanceCurrentMonth, 
			                distanceLast7Days, 
			                distanceLastDay,
			                totalAvailedLeaves, 
			                totalLeavesCurrentMonth, 
			                totalLeaveRequestsCurrentMonth
			            );

			            return new ApiResponse(HttpStatus.OK, dashboardData, "Dashboard data retrieved successfully");
			        } catch (Exception e) {
			            return new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR, null, "An error occurred while retrieving dashboard data");
			        }
			    }
			    
			}


		

