package com.coach.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coach.model.Member;




public class MemberDaoImpl extends SqlSessionDaoSupport implements MemberDao{
	private static final Logger log = LoggerFactory
			.getLogger(MemberDaoImpl.class);

	@Override
	public void insert(Member t) {
		try{
			this.getSqlSession().insert("insertMember", t);
		} catch(RuntimeException e){
			log.error("insertMember", e);
			throw e;
		}
		
	}

	@Override
	public List<Member> getList(Long coachId, Long teamId) {
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("coachId", coachId);
			map.put("teamId", teamId);
			return  this.getSqlSession().selectList("getMemberList", map);
		} catch(RuntimeException e){
			log.error("getMemberList", e);
			throw e;
		}
	}

	@Override
	public void update(Long coachId, Long teamId, Member m) {
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("coachId", coachId);
			map.put("teamId", teamId);
			map.put("mdto", m);
			this.getSqlSession().update("updateMember", map);
		} catch(RuntimeException e){
			log.error("updateMember", e);
			throw e;
		}
		
	}

	@Override
	public void delete(Long coachId, Long teamId, Long memberId) {
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("coachId", coachId);
			map.put("teamId", teamId);
			map.put("memberId",memberId);
			this.getSqlSession().update("deleteMember", map);
		} catch(RuntimeException e){
			log.error("deleteMember", e);
			throw e;
		}
		
	}

	@Override
	public void delete(Long coachId, Long teamId, List<Long> list) {
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("coachId", coachId);
			map.put("teamId", teamId);
			map.put("list",list);
			this.getSqlSession().update("deleteMemberInBatch", map);
		} catch(RuntimeException e){
			log.error("deleteMemberInBatch", e);
			throw e;
		}
		
	}

	
}