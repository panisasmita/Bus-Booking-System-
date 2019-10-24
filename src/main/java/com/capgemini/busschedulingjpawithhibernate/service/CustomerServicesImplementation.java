package com.capgemini.busschedulingjpawithhibernate.service;

import com.capgemini.busschedulingjpawithhibernate.dao.CustomerDAO;
import com.capgemini.busschedulingjpawithhibernate.dao.CustomerDAOImplementation;
import com.capgemini.busschedulingjpawithhibernate.dto.Feedback;
import com.capgemini.busschedulingjpawithhibernate.dto.Ticket;

public class CustomerServicesImplementation implements CustomerServices{

	CustomerDAO customerDAO = new CustomerDAOImplementation();

	@Override
	public Ticket bookTicket(int busId, int userId, int noOfSeats) {
		return customerDAO.bookTicket(busId, userId, noOfSeats);
	}

	@Override
	public boolean cancelTicket(int ticketId, int userId, int noOfSeats) {
		return customerDAO.cancelTicket(ticketId, userId, noOfSeats);
	}

	@Override
	public Ticket getTicketInfo(int ticketId) {
		return customerDAO.getTicketInfo(ticketId);
	}

	@Override
	public boolean giveFeedback(Feedback feedback) {
		return customerDAO.giveFeedback(feedback);
	}
	
	
}
