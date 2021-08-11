package com.airlines.flyaway.constants;

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
    
}
