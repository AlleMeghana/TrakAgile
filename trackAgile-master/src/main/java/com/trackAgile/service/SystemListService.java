package com.trackAgile.service;

import com.trackAgile.dto.ApiResponse;

public interface SystemListService {

	public void createInitList(String listName);

	public ApiResponse createList(String listName);

}
