package com.airlines.flyaway.threads;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


import com.airlines.flyaway.constants.FlightBookingStatus;
import com.airlines.flyaway.domain.FlightTicketBooking;
import com.airlines.flyaway.domain.Response;
import com.airlines.flyaway.services.BookTicketService;
import com.airlines.flyaway.services.GetTicketsService;
import com.airlines.flyaway.services.impl.BookTicketServiceImpl;
import com.airlines.flyaway.services.impl.GetTicketsServiceImpl;

public class WithdrawTicketsThread implements Runnable {


	private GetTicketsService getTicketService;

	private BookTicketService bookTicketService;

	public WithdrawTicketsThread() {
		getTicketService = new GetTicketsServiceImpl();
		bookTicketService = new BookTicketServiceImpl();
	}

	@Override
	public void run() {
		System.out.println("Started withdrwaing tickets at " + new Date()
				+ "of created status which was generated an hour before");
		boolean success = false;
		try {
			updateTickets();
			success = true;
		} catch (Exception e) {
			System.out.println("Unable to perform scheuled task");
			e.printStackTrace();
		} finally {
			System.out.println(" scheduled task completed " + ((success) ? "successfully" : "with errors"));
		}

	}

	@SuppressWarnings("unchecked")
	private void updateTickets() {
		FlightTicketBooking flightTicketBooking = new FlightTicketBooking();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.HOUR, -1);
		flightTicketBooking.setBookingTime(cal.getTime());
		flightTicketBooking.setFlightBookingStatus(FlightBookingStatus.CREATED);
		Response response = getTicketService.getTicketBookingDetails(flightTicketBooking);
		if (response != null && response.getData() != null) {
			List<FlightTicketBooking> list = (List<FlightTicketBooking>) response.getData();
			if(list.isEmpty()) {
				return;
			}
			for (FlightTicketBooking ticket : list) {
				bookTicketService.updateTicket(ticket.getBookingId(), null);
			}
		}

	}

}
