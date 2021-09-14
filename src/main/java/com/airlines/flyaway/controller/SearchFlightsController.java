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

import com.airlines.flyaway.constants.FlyawayConstants;
import com.airlines.flyaway.domain.City;
import com.airlines.flyaway.domain.FlightScheduleDetails;
import com.airlines.flyaway.domain.FlightScheduleSearchCriteria;
import com.airlines.flyaway.domain.Response;
import com.airlines.flyaway.services.FlightScheduleService;
import com.airlines.flyaway.services.impl.FlightScheduleServiceImpl;

public class SearchFlightsController extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4575954159416361148L;

	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("jsp/searchFlights.jsp");
		requestDispatcher.forward(request, response);
	}
	
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FlightScheduleService flightScheduleService = new FlightScheduleServiceImpl();
		FlightScheduleSearchCriteria flightScheduleSearchCriteria = new FlightScheduleSearchCriteria();
		City source = new City();
		source.setCityName(request.getParameter("source"));
		flightScheduleSearchCriteria.setSource(source);
		City destination = new City();
		destination.setCityName(request.getParameter("destination"));
		flightScheduleSearchCriteria.setDestination(destination);	
	
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	
		try {
			flightScheduleSearchCriteria.setDepartureTime(sdf1.parse(request.getParameter("departureTime")));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	
		try {
			flightScheduleSearchCriteria.setArrivalTime(sdf2.parse(request.getParameter("arrivalTime")));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		flightScheduleSearchCriteria.setNoOfPersons(Integer.parseInt(request.getParameter("noOfPersons")));
		//System.out.println("sss"+flightScheduleSearchCriteria);
		List<FlightScheduleDetails> list =  flightScheduleService.searchFlights(flightScheduleSearchCriteria);
		if (list != null && !list.isEmpty()) {
			if (list.get(0) != null) {
				request.setAttribute("flights", list);
			} else {
				Response respo = new Response(FlyawayConstants.AIRLINES_NOT_AVAILABLE);
				request.setAttribute("errorMessage", respo.getErrorMessage());
			}
		} else {
			request.setAttribute("errorMessage", "List not found");
		}
		System.out.println("list"+list);

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("jsp/searchFlights.jsp");
		requestDispatcher.forward(request, response);
	}
			
}
