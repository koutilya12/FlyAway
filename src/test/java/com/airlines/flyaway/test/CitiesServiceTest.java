package com.airlines.flyaway.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.airlines.flyaway.constants.FlyawayConstants;
import com.airlines.flyaway.domain.City;
import com.airlines.flyaway.domain.Response;
import com.airlines.flyaway.services.CitiesService;
import com.airlines.flyaway.services.impl.CitiesServiceImpl;

@Ignore
public class CitiesServiceTest {
	
	@Test
	public void insertCityTest() {
		City city = prepareCityObject();
		CitiesService citiesService = new CitiesServiceImpl();
		Response response = citiesService.insertCity(city);
		System.out.println(response);
		Assert.assertTrue(response != null && FlyawayConstants.SUCCESS.equals(response.getStatus()));
		
	}
	
	//@Test
	public void getCityTest() {
		City city = prepareCityObject();
		CitiesService citiesService = new CitiesServiceImpl();
		Response response = citiesService.getCity(city);
		List<City> list = (List<City>) response.getData();
		System.out.println(list);
		Assert.assertTrue(response != null && FlyawayConstants.SUCCESS.equals(response.getStatus()) && list.get(0) != null && list.get(0).getCityName().equalsIgnoreCase(city.getCityName()));
		
	}
	
	//@Test
	public void getCityTestForAll() {
		City city = prepareCityObject();
		CitiesService citiesService = new CitiesServiceImpl();
		Response response = citiesService.getCity(city);
		List<City> list = (List<City>) response.getData();
		System.out.println(list);
		Assert.assertTrue(response != null && FlyawayConstants.SUCCESS.equals(response.getStatus()));
		
	}

	private City prepareCityObject() {
		City city = new City();
		city.setCityName("Kolkatta");
		return city;
	
	}

}
