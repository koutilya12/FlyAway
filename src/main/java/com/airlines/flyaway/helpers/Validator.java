package com.airlines.flyaway.helpers;

import com.airlines.flyaway.domain.AirLine;
import com.airlines.flyaway.domain.City;
import com.airlines.flyaway.domain.FlightScheduleDetails;
import com.airlines.flyaway.domain.FlightTicketBooking;
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

	public static String validateFlightSchedule(FlightScheduleDetails flightScheduleDetails) {
		   if(flightScheduleDetails == null) {
	        	return "Flight details are empty";
	        }
	        if(flightScheduleDetails.getSource().getCityId() == 0l) {
	        	return "Source City name is empty";
	        }
	        if(flightScheduleDetails.getDestination().getCityId() == 01) {
	        	return "Destination city is empty";
	        }
	        if(flightScheduleDetails.getAirLineId().getAirLineId() == 0l) {
	        	return "Airline is empty";
	        }
	        if(flightScheduleDetails.getPrice() == 0l) {
	        	return "Price is empty";
	        }
	        if(flightScheduleDetails.getDepartureTime() == null) {
	        	return "Departure time is empty";
	        }
	        if(flightScheduleDetails.getArrivalTime() == null) {
	        	return "Arrival time is empty";
	        }
	        if(flightScheduleDetails.getCapacity() == 0) {
	        	return "Capacity is empty";
	        }
	        return null;
		
	}

	public static String validateUserDetails(User user) {
		if((user.getUserId() == 0l) || (user.getMobileNum() == null || user.getMobileNum().isBlank())) {
			return "User details empty";
	       }
		return null;
    }

	public static String validateFlightTicketBooking(FlightTicketBooking flightTicketBooking) {
		
		if(flightTicketBooking.getBookingTime() == null) {
			return "Booking time is empty";
		}
		if(flightTicketBooking.getFlight().getFlightId() == 0l) {
			return "flight Id is empty";
		}
		if(flightTicketBooking.getUser().getUserId() == 0l) {
			return "user id is empty";
		}
		if(flightTicketBooking.getPassengers() == null || flightTicketBooking.getPassengers().isEmpty()) {
			return "passengers list empty";
		}
		if(flightTicketBooking.getFlightBookingStatus() == null) {
			return "flight booking status is empty";
		}
		if(flightTicketBooking.getNoOfPersons() == 0  ||flightTicketBooking.getPassengers().size() != flightTicketBooking.getNoOfPersons()) {
			return "Invalid passengers size";
		}
		return null;
	}

}
