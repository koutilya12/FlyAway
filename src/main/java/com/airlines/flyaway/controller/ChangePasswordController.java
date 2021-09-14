package com.airlines.flyaway.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.airlines.flyaway.constants.FlyawayConstants;
import com.airlines.flyaway.domain.Response;
import com.airlines.flyaway.domain.User;
import com.airlines.flyaway.services.UserService;
import com.airlines.flyaway.services.impl.UserServiceImpl;

public class ChangePasswordController extends HttpServlet {
	
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("jsp/changePassword.jsp");
		requestDispatcher.forward(request, response);
	}
	
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		UserService userService = new UserServiceImpl();
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("jsp/changePassword.jsp");
		User user = new User();
		if(request.getParameter("newPassword").equals(request.getParameter("oldPassword"))){
			request.setAttribute("errorMessage", FlyawayConstants.PASSWORDS_CANNOT_BE_SAME);
		} else {
		if(request.getParameter("newPassword").equals(request.getParameter("confirmPassword"))) {
			user.setUserId(5);
			user.setPassword(request.getParameter("newPassword"));
			Response respo = userService.changePassword(request.getParameter("oldPassword"), user);
			if (respo != null) {
				if (respo.getStatus().equals(FlyawayConstants.SUCCESS)) {
					request.setAttribute("successMessage", "passwords successfully changed");
				} else {
					request.setAttribute("errorMessage", respo.getErrorMessage());
				}
			} else {
				request.setAttribute("errorMessage","Password change not successful");
			}
		} else{
			request.setAttribute("errorMessage", FlyawayConstants.PASSWORDS_NOT_MATCHING);
		}
	}
		requestDispatcher.forward(request, response);
  }

}
