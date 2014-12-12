package com.coach.dao;

import java.sql.Timestamp;

import com.coach.model.SysSession;



public interface SysSessionDao {

	SysSession getValidSessionBySessionId(String sessionId);

	void invalidSessionBySessionId(String sessionId);

	void save(SysSession session);

	void removeInalidSession(Timestamp timestamp);

	void updateLastAccessTime(String sessionId);
	

}
