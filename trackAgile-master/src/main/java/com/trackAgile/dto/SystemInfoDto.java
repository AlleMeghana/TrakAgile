package com.trackAgile.dto;

import java.sql.Time;

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
public class SystemInfoDto {
//To track the application usage by the employee

	private Long id;
	private Time loginTime;
	private Time logoutTime;

}
