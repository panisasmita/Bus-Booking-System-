package com.capgemini.busschedulingjpawithhibernate.service;

import java.util.List;

import com.capgemini.busschedulingjpawithhibernate.dao.CommonDAO;
import com.capgemini.busschedulingjpawithhibernate.dao.CommonDAOImplementation;
import com.capgemini.busschedulingjpawithhibernate.dto.Bus;
import com.capgemini.busschedulingjpawithhibernate.dto.Feedback;
import com.capgemini.busschedulingjpawithhibernate.dto.User;

public class CommonServicesImplementation implements CommonServices {

	CommonDAO commonDAO = new CommonDAOImplementation();

	@Override
	public List<Bus> exploreBus(String source, String destination) {
		return commonDAO.exploreBus(source, destination);
	}

	@Override
	public List<Feedback> viewFeedback() {
		return commonDAO.viewFeedback();
	}

	@Override
	public User login(int userId, String password) {
		return commonDAO.login(userId, password);
	}

	@Override
	public boolean updateUser(User user) {
		return commonDAO.updateUser(user);
	}
	
}
