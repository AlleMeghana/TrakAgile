package com.trackAgile.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trackAgile.Entity.Configurations;

public interface ConfigurationRepository extends JpaRepository<Configurations, Long> {

	Optional<Configurations> findByConfigItem(String key);

}
