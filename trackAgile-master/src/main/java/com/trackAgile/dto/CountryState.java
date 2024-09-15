package com.trackAgile.dto;

public class CountryState {
	private int id;
	private String state;
	
	public CountryState(){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "CountryState [id=" + id + ", state=" + state + "]";
	}
}