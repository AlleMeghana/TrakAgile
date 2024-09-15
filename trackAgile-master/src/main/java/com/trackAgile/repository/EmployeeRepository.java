package com.trackAgile.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trackAgile.Entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	//List<Employee> findByPackage(String packageName);
	
	 Optional<Employee> findByUser_UserName(String userName);

}
