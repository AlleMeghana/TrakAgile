package com.trackAgile.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.trackAgile.dto.RackInfoDto;

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
public class RackInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String rackName;
	private String rackMake;
	private String rackModel;
	private String serailNO;
	private String tagNo;
	private String capacity;
	
	private String rackImage;

	@JsonIgnore
	@ToString.Exclude
	@ManyToOne(targetEntity = SiteInfo.class, cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinColumn(name = "site_id", referencedColumnName = "id")
	private SiteInfo siteInfo;

	public RackInfo(RackInfoDto rackInfoDto) {
		this.rackMake = rackInfoDto.getRackMake();
		this.rackName = rackInfoDto.getRackName();
		this.rackModel = rackInfoDto.getRackModel();
		this.serailNO = rackInfoDto.getSerailNO();
		this.tagNo = rackInfoDto.getTagNo();
		this.capacity = rackInfoDto.getCapacity();
	}

}
