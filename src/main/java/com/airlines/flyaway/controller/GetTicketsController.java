package com.airlines.flyaway.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.airlines.flyaway.constants.FlyawayConstants;
import com.airlines.flyaway.domain.AirLine;
import com.airlines.flyaway.domain.FlightTicketBooking;
import com.airlines.flyaway.domain.Response;
import com.airlines.flyaway.domain.User;
import com.airlines.flyaway.services.AirLineService;
import com.airlines.flyaway.services.GetTicketsService;
import com.airlines.flyaway.services.impl.AirLineServiceImpl;
import com.airlines.flyaway.services.impl.GetTicketsServiceImpl;

public class GetTicketsController extends HttpServlet {
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GetTicketsService getTicketsService = new GetTicketsServiceImpl();
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("jsp/getTickets.jsp");
		User user = new User();
		user.setUserId(5);
		FlightTicketBooking flightTicketBooking = new FlightTicketBooking();
		flightTicketBooking.setUser(user);
		Response respo = getTicketsService.getTicketBookingDetails(flightTicketBooking);
		if (respo != null) {
			if (respo.getStatus().equals(FlyawayConstants.SUCCESS)) {
				request.setAttribute("ticketdetails", (List<FlightTicketBooking>) respo.getData());
			} else {
				request.setAttribute("errorMessage", respo.getErrorMessage());
			}
		} else {
			request.setAttribute("errorMessage"," Details not found");
		}
		requestDispatcher.forward(request, response);
	}
	
	
}
