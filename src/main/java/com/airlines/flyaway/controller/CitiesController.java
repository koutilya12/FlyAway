package com.airlines.flyaway.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;

public class CitiesController extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Hello World");
		
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		out.print("<h1> this is get method of cities controller servlet  </h1>");
	}
	
	

}
