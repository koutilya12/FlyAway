package com.airlines.flyaway.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.airlines.flyaway.constants.FlyawayConstants;
import com.airlines.flyaway.constants.UserTypes;
import com.airlines.flyaway.domain.User;

public class FlyAwayFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse resp = (HttpServletResponse) response;
			HttpSession session = req.getSession();
			String path = ((HttpServletRequest) request).getRequestURI();
			Object user = session.getAttribute(FlyawayConstants.USER_SESSION_OBJECT);
			if (isLoginUrl(path)) {
				chain.doFilter(request, response);
				return;
			}
			if (user != null) {
				if (hasRole((User) user, path)) {
					chain.doFilter(request, response);
				} else {
					RequestDispatcher rd = req.getRequestDispatcher(FlyawayConstants.HOME_URL);
					rd.include(req, resp);
				}
			} else {
				RequestDispatcher rd = req.getRequestDispatcher(FlyawayConstants.LOGIN_URL);
				rd.include(req, resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
			((HttpServletResponse) response).getWriter().println("An Error occured at server side");
		}
	}

	private boolean hasRole(User user, String path) {
		if (path.startsWith("/flyaway/cities") || path.startsWith("/flyaway/airlines")
				|| path.startsWith("/flyaway/flightschedule")) {
			return UserTypes.ADMIN.equals(user.getType());
		}
		if (path.startsWith("/flyaway/searchFlights") || path.startsWith("/flyaway/getTickets")) {
			return UserTypes.CUSTOMER.equals(user.getType());
		}
		return true;
	}

	private boolean isLoginUrl(String path) {
		return path.startsWith("/flyaway/login") || path.startsWith("/flyaway/register");
	}

	@Override
	public void destroy() {

	}

}
