package com.airlines.flyaway.services.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.airlines.flyaway.constants.FlyawayConstants;
import com.airlines.flyaway.dao.FlyAwayDao;
import com.airlines.flyaway.dao.impl.FlyAwayDaoImp;
import com.airlines.flyaway.domain.AirLine;
import com.airlines.flyaway.domain.City;
import com.airlines.flyaway.domain.FlightScheduleDetails;
import com.airlines.flyaway.domain.FlightScheduleSearchCriteria;
import com.airlines.flyaway.domain.Response;
import com.airlines.flyaway.helpers.Validator;
import com.airlines.flyaway.services.FlightScheduleService;

public class FlightScheduleServiceImpl implements FlightScheduleService {
	
	private FlyAwayDao dao;

	public FlightScheduleServiceImpl() {
		super();
		this.dao= new FlyAwayDaoImp();
	}

	@Override
	public Response insertFlight(FlightScheduleDetails flightScheduleDetails) {
		String errorMessage = Validator.validateFlightSchedule(flightScheduleDetails);
		if(errorMessage != null) {
			return new Response(FlyawayConstants.FAILED, errorMessage);
			
		}
		
		saveFlightScheduleDetails(flightScheduleDetails);
		if(flightScheduleDetails.getFlightId() != 0l) {
			return new Response(FlyawayConstants.SUCCESS);
	    }else {
	    	return new Response(FlyawayConstants.FAILED,FlyawayConstants.DB_ERROR);
	    }  
	}

	private void saveFlightScheduleDetails(FlightScheduleDetails flightScheduleDetails) {
		Session session = this.dao.openSession();
		Transaction tx = session.beginTransaction();		
		session.save(flightScheduleDetails);		
		tx.commit();	
		session.close();
	}

	@Override
	public List<FlightScheduleDetails> searchFlights(FlightScheduleSearchCriteria flightScheduleSearchCriteria) {
		Session session = this.dao.openSession();	    		
		String str = "select f FROM FlightScheduleDetails f  INNER JOIN f.source s INNER JOIN f.destination d  WHERE (0 =:flightIdFlag OR f.flightId =:flightId) AND (0=:sourceFlag OR  s.cityName = :source) AND (0=:destinationFlag OR d.cityName = :destination) AND (0= :departureFlag OR (departureTime BETWEEN :fromDepartureTime AND :toDepartureTime)) AND (0= :arrivalFlag OR (arrivalTime BETWEEN :fromArrivalTime AND :toArrivalTime))";
		
		@SuppressWarnings("unchecked")
		Query<FlightScheduleDetails> query = session.createQuery(str);
		query.setParameter("flightIdFlag", (flightScheduleSearchCriteria == null || flightScheduleSearchCriteria.getFlightId() == 0l) ? 0 : 1);
		query.setParameter("sourceFlag", (flightScheduleSearchCriteria == null || flightScheduleSearchCriteria.getSource() == null) ? 0 : 1);
		query.setParameter("destinationFlag", (flightScheduleSearchCriteria == null || flightScheduleSearchCriteria.getDestination() == null) ? 0 : 1);
		query.setParameter("departureFlag", (flightScheduleSearchCriteria == null || flightScheduleSearchCriteria.getDepartureTime() == null) ? 0 : 1);
		query.setParameter("arrivalFlag", (flightScheduleSearchCriteria == null || flightScheduleSearchCriteria.getArrivalTime() == null) ? 0 : 1);

		query.setParameter("flightId", (flightScheduleSearchCriteria == null || flightScheduleSearchCriteria.getFlightId() == 0l) ? null : flightScheduleSearchCriteria.getFlightId());
		query.setParameter("source",  (flightScheduleSearchCriteria == null || flightScheduleSearchCriteria.getSource() == null) ? null : flightScheduleSearchCriteria.getSource().getCityName());
		query.setParameter("destination",  (flightScheduleSearchCriteria == null || flightScheduleSearchCriteria.getDestination() == null) ? null : flightScheduleSearchCriteria.getDestination().getCityName());
		query.setParameter("fromDepartureTime",  (flightScheduleSearchCriteria == null || flightScheduleSearchCriteria.getDepartureTime() == null) ? null :getFromDate(flightScheduleSearchCriteria.getDepartureTime()));
		query.setParameter("toDepartureTime",  (flightScheduleSearchCriteria == null || flightScheduleSearchCriteria.getDepartureTime() == null) ? null :getToDate(flightScheduleSearchCriteria.getDepartureTime()));
		query.setParameter("fromArrivalTime",  (flightScheduleSearchCriteria == null || flightScheduleSearchCriteria.getArrivalTime() == null) ? null : getFromDate(flightScheduleSearchCriteria.getArrivalTime()));
		query.setParameter("toArrivalTime",  (flightScheduleSearchCriteria == null || flightScheduleSearchCriteria.getArrivalTime() == null) ? null : getToDate(flightScheduleSearchCriteria.getArrivalTime()));
		List<FlightScheduleDetails> list = (List<FlightScheduleDetails> ) query.list();
		session.close();
		return list; 
	}
	
	private   Date getFromDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String fromDate = new String(sdf.format(date));
		try {
			return sdf.parse(fromDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private  Date getToDate(Date date) {
		//System.out.println("toDate1"+date);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	
		String dateStr = new String(sdf.format(date));
		try {
		//	System.out.println("toDate"+dateStr );
			String newDate= dateStr.substring(0, 10) +" 23:59:59";
			return sdf.parse(newDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;  
	}

}
