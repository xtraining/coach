package com.coach.dao;

import java.util.List;

import com.coach.response.AreaResponse;



public interface AreaDao {
	
	public List<AreaResponse> getAllProvinces();

	public List<AreaResponse> getSubareaByCode(String areaCode);

}
