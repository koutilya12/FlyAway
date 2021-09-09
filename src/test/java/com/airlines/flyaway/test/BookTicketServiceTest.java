package com.airlines.flyaway.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.airlines.flyaway.constants.FlightBookingStatus;
import com.airlines.flyaway.constants.FlyawayConstants;
import com.airlines.flyaway.constants.GovDocumentType;
import com.airlines.flyaway.constants.PassengerGender;
import com.airlines.flyaway.domain.FlightScheduleDetails;
import com.airlines.flyaway.domain.FlightTicketBooking;
import com.airlines.flyaway.domain.PassengerDetails;
import com.airlines.flyaway.domain.Response;
import com.airlines.flyaway.domain.User;
import com.airlines.flyaway.services.BookTicketService;
import com.airlines.flyaway.services.impl.BookTicketServiceImpl;

@Ignore
public class BookTicketServiceTest {
	
	@Test
	public void saveTicketTest() {
		FlightTicketBooking flightTicketBooking = new FlightTicketBooking();
		BookTicketService bookTicketService = new BookTicketServiceImpl(); 
		Response response = bookTicketService.saveTicket(prepareBookTicketObject(flightTicketBooking));
		Assert.assertTrue(response != null && FlyawayConstants.SUCCESS.equals(response.getStatus()));
		
	}

	private FlightTicketBooking prepareBookTicketObject(FlightTicketBooking flightTicketBooking) {

		Date date = new Date();
		flightTicketBooking.setBookingTime(date);
		FlightScheduleDetails flight = new FlightScheduleDetails();
		flightTicketBooking.setFlight(flight);
		flightTicketBooking.getFlight().setFlightId(4);
		User user = new User();
		flightTicketBooking.setUser(user);
		flightTicketBooking.getUser().setUserId(4);
		flightTicketBooking.setNoOfPersons(1);
		List<PassengerDetails> passengers = new ArrayList();
		PassengerDetails passengerDetails = new PassengerDetails();
		passengers.add(0, passengerDetails);
		passengers.get(0).setAge(23);
		passengers.get(0).setGender(PassengerGender.FEMALE);
		passengers.get(0).setGovDocumentId("33rckff");
		passengers.get(0).setMobileNumber("9907700888");
		passengers.get(0).setPassengerName("kate");
		passengers.get(0).setGovDocumentType(GovDocumentType.AADHAAR);
		flightTicketBooking.setPassengers(passengers);
		flightTicketBooking.setFlightBookingStatus(FlightBookingStatus.CREATED);
		
		return flightTicketBooking;
		
	}

}
