/**
 * 
 */
package com.airlines.flyaway.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.airlines.flyaway.dao.FlyAwayDao;

/**
 * @author koutilya
 *
 */
public class FlywAwayDaoImp implements FlyAwayDao {
	
	
	private SessionFactory factory;
	
	
	public FlywAwayDaoImp() {
		super();
		this.factory  = getDBFactory();
	}

	private SessionFactory getDBFactory() {
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory factory = cfg.buildSessionFactory();
		return factory;
	}

	@Override
	public Session openSession() {
		return this.factory.openSession();
	}

}
