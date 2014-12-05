package com.coach.dao;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coach.model.TeamCheck;
import com.coach.model.TeamMember;




public class TeamCheckDaoImpl extends SqlSessionDaoSupport implements TeamCheckDao{
	private static final Logger log = LoggerFactory
			.getLogger(TeamCheckDaoImpl.class);

	@Override
	public void updateTime(Long teamCheckId) {
		try{
			this.getSqlSession().update("updateTime", teamCheckId);
		} catch(RuntimeException e){
			log.error("updateTime", e);
			throw e;
		}
		
	}

	@Override
	public void insert(TeamCheck check) {
		try{
			this.getSqlSession().insert("insertTeamCheck", check);
		} catch(RuntimeException e){
			log.error("insertTeamCheck", e);
			throw e;
		}
		
	}

	@Override
	public void updateLoaction(Long teamCheckId, String province, String city,
			String district, String street, String streetNumber) {
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("teamCheckId", teamCheckId);
			map.put("province", province);
			map.put("city", city);
			map.put("district", district);
			map.put("street", street);
			map.put("streetNumber", streetNumber);
			this.getSqlSession().update("updateLoaction", map);
		} catch(RuntimeException e){
			log.error("updateLoaction", e);
			throw e;
		}
	}
	
}