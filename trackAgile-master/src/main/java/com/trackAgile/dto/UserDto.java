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

public class UserDto {

	private Long id;
	private String userName;
	private String password;
	private String email;
	private String status;
	private String fullName;
	private String phoneNumber;
	private String role;
	private Boolean isFirstLogIn;
	private Boolean isLastLogIn;
//	private Time lastLogoutTime;

}
