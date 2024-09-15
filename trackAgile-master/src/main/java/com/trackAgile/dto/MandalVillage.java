package com.trackAgile.dto;

public class MandalVillage {
	private int id;
	private String village;
	
	public MandalVillage(){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	@Override
	public String toString() {
		return "MandalVillage [id=" + id + ", village=" + village + "]";
	}
}