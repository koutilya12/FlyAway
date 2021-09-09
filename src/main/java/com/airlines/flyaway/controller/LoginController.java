package com.airlines.flyaway.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.airlines.flyaway.constants.FlyawayConstants;
import com.airlines.flyaway.domain.Response;
import com.airlines.flyaway.domain.User;
import com.airlines.flyaway.services.impl.UserServiceImpl;


public class LoginController extends HttpServlet {
		
		/**
	 * 
	 */
	private static final long serialVersionUID = 7332867541446258780L;
		
		public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String emailId = request.getParameter("emailId");
			String password = request.getParameter("password");
			
			User user = new User();
			user.setEmailId(emailId);
			user.setPassword(password);
			
			UserServiceImpl userService = new UserServiceImpl();
			Response respo = userService.validateLogin(user);
						
			if(respo.getStatus().equals(FlyawayConstants.SUCCESS)) {
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("jsp/home.jsp");		    
				requestDispatcher.forward(request, response);
			} else {
				request.setAttribute("errorMessage", respo.getErrorMessage());
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("jsp/login.jsp");		    
				requestDispatcher.forward(request, response);
			}
			
		}

}
