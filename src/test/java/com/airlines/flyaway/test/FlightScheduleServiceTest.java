package com.airlines.flyaway.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.airlines.flyaway.constants.FlyawayConstants;
import com.airlines.flyaway.domain.AirLine;
import com.airlines.flyaway.domain.City;
import com.airlines.flyaway.domain.FlightScheduleDetails;
import com.airlines.flyaway.domain.FlightScheduleSearchCriteria;
import com.airlines.flyaway.domain.Response;
import com.airlines.flyaway.services.AirLineService;
import com.airlines.flyaway.services.FlightScheduleService;
import com.airlines.flyaway.services.impl.AirLineServiceImpl;
import com.airlines.flyaway.services.impl.FlightScheduleServiceImpl;

public class FlightScheduleServiceTest {
	

	
	//@Test
	public void getFlighScheduleWithNullValues() {
	FlightScheduleSearchCriteria searchCriteria = prepareFlightNullScheduleObject();
	FlightScheduleService flightScheduleService = new FlightScheduleServiceImpl();
	System.out.println(flightScheduleService.searchFlights(searchCriteria).size());
	
	}
	
	private FlightScheduleSearchCriteria prepareFlightNullScheduleObject() {
		
		FlightScheduleSearchCriteria flightScheduleSearchCriteria = new FlightScheduleSearchCriteria();

		return flightScheduleSearchCriteria;
	}

	//@Test
	public void getFlighScheduleWithSource() {
	FlightScheduleSearchCriteria searchCriteria = prepareFlightSourceScheduleObject();
	FlightScheduleServiceImpl flightScheduleServiceImpl = new FlightScheduleServiceImpl();
	System.out.println(flightScheduleServiceImpl.searchFlights(searchCriteria).size());
	
	}
	
	private FlightScheduleSearchCriteria prepareFlightSourceScheduleObject() {
		
		FlightScheduleSearchCriteria flightScheduleSearchCriteria = new FlightScheduleSearchCriteria();
		City source = new City();
		flightScheduleSearchCriteria.setSource(source);
		flightScheduleSearchCriteria.getSource().setCityName("Chennai");
		Date date = new Date();
		flightScheduleSearchCriteria.setDepartureTime(date);
		flightScheduleSearchCriteria.setArrivalTime(date);
		flightScheduleSearchCriteria.setNoOfPersons(4);

		return flightScheduleSearchCriteria;
	}
	
	//@Test
	public void getFlighScheduleWithDestination() {
	FlightScheduleSearchCriteria searchCriteria = prepareFlightDestinationScheduleObject();
	FlightScheduleServiceImpl flightScheduleServiceImpl = new FlightScheduleServiceImpl();
	System.out.println(flightScheduleServiceImpl.searchFlights(searchCriteria).size());
	
	}
	
	private FlightScheduleSearchCriteria prepareFlightDestinationScheduleObject() {
		FlightScheduleSearchCriteria flightScheduleSearchCriteria = new FlightScheduleSearchCriteria();
		City destination = new City();
		flightScheduleSearchCriteria.setDestination(destination);
		flightScheduleSearchCriteria.getDestination().setCityName("Bangalore");
		Date date = new Date();
		flightScheduleSearchCriteria.setDepartureTime(date);
		flightScheduleSearchCriteria.setArrivalTime(date);
		flightScheduleSearchCriteria.setNoOfPersons(3);

		return flightScheduleSearchCriteria;

	}

	@Test
	public void getFlightScheduleWithSourceAndDestination() {
	FlightScheduleSearchCriteria searchCriteria = prepareFlightAllScheduleObject();
	FlightScheduleServiceImpl flightScheduleServiceImpl = new FlightScheduleServiceImpl();
	System.out.println(flightScheduleServiceImpl.searchFlights(searchCriteria));
	
	}

	private FlightScheduleSearchCriteria prepareFlightAllScheduleObject() {
		FlightScheduleSearchCriteria flightScheduleSearchCriteria = new FlightScheduleSearchCriteria();
		City source = new City();
		City destination = new City();
		//flightScheduleSearchCriteria.setSource(source);
		//flightScheduleSearchCriteria.setDestination(destination);
		//flightScheduleSearchCriteria.getSource().setCityName("Chennai");
		//flightScheduleSearchCriteria.getDestination().setCityName("Bangalore");
		
		String sd1 = "2021-08-29 18:30:00";
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		try {
//			flightScheduleSearchCriteria.setDepartureTime(sdf1.parse(sd1));
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		String sd2 = "2021-09-03 09:45:00";
		try {
			flightScheduleSearchCriteria.setArrivalTime(sdf1.parse(sd2));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(flightScheduleSearchCriteria.toString());
		
		//flightScheduleSearchCriteria.setNoOfPersons(4);

		return flightScheduleSearchCriteria;

	}
	
	

}
