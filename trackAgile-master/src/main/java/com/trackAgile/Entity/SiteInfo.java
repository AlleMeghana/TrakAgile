package com.trackAgile.Entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.trackAgile.dto.SiteInfoDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SiteInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String siteName;
	private String siteNum;
	private String contactName;
	private Long contactPhone;

	// private Location location; need to add the location need to modify

//	@Lob
//	@Column(name = "coordinate",length = 65555)
//	private Geometry location;
	
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

	@JsonIgnore
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(targetEntity = SiteContactInfo.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "site_id", referencedColumnName = "id")
	private List<SiteContactInfo> siteCOntactInfo = new ArrayList<>();

	public SiteInfo(SiteInfoDto siteInfoDto) {
		this.siteName = siteInfoDto.getSiteName();
		this.siteNum = siteInfoDto.getSiteNum();
		this.contactName = siteInfoDto.getContactName();
		this.contactPhone = siteInfoDto.getContactPhone();
		this.district = siteInfoDto.getDistrict();
		this.mandal = siteInfoDto.getMandal();
		this.packge = siteInfoDto.getPackge();
		this.upsQty = siteInfoDto.getUpsQty();
		this.batteryQty = siteInfoDto.getBatteryQty();
		this.rackQty = siteInfoDto.getRackQty();
		this.otlQty = siteInfoDto.getOtlQty();
		this.ontQty = siteInfoDto.getOntQty();
		this.switchQty = siteInfoDto.getSwitchQty();
		this.solarPanalQty = siteInfoDto.getSolarPanalQty();
		this.lgdCode = siteInfoDto.getLgdCode();
		this.earthPitQty = siteInfoDto.getEarthPitQty();
		this.busBarQty = siteInfoDto.getBusBarQty();
		this.subType = siteInfoDto.getSubType();
		this.acUnitsQty = siteInfoDto.getAcUnitsQty();
		this.dgSetQty = siteInfoDto.getDgSetQty();
		this.ambienceQty = siteInfoDto.getAmbienceQty();
		this.ebMeterQty = siteInfoDto.getEbMeterQty();
		this.cablesQty = siteInfoDto.getCablesQty();
		this.fdmsBoxQty = siteInfoDto.getFdmsBoxQty();
		this.batteryStandQty = siteInfoDto.getBatteryStandQty();
		this.upsStandQty = siteInfoDto.getUpsStandQty();
		this.routerQty = siteInfoDto.getRackQty();

	}

	@JsonIgnore
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(targetEntity = Battery.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "site_id", referencedColumnName = "id")
	private List<Battery> battery = new ArrayList<>();

	@JsonIgnore
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(targetEntity = UPS.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "site_id", referencedColumnName = "id")
	private List<UPS> ups = new ArrayList<>();

	@JsonIgnore
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(targetEntity = Rack.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "site_id", referencedColumnName = "id")
	private List<Rack> rack = new ArrayList<>();

	@JsonIgnore
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(targetEntity = OLT.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "site_id", referencedColumnName = "id")
	private List<OLT> olt = new ArrayList<>();

	@JsonIgnore
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(targetEntity = ONT.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "site_id", referencedColumnName = "id")
	private List<ONT> ont = new ArrayList<>();

	// info----------------------------------------
	@JsonIgnore
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(targetEntity = UPSInfo.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "site_id", referencedColumnName = "id")
	private List<UPSInfo> upsInfo = new ArrayList<>();

	@JsonIgnore
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(targetEntity = BatteryInfo.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "site_id", referencedColumnName = "id")
	private List<BatteryInfo> batteryInfo = new ArrayList<>();

	@JsonIgnore
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(targetEntity = RackInfo.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "site_id", referencedColumnName = "id")
	private List<RackInfo> rackInfo = new ArrayList<>();

	@JsonIgnore
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(targetEntity = OLTInfo.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "site_id", referencedColumnName = "id")
	private List<OLTInfo> oltInfo = new ArrayList<>();

	@JsonIgnore
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(targetEntity = ONTInfo.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "site_id", referencedColumnName = "id")
	private List<ONTInfo> ontInfo = new ArrayList<>();

	@JsonIgnore
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(targetEntity = SwitchInfo.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "site_id", referencedColumnName = "id")
	private List<SwitchInfo> switchInfo = new ArrayList<>();

	@JsonIgnore
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(targetEntity = RouterInfo.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "site_id", referencedColumnName = "id")
	private List<RouterInfo> routerInfo = new ArrayList<>();

	@JsonIgnore
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(targetEntity = DGSetInfo.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "site_id", referencedColumnName = "id")
	private List<DGSetInfo> dgSetInfo = new ArrayList<>();

	@JsonIgnore
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(targetEntity = FDMSInfo.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "site_id", referencedColumnName = "id")
	private List<FDMSInfo> fdmsInfo = new ArrayList<>();

	@JsonIgnore
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(targetEntity = ACInfo.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "site_id", referencedColumnName = "id")
	private List<ACInfo> acInfo = new ArrayList<>();

	@JsonIgnore
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(targetEntity = CablesInfo.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "site_id", referencedColumnName = "id")
	private List<CablesInfo> cablesInfo = new ArrayList<>();

	@JsonIgnore
	@ToString.Exclude
	@EqualsAndHashCode.Exclude 
	@OneToMany(targetEntity = SolarPanalInfo.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "site_id", referencedColumnName = "id")
	private List<SolarPanalInfo> solaPanelInfo = new ArrayList<>();

}
