//package com.trackAgile.Entity;
//
//import javax.persistence.Convert;
//
//import org.hibernate.annotations.Type;
//import org.hibernate.annotations.TypeDef;
//import org.locationtech.jts.geom.Point;
//
//import ch.qos.logback.core.pattern.CompositeConverter;
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.Table;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import lombok.ToString;
//
//@Entity
//@Setter
//@Getter
//@ToString
//@NoArgsConstructor
//@Table(name = "location_entity")
//
////@TypeDef(name = "point", typeClass = org.geolatte.geom.Point.class)
//@TypeDef(name = "jts_geometry", typeClass = org.hibernate.spatial.JTSGeometryType.class)
//
//public class LocationEntity {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
//
//	private String name;
//
////	@Type(type = "org.hibernate.spatial.GeometryType")
////	@Column(columnDefinition = "POINT")
////	@Column(columnDefinition = "POINT NOT NULL")
//
////	@Column(name = "coordinates", columnDefinition = "POINT NOT NULL")
//	 @Column(columnDefinition = "POINT")
////	    @Type(type = "jts_geometry")
//	 @Type(value =org.hibernate.spatial.GeometryType )
//	private Point coordinates;
//

//}

package com.trackAgile.Entity;

import org.locationtech.jts.geom.Point;
import org.n52.jackson.datatype.jts.GeometryDeserializer;
import org.n52.jackson.datatype.jts.GeometrySerializer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@Table(name = "location_entity")
//@TypeDef(name = "jts_geometry", typeClass = org.hibernate.spatial.JTSGeometryType.class)
public class LocationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

//    @Column(columnDefinition = "POINT")
//    @Column(columnDefinition = "geometry(Point,4326)")
    
    @Column(name="the_geom", columnDefinition = "POINT")
    @JsonSerialize(using = GeometrySerializer.class)
    @JsonDeserialize(contentUsing = GeometryDeserializer.class)
    private Point coordinates;
}

