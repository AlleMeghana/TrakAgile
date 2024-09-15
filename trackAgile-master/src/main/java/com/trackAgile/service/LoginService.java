package com.trackAgile.service;

import com.trackAgile.dto.ApiResponse;
import com.trackAgile.dto.UserDto;

public interface LoginService {
	
	public ApiResponse saveUsers(UserDto usersDto);
	
	public ApiResponse updatePassword(UserDto usersDto);

	ApiResponse getAllUsers();

	ApiResponse getUserById(Long id);

	ApiResponse getUserByUsername(String userName);

}
