package com.trackAgile.controller;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.locationtech.jts.io.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trackAgile.dto.ApiResponse;
import com.trackAgile.dto.SiteInfoDto;
import com.trackAgile.service.SiteService;
import com.trackAgile.serviceImpl.ReadExcelServiceImpl;

@RestController
@RequestMapping("/site")
public class SiteController {

	@Autowired
	private SiteService siteService;
	
	@Autowired
	private ReadExcelServiceImpl readExcelServiceImpl;

//	@PostMapping("/info-save")
////	public ApiResponse savingSiteInfo(@RequestBody SiteInfoDto siteInfoDto, @RequestParam("file") MultipartFile file)
////			throws ParseException, IOException {
////		return siteService.savingSiteInfo(siteInfoDto, file);
////
////	}
//	
//	public ApiResponse savingSiteInfo(
//	        @RequestPart("siteInfoDto") String siteInfoDtoJson, 
//	        @RequestPart("file") MultipartFile file) throws ParseException, IOException {
//
//	        // Convert JSON string to SiteInfoDto
//	        ObjectMapper objectMapper = new ObjectMapper();
//	        SiteInfoDto siteInfoDto = objectMapper.readValue(siteInfoDtoJson, SiteInfoDto.class);
//
//	        // Call service method with parsed DTO and file
//	        return siteService.savingSiteInfo(siteInfoDto, file);
//	    }
	
	@PostMapping("/info-save")
	public ApiResponse savingSiteInfo(@RequestBody SiteInfoDto siteInfoDtoJson) throws ParseException, IOException {

	   
	    return siteService.savingSiteInfo(siteInfoDtoJson);
	}

	@GetMapping("/get")
	public ApiResponse getSiteInfo() throws IOException {
		return siteService.getSiteInfo();
  
	}
	
	@PostMapping("/upload-excel")
    public ResponseEntity<String> uploadExcel(@RequestParam("file") MultipartFile file) throws EncryptedDocumentException, IOException {
		readExcelServiceImpl.save(file);
        return ResponseEntity.ok("Data has been uploaded successfully.");
    }

}
