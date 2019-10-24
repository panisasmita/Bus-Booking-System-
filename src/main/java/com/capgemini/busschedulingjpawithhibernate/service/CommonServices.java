package com.capgemini.busschedulingjpawithhibernate.service;

import java.util.List;

import com.capgemini.busschedulingjpawithhibernate.dto.Bus;
import com.capgemini.busschedulingjpawithhibernate.dto.Feedback;
import com.capgemini.busschedulingjpawithhibernate.dto.User;

public interface CommonServices {
	public List<Bus> exploreBus(String source, String destination);
	public List<Feedback> viewFeedback();
	public User login(int userId, String password);
	public boolean updateUser(User user);
}
