package com.airlines.flyaway.test;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.airlines.flyaway.constants.FlyawayConstants;
import com.airlines.flyaway.constants.UserStatus;
import com.airlines.flyaway.constants.UserTypes;
import com.airlines.flyaway.domain.Response;
import com.airlines.flyaway.domain.User;
import com.airlines.flyaway.services.UserService;
import com.airlines.flyaway.services.impl.UserServiceImpl;

@Ignore
public class UserServiceTest {
	
	@Test
	public void validateLoginTest() {
		User user = validateUser();
		UserService userService = new UserServiceImpl();
		Response response = userService.validateLogin(user);
		Assert.assertTrue(response != null && FlyawayConstants.SUCCESS.equals(response.getStatus()));
		
	}
	
	private User validateUser() {
		User user = new User();
		user.setEmailId("maryisauser@gmail.com");
		user.setPassword("maryy@user");
		return user;
	}

	@Test
	public void registerUserTest() {
		User user = createUser();
		UserService userService = new UserServiceImpl();
		Response response = userService.registerUser(user);
		//System.out.println(user.getUserId()+"ss");
		Assert.assertTrue(response != null && FlyawayConstants.SUCCESS.equals(response.getStatus()) && user.getUserId() != 0l);

	}

	private User createUser() {
		User user = new User();
		user.setUserName("jenni");
		user.setMobileNum("1234567909");
		user.setEmailId("jenniisauser@gmail.com");
		user.setPassword("jennii@user");
		user.setType(UserTypes.CUSTOMER);
		user.setuStatus(UserStatus.ACTIVE);
		return user;
	}
}
