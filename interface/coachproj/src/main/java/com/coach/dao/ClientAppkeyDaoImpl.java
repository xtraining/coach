package com.coach.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coach.model.ClientAppkey;

public class ClientAppkeyDaoImpl extends SqlSessionDaoSupport implements ClientAppkeyDao{
	private static final Logger log = LoggerFactory
			.getLogger(ClientAppkeyDaoImpl.class);
	@Override
	public List<ClientAppkey> getAll() {
		try{
			return this.getSqlSession().selectList("getAll");
		} catch (RuntimeException re) {
			log.error("getAll", re);
			throw re;
		}
	}

	@Override
	public int getOsTypeByAppkey(String appKey) {
		try{
			return this.getSqlSession().selectOne("getOsTypeByAppkey", appKey);
		} catch (RuntimeException re) {
			log.error("getOsTypeByAppkey", re);
			throw re;
		}
	}


}
