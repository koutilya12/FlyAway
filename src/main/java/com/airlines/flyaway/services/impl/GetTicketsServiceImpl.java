package com.airlines.flyaway.services.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.airlines.flyaway.constants.FlyawayConstants;
import com.airlines.flyaway.dao.FlyAwayDao;
import com.airlines.flyaway.dao.impl.FlyAwayDaoImp;
import com.airlines.flyaway.domain.FlightTicketBooking;
import com.airlines.flyaway.domain.Response;
import com.airlines.flyaway.domain.User;
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
		String str = "FROM FlightTicketBooking WHERE (0 =:bookingIdFlag OR bookingId =:'" + flightTicketBooking.getBookingId() + "') AND (0 =:userIdFlag OR userId =:'" + flightTicketBooking.getUser().getUserId() + "')";
		@SuppressWarnings("rawtypes")
		Query query = session.createQuery(str);
		query.setParameter("bookingIdFlag", (flightTicketBooking == null || flightTicketBooking.getBookingId() == 0l) ? 0 : 1);
		query.setParameter("userIdFlag", (flightTicketBooking == null || flightTicketBooking.getUser().getUserId() == 0l) ? 0 : 1);

		query.setParameter("bookingId", (flightTicketBooking == null || flightTicketBooking.getBookingId() == 0l) ? null : flightTicketBooking.getBookingId());
		query.setParameter("userId", (flightTicketBooking == null || flightTicketBooking.getUser().getUserId() == 0l) ? null : flightTicketBooking.getUser().getUserId());
		@SuppressWarnings("unchecked")
		List<FlightTicketBooking> list = (List<FlightTicketBooking>) query.list();
		session.close();
		
		if(list != null && !list.isEmpty()) {
			return new Response(FlyawayConstants.SUCCESS,list);
	    }else {
	    	return new Response(FlyawayConstants.FAILED,FlyawayConstants.INVALID_BOOKING_DETAILS);
	    }
	}
}
