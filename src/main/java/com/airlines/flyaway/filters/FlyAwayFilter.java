package com.airlines.flyaway.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class FlyAwayFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpSession session =  ((HttpServletRequest) request).getSession();
		if(session.getAttribute("userDetails") != null) {
			if(request.get)
			chain.doFilter(request, response);
		}else {
			request..
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
