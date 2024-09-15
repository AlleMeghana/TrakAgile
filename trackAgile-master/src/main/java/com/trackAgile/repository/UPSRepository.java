package com.trackAgile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trackAgile.Entity.UPS;

@Repository
public interface UPSRepository extends JpaRepository<UPS, Long> {

}
