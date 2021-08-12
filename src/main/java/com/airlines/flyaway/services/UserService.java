package com.airlines.flyaway.services;

import com.airlines.flyaway.domain.Response;
import com.airlines.flyaway.domain.User;

public interface UserService {
	
	  /**
	   * Performs User Login & Validation
	   * @param user
	   * @return Response with status success if authenticated or else returns errorMessage
	   */
	  public Response validateLogin(User user);
	  /**
	   * Gets User Details
	   * @param userId
	   * @return Response with data as user details
	   */
	  public Response getUserDetails(long userId);
      /**
       * Changes Password
       * @param oldPassword
       * @param user
       * @return Response with status success if authenticated or else returns errorMessage
       */
	  public Response changePassword(String oldPassword,User user);
      /**
       * 
       * @param user
       * @return Response
       */
	  public Response registerUser(User user);
}



