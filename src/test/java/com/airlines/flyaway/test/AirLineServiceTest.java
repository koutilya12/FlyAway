package com.airlines.flyaway.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.airlines.flyaway.constants.FlyawayConstants;
import com.airlines.flyaway.domain.AirLine;
import com.airlines.flyaway.domain.City;
import com.airlines.flyaway.domain.Response;
import com.airlines.flyaway.services.AirLineService;
import com.airlines.flyaway.services.CitiesService;
import com.airlines.flyaway.services.impl.AirLineServiceImpl;
import com.airlines.flyaway.services.impl.CitiesServiceImpl;

public class AirLineServiceTest {
	
	@Test
	public void insertAirLineTest() {
		AirLine airLine = prepareAirLineObject();
		AirLineService airLineService = new AirLineServiceImpl();
		Response response = airLineService.insertAirLine(airLine);
		System.out.println(response);
		Assert.assertTrue(response != null && FlyawayConstants.SUCCESS.equals(response.getStatus()));
		
	}
	
	//@Test
	public void getAirLineTest() {
	AirLine airLine = prepareAirLineObject();
	AirLineService airLineService = new AirLineServiceImpl();
	Response response = airLineService.getAirLine(airLine);
	List<AirLine> list = (List<AirLine>) response.getData();
	System.out.println(list);
	Assert.assertTrue(response != null && FlyawayConstants.SUCCESS.equals(response.getStatus()) && list.get(0) != null && list.get(0).getAirLineName().equalsIgnoreCase(airLine.getAirLineName()));
	
	}
	
	private AirLine prepareAirLineObject() {
		AirLine airLine = new AirLine();
		airLine.setAirLineName("Spice Jet");
		return airLine;
		
	}
	
	

}
