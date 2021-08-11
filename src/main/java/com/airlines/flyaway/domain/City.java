package com.airlines.flyaway.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cities")
public class City {
	
	@Id 
	private long cityId;
	private String cityName;
	

	
	public long getCityId() {
		return cityId;
	}
	
	public void setCityId(long cityId) {
		this.cityId = cityId;
	}
	
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}


	
	public City(long cityId, String cityName) {
		super();
		this.cityId = cityId;
		this.cityName = cityName;
	}
	
	public City() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "City [cityId=" + cityId + ", cityName=" + cityName + "]";
	}
	
}
