package com.trackAgile.service;

import java.io.IOException;

import org.locationtech.jts.io.ParseException;
import org.springframework.web.multipart.MultipartFile;

import com.trackAgile.dto.ApiResponse;
import com.trackAgile.dto.SiteInfoDto;

public interface SiteService {

	//public ApiResponse savingSiteInfo(SiteInfoDto siteInfoDto,MultipartFile file) throws ParseException, IOException; 
	
	public ApiResponse getSiteInfo() throws IOException;
	
	public ApiResponse getSitInfoById(Long id);

	ApiResponse savingSiteInfo(SiteInfoDto siteInfoDto) throws ParseException, IOException;
}
