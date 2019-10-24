package com.capgemini.busschedulingjpawithhibernate.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


import com.capgemini.busschedulingjpawithhibernate.dto.User;

public class AdminDAOImplementation implements AdminDAO {

	EntityManagerFactory entityManagerFactory = null;
	EntityManager entityManager = null;
	EntityTransaction entityTransaction = null;
	
	@Override
	public User registerUser(User user) {
		entityManagerFactory = Persistence.createEntityManagerFactory("bookingTestPersistence");
		entityManager = entityManagerFactory.createEntityManager();
		entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		try {
			entityManager.persist(user);
			entityTransaction.commit();
			entityManager.close();
			return user;
		} 
		catch (Exception e) {
			entityTransaction.rollback();
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean deleteUser(int userId) {
		entityManagerFactory = Persistence.createEntityManagerFactory("bookingTestPersistence");
		entityManager = entityManagerFactory.createEntityManager();
		entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		try {
			User user = entityManager.find(User.class, userId);
			entityManager.remove(user);
			entityTransaction.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			entityTransaction.rollback();
		}
		entityManager.close();
		return false;
	}


	

	
}
