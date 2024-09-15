package com.trackAgile.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trackAgile.dto.ApiResponse;
import com.trackAgile.service.DashboardService;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {
	
	@Autowired
	private DashboardService dashboardService;
	
	 
	    
	    @GetMapping("/summary/{date}")
	    public ApiResponse getAttendanceSummary(@PathVariable("date") LocalDate date) {
	    return dashboardService.getAttendanceSummary(date);
	        
	    }
	    
	    @GetMapping("/summary")
	    public ApiResponse getAttendanceSummary() {
	       return dashboardService.getAttendanceSummary(null);
	    }
	    
	    @GetMapping("/fe/{empId}")
	    public ApiResponse getDashboardData(@PathVariable Long empId) {
	        return dashboardService.getDashboardDataForFE(empId);
	    }


}
