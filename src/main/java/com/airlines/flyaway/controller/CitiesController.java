package com.airlines.flyaway.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

import com.airlines.flyaway.constants.FlyawayConstants;
import com.airlines.flyaway.domain.City;
import com.airlines.flyaway.domain.Response;
import com.airlines.flyaway.services.CitiesService;
import com.airlines.flyaway.services.impl.CitiesServiceImpl;

public class CitiesController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8065571988232515805L;

	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CitiesService citiesService = new CitiesServiceImpl();
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("jsp/cities.jsp");
		Response respo = citiesService.getCity(null);
		if (respo != null) {
			if (respo.getStatus().equals(FlyawayConstants.SUCCESS)) {
				request.setAttribute("cities", (List<City>) respo.getData());
			} else {
				request.setAttribute("errorMesssage", respo.getErrorMessage());
			}
		} else {
			request.setAttribute("errorMesssage"," List not found");
		}
		requestDispatcher.forward(request, response);
	}

}
