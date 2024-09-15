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
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String siteName;
	private String siteNum;
	private String status; //active /removed 
	private String upsMake;
	private String upsModel;
	private String upsSerialNo;
	private String upsCode;
	private String rackName;
	private String rackModel;
	private String rackSerialNum;
	
	
	
}
