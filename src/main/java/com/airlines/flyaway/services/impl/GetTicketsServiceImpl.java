package com.airlines.flyaway.services.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.airlines.flyaway.constants.FlyawayConstants;
import com.airlines.flyaway.dao.FlyAwayDao;
import com.airlines.flyaway.dao.impl.FlyAwayDaoImp;
import com.airlines.flyaway.domain.FlightTicketBooking;
import com.airlines.flyaway.domain.Response;
import com.airlines.flyaway.services.GetTicketsService;

public class GetTicketsServiceImpl implements GetTicketsService{
	
    private FlyAwayDao dao;

	public GetTicketsServiceImpl() {
		super();
		this.dao = new FlyAwayDaoImp();
	}

	@Override
	public Response getTicketBookingDetails(FlightTicketBooking flightTicketBooking) {
		Session session = this.dao.openSession();
		try {
			String str = "FROM FlightTicketBooking WHERE (0 =:bookingIdFlag OR bookingId =:bookingId) AND (0 =:userIdFlag OR userId =:userId) AND (0 =:statusFlag OR flightBookingStatus = :flightBookingStatus) AND (0 =:bookingTimeFlag OR bookingTime <= :bookingTime)";
			@SuppressWarnings("rawtypes")
			Query query = session.createQuery(str);
			prepareSearchQuery(flightTicketBooking, query);
			@SuppressWarnings("unchecked")
			List<FlightTicketBooking> list = (List<FlightTicketBooking>) query.list();
			session.close();
			if(list != null) {
				return new Response(FlyawayConstants.SUCCESS,list);
		    }else {
		    	return new Response(FlyawayConstants.FAILED,FlyawayConstants.INVALID_BOOKING_DETAILS);
		    }
		}catch(Exception e) {
			e.printStackTrace();
			session.close();
	    	return new Response(FlyawayConstants.FAILED,"Unable to get ticket details ");
		}
		

	}

	private void prepareSearchQuery(FlightTicketBooking flightTicketBooking, @SuppressWarnings("rawtypes") Query query) {
		query.setParameter("bookingIdFlag", (flightTicketBooking == null || flightTicketBooking.getBookingId() == 0l) ? 0 : 1);
		query.setParameter("statusFlag", (flightTicketBooking == null || flightTicketBooking.getFlightBookingStatus() == null) ? 0 : 1);
		query.setParameter("bookingTimeFlag", (flightTicketBooking == null || flightTicketBooking.getBookingTime() == null) ? 0 : 1);
		query.setParameter("userIdFlag", (flightTicketBooking == null || flightTicketBooking.getUser() == null || flightTicketBooking.getUser().getUserId() == 0l) ? 0 : 1);

		query.setParameter("bookingId", (flightTicketBooking == null || flightTicketBooking.getBookingId() == 0l) ? null : flightTicketBooking.getBookingId());
		query.setParameter("userId", (flightTicketBooking == null || flightTicketBooking.getUser() == null || flightTicketBooking.getUser().getUserId() == 0l) ? null : flightTicketBooking.getUser().getUserId());
		query.setParameter("flightBookingStatus", (flightTicketBooking == null || flightTicketBooking.getFlightBookingStatus() == null) ? null : flightTicketBooking.getFlightBookingStatus());
		query.setParameter("bookingTime", (flightTicketBooking == null || flightTicketBooking.getBookingTime() == null) ? null : flightTicketBooking.getBookingTime());
	}
}
