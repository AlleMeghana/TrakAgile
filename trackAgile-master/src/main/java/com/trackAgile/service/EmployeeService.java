package com.trackAgile.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.trackAgile.dto.ApiResponse;
import com.trackAgile.dto.EmployeeDto;

public interface EmployeeService {

	public ApiResponse saveEmpolyee(EmployeeDto employeeDto);

	//public ApiResponse createEmployee(EmployeeDto empDTo, MultipartFile file) throws IOException;

	public ApiResponse getEmployee();
	
	public ApiResponse getEmployeeById(Long id);

	ApiResponse createEmployee(EmployeeDto empDTo) throws IOException;

	public ApiResponse getEmployeeByUsername(String userName);

}
