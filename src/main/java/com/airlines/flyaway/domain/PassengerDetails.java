package com.airlines.flyaway.domain;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.airlines.flyaway.constants.GovDocumentType;
import com.airlines.flyaway.constants.PassengerGender;
import com.airlines.flyaway.constants.convertors.GovDocumentTypeConvertor;
import com.airlines.flyaway.constants.convertors.PassengerGenderConvertor;
import com.airlines.flyaway.constants.convertors.UserStatusConvertor;

@Entity
@Table(name = "passenger_details")
public class PassengerDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private long bookingId;
	private String passengerName;
	@Convert(converter = PassengerGenderConvertor.class)
	private PassengerGender gender;
	private Integer age;
	private String mobileNumber;
	private String govDocumentId;
	@Convert(converter = GovDocumentTypeConvertor.class)
	private GovDocumentType govDocumentType;
	
	public GovDocumentType getGovDocumentType() {
		return govDocumentType;
	}
	public void setGovDocumentType(GovDocumentType govDocumentType) {
		this.govDocumentType = govDocumentType;
	}
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
	public PassengerGender getGender() {
		return gender;
	}
	public void setGender(PassengerGender gender) {
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
}
