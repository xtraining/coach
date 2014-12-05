package com.coach.dao;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coach.model.Member;
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

	@Override
	public Long getLatestCheck(Long teamId,
			Timestamp startTime) {
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("teamId", teamId);
			map.put("startTime", startTime);
			return this.getSqlSession().selectOne("getLatestCheck", map);
		} catch(RuntimeException e){
			log.error("getLatestCheck", e);
			throw e;
		}
	}

	@Override
	public List<Member> getMemberByCheckId(Long coachId, Long teamCheckId) {
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("teamCheckId", teamCheckId);
			map.put("coachId", coachId);
			return this.getSqlSession().selectList("getMemberByCheckId", map);
		} catch(RuntimeException e){
			log.error("getMemberByCheckId", e);
			throw e;
		}
	}
	

	@Override
	public List<TeamCheck> getTeamCheckHistory(Long teamId, Integer pageNumber,
			Integer pageSize) {
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("teamId", teamId);
			map.put("pageNumber", pageNumber);
			map.put("pageSize", pageSize);
			return this.getSqlSession().selectList("getTeamCheckHistory", map);
		} catch(RuntimeException e){
			log.error("getTeamCheckHistory", e);
			throw e;
		}
		
	}

	@Override
	public List<TeamCheck> getMemberCheckHistory(Long coachId, Long teamId,
			Long memberId) {
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("teamId", teamId);
			map.put("coachId", coachId);
			map.put("memberId", memberId);
			return this.getSqlSession().selectList("getMemberCheckHistory", map);
		} catch(RuntimeException e){
			log.error("getMemberCheckHistory", e);
			throw e;
		}
	}

	@Override
	public List<TeamMember> getTeamMemberListByPhoneNumber(Long coachId,
			String phoneNumber) {
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("coachId", coachId);
			map.put("phoneNumber", phoneNumber);
			return this.getSqlSession().selectList("getTeamMemberListByPhoneNumber", map);
		} catch(RuntimeException e){
			log.error("getTeamMemberListByPhoneNumber", e);
			throw e;
		}
	}
}