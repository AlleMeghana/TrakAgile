package com.trackAgile.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.trackAgile.dto.RackDto;

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
public class Rack {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String rackMake;
	private String rackMode;
	private String rackSerialNo;
	private String rackCabelEntreyPhotoUrl;
	private String rackCableEntrySecured;
	private String rackStatus;
	private String rackStatusRemarks;
	private String rackFullPhotoUrl;
	private String rackLongPhotoUrl;
	private String rackTemperature;
	private String rackTempPhotoUrl;
	private String rackInsidePhotoUrl;
	private String rackPatchChordRoutingPhotoUrl;
	private String rackPatchChordNamingPhotoUrl;
	private String rackFanStatus;
	private String rackPdu1Status;
	private String rackPdu2Status;
	private String acDcConverterPresent;
	private String acDcStatus;
	private String acDcSerialNo;
	private String ebInputVoltage;

	@JsonIgnore
	@ToString.Exclude
	@ManyToOne(targetEntity = SiteInfo.class, cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinColumn(name = "site_id", referencedColumnName = "id")
	private SiteInfo siteInfo;
	
	public Rack(RackDto rackDto) {
		// need to confrm and set the values
	}


}
