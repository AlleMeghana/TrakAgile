package com.trackAgile.dto;

import java.time.LocalDate;
import java.util.List;

import com.trackAgile.Entity.SiteInfo;

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
public class SiteInfoDto {

	private Long id;
	private String siteName;
	private String siteNum;
	private String contactName;
	private Long contactPhone;

	// private Location location; need to add the location need to modify

	private String district;
	private String mandal;
	private String packge; // package
	private Integer upsQty;
	private Integer batteryQty;
	private Integer rackQty;
	private Integer otlQty;
	private Integer ontQty;
	private Integer switchQty;
	private Integer solarPanalQty;
	private LocalDate lasrPMDate; // update when we do a pm task

	// added on 25/07
	private String lgdCode;
	private Integer earthPitQty;
	private Integer busBarQty;

	// added on 02/08/24
	private String subType;

	// added bcz these feilds are present in site page;//13/08/24
	private Integer acUnitsQty;
	private Integer dgSetQty;
	private Integer ambienceQty;
	private Integer ebMeterQty;
	private Integer cablesQty;
	private Integer fdmsBoxQty;
	private Integer routerQty;
	private Integer batteryStandQty;
	private Integer upsStandQty;

	// for storing the location
	private Double latX;
	private Double longY;

	private List<BatteryDto> batteryDto;

	private List<UPSDto> upsDto;

	private List<ONTDto> ontDto;

	private List<OLTDto> oltDto;

	private List<RackDto> rackDto;

	// info
	private List<BatteryInfoDto> batterInfoDto;

	private List<UPSInfoDto> upsInfoDto;

	private List<RackInfoDto> rackInfoDto;

	private List<ONTInfoDto> ontINfoDto;

	private List<OLTInfoDto> oltInfoDto;

	private List<SiteContactInfoDto> siteCOntactInfoDto;

	private List<CablesInfoDto> cabelsInfoDto;
	
	private List<AcInfoDto> acInfoDto;
	
	private List<DGSetInfoDto> dgSetInfoDto;

	public SiteInfoDto(SiteInfo siteInfo) {
		this.siteName = siteInfo.getSiteName();
		this.siteNum = siteInfo.getSiteNum();
		this.contactName = siteInfo.getContactName();
		this.contactPhone = siteInfo.getContactPhone();
		this.district = siteInfo.getDistrict();
		this.mandal = siteInfo.getMandal();
		this.packge = siteInfo.getPackge();
		this.upsQty = siteInfo.getUpsQty();
		this.batteryQty = siteInfo.getBatteryQty();
		this.rackQty = siteInfo.getRackQty();
		this.otlQty = siteInfo.getOtlQty();
		this.ontQty = siteInfo.getOntQty();
		this.switchQty = siteInfo.getSwitchQty();
		this.solarPanalQty = siteInfo.getSolarPanalQty();
		this.lasrPMDate = siteInfo.getLasrPMDate();
		this.lgdCode = siteInfo.getLgdCode();
		this.earthPitQty = siteInfo.getEarthPitQty();
		this.busBarQty = siteInfo.getBusBarQty();
		this.subType = siteInfo.getSubType();
		this.acUnitsQty = siteInfo.getAcUnitsQty();
		this.dgSetQty = siteInfo.getDgSetQty();
		this.ambienceQty = siteInfo.getAmbienceQty();
		this.ebMeterQty = siteInfo.getEbMeterQty();
		this.cablesQty = siteInfo.getCablesQty();
		this.fdmsBoxQty = siteInfo.getFdmsBoxQty();
		this.routerQty = siteInfo.getRouterQty();
		this.batteryStandQty = siteInfo.getBatteryStandQty();
		this.upsStandQty = siteInfo.getUpsStandQty();

	}

}
