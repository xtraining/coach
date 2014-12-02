package com.coach.dao;

import java.util.List;

import com.coach.model.AccessPrivilege;



public interface AccessPrivilegeDao {
	

	public List<String> getByAppKey(String appkey);

}
