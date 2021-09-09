package com.airlines.flyaway.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.airlines.flyaway.constants.FlightStatus;
import com.airlines.flyaway.constants.convertors.FlightStatusConvertor;

@Entity
@Table(name = "flight_schedule_details")
public class FlightScheduleDetails implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1754122564085577219L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private long flightId;
	
	@OneToOne//(cascade = CascadeType.ALL)
    @JoinColumn(name = "source", referencedColumnName = "cityId")
	private City source;
	@OneToOne//(cascade = CascadeType.ALL)
    @JoinColumn(name = "destination", referencedColumnName = "cityId")
	private City destination;
	@OneToOne//(cascade = CascadeType.ALL)
    @JoinColumn(name = "airlineId", referencedColumnName = "airLineId")
	private AirLine airLineId;
	private double price;
	private Date departureTime;
	private Date arrivalTime;
	private int capacity;
	private int filledCapacity;
  // @Enumerated(EnumType.STRING)
    @Convert(converter = FlightStatusConvertor.class)
	private FlightStatus status;
	
	public long getFlightId() {
		return flightId;
	}


	public void setFlightId(long flightId) {
		this.flightId = flightId;
	}


	public City getSource() {
		return source;
	}


	public void setSource(City source) {
		this.source = source;
	}


	public City getDestination() {
		return destination;
	}


	public void setDestination(City destination) {
		this.destination = destination;
	}


	public AirLine getAirLineId() {
		return airLineId;
	}


	public void setAirLineId(AirLine airLineId) {
		this.airLineId = airLineId;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public Date getDepartureTime() {
		return departureTime;
	}


	public void setDepartureTime(Date departureTime) {
		this.departureTime = departureTime;
	}


	public Date getArrivalTime() {
		return arrivalTime;
	}


	public void setArrivalTime(Date arrivalTime) {
		this.arrivalTime = arrivalTime;
	}


	public int getCapacity() {
		return capacity;
	}


	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}


	public int getFilledCapacity() {
		return filledCapacity;
	}


	public void setFilledCapacity(int filledCapacity) {
		this.filledCapacity = filledCapacity;
	}


	public FlightStatus getStatus() {
		return status;
	}


	public void setStatus(FlightStatus status) {
		this.status = status;
	}
	
	public FlightScheduleDetails() {
		super();
		// TODO Auto-generated constructor stub
	}


	public FlightScheduleDetails(long flightId, City source, City destination, AirLine airLineId,
			double price, Date departureTime, Date arrivalTime, int capacity, int filledCapacity, FlightStatus status) {
		super();
		this.flightId = flightId;
		this.source = source;
		this.destination = destination;
		this.airLineId = airLineId;
		this.price = price;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.capacity = capacity;
		this.filledCapacity = filledCapacity;
		this.status = status;
	}


	@Override
	public String toString() {
		return "FlightScheduleDetails [flightId=" + flightId + ", source=" + source + ", destination=" + destination
				+ ", airLineId=" + airLineId + ", price=" + price + ", departureTime=" + departureTime
				+ ", arrivalTime=" + arrivalTime + ", capacity=" + capacity + ", filledCapacity=" + filledCapacity
				+ ", status=" + status + "]";
	}


	
	
	
	

}
