package com.trackAgile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trackAgile.Entity.Rack;

@Repository
public interface RackRepository extends JpaRepository<Rack, Long> {

}
