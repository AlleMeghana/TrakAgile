package com.trackAgile.service;

import com.trackAgile.dto.ApiResponse;
import com.trackAgile.dto.LeaveRequestDto;
import com.trackAgile.dto.LocationDto;

public interface LeaveService {
	
//	public ApiResponse leaveCalcute(Long id,LeaveRequestDto leaveReqDto);
	
	public ApiResponse leaveApply(Long id,LeaveRequestDto leaveReqDto);
	
	public ApiResponse getLeaveInfo();

	
	public ApiResponse getLocation(LocationDto locDto);
	
	
	public ApiResponse LeaveCalculate(Long id,LeaveRequestDto leaveReqDto);
}
