package com.trackAgile.serviceImpl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.trackAgile.Entity.EmployeEmergencyContact;
import com.trackAgile.Entity.Employee;
import com.trackAgile.Entity.User;
import com.trackAgile.Entity.VehicleInfo;
import com.trackAgile.dto.ApiResponse;
import com.trackAgile.dto.EmployeEmergencyContactDto;
import com.trackAgile.dto.EmployeeDto;
import com.trackAgile.dto.VehicleInfoDto;
import com.trackAgile.repository.EmployeeRepository;
import com.trackAgile.repository.UserRepository;
import com.trackAgile.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	private final String FOLDER_PATH = "C:\\Users\\91756\\OneDrive\\Pictures\\meghana";

	// to save the employees and users to be created while creatingt he employees
	@Override
	public ApiResponse saveEmpolyee(EmployeeDto employeeDto) {
		Employee employee = new Employee(employeeDto);
		
		employeeRepository.save(employee);
		saveUsers(employee);
		return new ApiResponse("User saved successfully", HttpStatus.OK);
	}

	public ApiResponse saveUsers(Employee employee) {
		if (!employee.getPhone().isEmpty()) {
			// Check if the username already exists
			Optional<User> userOpt = userRepo.findByUserName(employee.getPhone());

			if (userOpt.isPresent()) {
				// If username already exists, return ApiResponse indicating the conflict
				return new ApiResponse("Username already exists", HttpStatus.CONFLICT);
			} else {
				User user = new User();
				user.setUserName(employee.getPhone());
				user.setEmail(employee.getEmail());
				user.setPassword(passwordEncoder.encode("System@01"));
				user.setPhoneNumber(employee.getPhone());
				user.setRole(employee.getDesignation());
				userRepo.save(user);

	            
	            employee.setUser(user);
	            employeeRepository.save(employee); 

				return new ApiResponse("User saved successfully", HttpStatus.OK);
			}
		} else {
			return new ApiResponse("No data found to insert", HttpStatus.BAD_REQUEST);
		}
	}
	

	// creating emplyee and adding their emergency contact details
	@Override
	public ApiResponse createEmployee(EmployeeDto empDTo) throws IOException {

		if (empDTo != null) {
			Employee employee = new Employee(empDTo);
//			String profilePic = uploadImageToFileSystem(file);
//			employee.setProfilePicUrl(profilePic);
			List<EmployeEmergencyContact> empemrList = new ArrayList<>();

			for (EmployeEmergencyContactDto emergncyContDto : empDTo.getEmpEmergencyDto()) {
				EmployeEmergencyContact emplyEmrgncy = new EmployeEmergencyContact(emergncyContDto);
				 // emplyEmrgncy.setEmployee(employee); // Set employee reference
				empemrList.add(emplyEmrgncy);
			}

			List<VehicleInfo> vehicleInfoList = new ArrayList<VehicleInfo>();
			for (VehicleInfoDto vehicleInfoDto : empDTo.getVehicleInfoDto()) {
				VehicleInfo vehicleInfo = new VehicleInfo(vehicleInfoDto);
				vehicleInfoList.add(vehicleInfo);
			}
			employee.setEmpEmergencyContact(empemrList);
			employee.setVehicleInfo(vehicleInfoList);
			
			employeeRepository.save(employee);
			saveUsers(employee);
			

			return new ApiResponse("Inserted successfully" + employee, HttpStatus.OK);
		} else {
			return new ApiResponse("No data found to store", HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ApiResponse getEmployee() {
		List<Employee> empolyeeList = employeeRepository.findAll();

		List<EmployeeDto> employeeDtoList = new ArrayList<EmployeeDto>();
		for (Employee employee : empolyeeList) {
			EmployeeDto employeeDto = new EmployeeDto(employee);
			String filePath = employee.getProfilePicUrl();
			employeeDto.setProfilePicUrl(filePath);
			employeeDtoList.add(employeeDto);

			List<EmployeEmergencyContactDto> empEmrgncyDtoList = new ArrayList<>();
			for (EmployeEmergencyContact empEmrgncy : employee.getEmpEmergencyContact()) {
				EmployeEmergencyContactDto emplyEmrgncyDto = new EmployeEmergencyContactDto(empEmrgncy);
				empEmrgncyDtoList.add(emplyEmrgncyDto);
			}
			List<VehicleInfoDto> vehicleInfoDtoLIst = new ArrayList<>();
			for (VehicleInfo vehicleInfo : employee.getVehicleInfo()) {
				VehicleInfoDto vehicleInfoDto = new VehicleInfoDto(vehicleInfo);
				vehicleInfoDtoLIst.add(vehicleInfoDto);
			}

			employeeDto.setEmpEmergencyDto(empEmrgncyDtoList);
			employeeDto.setVehicleInfoDto(vehicleInfoDtoLIst);
		}
		return new ApiResponse(employeeDtoList, HttpStatus.OK);
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

	@Override
	public ApiResponse getEmployeeById(Long id) {
		Optional<Employee> empOpt = employeeRepository.findById(id);

		Employee employee = empOpt.get();
		EmployeeDto employeeDto = new EmployeeDto(employee);

		List<EmployeEmergencyContactDto> empEmrgncyDtoList = new ArrayList<>();
		for (EmployeEmergencyContact empEmrgncy : employee.getEmpEmergencyContact()) {
			EmployeEmergencyContactDto emplyEmrgncyDto = new EmployeEmergencyContactDto(empEmrgncy);
			empEmrgncyDtoList.add(emplyEmrgncyDto);
		}
		List<VehicleInfoDto> vehicleInfoDtoLIst = new ArrayList<>();
		for (VehicleInfo vehicleInfo : employee.getVehicleInfo()) {
			VehicleInfoDto vehicleInfoDto = new VehicleInfoDto(vehicleInfo);
			vehicleInfoDtoLIst.add(vehicleInfoDto);
		}
		employeeDto.setEmpEmergencyDto(empEmrgncyDtoList);
		employeeDto.setVehicleInfoDto(vehicleInfoDtoLIst);

		return new ApiResponse(employeeDto, HttpStatus.OK);
	}
	
	public ApiResponse getEmployeeByUsername(String userName) {
	    Optional<Employee> employeeOptional = employeeRepository.findByUser_UserName(userName);

	    if (!employeeOptional.isPresent()) {
	        return new ApiResponse("Employee not found", HttpStatus.NOT_FOUND);
	    }

	    Employee employee = employeeOptional.get();
	    EmployeeDto employeeDto = new EmployeeDto(employee);
	    
	    return new ApiResponse(employeeDto, HttpStatus.OK);
	}

}
