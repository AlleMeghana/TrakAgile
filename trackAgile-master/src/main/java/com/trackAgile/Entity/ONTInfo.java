package com.trackAgile.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.trackAgile.dto.ONTInfoDto;

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
public class ONTInfo {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String ontName;
	private String ontMake;
	private String ontModel;
	private String serialNo;
	private String tagNo;
	private String capacity;
	
	private String ontImage;
	
	@JsonIgnore
	@ToString.Exclude
	@ManyToOne(targetEntity = SiteInfo.class, cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinColumn(name = "site_id", referencedColumnName = "id")
	private SiteInfo siteInfo;

	public ONTInfo(ONTInfoDto ontInfoDto) {
		this.ontMake=ontInfoDto.getOntMake();
		this.ontModel=ontInfoDto.getOntModel();
		this.ontName=ontInfoDto.getOntName();
		this.serialNo=ontInfoDto.getSerialNo();
		this.tagNo=ontInfoDto.getTagNo();
		this.capacity=ontInfoDto.getCapacity();
	}
}
