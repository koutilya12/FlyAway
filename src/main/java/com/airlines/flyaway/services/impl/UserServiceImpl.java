package com.airlines.flyaway.services.impl;


import java.util.List;


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
    
    private FlyAwayDao dao;
	
	public UserServiceImpl() {
		super();
		this.dao = new FlyAwayDaoImp();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response validateLogin(User user) {
		String errorMessage = Validator.validateLoginUser(user);
		if(errorMessage != null) {
			return new Response(FlyawayConstants.FAILED, errorMessage);
		}
		Session session = this.dao.openSession();	    		
		List<User> list = null;
		try {
			String str = "FROM User u WHERE u.emailId = '" + user.getEmailId() + "' AND u.password = '" + user.getPassword() + "'";		
			@SuppressWarnings("rawtypes")
			Query query = session.createQuery(str);
			list = (List<User>) query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.close();

		if(list != null && !list.isEmpty()) {
			return new Response(FlyawayConstants.SUCCESS);
	    }else {
	    	return new Response(FlyawayConstants.FAILED,FlyawayConstants.INVALID_CREDENTIALS);
	    }
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Response getUserDetails(User user) {
		String errorMessage = Validator.validateUserDetails(user);
		if(errorMessage != null) {
			return new Response(FlyawayConstants.FAILED, errorMessage);
		}
		Session session = this.dao.openSession();	    		
		List<User> list = null;
		try {
			String str = "FROM User WHERE (0 = :userIdFlag OR  userId = :userId) AND (0 = :mobNumFlag OR  mobileNum = :mobNum) AND (0=:emaildFlag OR emailId = :emailId)";
			Query query = session.createQuery(str);
			prepareSearchQuery(user, query);
			list = (List<User>) query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.close();
		
		if(list != null && !list.isEmpty()) {
			return new Response(FlyawayConstants.SUCCESS,list); 
	    }else {
	    	return new Response(FlyawayConstants.FAILED,FlyawayConstants.EMPTY_USER_DETAILS);
	    }
	}
	
	private void prepareSearchQuery(User user, @SuppressWarnings("rawtypes") Query query) {
		query.setParameter("userIdFlag", (user == null || user.getUserId() == 0l) ? 0 : 1);		
		query.setParameter("mobNumFlag", (user == null || user.getMobileNum() == null) ? 0 : 1);
		query.setParameter("emaildFlag", (user == null || user.getEmailId() == null) ? 0 : 1);

		query.setParameter("userId", (user == null || user.getUserId() == 0l) ? null : user.getUserId());		
		query.setParameter("mobNum", (user == null || user.getMobileNum() == null) ? null : user.getMobileNum());
		query.setParameter("emailId", (user == null || user.getEmailId() == null) ? null : user.getEmailId());
		query.getParameters().stream().forEach(item -> System.out.println(item.getName() +"::"+query.getParameterValue(item.getName() )));
		
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Response changePassword(String oldPassword, User user) {
		String errorMessage = Validator.validateChangePassword(user);
		if(errorMessage != null) {
			return new Response(FlyawayConstants.FAILED, errorMessage);
		}
		Session session = this.dao.openSession();
		int r = 0;
		try {
			Transaction tx =  session.beginTransaction();		
			String str = "UPDATE User  SET password=:pWd WHERE userId=:uId AND password=:oPwd";
			Query query = session.createQuery(str);
			query.setParameter("pWd",user.getPassword());
			query.setParameter("uId",user.getUserId());
			query.setParameter("oPwd", oldPassword);
			r = query.executeUpdate();
			
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
