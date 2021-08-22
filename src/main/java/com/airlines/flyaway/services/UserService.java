package com.airlines.flyaway.services;

import com.airlines.flyaway.domain.Response;
import com.airlines.flyaway.domain.User;

public interface UserService {
	
	  /**
	   * Performs User Login & Validation.
	   * @param user
	   * @return Response with status success if authenticated or else returns error Message.
	   */
	  public Response validateLogin(User user);
	  /**
	   * Gets User Details.
	   * @param userId
	   * @return Response with Data as User Details
	   */
	  public Response getUserDetails(long userId);
      /**
       * Changes Password.
       * @param oldPassword
       * @param user
       * @return Response with status success if successfully changed or  else returns error Message.
       */
	  public Response changePassword(String oldPassword,User user);
      /**
       * User registration.
       * @param user
       * @return Response with status success if successfully registered or else returns error Message.
       */
	  public Response registerUser(User user);
}



