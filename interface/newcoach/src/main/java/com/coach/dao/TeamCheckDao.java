package com.coach.dao;

import com.coach.model.TeamCheck;





public interface TeamCheckDao {

	void updateTime(Long id);

	void insert(TeamCheck check);

	void updateLoaction(Long teamCheckId, String province, String city,
			String district, String street, String streetNumber);


}
