package com.airlines.flyaway.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.airlines.flyaway.constants.FlightBookingStatus;
import com.airlines.flyaway.constants.convertors.FlightBookingStatusConvertor;

@Entity
@Table(name = "flight_ticket_booking_log")
public class FlightTicketBookingLog {
	
	private long bookingId;
	private Date bookingTime;
	@OneToOne
	@JoinColumn(name = "flightId", referencedColumnName = "flightId")
	private FlightScheduleDetails flight;
	@OneToOne
	@JoinColumn(name = "userId", referencedColumnName = "userId")
	private User user;
	@OneToMany
	@JoinColumn(name = "bookingId", referencedColumnName = "bookingId")
    private List<PassengerDetails> passengers;
    private Double totPrice;
	private String transactionId;
	@Convert(converter = FlightBookingStatusConvertor.class)
	private FlightBookingStatus flightBookingStatus;
    private int noOfPersons;

	
	public long getBookingId() {
		return bookingId;
	}
	public void setBookingId(long bookingId) {
		this.bookingId = bookingId;
	}
	public Date getBookingTime() {
		return bookingTime;
	}
	public void setBookingTime(Date bookingTime) {
		this.bookingTime = bookingTime;
	}
	public FlightScheduleDetails getFlight() {
		return flight;
	}
	public void setFlight(FlightScheduleDetails flight) {
		this.flight = flight;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<PassengerDetails> getPassengers() {
		return passengers;
	}
	public void setPassengers(List<PassengerDetails> passengers) {
		this.passengers = passengers;
	}
	public Double getTotPrice() {
		return totPrice;
	}
	public void setTotPrice(Double totPrice) {
		this.totPrice = totPrice;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public FlightBookingStatus getFlightBookingStatus() {
		return flightBookingStatus;
	}
	public void setFlightBookingStatus(FlightBookingStatus flightBookingStatus) {
		this.flightBookingStatus = flightBookingStatus;
	}
	public int getNoOfPersons() {
		return noOfPersons;
	}
	public void setNoOfPersons(int noOfPersons) {
		this.noOfPersons = noOfPersons;
	}

}
