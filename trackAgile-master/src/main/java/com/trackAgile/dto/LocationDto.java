package com.trackAgile.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LocationDto {

	private Long id;
	private String name;
	private Double x;
	private Double y;

}
