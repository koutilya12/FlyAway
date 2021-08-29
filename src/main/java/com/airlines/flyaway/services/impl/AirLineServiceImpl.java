package com.airlines.flyaway.services.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.airlines.flyaway.constants.FlyawayConstants;
import com.airlines.flyaway.dao.FlyAwayDao;
import com.airlines.flyaway.dao.impl.FlyAwayDaoImp;
import com.airlines.flyaway.domain.AirLine;
import com.airlines.flyaway.domain.City;
import com.airlines.flyaway.domain.Response;
import com.airlines.flyaway.helpers.Validator;
import com.airlines.flyaway.services.AirLineService;

public class AirLineServiceImpl implements AirLineService{

	

	private FlyAwayDao dao;

	public AirLineServiceImpl() {
		super();
		this.dao= new FlyAwayDaoImp();
	}
	

	@Override
	public Response insertAirLine(AirLine airLine) {
		String errorMessage = Validator.validateAirLine(airLine);
		if(errorMessage != null) {
			return new Response(FlyawayConstants.FAILED, errorMessage);
			
		}
		errorMessage = validateExistingAirLine(airLine);
		if(errorMessage != null) {
			return new Response(FlyawayConstants.FAILED, errorMessage);
		}
		saveAirLine(airLine);
		if(airLine.getAirLineId() != 0l) {
			return new Response(FlyawayConstants.SUCCESS);
	    }else {
	    	return new Response(FlyawayConstants.FAILED,FlyawayConstants.DB_ERROR);
	    }  
		
	}

	private void saveAirLine(AirLine airLine) {
		Session session = this.dao.openSession();
		Transaction tx = session.beginTransaction();		
		session.save(airLine);		
		tx.commit();	
		session.close();
		
	}


	private String validateExistingAirLine(AirLine airLine) {
		Response response = getAirLine(airLine);		
		if(response.getData() != null && !((List<AirLine>) response.getData()).isEmpty()) {
			return "This AirLine already exists";
		}

		return null;
	}


	@Override
	public Response deleteAirLine(AirLine airLine) {
		
		return null;
	}

	@Override
	public Response getAirLine(AirLine airLine) {
		Session session = this.dao.openSession();
		String str = "FROM AirLine WHERE (0 = :airLineNameFlag OR airLineName = :airLineName)";
		Query<AirLine> query = session.createQuery(str);
		query.setParameter("airLineNameFlag", (airLine == null || airLine.getAirLineName() == null) ? 0 : 1);
		query.setParameter("airLineName", airLine == null ? null : airLine.getAirLineName());
		
		List<AirLine> list = (List<AirLine>) query.list();
		session.close();
		
		if(list != null && !list.isEmpty()) {
			return new Response(FlyawayConstants.SUCCESS, list);
	    }else {
	    	return new Response(FlyawayConstants.FAILED, FlyawayConstants.AIRLINES_NOT_FOUND);
	    }
	}

}
