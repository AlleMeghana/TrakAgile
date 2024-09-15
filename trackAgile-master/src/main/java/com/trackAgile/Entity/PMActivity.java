package com.trackAgile.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class PMActivity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String locName;
	private String locNum;
	private String roomAmbiencePhotoUrl;
	private String ebMeterStatus;
	private String ebMeterPhotoUrl;
	private String powerTheft;
	private String ebMotorToPoleConnection;
	private String recordSheetStatus;
	private String mcbStatus;
	private String mcbPhotoUrl;
	private String upsStatus;
	private String upsPhotoUrl;

}
