package com.airlines.flyaway.domain;

import java.util.Date;

public class FlightTicketBooking {
	private long bookingId;
	private Date bookingTime;
	private long userId;
	private long flighdId;
	private int noOfPersons;
	private double totPrice;
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
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getFlighdId() {
		return flighdId;
	}
	public void setFlighdId(long flighdId) {
		this.flighdId = flighdId;
	}
	public int getNoOfPersons() {
		return noOfPersons;
	}
	public void setNoOfPersons(int noOfPersons) {
		this.noOfPersons = noOfPersons;
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
