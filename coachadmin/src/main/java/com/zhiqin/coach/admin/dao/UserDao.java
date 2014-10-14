package com.zhiqin.coach.admin.dao;


import com.zhiqin.coach.admin.entity.Roles;
import com.zhiqin.coach.admin.entity.User;


public interface UserDao extends BaseDao<User>{
	public int countUser(String userName,String userPassword);
	
	public User querySingleUser(String userName);
	
	public Roles findbyUserRole(String userId);
}
