package com.airlines.flyaway.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.airlines.flyaway.constants.FlyawayConstants;
import com.airlines.flyaway.domain.Response;
import com.airlines.flyaway.domain.User;
import com.airlines.flyaway.services.impl.UserServiceImpl;


public class LoginController extends HttpServlet {
		
		public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String str = request.getParameter("id");

			response.setContentType("text/html");
			
			PrintWriter out = response.getWriter();
			out.print("<h1> this is the id" + str + "</h1>");
		}
		
		
		public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			
			User user = new User();
			user.setEmailId(firstName);
			user.setPassword(lastName);
			
			UserServiceImpl userService = new UserServiceImpl();
			Response respo = userService.validateLogin(user);
	
			response.setContentType("text/html");
			
			PrintWriter out = response.getWriter();
			if(respo.getStatus().equals(FlyawayConstants.SUCCESS)) {
				out.print("<h1> the status is" + respo.getStatus() + "</h1>");
			} else {
				out.print("<h1> the error message is" + respo.getErrorMessage() + "</h1>");
			}
			
		}

}
