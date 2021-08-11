package com.airlines.flyaway.constants;

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
    
			

}
