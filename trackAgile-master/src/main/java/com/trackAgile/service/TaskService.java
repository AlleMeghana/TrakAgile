package com.trackAgile.service;

import com.trackAgile.Entity.Task;
import com.trackAgile.dto.ApiResponse;
import com.trackAgile.dto.TaskDto;

public interface TaskService {

	
	ApiResponse updateTaskByEmpId(Long empId, TaskDto updatedTaskDto);

	ApiResponse getListOfTasksById(Long id);

}
