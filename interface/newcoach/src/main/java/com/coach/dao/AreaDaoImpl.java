package com.coach.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coach.response.AreaResponse;

public class AreaDaoImpl extends SqlSessionDaoSupport implements AreaDao{
	private static final Logger log = LoggerFactory
			.getLogger(AreaDaoImpl.class);

	@Override
	public List<AreaResponse> getAllProvinces() {
		try{
			return this.getSqlSession().selectList("getAllProvinces");
		} catch (RuntimeException re) {
			log.error("getAllProvinces", re);
			throw re;
		}
	}

	@Override
	public List<AreaResponse> getSubareaByCode(String areaCode) {
		try{
			Map<String, String> map = new HashMap<String, String>();
			map.put("code", areaCode);
			map.put("subcode", StringUtils.replace(areaCode, "00", "%"));
			return this.getSqlSession().selectList("getSubareaByCode", map);
		} catch (RuntimeException re) {
			log.error("getAllProvinces", re);
			throw re;
		}
	}

}
