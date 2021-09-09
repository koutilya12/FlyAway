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
import com.airlines.flyaway.services.BookTicketService;
import com.airlines.flyaway.services.FlightScheduleService;
import com.airlines.flyaway.services.impl.BookTicketServiceImpl;
import com.airlines.flyaway.services.impl.FlightScheduleServiceImpl;

public class ProcessPaymentsController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8495913287532805311L;
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookTicketService bookTicketService = new BookTicketServiceImpl();
		String bookingId = request.getParameter("bookingId");
		String txnId = request.getParameter("transId");
		Response reponse =  bookTicketService.updateTicket(bookingId,txnId);
		if (response != null) {
			if (FlyawayConstants.SUCCESS.equals(reponse.getStatus())) {
				request.setAttribute("successMessage", FlyawayConstants.SUCCESS);
			} else {
				request.setAttribute("errorMessage", reponse.getErrorMessage());
			}
		} else {
			request.setAttribute("errorMessage", "Unable to update ticket");
		}
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("jsp/getTickets.jsp");
		requestDispatcher.forward(request, response);
	}
}
