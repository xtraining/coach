package com.coach.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coach.model.TeamMember;




public class TeamMemberDaoImpl extends SqlSessionDaoSupport implements TeamMemberDao{
	private static final Logger log = LoggerFactory
			.getLogger(TeamMemberDaoImpl.class);

	@Override
	public void insert(TeamMember t) {
		try{
			this.getSqlSession().insert("insertTeamMember", t);
		} catch(RuntimeException e){
			log.error("insertTeamMember", e);
			throw e;
		}
		
	}
	

	@Override
	public void changeMemberToDone(Long teamId, List<Long> list) {
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("teamId", teamId);
			map.put("list",list);
			this.getSqlSession().update("updateMemberTeam", map);
		} catch(RuntimeException e){
			log.error("updateMemberTeam", e);
			throw e;
		}
		
	}
	
}