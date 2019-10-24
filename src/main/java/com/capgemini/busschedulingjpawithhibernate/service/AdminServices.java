package com.capgemini.busschedulingjpawithhibernate.service;

import com.capgemini.busschedulingjpawithhibernate.dto.User;

public interface AdminServices {
	public User registerUser(User user);
	public boolean deleteUser(int userId);	
}