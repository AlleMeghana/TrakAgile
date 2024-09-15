package com.trackAgile.serviceImpl;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.trackAgile.Entity.Task;
import com.trackAgile.dto.ApiResponse;
import com.trackAgile.dto.TaskDto;
import com.trackAgile.repository.EmployeeRepository;
import com.trackAgile.repository.TaskRepository;
import com.trackAgile.service.TaskService;
@Service
public class TaskServiceImpl implements TaskService {
	

	    @Autowired
	    private TaskRepository taskRepository;

	    @Autowired
	    private EmployeeRepository employeeRepository;
	    
        
       
	    @Override
	    public ApiResponse updateTaskByEmpId(Long empId, TaskDto updatedTaskDto) {
	       
	        Task existingTask = taskRepository.findByEmployeeId(empId)
	                .orElseThrow(() -> new NoSuchElementException("Task not found for empId: " + empId + " with taskId: " + updatedTaskDto.getTaskId()));

	       
	        existingTask.setTitle(updatedTaskDto.getTitle());
	        existingTask.setDescription(updatedTaskDto.getDescription());
	        existingTask.setStatus(updatedTaskDto.getStatus());
	        existingTask.setPackge(updatedTaskDto.getPackge());
	        existingTask.setType(updatedTaskDto.getType());
	        existingTask.setSubType(updatedTaskDto.getSubType());
	        existingTask.setWorkStartTime(updatedTaskDto.getWorkStartTime());
	        existingTask.setWorkEndTime(updatedTaskDto.getWorkEndTime());
	        existingTask.setTaskEndTime(updatedTaskDto.getTaskEndTime());
	        existingTask.setLocation(updatedTaskDto.getLocation());
	        existingTask.setLoc_coordinates(updatedTaskDto.getLoc_coordinates());
	        existingTask.setReportedBy(updatedTaskDto.getReportedBy());
	        existingTask.setTtNumber(updatedTaskDto.getTtNumber());
	        existingTask.setWorkType(updatedTaskDto.getWorkType());

	       
	        taskRepository.save(existingTask);

	       
	        return new ApiResponse(updatedTaskDto,HttpStatus.OK);
	    }
	    
	    @Override
		public ApiResponse getListOfTasksById(Long id) {
			Optional<Task> task = taskRepository.findByEmployeeId(id);
	        if (task.isPresent()) {
	            return new ApiResponse(HttpStatus.OK, task.get(), "Tasks retrieved successfully.");
	        }
	        return new ApiResponse(HttpStatus.NOT_FOUND, "Tasks not found with EMP_ID: " + id, "No data available.");
	    }


}
