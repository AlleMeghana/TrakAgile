package com.trackAgile.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.trackAgile.Entity.Employee;
import com.trackAgile.Entity.LeaveRequest;

@Repository
public interface LeaveRequestInfoRepository extends JpaRepository<LeaveRequest, Long> {

	List<LeaveRequest> findByEmployeeId(Long id);

	 List<LeaveRequest> findByEmployeeAndRequestDate(Employee employee, LocalDate requestDate);
	 
	 @Query("SELECT SUM(l.noOfLeaves) FROM LeaveRequest l WHERE l.employee.id = :empId AND MONTH(l.startDate) = MONTH(CURRENT_DATE) AND l.status = 'APPROVED'")
	    Long findTotalLeavesCurrentMonthByEmpId(Long empId);
	    
	   
	 @Query("SELECT COUNT(l) FROM LeaveRequest l WHERE l.employee.id = :empId AND MONTH(l.requestDate) = MONTH(CURRENT_DATE)")
	    Long findTotalLeaveRequestsCurrentMonthByEmpId(Long empId);

}
