package com.trackAgile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trackAgile.Entity.SiteInfo;

@Repository
public interface SiteInfoRepository extends JpaRepository<SiteInfo, Long>{

}
