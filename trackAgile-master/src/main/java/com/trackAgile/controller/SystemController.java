package com.trackAgile.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trackAgile.Entity.User;
import com.trackAgile.dto.ApiResponse;
import com.trackAgile.repository.SystemListRepository;
import com.trackAgile.repository.UserRepository;
import com.trackAgile.service.ListItemsService;
import com.trackAgile.service.SystemListService;

@RestController
@RequestMapping("/system")
public class SystemController {
	
	@Autowired
	private SystemListService sysListSvc;
	
	@Autowired
	private ListItemsService listItmSvc;
	
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private SystemListRepository sysListRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/setup")
	public ApiResponse systeSetup() {
		ApiResponse responseObj = null;
		List<User> users = userRepo.findAll();
		if(users.size() == 0) {
			User user = new User();
			user.setUserName("sa");
			user.setEmail("info@globusitindia.com");
			user.setFullName("Globus Informatics India Pvt Ltd");
			user.setIsFirstLogIn(false);
			user.setPhoneNumber("9999999999");
			user.setPassword(passwordEncoder.encode("sysAdmin@01"));
			user.setIsLastLogInTime(LocalDate.now());
			user.setLastLogoutTime(LocalDate.now());
			user.setRole("ROLE_SYS_ADMIN");
			userRepo.save(user);
			List<String> listNames = new ArrayList<String>();
			listNames.add("Task Types");
			listNames.add("Task Status");
			listNames.add("Incident Status");
			listNames.add("Designations");
			listNames.add("Employee Status");
			listNames.add("Equipment Status");
			listNames.add("Vehicle Types");
			listNames.add("Roles");
			listNames.add("Relations");
			listNames.add("User Status");
			listNames.add("Gender");
			listNames.add("Leave Types");
			for(String listName:listNames) {
				sysListSvc.createInitList(listName);
				if(listName.equalsIgnoreCase("Task Types")) {
					List<String> taskTypesItems = new ArrayList<String>();
					taskTypesItems.add("Patrolling");
					taskTypesItems.add("FRT");
					taskTypesItems.add("PM");
					taskTypesItems.add("Corrective Maintenance");
					listItmSvc.createListItemsForListSetup(listName, taskTypesItems);
					
				}
				if(listName.equalsIgnoreCase("Task Status")) {
					List<String> taskStatusItems = new ArrayList<String>();
					taskStatusItems.add("New");
					taskStatusItems.add("Assigned");
					taskStatusItems.add("Accepted");
					taskStatusItems.add("Work in Progress");
					taskStatusItems.add("Incomplete");
					taskStatusItems.add("Completed");
					taskStatusItems.add("Done");
					listItmSvc.createListItemsForListSetup(listName, taskStatusItems);
				}
				if(listName.equalsIgnoreCase("Incident Status")) {
					List<String> incidentStausItems = new ArrayList<String>();
					incidentStausItems.add("Reported");
					incidentStausItems.add("Assigned");
					incidentStausItems.add("Accepted");
					incidentStausItems.add("Work in Progress");
					incidentStausItems.add("Incomplete");
					incidentStausItems.add("Completed");
					incidentStausItems.add("Done");
					listItmSvc.createListItemsForListSetup(listName, incidentStausItems);
				}
				if(listName.equalsIgnoreCase("Designations")) {
					List<String> designationItems = new ArrayList<String>();
					designationItems.add("Director");
					designationItems.add("Project Manager");
					designationItems.add("Package Manager");
					designationItems.add("Zonal Manager");
					designationItems.add("Manager");
					designationItems.add("Field Engineer");
					designationItems.add("GP Technician");
					designationItems.add("Patroller");
					designationItems.add("Splicer");
					designationItems.add("Driver");
					designationItems.add("Assistant Splicer");
					designationItems.add("Assistant");
					designationItems.add("Account");
					listItmSvc.createListItemsForListSetup(listName, designationItems);
				}
				if(listName.equalsIgnoreCase("Employee Status")) {
					List<String> empStatusItems = new ArrayList<String>();
					empStatusItems.add("Active");
					empStatusItems.add("Absconding");
					empStatusItems.add("Exit");
					empStatusItems.add("Under Notice");
					listItmSvc.createListItemsForListSetup(listName, empStatusItems);
				}
				if(listName.equalsIgnoreCase("Equipment Status")) {
					List<String> equipStatusItems = new ArrayList<String>();
					equipStatusItems.add("Good");
					equipStatusItems.add("Bad");
					equipStatusItems.add("Burnt");
					equipStatusItems.add("Theft");
					listItmSvc.createListItemsForListSetup(listName, equipStatusItems);
				}
				if(listName.equalsIgnoreCase("Vehicle Types")) {
					List<String> vehTypesItems = new ArrayList<String>();
					vehTypesItems.add("2 Wheeler");
					vehTypesItems.add("3 Wheeler");
					vehTypesItems.add("4 Wheeler");
					listItmSvc.createListItemsForListSetup(listName, vehTypesItems);
				}
				if(listName.equalsIgnoreCase("Roles")) {
					List<String> roleItems = new ArrayList<String>();
					roleItems.add("ADMIN");
					roleItems.add("PMGR");
					roleItems.add("PKGMGR");
					roleItems.add("ZMGR");
					roleItems.add("MANAGER");
					roleItems.add("FE");
					roleItems.add("FRT");
					roleItems.add("BOFFICE");
					roleItems.add("USER");
					listItmSvc.createListItemsForListSetup(listName, roleItems);
				}
				if(listName.equalsIgnoreCase("Relations")) {
					List<String> relationItems = new ArrayList<String>();
					relationItems.add("Father");
					relationItems.add("Mother");
					relationItems.add("Brother");
					relationItems.add("Sister");
					relationItems.add("Cousin");
					relationItems.add("Friend");
					relationItems.add("Relative");
					relationItems.add("Spouse");
					listItmSvc.createListItemsForListSetup(listName, relationItems);
				}
				if(listName.equalsIgnoreCase("User Status")) {
					List<String> userStatusItems = new ArrayList<String>();
					userStatusItems.add("Active");
					userStatusItems.add("Inactive");
					listItmSvc.createListItemsForListSetup(listName, userStatusItems);
				}
				if(listName.equalsIgnoreCase("Gender")) {
					List<String> genderItems = new ArrayList<String>();
					genderItems.add("Male");
					genderItems.add("Female");
					listItmSvc.createListItemsForListSetup(listName, genderItems);
				}
				if(listName.equalsIgnoreCase("Leave Types")) {
					List<String> leaveTypesItems = new ArrayList<String>();
					leaveTypesItems.add("Leave");
					leaveTypesItems.add("LOP");
					listItmSvc.createListItemsForListSetup(listName, leaveTypesItems);
				}				
			}			
			
			responseObj = new ApiResponse("System setup successful!", HttpStatus.OK);
		}else {
			responseObj = new ApiResponse("System was already setup. Check with your admin.", HttpStatus.OK);
		}		
		
		return responseObj;
	}


}
