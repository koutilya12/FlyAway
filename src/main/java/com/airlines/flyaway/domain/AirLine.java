package com.airlines.flyaway.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "air_lines")
public class AirLine {

	@Id
	private long airLineId;
	private String airLineName;
	
	
	
	public long getAirLineId() {
		return airLineId;
	}

	public void setAirLineId(long airLineId) {
		this.airLineId = airLineId;
	}

	public String getAirLineName() {
		return airLineName;
	}

	public void setAirLineName(String airLineName) {
		this.airLineName = airLineName;
	}




	public AirLine(long airLineId, String airLineName) {
		super();
		this.airLineId = airLineId;
		this.airLineName = airLineName;
	}

	public AirLine() {
		super();
		// TODO Auto-generated constructor stub
	}




	@Override
	public String toString() {
		return this.airLineId+" : "+this.airLineName;
	}
	
}
