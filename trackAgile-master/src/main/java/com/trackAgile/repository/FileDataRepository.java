package com.trackAgile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trackAgile.Entity.FIleData;

@Repository
public interface FileDataRepository extends JpaRepository<FIleData, Long> {

}
