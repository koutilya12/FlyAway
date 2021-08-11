package com.airlines.flyaway.domain;

public class PassengerDetails {
	private long bookingId;
	private String passengerName;
	private String gender;
	private int age;
	private String mobileNumber;
	private String govDocumentId;
	
	public long getBookingId() {
		return bookingId;
	}
	public void setBookingId(long bookingId) {
		this.bookingId = bookingId;
	}
	public String getPassengerName() {
		return passengerName;
	}
	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getGovDocumentId() {
		return govDocumentId;
	}
	public void setGovDocumentId(String govDocumentId) {
		this.govDocumentId = govDocumentId;
	}
	
}
