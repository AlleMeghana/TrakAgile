package com.trackAgile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trackAgile.Entity.ONT;

@Repository
public interface OntRepository extends JpaRepository<ONT, Long> {

}
