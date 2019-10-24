package com.capgemini.busschedulingjpawithhibernate.service;

import com.capgemini.busschedulingjpawithhibernate.dao.AdminDAO;
import com.capgemini.busschedulingjpawithhibernate.dao.AdminDAOImplementation;
import com.capgemini.busschedulingjpawithhibernate.dto.User;

public class AdminServicesImplementation  implements AdminServices{

	AdminDAO adminDAO = new AdminDAOImplementation();
	@Override
	public User registerUser(User user) {
		return adminDAO.registerUser(user);
	}

	@Override
	public boolean deleteUser(int userId) {
		return adminDAO.deleteUser(userId);
	}

}
