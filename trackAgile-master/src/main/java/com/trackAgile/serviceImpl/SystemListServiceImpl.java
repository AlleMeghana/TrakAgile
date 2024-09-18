package com.trackAgile.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trackAgile.Entity.SystemList;
import com.trackAgile.dto.ApiResponse;
import com.trackAgile.repository.SystemListRepository;
import com.trackAgile.service.SystemListService;

@Service
public class SystemListServiceImpl implements SystemListService{
	
	@Autowired
	private SystemListRepository sysListRepo;
	
	@Override
	public void createInitList(String listName) {
		SystemList sysList = sysListRepo.findByListName(listName);
		if(sysList == null) {
			sysList = new SystemList();
			sysList.setListName(listName);
			sysList.setStatus("Active");
		}
		sysListRepo.save(sysList);				
	}

	@Override
	public ApiResponse createList(String listName) {
		SystemList sysList = sysListRepo.findByListName(listName);
		if(sysList == null) {
			sysList = new SystemList();
			sysList.setListName(listName);
			sysList.setStatus("Active");
		}
		sysListRepo.save(sysList);
		
		return null;
	}

}
