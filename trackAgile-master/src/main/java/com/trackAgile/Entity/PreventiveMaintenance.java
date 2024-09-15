package com.trackAgile.Entity;

import java.time.LocalDate;

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
public class PreventiveMaintenance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String pmActivity;
	private String pmList; // dropdown pkg,zone,site,scope
	private String selfie; // need to change
	private String mandalLocation;
	private String siteAddress;
	private String mdlAccessPsnName; // Mandal Access person name
	private Long mdlAccessContactNo; // Mandal access person contact no
	private String equipmentRoom;
	private String equipmentRoomPhoto; // need to change
	private String rainWaterLeakInSite;
	private String rainWaterLeakInSitePhoto;
	private LocalDate lastPmDate;
	private String sheetInCabinet; // record sheet in cabinet
	private String envnmentCondition; // environmental condition
	private String acUnitMake;
	private String acUnitMakePhto;
	private Integer noOfAcs;
	private String acSerialNo;
	private String acSerialNoPhoto; // need to change the data type
	private String acUnitCapacity;
	private String acUnitCondition;
	private String acStatus;
	private String acOutUnitPhoto; // ac outside unit pic
	private String acOutUnitCondition;
	private String acOutHoleSealed;
	private String acOutHoleSealedPhoto; // ac outside holw sealed photo
	private String equipVoltLoad; // Equipment voltage Load
	private Integer equipCardTemp; // euipment card temperature
	private String ebMtrPoleConnction; // eb meter to pole connection
	private String ebPwrTheft; // eb power
	private String ebMtrPhoto; // eb meter photo
	private String ebMtrNo; // eb meter s.l no/ USC no
	private String ebMtrReading;
	private String stabilizerSerialNo;
	private String servoMake;
	private String servoCapacity;
	private String servoCondition;
	private String servoConditionPhoto;
	private String dgSetMake;
	private String dgSetCapacity;
	private String dgSetCondition;
	private String outMCBStatus; // ouside MCB status
	private String mcbRating;
	private String mcbStatus;
	private String mcbDcdbStatus;
	private String rackType;
	private String rackCableOrAny;
	private String rackCableOrAnyPhoto;
	private String rackCondition;
	private String rackconditionRemarks;
	private String rackPhoto;
	private String rackFullViewPhoto;
	private String rackAlarmOnCabinet;
	private String rackTemp; // rack temperature
	private String rackTempPhoto;
	private String rackOutsidePhoto;
	private String rackInsidePhoto;
	private String rackPatchChordRoutingPhoto;
	private String rackPatchChordNamingPhoto;
	private String rackFanStatus;
	private String rackPDU1Status;
	private String rackPDU2Status;
	private String acDcConverterPersent;
	private String acDcStatus;
	private String acDcSerialNo;

}
