package com.airlines.flyaway.services.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.airlines.flyaway.constants.FlyawayConstants;
import com.airlines.flyaway.dao.FlyAwayDao;
import com.airlines.flyaway.dao.impl.FlyAwayDaoImp;
import com.airlines.flyaway.domain.AirLine;
import com.airlines.flyaway.domain.FlightScheduleDetails;
import com.airlines.flyaway.domain.FlightScheduleSearchCriteria;
import com.airlines.flyaway.domain.FlightTicketBooking;
import com.airlines.flyaway.domain.PassengerDetails;
import com.airlines.flyaway.domain.Response;
import com.airlines.flyaway.helpers.Validator;
import com.airlines.flyaway.services.BookTicketService;
import com.airlines.flyaway.services.FlightScheduleService;

public class BookTicketServiceImpl implements BookTicketService{
	
	private FlyAwayDao dao;

	public BookTicketServiceImpl() {
		super();
		this.dao= new FlyAwayDaoImp();
	}
	

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
		
		insertInToBookingTable(flightTicketBooking);
		updateFilledCapacity(flightTicketBooking);
		
		if(flightTicketBooking.getBookingId() != 0l) {
			return new Response(FlyawayConstants.SUCCESS);
	    }else {
	    	return new Response(FlyawayConstants.FAILED,FlyawayConstants.DB_ERROR);
	    }  
	
	}
	
	private void updateFilledCapacity(FlightTicketBooking flightTicketBooking) {
		List<FlightScheduleDetails> flightList =  getFlightDetails(flightTicketBooking);
		flightList.get(0).setFilledCapacity( flightTicketBooking.getPassengers().size() + flightList.get(0).getFilledCapacity());
		
	}


	private void insertInToBookingTable(FlightTicketBooking flightTicketBooking) {
		saveFlightTicketBooking(flightTicketBooking);
		
	}

	private void saveFlightTicketBooking(FlightTicketBooking flightTicketBooking) {
		Session session = this.dao.openSession();
		Transaction tx = session.beginTransaction();		
		session.save(flightTicketBooking);
		if(flightTicketBooking.getBookingId() != 0l) {
			for(PassengerDetails passenger : flightTicketBooking.getPassengers()) {
				passenger.setBookingId(flightTicketBooking.getBookingId());
				session.save(passenger);
			}
		}
		tx.commit();	
		session.close();
		
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
