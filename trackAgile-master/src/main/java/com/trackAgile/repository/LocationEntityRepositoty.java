package com.trackAgile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.trackAgile.Entity.LocationEntity;

import jakarta.transaction.Transactional;

@Repository
public interface LocationEntityRepositoty extends JpaRepository<LocationEntity, Long> {

	@Transactional
	@Modifying
//	@Query(value = "INSERT INTO location_entity (name, coordinates) VALUES (:name, ST_PointFromText(:point))", nativeQuery = true)
	@Query(value = "INSERT INTO location_entity (name, coordinates) VALUES (:name, ST_PointFromText(:point))", nativeQuery = true)
//	void saveLocation(LocationEntity locEntity);7\
	void saveLocation(@Param("name") String name, @Param("point") String point);

}
 
