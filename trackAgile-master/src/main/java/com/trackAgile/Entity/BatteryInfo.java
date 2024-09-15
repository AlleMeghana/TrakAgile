package com.trackAgile.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.trackAgile.dto.BatteryInfoDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
public class BatteryInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String batteryMake;
	private String batteryModel;
	private String batterVoltage;
	private String serailNo;
	private String tagNo;
	private String photo;
	private String gpInfo;

	@JsonIgnore
	@ToString.Exclude
	@ManyToOne(targetEntity = SiteInfo.class, cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinColumn(name = "site_id", referencedColumnName = "id")
	private SiteInfo siteInfo;

	public BatteryInfo(BatteryInfoDto batterInfoDto) {
		this.batteryMake = batterInfoDto.getBatteryMake();
		this.batterVoltage = batterInfoDto.getBatterVoltage();
		this.batteryModel = batterInfoDto.getBatteryModel();
		this.serailNo = batterInfoDto.getSerailNo();
		this.tagNo = batterInfoDto.getTagNo();
		this.gpInfo = batterInfoDto.getGpInfo();
	}

}
