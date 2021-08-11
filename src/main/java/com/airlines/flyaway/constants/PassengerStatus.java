package com.airlines.flyaway.constants;

public enum PassengerStatus {
	
	CONFIRMED("C"),
	WITHDRAWN("W");

	private String value;
	
	PassengerStatus(String string) {
		this.value = string;
	}
	
	public String getValue() {
		return value;
	}
	
	public static void main(String[] args) {
		PassengerStatus passengerStatus = PassengerStatus.CONFIRMED;
		System.out.println(passengerStatus.value);
	}
	

}
