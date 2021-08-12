package com.airlines.flyaway.services;

import com.airlines.flyaway.domain.City;
import com.airlines.flyaway.domain.Response;

public interface CitiesService {
	/**
	 * Performs Insertion of City Name.
	 * @param city
	 * @return Response with Status success If successfully inserted or else Insertion failed.
	 */
	public Response insertCity(City city);
    /**
     * Performs deletion of City Name.
     * @param city
     * @return Response with Status success If successfully deleted or else deletion failed.
     */
	public Response deleteCity(City city);
	/**
	 * Gets the required City Name
	 * @param city
	 * @return Response with Status success if required City is available or else not available. 
	 */
	public Response getCity(City city);
		

}
