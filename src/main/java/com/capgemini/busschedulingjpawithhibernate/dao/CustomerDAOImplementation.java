package com.capgemini.busschedulingjpawithhibernate.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.capgemini.busschedulingjpawithhibernate.dto.Bus;
import com.capgemini.busschedulingjpawithhibernate.dto.Feedback;
import com.capgemini.busschedulingjpawithhibernate.dto.Ticket;

public class CustomerDAOImplementation implements CustomerDAO {
	EntityManagerFactory entityManagerFactory = null;
	EntityManager entityManager = null;
	EntityTransaction entityTransaction = null;

	@Override
	public Ticket bookTicket(int busId, int userId, int noOfSeats) {
		int seats = 0;
		Date journeyDate = null;
		entityManagerFactory = Persistence.createEntityManagerFactory("bookingTestPersistence");
		entityManager = entityManagerFactory.createEntityManager();
		entityTransaction = entityManager.getTransaction();
		Bus bus = entityManager.find(Bus.class, busId);
		seats = bus.getAvailableSeats();
		journeyDate = bus.getJourneyData();
		if (seats > 0 && seats >= noOfSeats) {
			try {
				entityTransaction.begin();
				Ticket ticket = new Ticket();
				Random random = new Random();
				int ticketId = random.nextInt(500);
				ticket.setTicketId(ticketId);
				ticket.setBusId(busId);
				ticket.setUserId(userId);
				ticket.setJourneyDate(journeyDate);
				ticket.setNoOfSeats(noOfSeats);
				ticket.setReservationDateTime(new java.sql.Timestamp(new java.util.Date().getTime()));
				entityManager.persist(ticket);
				bus.setAvailableSeats(seats - noOfSeats);
				entityTransaction.commit();
				return ticket;
			} catch (Exception e) {
				entityTransaction.rollback();
				e.printStackTrace();
			}
		} else {
			System.out.println("Seats not available");
		}
		return null;

	}

	@Override
	public boolean cancelTicket(int ticketId, int userId, int noOfSeats) {
		int availableSeats = 0;
		int busIds = 0;
		entityManagerFactory = Persistence.createEntityManagerFactory("bookingTestPersistence");
		entityManager = entityManagerFactory.createEntityManager();
		entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		Ticket ticket = entityManager.find(Ticket.class, ticketId);
		busIds = ticket.getBusId();
		try {
			entityManager.remove(ticket);
			Bus bus = entityManager.find(Bus.class, busIds);
			availableSeats = bus.getAvailableSeats();
			bus.setAvailableSeats(availableSeats + noOfSeats);
			entityTransaction.commit();
			return true;
		}catch (Exception e) {
			entityTransaction.rollback();
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Ticket getTicketInfo(int ticketId) {
		entityManagerFactory = Persistence.createEntityManagerFactory("bookingTestPersistence");
		entityManager = entityManagerFactory.createEntityManager();
		Ticket ticket = entityManager.find(Ticket.class, ticketId);
		entityManager.close();
		return ticket;
	}

	@Override
	public boolean giveFeedback(Feedback feedback) {
		entityManagerFactory = Persistence.createEntityManagerFactory("bookingTestPersistence");
		entityManager = entityManagerFactory.createEntityManager();
		entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		try {
			entityManager.persist(feedback);
			entityTransaction.commit();
			entityManager.close();
			return true;
		} catch (Exception e) {
			entityTransaction.rollback();
			e.printStackTrace();
		}
		return false;
	}
}
