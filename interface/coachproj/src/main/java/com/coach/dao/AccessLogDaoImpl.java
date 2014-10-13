package com.coach.dao;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coach.model.AccessLog;

public class AccessLogDaoImpl extends SqlSessionDaoSupport implements AccessLogDao{
	private static final Logger log = LoggerFactory
			.getLogger(AccessLogDaoImpl.class);
	@Override
	public int save(AccessLog accessLog) {
		return this.getSqlSession().insert("insert", accessLog);
	}

}
