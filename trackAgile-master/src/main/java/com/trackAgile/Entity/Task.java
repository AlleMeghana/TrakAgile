package com.trackAgile.Entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Task {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String taskId;
	private String title;
	private String description; // decripiton
	private String status;
	private Long packge; // package
	private String type;
	private String subType;
	private String createdBy;
	private String assignedBy; // name of the employee.
	private String assignedTo; // Name of the assigned Employee
	private LocalDate createdDate; // This will be crated by service whenever the task is created
	private LocalTime createdTime;
	private LocalTime workStartTime; // User to be presented with an option to start the work and the start time is
									// recorded accordingly
	private LocalTime workEndTime; // this time has to be physically entered by the tech or we will link this to a
								// status
	private String taskEndTime;
	private String location; // location name

	private String loc_coordinates; //location latitude and longitude
	private String reportedBy;
	private String ttNumber; // TT number provuded by NOC

	// private GPInfoDto gpInfo;
	
	//reation to SLA Service Level Agreement
	
	//added on 25/07
	private String workType;
	
	
	
	@ManyToOne
	@JoinColumn(name = "employee_id", referencedColumnName = "id")
	private Employee employee;  // Many tasks can be assigned to one employee

}
