package com.airlines.flyaway.helpers;

import java.util.regex.Pattern;

import com.airlines.flyaway.domain.AirLine;
import com.airlines.flyaway.domain.City;
import com.airlines.flyaway.domain.FlightScheduleDetails;
import com.airlines.flyaway.domain.FlightTicketBooking;
import com.airlines.flyaway.domain.User;

public class Validator {

	private Validator() {
	}

	private static final Pattern EMAIL_PATTERN = Pattern.compile("^(.+)@(.+)$");
	private static final Pattern MOBILE_PATTERN = Pattern.compile("^[6-9][0-9]{9}$");

	public static String validateUser(User user) {
		if (user == null) {
			return "User is empty";
		}
		if (user.getUserName() == null || user.getUserName().trim().isEmpty()) {
			return "User Name is Invalid";
		}
		if (user.getMobileNum() == null || user.getMobileNum().trim().isEmpty()
				|| !validateMobileNumber(user.getMobileNum())) {
			return "Invalid Mobile Number";
		}
		if (user.getType() == null) {
			return "Invalid User Type";
		}
		if (user.getuStatus() == null) {
			return "Invalid User Status";
		}
		if (user.getEmailId() == null || user.getEmailId().trim().isEmpty() || !validateEmailId(user.getEmailId())) {
			return "Invalid User Status";
		}
		return null;
	}

	private static boolean validateEmailId(String emailId) {
		return EMAIL_PATTERN.matcher(emailId).matches();
	}

	private static boolean validateMobileNumber(String mobileNum) {
		return MOBILE_PATTERN.matcher(mobileNum).matches();
	}

	public static String validateLoginUser(User user) {
		if (user == null) {
			return "User is empty";
		}
		if (user.getEmailId() == null || user.getEmailId().trim().isEmpty()) {
			return "Email Id Is Invalid";
		}
		if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
			return "Invalid Password";
		}
		return null;
	}

	public static String validateCity(City city) {
		if (city == null) {
			return "City is empty";
		}
		if (city.getCityName() == null || city.getCityName().trim().isEmpty()) {
			return "City name is empty";
		}
		return null;
	}

	public static String validateAirLine(AirLine airLine) {
		if (airLine == null) {
			return "airLine is empty";
		}
		if (airLine.getAirLineName() == null || airLine.getAirLineName().trim().isEmpty()) {
			return "AirLine name is empty";
		}
		return null;
	}

	public static String validateFlightSchedule(FlightScheduleDetails flightScheduleDetails) {
		if (flightScheduleDetails == null) {
			return "Flight details are empty";
		}
		if (flightScheduleDetails.getSource().getCityId() == 0l) {
			return "Source City name is empty";
		}
		if (flightScheduleDetails.getDestination().getCityId() == 01) {
			return "Destination city is empty";
		}
		if (flightScheduleDetails.getAirLineId().getAirLineId() == 0l) {
			return "Airline is empty";
		}
		if (flightScheduleDetails.getPrice() == 0l) {
			return "Price is empty";
		}
		if (flightScheduleDetails.getDepartureTime() == null) {
			return "Departure time is empty";
		}
		if (flightScheduleDetails.getArrivalTime() == null) {
			return "Arrival time is empty";
		}
		if (flightScheduleDetails.getCapacity() == 0) {
			return "Capacity is empty";
		}
		return null;

	}

	public static String validateUserDetails(User user) {
		if ((user.getUserId() == 0l) && (user.getMobileNum() == null || user.getMobileNum().trim().isEmpty())
				&& (user.getEmailId() == null || !validateEmailId(user.getEmailId()))) {
			return "User details empty";
		}
		return null;
	}

	public static String validateFlightTicketBooking(FlightTicketBooking flightTicketBooking) {

		if (flightTicketBooking.getBookingTime() == null) {
			return "Booking time is empty";
		}
		if (flightTicketBooking.getFlight().getFlightId() == 0l) {
			return "flight Id is empty";
		}
		if (flightTicketBooking.getUser().getUserId() == 0l) {
			return "user id is empty";
		}
		if (flightTicketBooking.getPassengers() == null || flightTicketBooking.getPassengers().isEmpty()) {
			return "passengers list empty";
		}
		if (flightTicketBooking.getFlightBookingStatus() == null) {
			return "flight booking status is empty";
		}
		if (flightTicketBooking.getNoOfPersons() == 0
				|| flightTicketBooking.getPassengers().size() != flightTicketBooking.getNoOfPersons()) {
			return "Invalid passengers size";
		}
		return null;
	}

	public static String validateChangePassword(User user) {

		if (user.getUserId() == 0l) {
			return "user id is empty";
		}
		if (user.getPassword() == null) {
			return "password is empty";
		}
		return null;
	}

}
