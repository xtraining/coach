package com.coach.dao;

import java.util.List;

import com.coach.model.User;
import com.coach.response.AreaResponse;



public interface AreaDao {
	
	public List<AreaResponse> getAllProvinces();

	public List<AreaResponse> getSubareaByCode(String areaCode);

}
