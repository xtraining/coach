package com.coach.dao;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppVersionDaoImpl extends SqlSessionDaoSupport implements AppVersionDao{
	private static final Logger log = LoggerFactory
			.getLogger(AppVersionDaoImpl.class);
	@Override
	public Integer getIdByVersion(String appVersion, int osType) {
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("appVersion", appVersion);
			map.put("osType", osType);
			return  this.getSqlSession().selectOne("getIdByVersion", map);
		} catch(RuntimeException e){
			log.error("getIdByVersion", e);
			throw e;
		}
	}



}
