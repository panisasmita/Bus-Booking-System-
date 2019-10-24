package com.capgemini.busschedulingjpawithhibernate.dao;

import com.capgemini.busschedulingjpawithhibernate.dto.User;

public interface AdminDAO {
	public User registerUser(User user);
	public boolean deleteUser(int userId);
}
