package com.trackAgile.dto;

import com.trackAgile.Entity.SiteContactInfo;

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
public class SiteContactInfoDto {
	
	
	private Long id;
	private String name;
	private String designation;
	private String phone; 
	
	public SiteContactInfoDto(SiteContactInfo siteCOntactInfo) {
		this.name=siteCOntactInfo.getName();
		this.designation=siteCOntactInfo.getDesignation();
		this.phone=siteCOntactInfo.getPhone();
	}
	

}
