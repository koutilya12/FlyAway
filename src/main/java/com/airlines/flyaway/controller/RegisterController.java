package com.airlines.flyaway.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.airlines.flyaway.constants.FlyawayConstants;
import com.airlines.flyaway.constants.UserStatus;
import com.airlines.flyaway.constants.UserTypes;
import com.airlines.flyaway.domain.Response;
import com.airlines.flyaway.domain.User;
import com.airlines.flyaway.services.UserService;
import com.airlines.flyaway.services.impl.UserServiceImpl;

public class RegisterController extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 931564504783705443L;

	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("jsp/register.jsp");
		requestDispatcher.forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("jsp/register.jsp");
		User user = new User();
		user.setUserName(request.getParameter("userName"));
		user.setMobileNum(request.getParameter("mobileNum"));
		user.setEmailId(request.getParameter("emailId"));
		user.setPassword(request.getParameter("password"));
		user.setType(UserTypes.CUSTOMER);
		user.setuStatus(UserStatus.ACTIVE);
		UserService userService = new UserServiceImpl();
		Response respo = userService.registerUser(user);
		if(respo != null && respo.getStatus().equals(FlyawayConstants.SUCCESS)) {
			request.setAttribute("successMessage", "User Successfully Registered");
		} else {
			request.setAttribute("errorMessage", respo.getErrorMessage());
		}
			requestDispatcher.forward(request, response);
	}
}
