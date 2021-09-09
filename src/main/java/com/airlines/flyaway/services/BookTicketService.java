package com.airlines.flyaway.services;

import com.airlines.flyaway.domain.FlightTicketBooking;
import com.airlines.flyaway.domain.Response;

public interface BookTicketService {
	/**
	 * Save Ticket booking details
	 * @param flightTicketBooking
	 * @return Response with Status success If ticket successfully created or else failed.
	 */
	public Response saveTicket(FlightTicketBooking flightTicketBooking);
	
	/**
	 * 
	 * @param bookingId
	 * @param txnId
	 * @return
	 */

	public Response updateTicket(String bookingId, String txnId);
	
}
