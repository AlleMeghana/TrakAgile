package com.trackAgile.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.trackAgile.Entity.Employee;
import com.trackAgile.Entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

	
	 @Query("SELECT t FROM Task t WHERE t.createdDate = :date")
	List<Task> findByCreatedDate(LocalDate date);

	

	 Optional<Task> findByEmployeeId(Long empId);



	List<Task> findByEmployeeIdAndCreatedDate(Long loggedInEmployeeId, LocalDate now);



	List<Task> findByEmployeeIdAndStatus(Long loggedInEmployeeId, String status);



	
}
