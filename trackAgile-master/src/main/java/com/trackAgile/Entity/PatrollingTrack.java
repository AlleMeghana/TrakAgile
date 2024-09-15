package com.trackAgile.Entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.locationtech.jts.geom.Geometry; // Using org.locationtech.jts.geom
import org.locationtech.jts.geom.MultiPoint;
import org.locationtech.jts.geom.Point;   // Replace com.vividsolutions.jts.awt.PointShapeFactory.Point
import org.n52.jackson.datatype.jts.GeometryDeserializer;
import org.n52.jackson.datatype.jts.GeometrySerializer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
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
public class PatrollingTrack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private String empName;
    private Double odometerStartReading;
    private String startReadingPhotoUrl;
    private Double odometerEndReading;  // Fixed variable name case
    private String endReadingPhotoUrl;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate endDate;
    private Double travelledDistance;  // Fixed variable name typo

    // Using geometry
    @Lob
    @Column(name = "coordinate", length = 65555)
    private Geometry point;

    @Lob
    @Column(name = "lastKnownLocation", length = 65555)
    private Geometry lastKnownLocation;

    // Using points
    @Column(name = "location_track")
    @JsonSerialize(using = GeometrySerializer.class)
    @JsonDeserialize(contentUsing = GeometryDeserializer.class)
    private List<Point> locationTrack = new ArrayList<>();  // Now using org.locationtech.jts.geom.Point

    @Column(name = "lastnow_location", columnDefinition = "POINT")
    @JsonSerialize(using = GeometrySerializer.class)
    @JsonDeserialize(contentUsing = GeometryDeserializer.class)
    private Point lastNowLocation;  // Now using org.locationtech.jts.geom.Point

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

}
