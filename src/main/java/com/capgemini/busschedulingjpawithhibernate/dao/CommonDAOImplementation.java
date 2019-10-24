package com.capgemini.busschedulingjpawithhibernate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.capgemini.busschedulingjpawithhibernate.dto.Bus;
import com.capgemini.busschedulingjpawithhibernate.dto.Feedback;
import com.capgemini.busschedulingjpawithhibernate.dto.User;

public class CommonDAOImplementation implements CommonDAO{
	EntityManagerFactory entityManagerFactory = null;
	EntityManager entityManager = null;
	EntityTransaction entityTransaction = null;

	@Override
	public List<Bus> exploreBus(String source, String destination) {
		List<Bus> busList = null;
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("bookingTestPersistence");
			entityManager = entityManagerFactory.createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			Query query=entityManager.createQuery("FROM Bus WHERE source=: src AND destination=: dstn");
			query.setParameter("src", source);
			query.setParameter("dstn", destination);
			busList=query.getResultList();
			entityTransaction.commit();
			entityManager.close();
			return busList;
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return null;
	}

	@Override
	public List<Feedback> viewFeedback() {
		List<Feedback> feedback=null;
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("bookingTestPersistence");
			entityManager = entityManagerFactory.createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			TypedQuery<Feedback> query=entityManager.createQuery("FROM feedback_details", Feedback.class);
			feedback=query.getResultList();
			entityTransaction.commit();
			entityManager.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User login(int userId, String password) {
		entityManagerFactory = Persistence.createEntityManagerFactory("bookingTestPersistence");
		entityManager = entityManagerFactory.createEntityManager();
		User user = entityManager.find(User.class, userId);
		if(user.getPassword().equals(password)) {
			return user;
		}
		entityManager.close();
		return null;
	}

	@Override
	public boolean updateUser(User user) {
		entityManagerFactory = Persistence.createEntityManagerFactory("bookingTestPersistence");
		entityManager = entityManagerFactory.createEntityManager();
		entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		try {
			User users = entityManager.find(User.class, user.getUserId());
			if(users!=null) {
				users.setUserName(user.getUserName());
				users.setEmailId(user.getEmailId());
				users.setContactNumber(user.getContactNumber() );
				users.setPassword(user.getPassword());
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
}
