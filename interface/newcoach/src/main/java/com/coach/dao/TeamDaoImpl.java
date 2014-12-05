package com.coach.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coach.model.Team;
import com.coach.model.TeamCheck;




public class TeamDaoImpl extends SqlSessionDaoSupport implements TeamDao{
	private static final Logger log = LoggerFactory
			.getLogger(TeamDaoImpl.class);

	@Override
	public void insert(Team t) {
		try{
			this.getSqlSession().insert("insertTeam", t);
		} catch(RuntimeException e){
			log.error("insertTeam", e);
			throw e;
		}
		
	}

	@Override
	public void updateTeam(Team t) {
		try{
			 this.getSqlSession().update("updateTeam", t);
		} catch(RuntimeException e){
			log.error("updateTeam", e);
			throw e;
		}
		
	}

	@Override
	public List<Team> getListByCoachId(Long coachId, Integer status) {
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("coachId", coachId);
			map.put("status", status);
			return  this.getSqlSession().selectList("getListByCoachId", map);
		} catch(RuntimeException e){
			log.error("getListByCoachId", e);
			throw e;
		}
	}

	@Override
	public void updateTeamStatus(Team t) {
		try{
			 this.getSqlSession().update("updateTeamStatus", t);
		} catch(RuntimeException e){
			log.error("updateTeamStatus", e);
			throw e;
		}
		
		
	}
	
}