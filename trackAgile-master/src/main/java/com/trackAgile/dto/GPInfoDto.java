package com.trackAgile.dto;

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
public class GPInfoDto {

	private Long id;
	private String gpNo;
	private String gpName;
	private String district;
	private String mandal;
	private String contactName;
	private String contactPh;

}
