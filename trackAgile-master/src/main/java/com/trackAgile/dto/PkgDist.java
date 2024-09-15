package com.trackAgile.dto;

public class PkgDist {
	public int id;
	  public String district;
		
		public PkgDist(){
			
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getDistrict() {
			return district;
		}

		public void setDistrict(String district) {
			this.district = district;
		}

		@Override
		public String toString() {
			return "PkgDist [id=" + id + ", district=" + district + "]";
		}
		
}