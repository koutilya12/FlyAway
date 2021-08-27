package com.airlines.flyaway.services.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.airlines.flyaway.constants.FlyawayConstants;
import com.airlines.flyaway.dao.FlyAwayDao;
import com.airlines.flyaway.dao.impl.FlyAwayDaoImp;
import com.airlines.flyaway.domain.City;
import com.airlines.flyaway.domain.Response;
import com.airlines.flyaway.helpers.Validator;
import com.airlines.flyaway.services.CitiesService;

public class CitiesServiceImpl implements CitiesService {
	
	private FlyAwayDao dao;

	public CitiesServiceImpl() {
		super();
		this.dao= new FlyAwayDaoImp();
	}
	
	@Override 
	public Response insertCity(City city) {
		
		String errorMessage = Validator.validateCity(city);
		if(errorMessage != null) {
			return new Response(FlyawayConstants.FAILED, errorMessage);
		}
		errorMessage = validateExistingCity(city);
		if(errorMessage != null) {
			return new Response(FlyawayConstants.FAILED, errorMessage);
		}
		saveCity(city);
		if(city.getCityId() != 0l) {
			return new Response(FlyawayConstants.SUCCESS);
	    }else {
	    	return new Response(FlyawayConstants.FAILED,FlyawayConstants.DB_ERROR);
	    }  
	}

	private void saveCity(City city) {
		Session session = this.dao.openSession();
		Transaction tx = session.beginTransaction();		
		session.save(city);		
		tx.commit();	
		session.close();
	}


	@SuppressWarnings("unchecked")
	private String validateExistingCity(City city) {
		Response response = getCity(city);		
		if(response.getData() != null && !((List<City>) response.getData()).isEmpty()) {
			return "This city already exists";
		}
		return null;
			
	}

	@Override
	public Response deleteCity(City city) {		
		return null;
	}

	@Override
	public Response getCity(City city) {
		Session session = this.dao.openSession();	    		
		String str = "FROM City WHERE (0 = :cityNameFlag OR cityName = :cityName)";
		@SuppressWarnings("unchecked")
		Query<City> query = session.createQuery(str);
		query.setParameter("cityNameFlag", (city == null || city.getCityName() == null) ? 0 : 1);
		query.setParameter("cityName",  city == null ? null : city.getCityName());
		List<City> list = (List<City>) query.list();
		session.close();
		if(list != null && !list.isEmpty()) {
			return new Response(FlyawayConstants.SUCCESS, list);
	    }else {
	    	return new Response(FlyawayConstants.FAILED, FlyawayConstants.CITY_NOT_FOUND);
	    }
	}

}
