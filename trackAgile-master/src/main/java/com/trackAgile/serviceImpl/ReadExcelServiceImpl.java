package com.trackAgile.serviceImpl;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.trackAgile.Entity.SiteInfo;
import com.trackAgile.repository.SiteInfoRepository;

@Service
public class ReadExcelServiceImpl {

	@Autowired
	SiteInfoRepository siteInfoRepository;

	public void readExcelAndSaveToDatabase(String filePath) {
		try (FileInputStream fis = new FileInputStream(filePath); Workbook workbook = WorkbookFactory.create(fis)) {

			Sheet sheet = (Sheet) workbook.getSheetAt(0); // Assuming data is in the first sheet
			Iterator<Row> rowIterator = sheet.iterator();

			// Skip the header row if present
			if (rowIterator.hasNext()) {
				rowIterator.next();
			}
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();

				SiteInfo siteInfo = new SiteInfo();
				siteInfo.setSiteName(row.getCell(0).getStringCellValue());

				siteInfoRepository.save(siteInfo);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void save(MultipartFile file) throws EncryptedDocumentException, IOException {
//		List<List<String>> rows = new ArrayList<>();
//		Workbook workBook = WorkbookFactory.create(file.getInputStream());
//		Sheet sheet=workBook.getSheetAt(0);
//		rows=StreamSupport.stream(sheet.spliterator(), false)
//				.map(row ->StreamSupport.stream(row.spliterator(), false).map(this::getCellStringValue)
//						.collect(Collectors.toList())).collect(Collectors.toList());

		try (InputStream inputStream = file.getInputStream(); Workbook workbook = new XSSFWorkbook(inputStream)) {

			Iterator<Sheet> sheetIterator = workbook.iterator();

			while (sheetIterator.hasNext()) {
				Sheet sheet = sheetIterator.next();
				Iterator<Row> rowIterator = sheet.iterator();

				while (rowIterator.hasNext()) {
					Row row = rowIterator.next();
					if (row.getRowNum() == 0) {
						continue; // Skip header row
					}

					SiteInfo siteInfo = new SiteInfo();
					siteInfo.setContactName(row.getCell(0).getStringCellValue());
					siteInfo.setSiteName(row.getCell(0).getStringCellValue());
					siteInfo.setSiteNum(row.getCell(0).getStringCellValue());
//	                    siteInfo.setContactPhone(row.getCell(2).getNumericCellValue());
//	                    employee.setDepartment(row.getCell(1).getStringCellValue());
//	                    employee.setSalary(row.getCell(2).getNumericCellValue());

					siteInfoRepository.save(siteInfo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
