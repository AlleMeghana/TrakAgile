package com.trackAgile.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.trackAgile.dto.UPSDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
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

public class UPS {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String upsPhyStatus; // UPS Physical status
	private String upsStand;
	private String upsWorkingStatus;
	private String upsChemicalIssue;
	private Integer upsInputVolt; // UPS Input Voltage
	private String upsInputVoltPhoto;
	private Integer upsOutputVoltage;
	private String upsLoad;
	private String upsDisplayPhotos;
	private String upsReachToNOC; // UPS reachability to NOC
	private String upsMake; 
	private String upsserialNo;
	private String upsSerialNOPhoto;
	private String photo;
	private String upsPhotoPreClean;
	private String upsPhotoAfterClean;

	@JsonIgnore
	@ToString.Exclude
	@ManyToOne(targetEntity = SiteInfo.class, cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinColumn(name = "site_id", referencedColumnName = "id")
	private SiteInfo siteInfo;
	
	public UPS(UPSDto upsDto) {
		//need to confirm and set 
	}

}
