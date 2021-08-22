package com.airlines.flyaway.constants;

import java.util.stream.Stream;

public enum UserStatus {
	
	ACTIVE("A"),
    INACTIVE("I");
	
	private String value;
	
	UserStatus(String string) {
		this.value = string;
	}
	
	public String getValue() {
		return value;
	}
	
	public static void main(String[] args) {
		
		UserStatus userStatus = UserStatus.ACTIVE;
		System.out.println(userStatus.value);
    
	}
	
	public static UserStatus getStatus(String value) {
		return Stream.of(UserStatus.values()).filter(c -> c.getValue().equals(value)).findFirst().orElse(null);
	}
    
}
