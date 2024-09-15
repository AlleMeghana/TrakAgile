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
public class ReplacementInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String eqpName;
	private String eqpMake;
	private String eqpSerialNo;
	private String replacedEqpName;
	private String replacedEqpSerialNo;
	private String replaceDate;
	private LocalDate replacedDate;
	private String replacedBy;
	private String photosBefore;
	private String photosAfter;
	private String reasonForReplacement;

	// realtion to gpinfo table

}
