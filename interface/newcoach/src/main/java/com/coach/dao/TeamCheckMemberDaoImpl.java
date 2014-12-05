package com.coach.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coach.common.Constants.SMS_STATUS;
import com.coach.model.Member;
import com.coach.model.TeamMember;




public class TeamCheckMemberDaoImpl extends SqlSessionDaoSupport implements TeamCheckMemberDao{
	private static final Logger log = LoggerFactory
			.getLogger(TeamCheckMemberDaoImpl.class);

	@Override
	public List<Member> getSmsMemberList(Long teamId,  Long teamCheckId, List<Long> attendMemberIdList) {
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("teamId", teamId);
			map.put("teamCheckId", teamCheckId);
			map.put("attendMemberIdList", attendMemberIdList);
			return  this.getSqlSession().selectList("getSmsMemberList", map);
		} catch(RuntimeException e){
			log.error("getSmsMemberList", e);
			throw e;
		}
	}

	@Override
	public void deleteByTeamCheckId(Long teamCheckId) {
		try{
			 this.getSqlSession().delete("deleteByTeamCheckId", teamCheckId);
		} catch(RuntimeException e){
			log.error("deleteByTeamCheckId", e);
			throw e;
		}
		
	}

	@Override
	public void saveAttend(Long teamCheckId, Long teamId, List<Long> attendMemberIdList) {
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("teamId", teamId);
			map.put("attendMemberIdList", attendMemberIdList);
			map.put("teamCheckId", teamCheckId);
			 this.getSqlSession().delete("saveAttend", map);
		} catch(RuntimeException e){
			log.error("saveAttend", e);
			throw e;
		}
		
	}

	@Override
	public void saveAbcent(Long teamCheckId, Long teamId, List<Long> attendMemberIdList) {
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("teamId", teamId);
			map.put("attendMemberIdList", attendMemberIdList);
			map.put("teamCheckId", teamCheckId);
			 this.getSqlSession().delete("saveAbcent", map);
		} catch(RuntimeException e){
			log.error("saveAbcent", e);
			throw e;
		}
		
	}

	
}