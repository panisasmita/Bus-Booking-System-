package com.capgemini.busschedulingjpawithhibernate.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.capgemini.busschedulingjpawithhibernate.dto.Bus;
import com.capgemini.busschedulingjpawithhibernate.dto.Ticket;


public class OwnerDAOImplementation implements OwnerDAO{

	EntityManagerFactory entityManagerFactory = null;
	EntityManager entityManager = null;
	EntityTransaction entityTransaction = null;
	
	@Override
	public Bus addBus(Bus bus) {
		entityManagerFactory = Persistence.createEntityManagerFactory("bookingTestPersistence");
		entityManager = entityManagerFactory.createEntityManager();
		entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		try {
			entityManager.persist(bus);
			entityTransaction.commit();
			entityManager.close();
			return bus;
		} 
		catch (Exception e) {
			entityTransaction.rollback();
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean updateBus(Bus bus) {
		entityManagerFactory = Persistence.createEntityManagerFactory("bookingTestPersistence");
		entityManager = entityManagerFactory.createEntityManager();
		entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		try {
			Bus buses = entityManager.find(Bus.class, bus.getBusId());
			if(buses!=null) {
			buses.setAvailableSeats(bus.getAvailableSeats());
			buses.setBusName(bus.getBusName());
			buses.setBusType(bus.getBusType());
			buses.setCapacity(bus.getCapacity());
			buses.setDestination(bus.getDestination());
			buses.setFare(bus.getFare());
			buses.setSource(bus.getSource());
			entityTransaction.commit();
			entityManager.close();
			return true;
			}
			
	} 
		catch (Exception e) {
			entityTransaction.rollback();
			e.printStackTrace();
		}
		entityManager.close();
				return false;
	}

	@Override
	public boolean deleteBus(int busId) {
		entityManagerFactory = Persistence.createEntityManagerFactory("bookingTestPersistence");
		entityManager = entityManagerFactory.createEntityManager();
		entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		try {
			Bus bus = entityManager.find(Bus.class, busId);
			entityManager.remove(bus);
			entityTransaction.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			entityTransaction.rollback();
		}
		entityManager.close();
		return false;
	}

	@Override
	public List<Ticket> getTicketByBusId(int busId, Date journeyDate) {
		entityManagerFactory = Persistence.createEntityManagerFactory("bookingTestPersistence");
		entityManager = entityManagerFactory.createEntityManager();
		String jpql = "FROM ticket_details where busId=:busId AND journeyDate=:journeyDate";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("busId", busId);
		query.setParameter("journeyDate", journeyDate);
		List<Ticket> tickets = query.getResultList();
		entityManager.close();
		return tickets;
	}

	
}
