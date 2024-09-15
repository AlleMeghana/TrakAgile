package com.trackAgile.service;

import java.util.List;

import com.trackAgile.dto.CountryList;
import com.trackAgile.dto.CountryState;
import com.trackAgile.dto.DistrictMandal;
import com.trackAgile.dto.MandalVillage;
import com.trackAgile.dto.PkgDist;
import com.trackAgile.dto.StatePackage;

public interface CountriesInfoService {

	List<CountryList> getAllCountries();

	List<CountryState> getCountryStates(String country);

	List<StatePackage> getStatePackages(String country, String state);

	List<PkgDist> getPackagesDistricts(String country, String state, String pkg);

	List<DistrictMandal> getDistrictMandals(String country, String state, String pkg, String district);

	List<MandalVillage> getMandalVillages(String country, String state, String pkg, String district, String mandal);

}
