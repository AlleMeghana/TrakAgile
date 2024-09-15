package com.trackAgile.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.trackAgile.dto.OLTDto;

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
public class OLT {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String oltPhyStatus; // OLT physical status
	private String oltMake;
	private String oltStatus;
	private String oltEarthingPhoto;
	private String oltWorkingPhoto;
	private Integer oltSlno;
	private String oltSlnoPhoto;
	private String oltFanFilterBforeClean; // need to upload the image
	private String oltFanFilterAfterClean;

	@JsonIgnore
	@ToString.Exclude
	@ManyToOne(targetEntity = SiteInfo.class, cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinColumn(name = "site_id", referencedColumnName = "id")
	private SiteInfo siteInfo;
	
	public OLT(OLTDto oltDto) {
		//need to confirm and set the values
	}

}
