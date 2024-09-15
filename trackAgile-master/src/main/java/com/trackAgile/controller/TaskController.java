package com.trackAgile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trackAgile.dto.ApiResponse;
import com.trackAgile.dto.TaskDto;
import com.trackAgile.service.TaskService;

@RestController
@RequestMapping("/task")
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
	 @PostMapping("/update/{empId}")
	    public ApiResponse updateTaskByEmpId(@PathVariable Long empId, @RequestBody TaskDto updatedTaskDto) {
	      return taskService.updateTaskByEmpId(empId, updatedTaskDto);
	           }
	 
	 @GetMapping("/taskInfo/{empId}")
	    public ApiResponse getTasksById(@PathVariable Long empId) {
	        return taskService.getListOfTasksById(empId);
	        
	    }
}
