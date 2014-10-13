package com.coach.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccessPrivilegeDaoImpl extends SqlSessionDaoSupport implements AccessPrivilegeDao{
	private static final Logger log = LoggerFactory
			.getLogger(AccessPrivilegeDaoImpl.class);
	@Override
	public List<String> getByAppKey(String appkey) {
		return  this.getSqlSession().selectList("getByAppKey", appkey);
	}


}
