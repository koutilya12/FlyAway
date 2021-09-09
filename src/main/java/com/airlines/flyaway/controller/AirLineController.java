package com.airlines.flyaway.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.airlines.flyaway.constants.FlyawayConstants;
import com.airlines.flyaway.domain.AirLine;
import com.airlines.flyaway.domain.City;
import com.airlines.flyaway.domain.Response;
import com.airlines.flyaway.services.AirLineService;
import com.airlines.flyaway.services.CitiesService;
import com.airlines.flyaway.services.impl.AirLineServiceImpl;
import com.airlines.flyaway.services.impl.CitiesServiceImpl;

public class AirLineController  extends HttpServlet {
	
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AirLineService airLineService = new AirLineServiceImpl();
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("jsp/airlines.jsp");
		Response respo = airLineService.getAirLine(null);
		if (respo != null) {
			if (respo.getStatus().equals(FlyawayConstants.SUCCESS)) {
				request.setAttribute("airlines", (List<AirLine>) respo.getData());
			} else {
				request.setAttribute("errorMessage", respo.getErrorMessage());
			}
		} else {
			request.setAttribute("errorMessage"," List not found");
		}
		requestDispatcher.forward(request, response);
	}
	
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AirLineService airLineService = new AirLineServiceImpl();
		RequestDispatcher requestDispatcher =  request.getRequestDispatcher("jsp/airlines.jsp");
		AirLine airLine = new AirLine();
		airLine.setAirLineName(request.getParameter("airLineName"));
		Response respo = airLineService.insertAirLine(airLine);
		if (respo != null) {
			if (respo.getStatus().equals(FlyawayConstants.SUCCESS)) {
				request.setAttribute("successMessage", "Sucessfully Inserted");
			} else {
				request.setAttribute("errorMessage", respo.getErrorMessage());
			}
		} else {
			request.setAttribute("errorMessage","Unable to save airline");
		}
		doGet(request, response);
	}
	
}
