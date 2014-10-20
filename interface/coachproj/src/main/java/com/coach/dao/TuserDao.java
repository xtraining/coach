package com.coach.dao;

import com.coach.model.Tuser;




public interface TuserDao {

	public void save(Tuser c);
	

	public Long getByPhoneNumber(String phoneNumber);
}
