package com.trackAgile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trackAgile.dto.ApiResponse;
import com.trackAgile.dto.LeaveRequestDto;
import com.trackAgile.dto.LocationDto;
import com.trackAgile.service.LeaveService;
 
@RestController
@RequestMapping("/leave")
public class LeaveController {
	
	@Autowired
	private LeaveService leaveService;
	
	@PostMapping("/apply/{id}")
	public ApiResponse leaveApply(@PathVariable("id") Long id,@RequestBody LeaveRequestDto leaveReqDto) {
		return leaveService.leaveApply(id, leaveReqDto);
		
	}
	
	@PostMapping("/location")//
	public ApiResponse getLocation(@RequestBody LocationDto locDto) {
		return leaveService.getLocation(locDto);	
	}
	
	@PostMapping("/calculate/{id}")
	public ApiResponse LeaveCalculate(@PathVariable("id") Long id,@RequestBody LeaveRequestDto leaveReqDto) {
		return leaveService.LeaveCalculate(id, leaveReqDto);
		
	}
	
	

}
                            