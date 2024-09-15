package com.trackAgile.dto;

public class DistrictMandal {
	private int id;
	private String mandal;
	
	public DistrictMandal(){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMandal() {
		return mandal;
	}

	public void setMandal(String mandal) {
		this.mandal = mandal;
	}

	@Override
	public String toString() {
		return "DistrictMandal [id=" + id + ", mandal=" + mandal + "]";
	}
}