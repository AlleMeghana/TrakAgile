package com.trackAgile.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trackAgile.dto.CountryList;
import com.trackAgile.dto.CountryState;
import com.trackAgile.dto.DistrictMandal;
import com.trackAgile.dto.MandalVillage;
import com.trackAgile.dto.PkgDist;
import com.trackAgile.dto.StatePackage;
import com.trackAgile.repository.CountriesInfoRepository;
import com.trackAgile.service.CountriesInfoService;

	@Service
	public class CountriesInfoServiceImpl implements CountriesInfoService {

	    @Autowired
	    private final CountriesInfoRepository countriesRepo;

	    public CountriesInfoServiceImpl(CountriesInfoRepository countriesRepo) {
	        this.countriesRepo = countriesRepo;
	    }
        @Override
	    public List<CountryList> getAllCountries() {
	        List<String> countriesList = countriesRepo.findDistinctCountries();
	        List<CountryList> countryNames = new ArrayList<>();
	        for (int countryIdx = 0; countryIdx < countriesList.size(); countryIdx++) {
	            CountryList cl = new CountryList();
	            cl.setId(countryIdx + 1);
	            cl.setCountry(countriesList.get(countryIdx));
	            countryNames.add(cl);
	        }
	        return countryNames;
	    }
        @Override
	    public List<CountryState> getCountryStates(String country) {
	        List<String> statesList = countriesRepo.findDistinctStatesByCountry(country);
	        List<CountryState> countryStates = new ArrayList<>();
	        for (int statesIdx = 0; statesIdx < statesList.size(); statesIdx++) {
	            CountryState cs = new CountryState();
	            cs.setId(statesIdx + 1);
	            cs.setState(statesList.get(statesIdx));
	            countryStates.add(cs);
	        }
	        return countryStates;
	    }
        
        
        @Override
	    public List<StatePackage> getStatePackages(String country, String state) {
	        List<String> pkgList = countriesRepo.findDistinctPkgByCountryAndState(country, state);
	        List<StatePackage> statePkgs = new ArrayList<>();
	        for (int pkgIdx = 0; pkgIdx < pkgList.size(); pkgIdx++) {
	            StatePackage sd = new StatePackage();
	            sd.setId(pkgIdx + 1);
	            sd.setPkg(pkgList.get(pkgIdx));
	            statePkgs.add(sd);
	        }
	        return statePkgs;
	    }
        
        

        @Override
	    public List<PkgDist> getPackagesDistricts(String country, String state, String pkg) {
	        List<String> distList = countriesRepo.findDistinctDistrictsByCountryAndStateAndPkg(country, state,pkg);
	        List<PkgDist> PkgsDistricts = new ArrayList<>();
	        for (int distIdx = 0; distIdx < distList.size(); distIdx++) {
	            PkgDist sd = new PkgDist();
	            sd.setId(distIdx + 1);
	            sd.setDistrict(distList.get(distIdx));
	            PkgsDistricts.add(sd);
	        }
	        return PkgsDistricts;
	    }
        @Override
	    public List<DistrictMandal> getDistrictMandals(String country, String state,String pkg, String district) {
	        List<String> mandalsList = countriesRepo.findDistinctMandalsByCountryAndStateAndPkgAndDistrict(country, state,pkg, district);
	        List<DistrictMandal> districtMandals = new ArrayList<>();
	        for (int manIdx = 0; manIdx < mandalsList.size(); manIdx++) {
	            DistrictMandal dm = new DistrictMandal();
	            dm.setId(manIdx + 1);
	            dm.setMandal(mandalsList.get(manIdx));
	            districtMandals.add(dm);
	        }
	        return districtMandals;
	    }
        @Override
	    public List<MandalVillage> getMandalVillages(String country, String state,String pkg, String district, String mandal) {
	        List<String> villagesList = countriesRepo.findDistinctVillagesByCountryAndStateAndPkgAndDistrictAndMandal(country, state,pkg, district, mandal);
	        List<MandalVillage> mandalVillages = new ArrayList<>();
	        for (int villIdx = 0; villIdx < villagesList.size(); villIdx++) {
	            MandalVillage mv = new MandalVillage();
	            mv.setId(villIdx + 1);
	            mv.setVillage(villagesList.get(villIdx));
	            mandalVillages.add(mv);
	        }
	        return mandalVillages;
	    }
	}