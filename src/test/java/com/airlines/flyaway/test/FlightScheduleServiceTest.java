package com.airlines.flyaway.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.airlines.flyaway.constants.FlightStatus;
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

@Ignore
public class FlightScheduleServiceTest {
	
	@Test
	public void insertFlightScheduleDetails() {
		FlightScheduleDetails flightScheduleDetails = prepareFlightscheduleDetailsObject();
		FlightScheduleService flightScheduleService = new FlightScheduleServiceImpl();
		Response response = flightScheduleService.insertFlight(flightScheduleDetails);
		Assert.assertTrue(response != null && FlyawayConstants.SUCCESS.equals(response.getStatus()));

	}

	
	private FlightScheduleDetails prepareFlightscheduleDetailsObject() {
		FlightScheduleDetails flightScheduleDetails = new FlightScheduleDetails();
//		flightScheduleDetails.setFlightId(8);
		City source = new City();
		flightScheduleDetails.setSource(source);
        flightScheduleDetails.getSource().setCityId(4);
        City destination = new City();
		flightScheduleDetails.setDestination(destination);
		flightScheduleDetails.getDestination().setCityId(20);
		AirLine airLine = new AirLine();
		flightScheduleDetails.setAirLineId(airLine);
		flightScheduleDetails.getAirLineId().setAirLineId(25);
		flightScheduleDetails.setPrice(7500);
		
		String sd1 = "2021-08-31 11:40:00";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			flightScheduleDetails.setDepartureTime(sdf.parse(sd1));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		String sd2 = "2021-09-04 16:35:00";	
		try {
			flightScheduleDetails.setArrivalTime(sdf.parse(sd2));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		flightScheduleDetails.setCapacity(178);
		flightScheduleDetails.setFilledCapacity(124);
		flightScheduleDetails.setStatus(FlightStatus.ACTIVE);
		return flightScheduleDetails;
	}


	//@Test
	public void getFlightScheduleWithNullValues() {
	FlightScheduleSearchCriteria searchCriteria = prepareFlightNullScheduleObject();
	FlightScheduleService flightScheduleService = new FlightScheduleServiceImpl();
	System.out.println(flightScheduleService.searchFlights(searchCriteria).size());
	
	}
	
	private FlightScheduleSearchCriteria prepareFlightNullScheduleObject() {
		
		FlightScheduleSearchCriteria flightScheduleSearchCriteria = new FlightScheduleSearchCriteria();
		return flightScheduleSearchCriteria;
	}

	//@Test
	public void getFlightScheduleWithSource() {
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
	public void getFlightScheduleWithDestination() {
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

	//@Test
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
