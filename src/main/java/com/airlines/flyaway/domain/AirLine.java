package com.airlines.flyaway.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "air_lines")
public class AirLine implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7997938870084769137L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
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
