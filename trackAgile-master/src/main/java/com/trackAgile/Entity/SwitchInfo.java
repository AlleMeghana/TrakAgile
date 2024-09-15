package com.trackAgile.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.trackAgile.dto.UPSInfoDto;

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
public class SwitchInfo {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String upsName;
	private String make;
	private String model;
	private String tagNo;
	private String serialNo;
	private String capacity;
	
	@JsonIgnore
	@ToString.Exclude
	@ManyToOne(targetEntity = SiteInfo.class, cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinColumn(name = "site_id", referencedColumnName = "id")
	private SiteInfo siteInfo;
	
	public SwitchInfo(UPSInfoDto upsInfoDto) {
		this.upsName=upsInfoDto.getUpsName();
		this.make=upsInfoDto.getUpsMake();
		this.model=upsInfoDto.getUpsModel();
		this.tagNo=upsInfoDto.getTagNo();
		this.serialNo=upsInfoDto.getSerailNo();
		this.capacity=upsInfoDto.getCapacity();
	}

	

}
