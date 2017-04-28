package com.webtools.airline.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Pattern;

@Entity
@Table(name="FLIGHT_DETAILS")
public class FlightDetails {

	@Id
    @Pattern(regexp="[0-9]*",message="Please enter Only Numbers")
	@Column(name="FLIGHT_ID",nullable=false)
	private String flightID;

	@Temporal(TemporalType.DATE)
	@Column(name = "startDate", nullable = false, length = 10)
	private Date startDate;
	
	@Column(name="flight_Company")
	private String flightCompany;
	
	@Column(name="from_place")
	private String fromPlace;
	
	@Column(name="to_place")
	private String toPlace;
	
	@Column(name="price")
	private float price;
	
	@Column(name="ticketAvailability")
	private int ticketAvailability;
	
	
	
	@Temporal(TemporalType.DATE)
	@Column(name = "endDate", nullable = false, length = 10)
	private Date endDate;
	
	@Column(name="startTime")
	private String startTime;
	
	@Column(name="journey_hours")
	private String journeyhours;
	
	@Column(name="endTime")
	private String endTime;
	
	
	public String getFlightCompany() {
		return flightCompany;
	}
	public void setFlightCompany(String flightCompany) {
		this.flightCompany = flightCompany;
	}
	public String getFromPlace() {
		return fromPlace;
	}
	public void setFromPlace(String fromPlace) {
		this.fromPlace = fromPlace;
	}
	public String getToPlace() {
		return toPlace;
	}
	public void setToPlace(String toPlace) {
		this.toPlace = toPlace;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getTicketAvailability() {
		return ticketAvailability;
	}
	public void setTicketAvailability(int ticketAvailability) {
		this.ticketAvailability = ticketAvailability;
	}
	
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getJourneyhours() {
		return journeyhours;
	}
	public void setJourneyhours(String journeyhours) {
		this.journeyhours = journeyhours;
	}
	public String getFlightID() {
		return flightID;
	}
	public void setFlightID(String flightID) {
		this.flightID = flightID;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	
	
	
	
}
