package com.trackAgile.Entity;

import java.sql.Time;
import java.time.LocalDate;

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
public class FrtTripInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDate workDate;
	private Time workStartTime;
	private Time workEndTime;
	private Boolean ownVehicle;
	private String rentVehicleModel;
	private String rentVehicleMake;
	private String rentVehicleRegNo;
	private Integer tripDistance;
	// trip dsitance [] //Distance traveled during the trip (in km or miles)need to
	// modify this
	private Integer vehicleStartReading;
	private String vehicleStartReadingPhoto;
	private String vehicleEndReadingPhoto;

}
