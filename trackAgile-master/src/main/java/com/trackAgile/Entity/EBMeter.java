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
public class EBMeter {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String ebMeterMake;
	private String ebMeterModel;
	private String ebMeterSerialNo;
	private String ebmeterPoleConnectionStatus;
	private String ebMeterTheft;
	private String ebMeterPhoto;
	private Integer ebMeterReading;
	
	
	//relation to task table and
	//realtion to site
	

}
