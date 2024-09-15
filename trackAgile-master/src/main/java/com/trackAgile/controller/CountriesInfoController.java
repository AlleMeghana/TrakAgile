package com.trackAgile.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trackAgile.dto.CountryList;
import com.trackAgile.dto.CountryState;
import com.trackAgile.dto.DistrictMandal;
import com.trackAgile.dto.MandalVillage;
import com.trackAgile.dto.PkgDist;
import com.trackAgile.dto.StatePackage;
import com.trackAgile.service.CountriesInfoService;


@RestController
@RequestMapping("/api")
public class CountriesInfoController {

    @Autowired
    private CountriesInfoService countriesInfoService;

    @GetMapping("/countries")
    public List<CountryList> getAllCountries() {
        return countriesInfoService.getAllCountries();
    }

    @GetMapping("/{country}/states")
    public List<CountryState> getCountryStates(@PathVariable String country) {
        return countriesInfoService.getCountryStates(country);
    }

    @GetMapping("/{country}/states/{state}/pkgs")
    public List<StatePackage> getStatePackages(@PathVariable String country, @PathVariable String state) {
        return countriesInfoService.getStatePackages(country, state);
    }

    @GetMapping("/{country}/states/{state}/pkg/{pkg}/districts")
    public List<PkgDist> getPkgsDistricts(@PathVariable String country, @PathVariable String state, @PathVariable String pkg) {
        return countriesInfoService.getPackagesDistricts(country, state, pkg);
    }
    
    @GetMapping("/{country}/states/{state}/pkg/{pkg}/districts/{district}/mandals")
    public List<DistrictMandal> getDistrictMandal(@PathVariable String country, @PathVariable String state,@PathVariable String pkg, @PathVariable String district) {
        return countriesInfoService.getDistrictMandals(country, state,pkg, district);
    }

    @GetMapping("/{country}/states/{state}/pkg/{pkg}/districts/{district}/mandals/{mandal}/villages")
    public List<MandalVillage> getMandalVillages(@PathVariable String country, @PathVariable String state,@PathVariable String pkg, @PathVariable String district, @PathVariable String mandal) {
        return countriesInfoService.getMandalVillages(country, state,pkg, district, mandal);
    }
    
}