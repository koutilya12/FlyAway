package com.airlines.flyaway.applications;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.airlines.flyaway.domain.City;


public class CityApp {
	
	public static void main(String[] args) {
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory factory = cfg.buildSessionFactory();

		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		
		City city = new City();
		city.setCityName("Hyderabad1");
		session.save(city);
		tx.commit();
		session.close();
		
        listCityNames();
		
	}
	
	public static void listCityNames() {
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory factory=cfg.buildSessionFactory();
		Session session = factory.openSession();
		
		@SuppressWarnings("unchecked")
		List<City> city = (List<City> ) session.createQuery("FROM City").list();
		for(Iterator<City> iterator = city.iterator(); iterator.hasNext(); ) {
			
			City cityList = iterator.next();
			long num = cityList.getCityId();
			String name = cityList.getCityName();
			System.out.println("City Id : " +num + "\t" +"City Name: " + name);
			
		}
		
		
	}
	
	
	

}
