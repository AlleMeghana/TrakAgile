package com.trackAgile.serviceImpl;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.trackAgile.Entity.BatteryInfo;
import com.trackAgile.repository.BatteryInfoRepository;
import com.trackAgile.service.BatteryInfoService;

@Service
public class BatteryInfoServiceImpl implements BatteryInfoService {

	private BatteryInfoRepository batteryInfoRepo;

	private final String FOLDER_PATH = "C:\\Users\\91756\\OneDrive\\Pictures\\meghana";

	public String uploadImageToFileSystem(MultipartFile file) throws IOException {
		String filePath = FOLDER_PATH + file.getOriginalFilename();

		// Transfer file to the desired location
		file.transferTo(new File(filePath));

		// Save the file metadata to the database
		BatteryInfo fileData = batteryInfoRepo.save(BatteryInfo.builder().photo(filePath).build());

		// Return success message if file data is saved successfully
		if (fileData != null) {
			return "File uploaded successfully: " + filePath;
		}

		return "Failed to upload file";
	}

}
