package com.trackAgile.serviceImpl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.locationtech.jts.io.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.trackAgile.Entity.ACInfo;
import com.trackAgile.Entity.BatteryInfo;
import com.trackAgile.Entity.CablesInfo;
import com.trackAgile.Entity.DGSetInfo;
import com.trackAgile.Entity.OLTInfo;
import com.trackAgile.Entity.ONTInfo;
import com.trackAgile.Entity.RackInfo;
import com.trackAgile.Entity.RouterInfo;
import com.trackAgile.Entity.SiteContactInfo;
import com.trackAgile.Entity.SiteInfo;
import com.trackAgile.Entity.SolarPanalInfo;
import com.trackAgile.Entity.SwitchInfo;
import com.trackAgile.Entity.UPSInfo;
import com.trackAgile.dto.AcInfoDto;
import com.trackAgile.dto.ApiResponse;
import com.trackAgile.dto.BatteryInfoDto;
import com.trackAgile.dto.CablesInfoDto;
import com.trackAgile.dto.DGSetInfoDto;
import com.trackAgile.dto.OLTInfoDto;
import com.trackAgile.dto.ONTInfoDto;
import com.trackAgile.dto.RackInfoDto;
import com.trackAgile.dto.SiteContactInfoDto;
import com.trackAgile.dto.SiteInfoDto;
import com.trackAgile.dto.UPSInfoDto;
import com.trackAgile.repository.SiteInfoRepository;
import com.trackAgile.service.SiteService;

@Service
public class SiteServiceImpl implements SiteService {

	@Autowired
	private SiteInfoRepository siteInfoRepo;

	private final String FOLDER_PATH = "C:\\Users\\91756\\OneDrive\\Pictures\\meghana";
   
	@Override
	public ApiResponse savingSiteInfo(SiteInfoDto siteInfoDto) throws ParseException, IOException {
//		WKTReader wktReader = new WKTReader();
//		Geometry geometry = wktReader.read("POINT(+" + siteInfoDto.getLatX() + " " + siteInfoDto.getLongY() + " )");

		SiteInfo siteInfo = new SiteInfo(siteInfoDto);
//		siteInfo.setLocation(geometry);

		List<SiteContactInfo> siteContactInfoList = new ArrayList<>();
		for (SiteContactInfoDto siteContactInfo : siteInfoDto.getSiteCOntactInfoDto()) {
			SiteContactInfo siteContactInfoDto = new SiteContactInfo(siteContactInfo);
			siteContactInfoList.add(siteContactInfoDto);
		}
		if (siteInfoDto.getUpsQty() != null && siteInfoDto.getUpsQty() > 0) {
			List<UPSInfo> upsInfoList = new ArrayList<>();
			for (UPSInfoDto upsInfoDto : siteInfoDto.getUpsInfoDto()) {
				UPSInfo upsInfo = new UPSInfo(upsInfoDto);
				upsInfoList.add(upsInfo);
			}
			siteInfo.setUpsInfo(upsInfoList);
		} else if (siteInfoDto.getBatteryQty() != null && siteInfoDto.getBatteryQty() > 0) {
			List<BatteryInfo> batterInfoList = new ArrayList<>();
			for (BatteryInfoDto batterInfoDto : siteInfoDto.getBatterInfoDto()) {
				BatteryInfo batteryInfo = new BatteryInfo(batterInfoDto);
				//String filePath = uploadImageToFileSystem(file);
				//batteryInfo.setPhoto(filePath);
				batterInfoList.add(batteryInfo);
			}
			siteInfo.setBatteryInfo(batterInfoList);
		} else if (siteInfoDto.getRackQty() != null && siteInfoDto.getRackQty() > 0) {
			List<RackInfo> rackInfoList = new ArrayList<>();
			for (RackInfoDto rackInfoDto : siteInfoDto.getRackInfoDto()) {
				RackInfo rackInfo = new RackInfo(rackInfoDto);
				rackInfoList.add(rackInfo);
			}
			siteInfo.setRackInfo(rackInfoList);
		} else if (siteInfoDto.getOtlQty() != null && siteInfoDto.getOtlQty() > 0) {
			List<OLTInfo> oltInfoList = new ArrayList<>();
			for (OLTInfoDto oltInfoDto : siteInfoDto.getOltInfoDto()) {
				OLTInfo oltInfo = new OLTInfo(oltInfoDto);
				oltInfoList.add(oltInfo);
			}
			siteInfo.setOltInfo(oltInfoList);
		} else if (siteInfoDto.getOntQty() != null && siteInfoDto.getOntQty() > 0) {
			List<ONTInfo> ontInfoList = new ArrayList<>();
			for (ONTInfoDto ontInfoDto : siteInfoDto.getOntINfoDto()) {
				ONTInfo ontInfo = new ONTInfo(ontInfoDto);
				ontInfoList.add(ontInfo);
			}
			siteInfo.setOntInfo(ontInfoList);
		} else if (siteInfoDto.getSwitchQty() != null && siteInfoDto.getSwitchQty() > 0) {
			List<SwitchInfo> swithcInfoList = new ArrayList<>();
			for (UPSInfoDto upsInfoDto : siteInfoDto.getUpsInfoDto()) {
				SwitchInfo switchInfo = new SwitchInfo(upsInfoDto);
				swithcInfoList.add(switchInfo);
			}
			siteInfo.setSwitchInfo(swithcInfoList);
		} else if (siteInfoDto.getRouterQty() != null && siteInfoDto.getRouterQty() > 0) {
			List<RouterInfo> routerInfoList = new ArrayList<>();
			for (UPSInfoDto upsInfoDto : siteInfoDto.getUpsInfoDto()) {
				RouterInfo routerInfo = new RouterInfo(upsInfoDto);
				routerInfoList.add(routerInfo);
			}
			siteInfo.setRouterInfo(routerInfoList);
		} else if (siteInfoDto.getCablesQty() != null && siteInfoDto.getCablesQty() > 0) {
			List<CablesInfo> cablesInfoList = new ArrayList<>();
			for (CablesInfoDto cablesInfoDto : siteInfoDto.getCabelsInfoDto()) {
				CablesInfo cablesInfo = new CablesInfo(cablesInfoDto);
				cablesInfoList.add(cablesInfo);
			}
			siteInfo.setCablesInfo(cablesInfoList);
		} else if (siteInfoDto.getSolarPanalQty() != null && siteInfoDto.getSolarPanalQty() > 0) {
			List<SolarPanalInfo> solarPanelInfoList = new ArrayList<>();
			for (UPSInfoDto upsInfoDto : siteInfoDto.getUpsInfoDto()) {
				SolarPanalInfo solarPanelInfo = new SolarPanalInfo(upsInfoDto);
				solarPanelInfoList.add(solarPanelInfo);
			}
			siteInfo.setSolaPanelInfo(solarPanelInfoList);
		}
		siteInfoRepo.save(siteInfo);
		return new ApiResponse("saved successfully" + siteInfo, HttpStatus.OK);
	}

	@Override
	public ApiResponse getSiteInfo() throws IOException {
		List<SiteInfo> siteInfoList = siteInfoRepo.findAll();
		List<SiteInfoDto> siteInfoDtoList = new ArrayList<>();
		for (SiteInfo siteInfo : siteInfoList) {
			SiteInfoDto siteInfoDto = new SiteInfoDto(siteInfo);
			List<SiteContactInfoDto> contactInfoDtoList = new ArrayList<>();
			for (SiteContactInfo siteCOntactInfo : siteInfo.getSiteCOntactInfo()) {
				SiteContactInfoDto siteContactInfoDto = new SiteContactInfoDto(siteCOntactInfo);
				contactInfoDtoList.add(siteContactInfoDto);
			}
			siteInfoDto.setSiteCOntactInfoDto(contactInfoDtoList);
			if (siteInfo.getAcUnitsQty() != null && siteInfo.getAcUnitsQty() > 0) {
				List<AcInfoDto> acInfoDtoList = new ArrayList<>();
				for (ACInfo acInfo : siteInfo.getAcInfo()) {
					AcInfoDto acInfoDto = new AcInfoDto(acInfo);
					acInfoDtoList.add(acInfoDto);
				}
				siteInfoDto.setAcInfoDto(acInfoDtoList);
			} else if (siteInfo.getBatteryQty() != null && siteInfo.getBatteryQty() > 0) {
				List<BatteryInfoDto> batteryInfoDtoList = new ArrayList<>();
				for (BatteryInfo batteryInfo : siteInfo.getBatteryInfo()) {
					BatteryInfoDto batteryInfodto = new BatteryInfoDto(batteryInfo);
					String filePath=batteryInfo.getPhoto();
//					byte[] images = Files.readAllBytes(new File(filePath).toPath());
					batteryInfodto.setPhoto(filePath);
					batteryInfoDtoList.add(batteryInfodto);
				}
				siteInfoDto.setBatterInfoDto(batteryInfoDtoList);
				
			} else if (siteInfo.getCablesQty() != null && siteInfo.getCablesQty() > 0) {
				List<CablesInfoDto> cablesInfoDtoLIst = new ArrayList<>();
				for (CablesInfo cablesInfo : siteInfo.getCablesInfo()) {
					CablesInfoDto cablesInfoDto = new CablesInfoDto(cablesInfo);
					cablesInfoDtoLIst.add(cablesInfoDto);
				}
				siteInfoDto.setCabelsInfoDto(cablesInfoDtoLIst);
			} else if (siteInfo.getDgSetQty() != null && siteInfo.getDgSetQty() > 0) {
				List<DGSetInfoDto> dgSetInfoDtoList = new ArrayList<>();
				for (DGSetInfo dgSetInfo : siteInfo.getDgSetInfo()) { 
					DGSetInfoDto dgSetInfoDto = new DGSetInfoDto(dgSetInfo);
					dgSetInfoDtoList.add(dgSetInfoDto);
				}
				siteInfoDto.setDgSetInfoDto(dgSetInfoDtoList);
			} else if (siteInfo.getOntQty() != null && siteInfo.getOntQty() > 0) {
				List<ONTInfoDto> ontInfoDtoList = new ArrayList<>();
				for (ONTInfo ontInfo : siteInfo.getOntInfo()) {
					ONTInfoDto ontInfoDto = new ONTInfoDto(ontInfo);
					ontInfoDtoList.add(ontInfoDto);
				}
				siteInfoDto.setOntINfoDto(ontInfoDtoList);
			} else if (siteInfo.getOtlQty() != null && siteInfo.getOtlQty() > 0) {
				List<OLTInfoDto> oltInfoDtoList = new ArrayList<>();
				for (OLTInfo oltInfo : siteInfo.getOltInfo()) {
					OLTInfoDto oltInfoDto = new OLTInfoDto(oltInfo);
					oltInfoDtoList.add(oltInfoDto);
				}
				siteInfoDto.setOltInfoDto(oltInfoDtoList);
			} else if (siteInfo.getRackQty() != null && siteInfo.getRackQty() > 0) {
				List<RackInfoDto> rackinfoDtoList = new ArrayList<>();
				for (RackInfo rackInfo : siteInfo.getRackInfo()) {
					RackInfoDto rackInfoDto = new RackInfoDto(rackInfo);
					rackinfoDtoList.add(rackInfoDto);
				}
				siteInfoDto.setRackInfoDto(rackinfoDtoList);

			} else if (siteInfo.getRouterQty() != null && siteInfo.getRouterQty() > 0) {
				List<UPSInfoDto> upsInfoDtoList = new ArrayList<>();
				for (RouterInfo routerInfo : siteInfo.getRouterInfo()) {
					UPSInfoDto upsInfoDto = new UPSInfoDto(routerInfo);
					upsInfoDtoList.add(upsInfoDto);
				}
				siteInfoDto.setRackInfoDto(null);
			} else if (siteInfo.getUpsQty() != null && siteInfo.getUpsQty() > 0) {
				List<UPSInfoDto> upsInfoDtoList = new ArrayList<>();
				for (UPSInfo upsInfo : siteInfo.getUpsInfo()) {
					UPSInfoDto upsInfoDto = new UPSInfoDto(upsInfo);
					upsInfoDtoList.add(upsInfoDto);
				}
				siteInfoDto.setUpsInfoDto(upsInfoDtoList);
			} else if (siteInfo.getSwitchQty() != null && siteInfo.getSwitchQty() > 0) {
				List<UPSInfoDto> upsInfoDtoList = new ArrayList<>();
				for (SwitchInfo switchInfo : siteInfo.getSwitchInfo()) {
					UPSInfoDto upsInfoDto = new UPSInfoDto(switchInfo);
					upsInfoDtoList.add(upsInfoDto);
				}
				siteInfoDto.setUpsInfoDto(upsInfoDtoList);
			} else if (siteInfo.getSolarPanalQty() != null && siteInfo.getSolarPanalQty() > 0) {
				List<UPSInfoDto> upsInfoDtoList = new ArrayList<>();
				for (SolarPanalInfo solarPanalInfo : siteInfo.getSolaPanelInfo()) {
					UPSInfoDto upsInfoDto = new UPSInfoDto(solarPanalInfo);
					upsInfoDtoList.add(upsInfoDto);
				}
				siteInfoDto.setUpsInfoDto(upsInfoDtoList);
			}
			siteInfoDtoList.add(siteInfoDto);
		}
		return new ApiResponse("saved successfully" + siteInfoDtoList, HttpStatus.OK);
	}

	@Override
	public ApiResponse getSitInfoById(Long id) {
		Optional<SiteInfo> siteinfoOpt = siteInfoRepo.findById(id);
		SiteInfo siteInfo = siteinfoOpt.get();

		return null;
	}

	public String uploadImageToFileSystem(MultipartFile file) throws IOException {
		String filePath = FOLDER_PATH + file.getOriginalFilename();
		// Transfer file to the desired location
		file.transferTo(new File(filePath));
		// Save the file metadata to the database
//		BatteryInfo fileData = siteInfoRepo.save(BatteryInfo.builder().photo(filePath).build());

		// Return success message if file data is saved successfully
//		if (fileData != null) {
//			return "File uploaded successfully: " + filePath;
//		}

		return filePath;
	}

}
