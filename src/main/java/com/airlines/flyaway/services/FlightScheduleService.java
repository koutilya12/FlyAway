package com.airlines.flyaway.services;

import java.util.List;

import com.airlines.flyaway.domain.FlightScheduleDetails;
import com.airlines.flyaway.domain.FlightScheduleSearchCriteria;
import com.airlines.flyaway.domain.Response;

public interface FlightScheduleService {
	/**
	 * Performs Insertion of Flights.
	 * @param flightScheduleDetails
	 * @return Response with Status success If successfully inserted or else Insertion failed.
	 */
	public Response insertFlight(FlightScheduleDetails flightScheduleDetails);	
	/**
	 * Performs search for the required Flight.
	 * @param flightScheduleSearchCriteria
	 * @return Status success If search successful or else not found. 
	 */
	public List<FlightScheduleDetails> searchFlights(FlightScheduleSearchCriteria flightScheduleSearchCriteria);

}
