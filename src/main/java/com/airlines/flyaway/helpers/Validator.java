package com.airlines.flyaway.helpers;

import com.airlines.flyaway.domain.AirLine;
import com.airlines.flyaway.domain.City;
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

	public static String validateLoginUser(User user) {
        if(user == null) {
        	return "User is empty";
        }
        if(user.getEmailId() == null || user.getEmailId().isBlank()) {
        	return "Email Id Is Invalid";
        }
        if(user.getPassword() == null || user.getPassword().isBlank()) {
        	return "Invalid Password"; 
        }
        return null;
	}

	public static String validateCity(City city) {
        if(city == null) {
        	return "City is empty";
        }
        if(city.getCityName() == null || city.getCityName().isBlank()) {
        	return "City name is empty";
        }
        return null;
	}

	public static String validateAirLine(AirLine airLine) {
		 if(airLine == null) {
	        	return "airLine is empty";
	        }
	        if(airLine.getAirLineName() == null || airLine.getAirLineName().isBlank()) {
	        	return "AirLine name is empty";
	        }
		return null;
	}

}
