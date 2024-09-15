package com.trackAgile.service;

import java.time.LocalDate;

import com.trackAgile.dto.ApiResponse;
import com.trackAgile.dto.AttendenceDto;

public interface DashboardService {

	ApiResponse getAttendanceSummary(LocalDate date);

	ApiResponse getAttendanceSummary(LocalDate date, String packageName);

	ApiResponse getDashboardDataForFE(Long empId);

 }
