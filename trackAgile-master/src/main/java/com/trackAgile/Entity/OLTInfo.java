package com.trackAgile.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.trackAgile.dto.OLTInfoDto;

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
public class OLTInfo {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String oltName;
	private String oltMake;
	private String oltModel;
	private String serialNo;
	private String tagNo;
	private String capacity;

	private String oltImage;
	
	@JsonIgnore
	@ToString.Exclude
	@ManyToOne(targetEntity = SiteInfo.class, cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinColumn(name = "site_id", referencedColumnName = "id")
	private SiteInfo siteInfo;
	
	public OLTInfo(OLTInfoDto oltInfoDto) {
		this.oltMake=oltInfoDto.getOltMake();
		this.oltModel=oltInfoDto.getOltModel();
		this.oltName=oltInfoDto.getOltName();
		this.serialNo=oltInfoDto.getSerialNo();
		this.tagNo=oltInfoDto.getTagNo();
		this.capacity=oltInfoDto.getCapacity();
	
	}

	
}
