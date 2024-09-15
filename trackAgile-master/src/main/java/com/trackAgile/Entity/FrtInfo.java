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
public class FrtInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String frtNo;
	private String splicerName;
	private String splicePhone;
	private String driverName;
	private String driverPhone;
	private String helper1Name;
	private String helper1Phone;
	private String helper2Name;
	private String helper2Phone;
	private String astName;
	private String astPhone;
	private String pkg;
	private String baseLocation;
	private Boolean ladderAvailble;
	private Boolean splicingMachineAvailable;
	private Boolean toolKitAvailable;
	private Boolean ppeKitAvailable;
	private String splicerEmpRel;
	private String driverEmpRel;
	private String helper1EmpRel;
	private String helper2EmpRel;
	private String astEmpRel;

}
