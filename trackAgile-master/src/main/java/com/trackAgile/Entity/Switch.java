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
public class Switch {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String switchPhyStatus; // switch physical status
	private String steichStatus;
	private String switchEarthingPhoto;
	private String switchType;
	private String switchWorking;
	private String switchWorkingPhoto;
	private String switchSlno;
	private String switchSlnoPhoto;

}
