package com.trackAgile.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.trackAgile.dto.BatteryDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
public class Battery {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String smpsUtilization;
	private String batteryMake;
	private String batteryBankMakePhoto;
	private String batteryStatus;
	private String batteryStand;
	private String batteryBankCapacity;
	private String batterCondition;
	private String batterConditionPhoto;
	private String batterBankPhoto;
	private String batteryVoltslNo; // batter voltage sl no
	private String batteryVoltage;

	// added on 25/07
	private String batterModel;
	private String serialNo;
	private String gpInfo;

	// realtion to Pm activy info

	@JsonIgnore
	@ToString.Exclude
	@ManyToOne(targetEntity = SiteInfo.class, cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinColumn(name = "site_id", referencedColumnName = "id")
	private SiteInfo siteInfo;
	
	public Battery(BatteryDto batterDto) {
		//need to confm and set the values
	}

}