package com.airlines.flyaway.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "flight_ticket_booking")
public class FlightTicketBooking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bookingId;
	public Long getBookingId() {
		return bookingId;
	}
	public void setBookingId(Long bookingId) {
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
	private Date bookingTime;
	@OneToOne
	@JoinColumn(name = "flight", referencedColumnName = "flightId")
	private FlightScheduleDetails flight;
	@OneToOne
	@JoinColumn(name = "user", referencedColumnName = "userId")
	private User user;
	@OneToMany
	@JoinColumn(name = "passengers", referencedColumnName = "passengers")
    private List<PassengerDetails> passengers;
    private Double totPrice;
	private String transactionId;
	
	

}
