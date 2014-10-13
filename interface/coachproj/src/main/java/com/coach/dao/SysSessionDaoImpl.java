package com.coach.dao;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coach.model.SysSession;

public class SysSessionDaoImpl extends SqlSessionDaoSupport implements SysSessionDao{
	private static final Logger log = LoggerFactory
			.getLogger(SysSessionDaoImpl.class);
	@Override
	public SysSession getValidSessionBySessionId(String sessionId) {
		try{
			return this.getSqlSession().selectOne("getValidSessionBySessionId", sessionId);
		} catch(RuntimeException e){
			log.error("getValidSessionBySessionId", e);
			throw e;
		}
	}

	@Override
	public void invalidSessionBySessionId(String sessionId) {
		try{
			this.getSqlSession().delete("invalidSessionBySessionId", sessionId);
		} catch(RuntimeException e){
			log.error("invalidSessionBySessionId", e);
			throw e;
		}
		
	}

	@Override
	public void save(SysSession session) {
		try{
			this.getSqlSession().insert("insertSysSession", session);
		} catch(RuntimeException e){
			log.error("save", e);
			throw e;
		}
		
	}


}
