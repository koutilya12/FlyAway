package com.airlines.flyaway.constants;

import java.util.stream.Stream;

public enum FlightBookingStatus {
	
	CREATED ("C"),
	WITHDRAWN ("W"),
	APPROVED("A");
	

	private String value;
	
	FlightBookingStatus(String value) {
		this.value = value;
	}


	public String getValue() {
		return value;
	}
	
	
	public static void main(String[] args) {
		
		FlightBookingStatus fbstatus = FlightBookingStatus.CREATED; 

		System.out.println(fbstatus.getValue());
		
	}
	
	public static FlightBookingStatus getStatus(String value) {
		return Stream.of(FlightBookingStatus.values()).filter(c -> c.getValue().equals(value)).findFirst().orElse(null);
	}

	

}
