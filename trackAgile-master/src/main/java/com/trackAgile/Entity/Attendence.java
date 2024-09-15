package com.trackAgile.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Point;
import org.n52.jackson.datatype.jts.GeometryDeserializer;
import org.n52.jackson.datatype.jts.GeometrySerializer;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
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
@Table(name = "attendence_loc")
public class Attendence {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate date;
	private LocalTime logInTime;
	private LocalTime logoutTime;
	private String status;

	// last location time on 07/08/24
	private LocalDateTime lastKnownTime;
	
	private Double distance;

	// using geometry
	@Lob
	@Column(name = "coordinate", length = 65555)
	private Geometry point;

	@Lob
	@Column(name = "lastKnownLocation", length = 65555)
	private Geometry lastKnownLocation;

	// using points
	@Column(name = "location_track")
	@JsonSerialize(using = GeometrySerializer.class)
	@JsonDeserialize(contentUsing = GeometryDeserializer.class)
//	@Convert(converter = PointListConverter.class)
	private List<Point> locationTrack = new ArrayList<>();
//
	@Column(name = "lastnow_location", columnDefinition = "POINT")
	@JsonSerialize(using = GeometrySerializer.class)
	@JsonDeserialize(contentUsing = GeometryDeserializer.class)
	private Point lastNowLoaction;

	@JsonIgnore
	@ToString.Exclude
	@ManyToOne(targetEntity = User.class, cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	@PrePersist
	protected void onCreate() {
		logInTime = LocalTime.now();
//		logInTime = ZonedDateTime.now(ZoneI	d.of("Asia/Kolkata")).toLocalDateTime();
	}

// private Double lastnowLocation; removed
//	private Location locationTrack[]; need to modify and should give the location list

	// emp tracking create -date ,locations[],lastlocation,vechle no,start
	// reading,end reading, total dist,
	// one record per day

	//
}
