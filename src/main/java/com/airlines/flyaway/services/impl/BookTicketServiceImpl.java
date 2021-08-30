package com.airlines.flyaway.services.impl;

import java.util.List;

import com.airlines.flyaway.constants.FlyawayConstants;
import com.airlines.flyaway.domain.FlightScheduleDetails;
import com.airlines.flyaway.domain.FlightScheduleSearchCriteria;
import com.airlines.flyaway.domain.FlightTicketBooking;
import com.airlines.flyaway.domain.PassengerDetails;
import com.airlines.flyaway.domain.Response;
import com.airlines.flyaway.helpers.Validator;
import com.airlines.flyaway.services.BookTicketService;
import com.airlines.flyaway.services.FlightScheduleService;

public class BookTicketServiceImpl implements BookTicketService{

	@Override
	public Response saveTicket(FlightTicketBooking flightTicketBooking) {
		
		String errorMessage = Validator.validateFlightTicketBooking(flightTicketBooking);
		if(errorMessage != null) {
			return new Response(FlyawayConstants.FAILED, errorMessage);	
		}
		errorMessage = validateFilledCapacity(flightTicketBooking);
		if(errorMessage != null) {
			return new Response(FlyawayConstants.FAILED, errorMessage);	
		}
		flightTicketBooking.setTotPrice(calculateTotalPrice(flightTicketBooking));

		
		return null;
	}

	private Double calculateTotalPrice(FlightTicketBooking flightTicketBooking) {
		
		return flightTicketBooking.getPassengers().size() * getFlightDetails(flightTicketBooking).get(0).getPrice();
	}

	private List<FlightScheduleDetails> getFlightDetails(FlightTicketBooking flightTicketBooking) {
		FlightScheduleService flightScheduleService = new FlightScheduleServiceImpl();
		FlightScheduleSearchCriteria flightScheduleSearchCriteria = new FlightScheduleSearchCriteria();
		flightScheduleSearchCriteria.setFlightId(flightTicketBooking.getFlight().getFlightId());
		List<FlightScheduleDetails> list = flightScheduleService.searchFlights(flightScheduleSearchCriteria);	
		return list;
	}

	private String validateFilledCapacity(FlightTicketBooking flightTicketBooking ) {
		List<FlightScheduleDetails> flightList =  getFlightDetails(flightTicketBooking);
		if(flightList == null || flightList.isEmpty()) {
			return "No flight available against this id";
		}
		if(flightTicketBooking.getPassengers().size() > (flightList.get(0).getCapacity() - flightList.get(0).getFilledCapacity()))
		return "insufficient seats";
		else { 
		return null;
		}
	}

}
