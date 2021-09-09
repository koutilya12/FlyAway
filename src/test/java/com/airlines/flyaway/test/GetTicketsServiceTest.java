package com.airlines.flyaway.test;

import org.junit.Assert;
import org.junit.Test;

import com.airlines.flyaway.constants.FlyawayConstants;
import com.airlines.flyaway.domain.FlightTicketBooking;
import com.airlines.flyaway.domain.Response;
import com.airlines.flyaway.domain.User;
import com.airlines.flyaway.services.GetTicketsService;
import com.airlines.flyaway.services.impl.GetTicketsServiceImpl;

public class GetTicketsServiceTest {
	
	@Test
	public void getTicketDetails() {
		FlightTicketBooking flightTicketBooking = new FlightTicketBooking();
		flightTicketBooking.setBookingId(16);
		User user = new User();
		user.setUserId(5);
		flightTicketBooking.setUser(user);
		GetTicketsService getTicketsService = new GetTicketsServiceImpl();
		Response response = getTicketsService.getTicketBookingDetails(flightTicketBooking);
		Assert.assertTrue(response != null && FlyawayConstants.SUCCESS.equals(response.getStatus()));
	}
}
