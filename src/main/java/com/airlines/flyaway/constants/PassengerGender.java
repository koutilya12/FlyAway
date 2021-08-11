package com.airlines.flyaway.constants;

public enum PassengerGender {
	
	
	MALE ("M"),
	FEMALE ("F"),
	OTHERS ("O");
	
	private String value;

	PassengerGender(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
