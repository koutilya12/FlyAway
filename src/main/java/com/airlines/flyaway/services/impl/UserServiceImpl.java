package com.airlines.flyaway.services.impl;


import java.util.List;
import java.util.Scanner;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.airlines.flyaway.constants.FlyawayConstants;
import com.airlines.flyaway.dao.FlyAwayDao;
import com.airlines.flyaway.dao.impl.FlyAwayDaoImp;
import com.airlines.flyaway.domain.Response;
import com.airlines.flyaway.domain.User;
import com.airlines.flyaway.helpers.Validator;
import com.airlines.flyaway.services.UserService;

public class UserServiceImpl implements UserService {
    public Scanner scanner = new Scanner(System.in);
    
    private FlyAwayDao dao;
	
	public UserServiceImpl() {
		super();
		this.dao = new FlyAwayDaoImp();
	}

	@Override
	public Response validateLogin(User user) {
		String errorMessage = Validator.validateLoginUser(user);
		if(errorMessage != null) {
			return new Response(FlyawayConstants.FAILED, errorMessage);
		}
		Session session = this.dao.openSession();	    		
		String str = "FROM User u WHERE u.emailId = '" + user.getEmailId() + "' AND u.password = '" + user.getPassword() + "'";		
		@SuppressWarnings("rawtypes")
		Query query = session.createQuery(str);
		@SuppressWarnings("unchecked")
		List<User> list = (List<User>) query.list();
		System.out.println(list);		
		session.close();

		if(list != null && !list.isEmpty()) {
			return new Response(FlyawayConstants.SUCCESS);
	    }else {
	    	return new Response(FlyawayConstants.FAILED,FlyawayConstants.INVALID_CREDENTIALS);
	    }
	}

	@Override
	public Response getUserDetails(User user) {
		String errorMessage = Validator.validateUserDetails(user);
		if(errorMessage != null) {
			return new Response(FlyawayConstants.FAILED, errorMessage);
		}
		Session session = this.dao.openSession();	    		
		String str = "FROM User WHERE userId = '" + user.getUserId() + "' OR mobileNum = '" + user.getMobileNum() + "'";
		Query query = session.createQuery(str);
		List<User> list = (List<User>) query.list();
		session.close();
		
		if(list != null && !list.isEmpty()) {
			return new Response(list); 
	    }else {
	    	return new Response(FlyawayConstants.FAILED,FlyawayConstants.INVALID_USER_DETAILS);
	    }
	}

	@Override
	public Response changePassword(String oldPassword, User user) {
		String errorMessage = Validator.validateChangePassword(user);
		if(errorMessage != null) {
			return new Response(FlyawayConstants.FAILED, errorMessage);
		}
		Session session = this.dao.openSession();
		Transaction tx =  session.beginTransaction();		
		String str = "UPDATE User  SET password=:pWd WHERE userId=:uId AND password=:oPwd";
		Query query = session.createQuery(str);
		query.setParameter("pWd",user.getPassword());
		query.setParameter("uId",user.getUserId());
		query.setParameter("oPwd", oldPassword);
		int r = query.executeUpdate();
		
		tx.commit();
		session.close();
		
		if(r != 0) {
			return new Response(FlyawayConstants.SUCCESS); 
	    }else {
	    	return new Response(FlyawayConstants.FAILED,FlyawayConstants.FAILED_PASSWORD_CHANGE);
	    }
	}

	@Override
	public Response registerUser(User user) {
		String errorMessage = Validator.validateUser(user);
		if(errorMessage != null) {
			return new Response(FlyawayConstants.FAILED, errorMessage);
		}
		if(!saveUser(user)) {
			return new Response(FlyawayConstants.FAILED, "Registration Failed");
		}
		return new Response(FlyawayConstants.SUCCESS);
	}

	private boolean saveUser(User user) {
		Session session = this.dao.openSession();
		boolean b = false;
		try {
			Transaction tx =  session.beginTransaction();		
			session.save(user);
			tx.commit();
			b = true;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return b;
	}

}
