package com.trackAgile.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.trackAgile.Entity.PatrollingTrack;

import jakarta.transaction.Transactional;

public interface PattrolerRepository extends JpaRepository<PatrollingTrack, Long> {

	@Transactional
	@Modifying
	@Query(value = "INSERT INTO patrolling_track (date, emp_name, odometer_start_reading, start_reading_photo_url, "
			+ "odometer_end_reading, end_reading_photo_url, start_time, end_time, end_date, travalled_distance, "
			+ "coordinate, last_known_location, location_track, lastnow_location, employee_id) "
			+ "VALUES (:date, :empName, :odometerStartReading, :startReadingPhotoUrl, :odometerEndReading, "
			+ ":endReadingPhotoUrl, :startTime, :endTime, :endDate, :travalledDistance, "
			+ "ST_PointFromText(:coordinate), ST_PointFromText(:lastKnownLocation), :locationTrack, "
			+ "ST_PointFromText(:lastNowLocation), :employeeId)", nativeQuery = true)
	void savePatrollingTrack(@Param("date") LocalDate date, @Param("empName") String empName,
			@Param("odometerStartReading") Long odometerStartReading,
			@Param("startReadingPhotoUrl") String startReadingPhotoUrl,
			@Param("odometerEndReading") Long odometerEndReading,
			@Param("endReadingPhotoUrl") String endReadingPhotoUrl, @Param("startTime") LocalTime startTime,
			@Param("endTime") LocalTime endTime, @Param("endDate") LocalDate endDate,
			@Param("travalledDistance") Long travalledDistance, @Param("coordinate") String coordinate,
			@Param("lastKnownLocation") String lastKnownLocation, @Param("locationTrack") String locationTrack, // Assuming
																												// locationTrack
																												// is
																												// handled
																												// separately
			@Param("lastNowLocation") String lastNowLocation, @Param("employeeId") Long employeeId);

	@Modifying
	@Transactional
	@Query(value = "SELECT ST_AsText(`location_track`) as location_track, ST_AsText(`lastnow_location`) as lastnow_location "
			+ "FROM patrolling_track WHERE id = :id", nativeQuery = true)
	Optional<PatrollingTrack> findLocationById(@Param("id") Long id);

	// Optional<User> findByEmployeeIdAndDate(Long loggedInEmployeeId, LocalDate
	// now);

	Optional<PatrollingTrack> findByEmployee_IdAndDate(Long employeeId, LocalDate endDate);

	@Query("SELECT SUM(pt.travelledDistance) FROM PatrollingTrack pt WHERE pt.employee.id = :empId AND MONTH(pt.date) = :month")
	Double findTotalDistanceByEmpIdAndMonth(@Param("empId") Long empId, @Param("month") int month);

	@Query("SELECT SUM(pt.travelledDistance) FROM PatrollingTrack pt WHERE pt.employee.id = :empId AND pt.date BETWEEN :startDate AND :endDate")
	Double findTotalDistanceByEmpIdAndDateRange(@Param("empId") Long empId, @Param("startDate") LocalDate startDate,
			@Param("endDate") LocalDate endDate);

	@Query("SELECT SUM(pt.travelledDistance) FROM PatrollingTrack pt WHERE pt.employee.id = :empId AND pt.date = :date")
	Double findDistanceByEmpIdAndDate(@Param("empId") Long empId, @Param("date") LocalDate date);

	List<PatrollingTrack> findByEmployeeId(Long employeeId);

	// Optional<User> findByEmployeeIdAndDate(Long loggedInEmployeeId, LocalDate
	// now);

}
