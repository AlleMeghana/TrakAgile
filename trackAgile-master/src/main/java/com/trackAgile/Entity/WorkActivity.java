package com.trackAgile.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class WorkActivity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String ospType;
	private Integer availableFiber;
	private Integer consumedFiber;
	private Integer fiberConsumed;
	private Boolean ductUsed;
	private Integer ductConsumed;
	private String jointClosureType;
	private Integer noOfJointClosuresConsumed;
	private Integer sleevesConsumed;
	private Boolean chamberInstallationStatus;
	private String RestorationType;
	private String photoBefore;
	private String photoAfter;
	private String workDetails;
	private String remarks;
	
	//realtion to task table 
	
	

}
