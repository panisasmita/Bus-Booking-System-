package com.capgemini.busschedulingjpawithhibernate.dto;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="ticket_details")
public class Ticket {
	@Id
	@Column
	private int ticketId;
	
	@Column
	private int busId;

	@Column
	private int userId;
	@Column
	private Date journeyDate;
	@Column
	private int noOfSeats;
	@Column
	private Timestamp reservationDateTime;
	
	public int getTicketId() {
		return ticketId;
	}
	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}
	public int getBusId() {
		return busId;
	}
	public void setBusId(int busId) {
		this.busId = busId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Date getJourneyDate() {
		return journeyDate;
	}
	public void setJourneyDate(Date journeyDate) {
		this.journeyDate = journeyDate;
	}
	public int getNoOfSeats() {
		return noOfSeats;
	}
	public void setNoOfSeats(int noOfSeats) {
		this.noOfSeats = noOfSeats;
	}
	public Timestamp getReservationDateTime() {
		return reservationDateTime;
	}
	public void setReservationDateTime(Timestamp reservationDateTime) {
		this.reservationDateTime = reservationDateTime;
	}
	@Override
	public String toString() {
		return "Ticket [ticketId=" + ticketId + ", busId=" + busId + ", userId=" + userId + ", journeyDate="
				+ journeyDate + ", noOfSeats=" + noOfSeats + ", reservationDateTime=" + reservationDateTime + "]";
	}
}
