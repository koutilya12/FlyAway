package com.airlines.flyaway.domain;

import java.util.Date;
import java.util.List;

public class FlightTicketBooking {
	private Long bookingId;
	private Date bookingTime;
	private  flightId;
	private User user;
    private List<PassengerDetails> passengers;
    private Double totPrice;
	private String transactionId;
	
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
	public long getFlighdId() {
		return flightId;
	}
	public void setFlighdId(long flighdId) {
		this.flightId = flighdId;
	}
	public double getTotPrice() {
		return totPrice;
	}
	public void setTotPrice(double totPrice) {
		this.totPrice = totPrice;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	

}
