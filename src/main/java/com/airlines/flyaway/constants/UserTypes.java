package com.airlines.flyaway.constants;

import java.util.stream.Stream;

public enum UserTypes {
	
	ADMIN("A"),
	CUSTOMER("C");
	
	private String value;
	
	UserTypes(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
    public static void main(String[] args)  {
    	
	UserTypes userType = UserTypes.CUSTOMER;
	
    System.out.println(userType.value);
    
 }
	public static UserTypes getStatus(String value) {
		return Stream.of(UserTypes.values()).filter(c -> c.getValue().equals(value)).findFirst().orElse(null);
	}
    
			

}
