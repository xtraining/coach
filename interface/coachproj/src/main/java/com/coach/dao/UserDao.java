package com.coach.dao;

import com.coach.model.User;



public interface UserDao {
	

	public int insert(User user);
	
	public User getUserById(long id);
	
	public void excludeMethod();

}
