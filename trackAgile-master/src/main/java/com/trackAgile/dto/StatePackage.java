package com.trackAgile.dto;

public class StatePackage {
	public int id;
  public String pkg;
	
	public StatePackage(){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPkg() {
		return pkg;
	}

	public void setPkg(String pkg) {
		this.pkg = pkg;
	}

	@Override
	public String toString() {
		return "StatePackage [id=" + id + ", Package=" + pkg + "]";
	}
	
}