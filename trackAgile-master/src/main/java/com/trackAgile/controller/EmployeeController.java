package com.trackAgile.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trackAgile.dto.ApiResponse;
import com.trackAgile.dto.EmployeeDto;
import com.trackAgile.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService empService;

	// saving employee and users at a time
	@PostMapping("/save")
	public ApiResponse saveEmpolyee(@RequestBody EmployeeDto employeeDto) {
		return empService.saveEmpolyee(employeeDto);

	}

	// creating employee and their emergency details and vehicle details
//	@PostMapping("/create")
//	public ApiResponse createEmployee(@RequestPart("empDTo") String empDTo, @RequestPart("file") MultipartFile file)
//			throws IOException {
//		ObjectMapper objectMapper = new ObjectMapper();
//		EmployeeDto employeeDto = objectMapper.readValue(empDTo, EmployeeDto.class);
//		return empService.createEmployee(employeeDto, file);
//
//	}

	@PostMapping("/create")
	public ApiResponse createEmployee(@RequestBody EmployeeDto empDto) throws IOException {
		// Call service method to create employee
		return empService.createEmployee(empDto);

	}

	@GetMapping("/get")
	public ApiResponse getEmployee() {
		return empService.getEmployee();

	}

	@GetMapping("/getById/{id}")
	public ApiResponse getEmployeeById(@PathVariable Long id) {
		return empService.getEmployeeById(id);

	}

	@GetMapping("/getByUserName/{userName}")
	public ApiResponse getEmployeeByUsername(@PathVariable String userName) {

		return empService.getEmployeeByUsername(userName);
	}

}
