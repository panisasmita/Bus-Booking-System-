package com.capgemini.busschedulingjpawithhibernate.dto;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="bus_details")
public class Bus {
	@Id
	@Column
	private int busId;
	@Column
	private String busName;
	@Column
	private String busType;
	@Column
	private String source;
	@Column
	private String destination;
	@Column
	private int capacity;
	@Column
	private int availableSeats;
	@Column
	private double fare;
	@Column
	private Date journeyData;
	
	public int getBusId() {
		return busId;
	}
	public void setBusId(int busId) {
		this.busId = busId;
	}
	public String getBusName() {
		return busName;
	}
	public void setBusName(String busName) {
		this.busName = busName;
	}
	public String getBusType() {
		return busType;
	}
	public void setBusType(String busType) {
		this.busType = busType;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public int getAvailableSeats() {
		return availableSeats;
	}
	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}
	public double getFare() {
		return fare;
	}
	public void setFare(double fare) {
		this.fare = fare;
	}
	public Date getJourneyData() {
		return journeyData;
	}
	public void setJourneyData(Date journeyData) {
		this.journeyData = journeyData;
	}
	@Override
	public String toString() {
		return "Bus [busId=" + busId + ", busName=" + busName + ", busType=" + busType + ", source=" + source
				+ ", destination=" + destination + ", capacity=" + capacity + ", availableSeats=" + availableSeats
				+ ", fare=" + fare + ", journeyData=" + journeyData + "]";
	}
	

}
