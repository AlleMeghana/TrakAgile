package com.trackAgile.serviceImpl;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.trackAgile.Entity.Employee;
import com.trackAgile.Entity.LeaveInfo;
import com.trackAgile.Entity.LeaveRequest;
import com.trackAgile.Entity.LocationEntity;
import com.trackAgile.dto.ApiResponse;
import com.trackAgile.dto.LeaveRequestDto;
import com.trackAgile.dto.LocationDto;
import com.trackAgile.repository.EmployeeRepository;
import com.trackAgile.repository.LeaveInfoRepository;
import com.trackAgile.repository.LeaveRequestInfoRepository;
import com.trackAgile.repository.LocationEntityRepositoty;
import com.trackAgile.service.LeaveService;

@Service
public class LeaveServiceImpl implements LeaveService {

	@Autowired
	private EmployeeRepository empRepo;

	@Autowired
	private LeaveInfoRepository leaveInfoRepo;

	@Autowired
	private LeaveRequestInfoRepository leaveReqRepo;

	@Autowired
	private LocationEntityRepositoty locEntityRepo;

	public ApiResponse leaveCalcute(Long id) {

//		LeaveRequest leaveReq = leaveReqRepo.findByEmpId(id);  
		Optional<Employee> empOptional = empRepo.findById(id);
		Employee employee = empOptional.get();
		List<LeaveRequest> leaveRequests = leaveReqRepo.findByEmployeeId(id);
		if (leaveRequests == null) {
			throw new RuntimeException("Leave record not found");
		} else {
			LeaveInfo leaveInfo = new LeaveInfo();
			leaveInfo.setAllotedLeaves(12L);

			// Summing up all the leaves availed by the employee
			long totalAvailedLeaves = leaveRequests.stream().mapToLong(LeaveRequest::getNoOfLeaves).sum();
			leaveInfo.setAvailedLeaves(totalAvailedLeaves);
			long balance = leaveInfo.getAllotedLeaves() - leaveInfo.getAvailedLeaves();
			leaveInfo.setBalance(balance);
			leaveInfo.setLeaveType(leaveRequests.get(0).getLeaveType());
			leaveInfo.setEmployee(employee);
			leaveInfoRepo.save(leaveInfo);

		}
		return new ApiResponse("success", HttpStatus.OK);
	}

	@Override
	public ApiResponse leaveApply(Long id, LeaveRequestDto leaveReqDto) {

		Optional<Employee> empOptional = empRepo.findById(id);
		List<LeaveRequest> leaveRequestList = new ArrayList<>();
		if (empOptional.isPresent()) {
			Employee employee = empOptional.get();

			// Get the current month and year
			LocalDate requestDate = leaveReqDto.getRequestDate();
			YearMonth currentMonth = YearMonth.from(requestDate);

			// Check if there is already a casual leave in the current month
			boolean hasCasualLeaveThisMonth = employee.getLeaveRequest().stream()
					.anyMatch(leave -> leave.getLeaveType().equals("casual")
							&& YearMonth.from(leave.getStartDate()).equals(currentMonth));

			LeaveRequest leaveRequest = new LeaveRequest();
			leaveRequest.setLeaveType(leaveReqDto.getLeaveType());

			leaveRequest.setRemarks(leaveReqDto.getRemarks());
			leaveRequest.setRequestDate(requestDate);
			leaveRequest.setStartDate(leaveReqDto.getStartDate());
			leaveRequest.setEndDate(leaveReqDto.getEndDate());
			LocalDate startDate = leaveReqDto.getStartDate();
			LocalDate endDate = leaveReqDto.getEndDate();
			Long totalNoOfDays = ChronoUnit.DAYS.between(startDate, endDate) + 1;
			leaveRequest.setNoOfLeaves(totalNoOfDays);

			// If there is already a casual leave this month, convert the leave type to LOP
			if (hasCasualLeaveThisMonth && leaveReqDto.getLeaveType().equalsIgnoreCase("casual")) {
				leaveRequest.setLeaveType("lop");
			}

			// Set approvedBy and approvedDateTime to null as initially they are not
			// approved
			leaveRequest.setApprovedBy(null);
			leaveRequest.setApprovedDateTime(null);
			leaveRequestList.add(leaveRequest);
			employee.setLeaveRequest(leaveRequestList);
			leaveReqRepo.save(leaveRequest);
			leaveCalcute(id);

		}

		return new ApiResponse(leaveRequestList, HttpStatus.OK);
	}

	@Override
	public ApiResponse getLeaveInfo() {

		return null;
	}

	
	
	//ignore 
	@Override
	public ApiResponse getLocation(LocationDto locDto) {
		GeometryFactory geometryFactory = new GeometryFactory();
		Point point = geometryFactory.createPoint(new Coordinate(locDto.getX(), locDto.getY()));
		LocationEntity locEntity = new LocationEntity();
		locEntity.setName(locDto.getName());
		locEntity.setCoordinates(point);
//		locEntityRepo.saveLocation(locEntity);
		locEntityRepo.saveLocation(locEntity.getName(), point.toText());
		return new ApiResponse(locEntity, HttpStatus.OK);
	}

	@Override
	public ApiResponse LeaveCalculate(Long id, LeaveRequestDto leaveReqDto) {
		Optional<Employee> empOptional = empRepo.findById(id);
		List<LeaveRequest> leaveRequestList = new ArrayList<>();
		
		if (empOptional.isPresent()) {
			Employee employee = empOptional.get();
			
			LeaveRequest leaveRequest = new LeaveRequest();
			leaveRequest.setLeaveType(leaveReqDto.getLeaveType());

			leaveRequest.setRemarks(leaveReqDto.getRemarks());
			
			LocalDate requestDate = leaveReqDto.getRequestDate();
//			YearMonth currentMonth = YearMonth.from(requestDate);
			leaveRequest.setRequestDate(requestDate);
			leaveRequest.setStartDate(leaveReqDto.getStartDate());
			leaveRequest.setEndDate(leaveReqDto.getEndDate());
			LocalDate startDate = leaveReqDto.getStartDate();
			LocalDate endDate = leaveReqDto.getEndDate();
			Long totalNoOfDays = ChronoUnit.DAYS.between(startDate, endDate) + 1;
			leaveRequest.setNoOfLeaves(totalNoOfDays);
			
			//need to ask
			leaveRequest.setApprovedBy(null);
			leaveRequest.setApprovedDateTime(null);
			leaveRequestList.add(leaveRequest);
			employee.setLeaveRequest(leaveRequestList);
			leaveReqRepo.save(leaveRequest);
			
		}
		
		return null;
	}

}
