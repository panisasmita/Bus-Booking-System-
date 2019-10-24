package com.capgemini.busschedulingjpawithhibernate.service;

import java.util.Date;
import java.util.List;

import com.capgemini.busschedulingjpawithhibernate.dao.OwnerDAO;
import com.capgemini.busschedulingjpawithhibernate.dao.OwnerDAOImplementation;
import com.capgemini.busschedulingjpawithhibernate.dto.Bus;
import com.capgemini.busschedulingjpawithhibernate.dto.Ticket;

public class OwnerServicesImplementation implements OwnerServices{

	OwnerDAO ownerDAO = new OwnerDAOImplementation();

	@Override
	public Bus addBus(Bus bus) {
	return ownerDAO.addBus(bus);
	}

	@Override
	public boolean updateBus(Bus bus) {
	return ownerDAO.updateBus(bus);
	}

	@Override
	public boolean deleteBus(int busId) {
		return ownerDAO.deleteBus(busId);
	}

	@Override
	public List<Ticket> getTicketByBusId(int busId, Date journeyDate) {
		return ownerDAO.getTicketByBusId(busId, journeyDate);
	}
	
}
