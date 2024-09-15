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
public class Router {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String routerPhyStatus; // router physical status
	private String routerStatus;
	private String routerIpAddress;
	private String mandalRouterReachablitiyStatus;
	private String mdlRouterEarthingPhoto; // mandal router earthing image
	private String routerFanBforeClean; // need to upload the photos
	private String routerFanAfterClean;
	private String routerType;
	private String routerSlno;
	private String routerSlnoPhoto;

}
