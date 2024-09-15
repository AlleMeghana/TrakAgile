package com.trackAgile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trackAgile.Entity.Battery;

@Repository
public interface BatteryRepository extends JpaRepository<Battery, Long> {

}
