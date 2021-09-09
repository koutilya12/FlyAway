package com.airlines.flyaway.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.airlines.flyaway.constants.FlightStatus;
import com.airlines.flyaway.constants.FlyawayConstants;
import com.airlines.flyaway.domain.AirLine;
import com.airlines.flyaway.domain.City;
import com.airlines.flyaway.domain.FlightScheduleDetails;
import com.airlines.flyaway.domain.FlightScheduleSearchCriteria;
import com.airlines.flyaway.domain.Response;
import com.airlines.flyaway.services.AirLineService;
import com.airlines.flyaway.services.CitiesService;
import com.airlines.flyaway.services.FlightScheduleService;
import com.airlines.flyaway.services.impl.AirLineServiceImpl;
import com.airlines.flyaway.services.impl.CitiesServiceImpl;
import com.airlines.flyaway.services.impl.FlightScheduleServiceImpl;

public class FlightScheduleController extends HttpServlet {
	
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FlightScheduleService flightScheduleService = new FlightScheduleServiceImpl();
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("jsp/flightschedule.jsp");
		List<FlightScheduleDetails> list =  flightScheduleService.searchFlights(null);
		if (list != null) {
			if (list.get(0) != null) {
				request.setAttribute("flights", list);
			} else {
				Response respo = new Response(FlyawayConstants.AIRLINES_NOT_AVAILABLE);
				request.setAttribute("errorMessage", respo.getErrorMessage());
			}
		} else {
			request.setAttribute("errorMessage", "List not found");
		}
		requestDispatcher.forward(request, response);
	}
	
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FlightScheduleService flightScheduleService = new FlightScheduleServiceImpl();
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("jsp/flightschedule.jsp");
		FlightScheduleDetails flightScheduleDetails = new FlightScheduleDetails();
		
		City source = new City();
		source.setCityName(request.getParameter("source"));
		CitiesService citiesService = new CitiesServiceImpl();
		Response respo = citiesService.getCity(source); 
		if( respo != null && respo.getStatus().equals(FlyawayConstants.SUCCESS)) {
			List<City> list = (List<City>) respo.getData();
			flightScheduleDetails.setSource(list.get(0)); 
		} else {
			request.setAttribute("errorMessage","Given Source City is not found");
		}
		
		City destination = new City();
		destination.setCityName(request.getParameter("destination"));
		respo = citiesService.getCity(destination);
		if( respo != null && respo.getStatus().equals(FlyawayConstants.SUCCESS)) {
			List<City> list = (List<City>) respo.getData();
			flightScheduleDetails.setDestination(list.get(0)); 
		} else {
			request.setAttribute("errorMessage","Given Destination City is not found");
		}
	
		AirLine airLine = new AirLine();
		airLine.setAirLineName(request.getParameter("airLineName"));
		AirLineService airLineService= new AirLineServiceImpl();
		respo = airLineService.getAirLine(airLine);
		if( respo != null && respo.getStatus().equals(FlyawayConstants.SUCCESS)) {
			List<AirLine> list = (List<AirLine>) respo.getData();
			flightScheduleDetails.setAirLineId(list.get(0));
		}
		
		flightScheduleDetails.setPrice(Long.parseLong(request.getParameter("price")));
		
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	
		try {
			flightScheduleDetails.setDepartureTime(sdf1.parse(request.getParameter("departureTime")));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
	    sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	
		try {
			flightScheduleDetails.setArrivalTime(sdf1.parse(request.getParameter("arrivalTime")));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		flightScheduleDetails.setCapacity(Integer.parseInt(request.getParameter("capacity")));
		flightScheduleDetails.setFilledCapacity(Integer.parseInt(request.getParameter("filledCapacity")));
		flightScheduleDetails.setStatus(FlightStatus.ACTIVE);
		
		respo = flightScheduleService.insertFlight(flightScheduleDetails);
		if (respo != null) {
			if (respo.getStatus().equals(FlyawayConstants.SUCCESS)) {
					request.setAttribute("successMessage", "Sucessfully Inserted");
				} else {
					request.setAttribute("errorMessage", respo.getErrorMessage());
				}
			} else {
				request.setAttribute("errorMessage","Unable to Insert Flight schedule details");
			}
		doGet(request, response);
	  
    }
}
