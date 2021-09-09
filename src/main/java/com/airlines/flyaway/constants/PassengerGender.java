package com.airlines.flyaway.constants;

import java.util.stream.Stream;

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
	
	public static PassengerGender getGender(String value) {
		return Stream.of(PassengerGender.values()).filter(c -> c.getValue().equals(value)).findFirst().orElse(null);
	}

}
