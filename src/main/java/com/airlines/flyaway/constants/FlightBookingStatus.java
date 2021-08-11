package com.airlines.flyaway.constants;

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
	

}
