package com.airlines.flyaway.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.airlines.flyaway.constants.FlightBookingStatus;
import com.airlines.flyaway.constants.convertors.FlightBookingStatusConvertor;

@Entity
@Table(name = "flight_ticket_booking_log")
public class FlightTicketBookingLog implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -629816146656226737L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name="bookingId")
	private Long bookingId;
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
	@Override
	public String toString() {
		return "FlightTicketBookingLog [id=" + id + ", bookingId=" + bookingId + ", bookingTime=" + bookingTime
				+ ", flight=" + flight + ", user=" + user + ", passengers=" + passengers.toString() + ", totPrice=" + totPrice
				+ ", transactionId=" + transactionId + ", flightBookingStatus=" + flightBookingStatus + ", noOfPersons="
				+ noOfPersons + "]";
	}

}
