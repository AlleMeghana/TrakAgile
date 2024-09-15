package com.trackAgile.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.trackAgile.Entity.CountriesInfo;
public interface CountriesInfoRepository extends JpaRepository<CountriesInfo, String> {

    List<CountriesInfo> findByVillage(String village);
    List<CountriesInfo> findByMandal(String mandal);
    List<CountriesInfo> findByDistrict(String district);
    List<CountriesInfo> findByState(String state);
    List<CountriesInfo> findByCountry(String country);

    @Query("SELECT DISTINCT c.country FROM CountriesInfo c")
    List<String> findDistinctCountries();

    @Query("SELECT DISTINCT c.state FROM CountriesInfo c WHERE c.country = :country")
    List<String> findDistinctStatesByCountry(String country);
    
    @Query("SELECT DISTINCT c.pkg FROM CountriesInfo c WHERE c.country = :country AND c.state = :state")
    List<String> findDistinctPkgByCountryAndState(String country, String state);

    @Query("SELECT DISTINCT c.district FROM CountriesInfo c WHERE c.country = :country AND c.state = :state AND c.pkg = :pkg")
    List<String> findDistinctDistrictsByCountryAndStateAndPkg(String country, String state,String pkg);

    @Query("SELECT DISTINCT c.mandal FROM CountriesInfo c WHERE c.country = :country AND c.state = :state AND c.pkg = :pkg AND c.district = :district")
    List<String> findDistinctMandalsByCountryAndStateAndPkgAndDistrict(String country, String state,String pkg, String district);

    @Query("SELECT DISTINCT c.village FROM CountriesInfo c WHERE c.country = :country AND c.state = :state AND c.pkg = :pkg AND c.district = :district AND c.mandal = :mandal")
    List<String> findDistinctVillagesByCountryAndStateAndPkgAndDistrictAndMandal(String country, String state,String pkg, String district, String mandal);
}