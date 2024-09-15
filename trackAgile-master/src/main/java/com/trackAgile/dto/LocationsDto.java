package com.trackAgile.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LocationsDto {

	private Long id;
	private Double longitute;
	private Double latitute;
	private LocalDate lastPmDate; // pm- preventive maintainance

}
