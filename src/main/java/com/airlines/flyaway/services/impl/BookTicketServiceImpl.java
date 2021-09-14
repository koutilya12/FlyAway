package com.airlines.flyaway.services.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.airlines.flyaway.constants.FlightBookingStatus;
import com.airlines.flyaway.constants.FlyawayConstants;
import com.airlines.flyaway.dao.FlyAwayDao;
import com.airlines.flyaway.dao.impl.FlyAwayDaoImp;
import com.airlines.flyaway.domain.FlightScheduleDetails;
import com.airlines.flyaway.domain.FlightScheduleSearchCriteria;
import com.airlines.flyaway.domain.FlightTicketBooking;
import com.airlines.flyaway.domain.FlightTicketBookingLog;
import com.airlines.flyaway.domain.PassengerDetails;
import com.airlines.flyaway.domain.Response;
import com.airlines.flyaway.helpers.Validator;
import com.airlines.flyaway.services.BookTicketService;
import com.airlines.flyaway.services.FlightScheduleService;
import com.airlines.flyaway.services.GetTicketsService;

public class BookTicketServiceImpl implements BookTicketService {

	private FlyAwayDao dao;

	private GetTicketsService getTicketsService;

	public BookTicketServiceImpl() {
		super();
		this.dao = new FlyAwayDaoImp();
		this.getTicketsService = new GetTicketsServiceImpl();
	}

	@Override
	public Response saveTicket(FlightTicketBooking flightTicketBooking) {

		String errorMessage = Validator.validateFlightTicketBooking(flightTicketBooking);
		if (errorMessage != null) {
			return new Response(FlyawayConstants.FAILED, errorMessage);
		}
		errorMessage = validateFilledCapacity(flightTicketBooking);
		if (errorMessage != null) {
			return new Response(FlyawayConstants.FAILED, errorMessage);
		}
		flightTicketBooking.setTotPrice(calculateTotalPrice(flightTicketBooking));

		errorMessage = insertInToBookingTable(flightTicketBooking);
		if (errorMessage != null) {
			return new Response(FlyawayConstants.FAILED, errorMessage);
		}

		if (flightTicketBooking.getBookingId() != 0l) {
			return new Response(FlyawayConstants.SUCCESS);
		} else {
			return new Response(FlyawayConstants.FAILED, FlyawayConstants.DB_ERROR);
		}

	}

	@SuppressWarnings("rawtypes")
	private boolean updateFilledCapacity(FlightTicketBooking flightTicketBooking, Session session) {
		String str = "UPDATE FlightScheduleDetails  SET filledCapacity = (filledCapacity + :updateCapacity) WHERE flightId=:flightId AND (capacity - (filledCapacity + :updateCapacity)) > -1";
		Query query = session.createQuery(str);
		query.setParameter("flightId", flightTicketBooking.getFlight().getFlightId());
		query.setParameter("updateCapacity", flightTicketBooking.getPassengers().size());
		return query.executeUpdate() == 0 ? false : true;
	}

	private String insertInToBookingTable(FlightTicketBooking flightTicketBooking) {
		Session session = this.dao.openSession();
		Transaction tx = session.beginTransaction();
		saveFlightTicketBooking(flightTicketBooking, session);
		if (updateFilledCapacity(flightTicketBooking, session)) {
			tx.commit();
			session.close();
		} else {
			session.close();
			return "insufficient seats";
		}
		return null;
	}

	private void saveFlightTicketBooking(FlightTicketBooking flightTicketBooking, Session session) {
		session.save(flightTicketBooking);
		if (flightTicketBooking.getBookingId() != 0l) {
			for (PassengerDetails passenger : flightTicketBooking.getPassengers()) {
				passenger.setBookingId(flightTicketBooking.getBookingId());
				session.save(passenger);
			}
		}
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

	private String validateFilledCapacity(FlightTicketBooking flightTicketBooking) {
		List<FlightScheduleDetails> flightList = getFlightDetails(flightTicketBooking);
		if (flightList == null || flightList.isEmpty()) {
			return "No flight available against this id";
		}
		if (flightTicketBooking.getPassengers()
				.size() > (flightList.get(0).getCapacity() - flightList.get(0).getFilledCapacity()))
			return "insufficient seats";
		else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response updateTicket(long bookingId, String txnId) {
		FlightBookingStatus status = (txnId == null) ? FlightBookingStatus.WITHDRAWN : FlightBookingStatus.APPROVED;

		FlightTicketBooking flightTicketBooking = new FlightTicketBooking();
		Response response = getTicketsService.getTicketBookingDetails(flightTicketBooking);

		if (!FlyawayConstants.SUCCESS.equals(response.getStatus())) {
			return response;
		}

		FlightTicketBooking prevFlightTicketBooking = ((List<FlightTicketBooking>) response.getData()).get(0);
		Session session = this.dao.openSession();
		Transaction tx = session.beginTransaction();
		insertIntoLog(prevFlightTicketBooking, session);
		int r = updateTicket(txnId, status, session);
		if (FlightBookingStatus.WITHDRAWN.equals(status)) {
			releaseSeats(prevFlightTicketBooking.getFlight().getFlightId(), prevFlightTicketBooking.getNoOfPersons(),
					session);
		}
		tx.commit();
		session.close();
		if (r != 0) {
			return new Response(FlyawayConstants.SUCCESS);
		} else {
			return new Response(FlyawayConstants.FAILED, FlyawayConstants.UNABLE_TO_UPDATE_TICKET);
		}
	}

	private int updateTicket(String txnId, FlightBookingStatus status, Session session) {
		String str = "UPDATE FlightTicketBooking  SET transactionId = IF(:transactionIdFlag;:transactionId,null), flightBookingStatus = :flightBookingStatus WHERE bookingId=:bookingId";
		@SuppressWarnings("rawtypes")
		Query query = session.createQuery(str);
		query.setParameter("transactionId", txnId);
		query.setParameter("flightBookingStatus", status);
		int r = query.executeUpdate();
		return r;
	}

	private void releaseSeats(long flightId, int noOfPersons, Session session) {
		String str = "UPDATE FlightScheduleDetails  SET filledCapacity = (filledCapacity - :updateCapacity) WHERE flightId=:flightId";
		@SuppressWarnings("rawtypes")
		Query query = session.createQuery(str);
		query.setParameter("flightId", flightId);
		query.setParameter("updateCapacity", noOfPersons);
		query.executeUpdate();
	}

	private void insertIntoLog(FlightTicketBooking prevFlightTicketBooking, Session session) {
		FlightTicketBookingLog logData = new FlightTicketBookingLog();
		try {
			BeanUtils.copyProperties(logData, prevFlightTicketBooking);
			session.save(logData);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

}
