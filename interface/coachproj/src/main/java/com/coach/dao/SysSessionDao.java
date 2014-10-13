package com.coach.dao;

import com.coach.model.SysSession;



public interface SysSessionDao {

	SysSession getValidSessionBySessionId(String sessionId);

	void invalidSessionBySessionId(String sessionId);

	void save(SysSession session);
	

}
