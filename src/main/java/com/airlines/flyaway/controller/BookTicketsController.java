package com.airlines.flyaway.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.airlines.flyaway.constants.FlightBookingStatus;
import com.airlines.flyaway.constants.FlyawayConstants;
import com.airlines.flyaway.constants.GovDocumentType;
import com.airlines.flyaway.constants.PassengerGender;
import com.airlines.flyaway.domain.FlightScheduleDetails;
import com.airlines.flyaway.domain.FlightTicketBooking;
import com.airlines.flyaway.domain.PassengerDetails;
import com.airlines.flyaway.domain.Response;
import com.airlines.flyaway.domain.User;
import com.airlines.flyaway.services.BookTicketService;
import com.airlines.flyaway.services.impl.BookTicketServiceImpl;

public class BookTicketsController extends HttpServlet {
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		BookTicketService bookTicketService = new BookTicketServiceImpl();
		FlightTicketBooking flightTicketBooking = prepareFlightTicketBookingObject(request);
		Response respo = bookTicketService.saveTicket(flightTicketBooking);
		if (respo != null) {
			if (respo.getStatus().equals(FlyawayConstants.SUCCESS)) {
				request.setAttribute("successMessage", "Ticket(s) successfully booked");
				request.setAttribute("bookingId",flightTicketBooking.getBookingId());
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("jsp/dummyBankPayment.jsp");
				requestDispatcher.forward(request, response);
			} else {
				request.setAttribute("errorMessage", respo.getErrorMessage());
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("jsp/getTickets.jsp");
				requestDispatcher.forward(request, response);
			}
		} else {
			request.setAttribute("errorMessage","Unable to book ticket(s)");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("jsp/getTickets.jsp");
			requestDispatcher.forward(request, response);
		}
	}

	private FlightTicketBooking prepareFlightTicketBookingObject(HttpServletRequest request) {
		FlightTicketBooking flightTicketBooking = new FlightTicketBooking();
		Date date = new Date();
		flightTicketBooking.setBookingTime(date);
		FlightScheduleDetails flightScheduleDetails = new FlightScheduleDetails();
		flightScheduleDetails.setFlightId(Long.parseLong(request.getParameter("flightId")));
		flightTicketBooking.setFlight(flightScheduleDetails);
		User user = getUserFromSesion(request);
		flightTicketBooking.setUser(user);
		List<PassengerDetails> list = preparePassengerDetailsList(request);
		flightTicketBooking.setNoOfPersons(Integer.parseInt(request.getParameter("noOfPersons")));
		flightTicketBooking.setPassengers(list);
		flightTicketBooking.setFlightBookingStatus(FlightBookingStatus.CREATED);
		
		return flightTicketBooking;
	}

	private User getUserFromSesion(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Object user = session.getAttribute(FlyawayConstants.USER_SESSION_OBJECT);
		return user != null ? (User ) user : null;
	}

	private List<PassengerDetails> preparePassengerDetailsList(HttpServletRequest request) {
		List<PassengerDetails> list = new ArrayList<>();
		for(int i=0; i < Integer.parseInt(request.getParameter("noOfPersons")); i++) {
			PassengerDetails passengerDetails = new PassengerDetails();
			passengerDetails.setPassengerName(request.getParameter("passengerName-"+i));
			passengerDetails.setGender(PassengerGender.getGender(request.getParameter("gender-"+i)));
			passengerDetails.setAge(Integer.parseInt(request.getParameter("age-"+i)));
			passengerDetails.setMobileNumber(request.getParameter("mobileNumber-"+i));
			passengerDetails.setGovDocumentType(GovDocumentType.getGovType(request.getParameter("govDocumentType-"+i)));
			passengerDetails.setGovDocumentId(request.getParameter("govDocumentId-"+i));
		    list.add(passengerDetails);	
		}
		return list;
	}
	
}
