package com.airlines.flyaway.helpers;

import com.airlines.flyaway.domain.User;

public class Validator {
	
	
	public static String validateUser(User user) {
        if(user == null) {
        	return "User is empty";
        }
        if(user.getUserName() == null || user.getUserName().isBlank()) {
        	return "User Name is Invalid";
        }
        if(user.getMobileNum() == null || user.getMobileNum().isBlank()) {
        	return "Invalid Mobile Number"; 
        }
        if(user.getType() == null) {
        	return "Invalid User Type";
        }
        if(user.getuStatus() == null) {
        	return "Invalid User Status";
        } 
        return null;
	}

}
