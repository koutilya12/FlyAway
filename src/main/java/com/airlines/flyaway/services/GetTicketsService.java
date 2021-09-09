package com.airlines.flyaway.services;

import com.airlines.flyaway.domain.FlightTicketBooking;
import com.airlines.flyaway.domain.Response;

public interface GetTicketsService {
	/**
	 * Gets successfully booked  ticket details
	 * @param flightTicketBooking
	 * @return Response with status success if tickets booked or else returns error Message.
	 */
	  
	public Response getTicketBookingDetails(FlightTicketBooking flightTicketBooking);
		
}
