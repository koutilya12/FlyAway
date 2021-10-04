package com.airlines.flyaway.test;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.airlines.flyaway.constants.FlightBookingStatus;
import com.airlines.flyaway.constants.FlyawayConstants;
import com.airlines.flyaway.domain.FlightTicketBooking;
import com.airlines.flyaway.domain.Response;
import com.airlines.flyaway.domain.User;
import com.airlines.flyaway.services.GetTicketsService;
import com.airlines.flyaway.services.impl.GetTicketsServiceImpl;

//@Ignore
public class GetTicketsServiceTest {
	
	@Ignore
	@Test
	public void getTicketDetails() {
		FlightTicketBooking flightTicketBooking = new FlightTicketBooking();
		flightTicketBooking.setBookingId(26);
		User user = new User();
		user.setUserId(5);
		flightTicketBooking.setUser(user);
		GetTicketsService getTicketsService = new GetTicketsServiceImpl();
		Response response = getTicketsService.getTicketBookingDetails(flightTicketBooking);
		Assert.assertTrue(response != null && FlyawayConstants.SUCCESS.equals(response.getStatus()));
	}
	
	//@Ignore
	@Test
	public void getTicketDetailsForCreated() {
		FlightTicketBooking flightTicketBooking = new FlightTicketBooking();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.HOUR, -1);
		flightTicketBooking.setBookingTime(cal.getTime());
		flightTicketBooking.setFlightBookingStatus(FlightBookingStatus.CREATED);
		GetTicketsService getTicketsService = new GetTicketsServiceImpl();
		Response response = getTicketsService.getTicketBookingDetails(flightTicketBooking);
		System.out.println(response);
		Assert.assertTrue(response != null && FlyawayConstants.SUCCESS.equals(response.getStatus()));
	}
}
