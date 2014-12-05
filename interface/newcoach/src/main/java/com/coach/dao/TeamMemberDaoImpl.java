package com.coach.dao;

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
	
}