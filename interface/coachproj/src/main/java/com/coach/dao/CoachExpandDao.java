package com.coach.dao;

import com.coach.model.CoachExpand;
import com.coach.model.SysSession;



public interface CoachExpandDao {

	void save(CoachExpand ex);

	void deleteBaiduBinding(Long coachId, String baiduUserId,
			String baiduChannelId);
	

}
