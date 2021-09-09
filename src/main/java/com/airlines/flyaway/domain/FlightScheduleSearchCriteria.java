package com.airlines.flyaway.domain;

import java.util.Date;

public class FlightScheduleSearchCriteria {
	
	private City source;
	private City destination;
	private Date departureTime;
	private Date arrivalTime;
	private int noOfPersons;
	private long flightId;
	
	
	
	public City getSource() {
		return source;
	}



	public void setSource(City source) {
		this.source = source;
	}



	public City getDestination() {
		return destination;
	}



	public void setDestination(City destination) {
		this.destination = destination;
	}



	public Date getDepartureTime() {
		return departureTime;
	}



	public void setDepartureTime(Date departureTime) {
		this.departureTime = departureTime;
	}



	public Date getArrivalTime() {
		return arrivalTime;
	}



	public void setArrivalTime(Date arrivalTime) {
		this.arrivalTime = arrivalTime;
	}



	public int getNoOfPersons() {
		return noOfPersons;
	}



	public void setNoOfPersons(int noOfPersons) {
		this.noOfPersons = noOfPersons;
	}



	public long getFlightId() {
		return flightId;
	}



	public void setFlightId(long flightId) {
		this.flightId = flightId;
	}



	@Override
	public String toString() {
		return "FlightScheduleSearchCriteria [source=" + source + ", destination=" + destination + ", departureTime="
				+ departureTime + ", arrivalTime=" + arrivalTime + ", noOfPersons=" + noOfPersons + ", flightId="
				+ flightId + "]";
	}
	

}
