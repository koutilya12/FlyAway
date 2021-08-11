package com.airlines.flyaway.applications;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.airlines.flyaway.constants.FlightStatus;
import com.airlines.flyaway.domain.AirLine;
import com.airlines.flyaway.domain.City;
import com.airlines.flyaway.domain.FlightScheduleDetails;

public class FlightScheduleDetailsApplication {
	private static SessionFactory factory;

	public static void main(String[] args) {
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory factory=cfg.buildSessionFactory();
		
		City source = new City();
		//source.setCityName("Mumbai");
		source.setCityId(45l);
		
        City destination = new City();
		//destination.setCityName("Hyderabad");
        destination.setCityId(4l);
		AirLine airLine = new AirLine();
		//airLine.setAirLineName("Jet airways");
		airLine.setAirLineId(1l);
	
		FlightScheduleDetails fsd1 = new FlightScheduleDetails();
		
		fsd1.setPrice(5400);
		fsd1.setDepartureTime(stringToDate("2021-08-21 09:45:00"));
		fsd1.setArrivalTime(stringToDate("2021-08-16 15:30:00"));
        fsd1.setCapacity(290);
        fsd1.setFilledCapacity(175);
        fsd1.setStatus(FlightStatus.INACTIVE);
        fsd1.setAirLineId(airLine);
        fsd1.setSource(source);
        fsd1.setDestination(destination);


        Session s = factory.openSession();
        
        Transaction tx = s.beginTransaction();
        s.save(fsd1);
        tx.commit();
		factory.close();
		
		/* Add few Flight Schedule Details in database */
//		fsd1 =(FlightScheduleDetails)addFlightScheduleDetails(1, 3, 2101, 5400, stringToDate("2021-08-16 15:30:00"),
//				stringToDate("2021-08-21 9:45:00"), 240, 168, FlightStatus.ACTIVE);
//		addFlightScheduleDetails(2, 1, 2102, 3800, stringToDate("2021-08-16 18:55:00"),
//				stringToDate("2021--07-21 23:55:00"), 240, 175, FlightStatus.ACTIVE);
//		addFlightScheduleDetails(3, 2, 2103, 5500, stringToDate("2021-08-15 15:00:00"),
//				stringToDate("2021-08-22 12:00:00"), 300, 244, FlightStatus.INACTIVE);
}

	public static Date stringToDate(String sDate) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
			Date date = formatter.parse(sDate);
			return date;
		} catch (Exception e) {
			System.out.println("Error in conversion");

		}
		return null;
	}
	/*
	
	
	public static FlightScheduleDetails addFlightScheduleDetails(int source, int destination, int airLineId, double price,
			Date departureTime, Date arrivalTime, int capacity, int filledCapacity, FlightStatus status) {
		Session session = factory.openSession();
		Transaction tx = null;
		Integer flightId = null;
		City city = new City();

		try {
			tx = session.beginTransaction();
			FlightScheduleDetails flightScheduleDetails = new FlightScheduleDetails();
			city.setCityId(fsd1);
			flightScheduleDetails.setSource(source);
			flightScheduleDetails.setDestination(destination);
			flightScheduleDetails.setAirLineId(airLineId);
			flightScheduleDetails.setPrice(price);
			flightScheduleDetails.setDepartureTime(departureTime);
			flightScheduleDetails.setArrivalTime(arrivalTime);
			flightScheduleDetails.setCapacity(capacity);
			flightScheduleDetails.setFilledCapacity(filledCapacity);
			flightScheduleDetails.setStatus(status);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return flightId;
	}


	/* Method to READ all the employees 
	public void listFlightScheduleDetails() {
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			List flightScheduleDetailss = session.createQuery("FROM FlightScheduleDetails").list();
			for (Iterator iterator = flightScheduleDetailss.iterator(); iterator.hasNext();) {
				FlightScheduleDetails fSD = (FlightScheduleDetails) iterator.next();
				System.out.print("Source: " + fSD.getSource());
				System.out.print("Destination:  " + fSD.getDestination());
				System.out.println("airlineId : " + fSD.getAirLineId());
				System.out.print("price: " + fSD.getPrice());
				System.out.print("departureTime: " + fSD.getDepartureTime());
				System.out.println("arrivalTime: " + fSD.getArrivalTime());
				System.out.print("capacity: " + fSD.getCapacity());
				System.out.print("filledCapacity: " + fSD.getFilledCapacity());
				System.out.println("flightStatus: " + fSD.getStatus());
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}*/

}
