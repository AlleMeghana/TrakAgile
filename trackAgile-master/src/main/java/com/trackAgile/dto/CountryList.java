package com.trackAgile.dto;

public class CountryList {
	
	public int id;
	public String country;
	
	public CountryList(){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "CountryList [id=" + id + ", country=" + country + "]";
	}
	
}