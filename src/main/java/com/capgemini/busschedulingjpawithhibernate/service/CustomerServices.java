package com.capgemini.busschedulingjpawithhibernate.service;

import com.capgemini.busschedulingjpawithhibernate.dto.Feedback;
import com.capgemini.busschedulingjpawithhibernate.dto.Ticket;

public interface CustomerServices {
	public Ticket bookTicket(int busId,int userId, int noOfSeats);
	public boolean cancelTicket(int ticketId, int userId, int noOfSeats);
	public Ticket getTicketInfo(int ticketId);
	public boolean giveFeedback(Feedback feedback);


}
