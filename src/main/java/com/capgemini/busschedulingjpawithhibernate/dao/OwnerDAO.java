package com.capgemini.busschedulingjpawithhibernate.dao;

import java.util.Date;
import java.util.List;

import com.capgemini.busschedulingjpawithhibernate.dto.Bus;
import com.capgemini.busschedulingjpawithhibernate.dto.Ticket;

public interface OwnerDAO {
	public Bus addBus(Bus bus);
	public boolean updateBus(Bus bus);
	public boolean deleteBus(int busId);
	public List<Ticket> getTicketByBusId(int busId, java.util.Date journeyDate);
}
