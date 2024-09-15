package com.trackAgile.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.trackAgile.Entity.Attendence;
import com.trackAgile.Entity.User;

import jakarta.transaction.Transactional;

@Repository
public interface AttendenceRepository extends JpaRepository<Attendence, Long> {

//	@Query(value = "INSERT INTO location_entity (name, coordinates) VALUES (:name, ST_PointFromText(:point))", nativeQuery = true)
////	void saveLocation(LocationEntity locEntity);7\
//	void saveLocation(@Param("date") Date date, @Param("point") String point);

//	@Query(value = "INSERT INTO attendence (date,status,lastnow_location) VALUES (:date, ST_PointFromText(:point))", nativeQuery = true)
//	void saveAttendence(@Param("date") Date date, @Param("status")String status,@Param("point") String point); 

	@Transactional
	@Modifying
	@Query(value = "INSERT INTO attendence (date, status,lastnow_location,location_track) VALUES (:date, :status,ST_PointFromText(:point),ST_PointFromText(:pointTrack))", nativeQuery = true)
	void saveAttendence(@Param("date") LocalDate localDate, @Param("status") String status, @Param("point") String point,
			@Param("pointTrack") String pointTrack);

//	@Transactional
//	@Modifying
//	@Query(value = "SELECT ST_AsText(location_track) AS location_track, "
//			+ "ST_AsText(lastnow_location) AS lastnow_location , " +" date as date ,status as status"+"FROM trackagile.attendence", nativeQuery = true)
//	List<Attendence> findAllAttendence();

	@Modifying
	@Transactional
	@Query(value = "select ST_AsText(`location_track`) as location_track,ST_AsText(`lastnow_location`) as lastnow_location "
			+ "FROM attendence where id=:id",nativeQuery = true)
	Optional<Attendence> findLocationById(Long id);

	List<Attendence> findByDate(LocalDate date);

	@Query("SELECT a FROM Attendence a WHERE a.user.id = :userId AND a.date = :date")
	Optional<Attendence> findByUserIdAndDate(@Param("userId") Long userId, @Param("date") LocalDate date);


	//Optional<User> findActiveAttendanceByEmployee(Long loggedInEmployeeId);

	//List<Attendence> findByEmployee_PackageAndDate(String packageName, LocalDate date);

	// List<Attendence> findByUser_Employee_UserPackageAndDate(String userPackage, LocalDate date);

}
