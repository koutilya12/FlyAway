package com.airlines.flyaway.services;

import com.airlines.flyaway.domain.AirLine;
import com.airlines.flyaway.domain.Response;

public interface AirLineService {
	/**
	 * Performs Insertion of AirLine.
	 * @param airLine
	 * @return Response with Status success If successfully inserted or else Insertion failed.
	 */
	public Response insertAirLine(AirLine airLine);
	/**
	 * Performsdeletion of AirLine.
	 * @param airLine
	 * @return Response with Status success If successfully deleted or else deletion failed.
	 */
	public Response deleteAirLine(AirLine airLine);
	/**
	 * Gets the required AirLine.
	 * @param airLine
	 * @return Response with Status success if required Airline is available or else not available. 
	 */
	public Response getAirLine(AirLine airLine);
	

}
