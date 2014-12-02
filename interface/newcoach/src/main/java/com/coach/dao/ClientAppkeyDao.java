package com.coach.dao;

import java.util.List;

import com.coach.model.ClientAppkey;



public interface ClientAppkeyDao {

	public List<ClientAppkey> getAll();

	public int getOsTypeByAppkey(String appKey);

}
