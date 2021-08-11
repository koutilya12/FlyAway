package com.airlines.flyaway.constants;

import java.util.stream.Stream;

public enum FlightStatus {

	ACTIVE("A"), INACTIVE("I");

	private String value;

	FlightStatus(String string) {
		this.value = string;
	}

	public String getValue() {
		return value;
	}

	public static void main(String[] args) {

		FlightStatus flightStatus = FlightStatus.ACTIVE;
		System.out.println(flightStatus.value);
	}

	public static FlightStatus getStatus(String value) {
		return Stream.of(FlightStatus.values()).filter(c -> c.getValue().equals(value)).findFirst().orElse(null);
	}

}
