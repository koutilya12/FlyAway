package com.airlines.flyaway.applications;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.airlines.flyaway.domain.AirLine;

public class AirLineApp {
	
	public static void main(String[] args) {
		
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory factory = cfg.buildSessionFactory();
		
		AirLine aL = new AirLine();
		aL.setAirLineName("Indigo Airlines");
		Session session = factory.openSession();
		
		Transaction tx = session.beginTransaction();
		
		session.save(aL);
		
		tx.commit();
	
		session.close();
		factory.close();
		
		getAirName();
		
	}
	
	public static void getAirName() {
		
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		
		AirLine al = (AirLine)session.get(AirLine.class, (long)18);
		System.out.println(al);
	
		session.close();
		factory.close();
	
	 }

}
