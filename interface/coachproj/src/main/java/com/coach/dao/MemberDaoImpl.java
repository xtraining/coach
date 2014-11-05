package com.coach.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coach.common.Constants.COURSE_MEMBER_STATUS;
import com.coach.common.Constants.COURSE_STATUS;
import com.coach.common.Constants.LESSON_MEMBER_STATUS;
import com.coach.model.Member;
import com.coach.response.MemberDetailResponse;
import com.coach.response.SearchMemberResponse;

public class MemberDaoImpl extends SqlSessionDaoSupport implements MemberDao{
	private static final Logger log = LoggerFactory
			.getLogger(MemberDaoImpl.class);

	@Override
	public void save(Member m) {
		try{
			this.getSqlSession().insert("insertMember", m);
		} catch(RuntimeException e){
			log.error("save", e);
			throw e;
		}
		
	}

	@Override
	public List<Member> getMemberByCourseId(Long courseId) {
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("status", COURSE_MEMBER_STATUS.ACTIVE.getValue() +"");
			map.put("courseId", courseId);
			return this.getSqlSession().selectList("getMemberByCourseId", map);
		} catch(RuntimeException e){
			log.error("getMemberByCourseId", e);
			throw e;
		}
	}

	@Override
	public List<Member> getMemberByCoachId(Long coachId) {
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("cmDeletedStatus", COURSE_MEMBER_STATUS.DELETED.getValue());
			map.put("courseActiveStatus", COURSE_STATUS.ACTIVE.getValue());
			map.put("attendStatus", LESSON_MEMBER_STATUS.CHECK.getValue());
			map.put("coachId", coachId);
			return this.getSqlSession().selectList("getMemberByCoachId", map);
		} catch(RuntimeException e){
			log.error("getMemberByCoachId", e);
			throw e;
		}
	}

	@Override
	public List<Member> getMemberByCoachIdAndKeyword(Long coachId, String keyword) {
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("cmActiveStatus", COURSE_MEMBER_STATUS.ACTIVE.getValue());
			map.put("courseActiveStatus", COURSE_STATUS.ACTIVE.getValue());
			map.put("attendStatus", LESSON_MEMBER_STATUS.CHECK.getValue());
			map.put("coachId", coachId);
			map.put("keyword", "%" + keyword.toLowerCase() + "%");
			return this.getSqlSession().selectList("getMemberByCoachIdAndKeyword", map);
		} catch(RuntimeException e){
			log.error("getMemberByCoachIdAndKeyword", e);
			throw e;
		}
	}

	@Override
	public List<Member> getMemberByCoachId(Long coachId,
			Long courseId) {
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("cmDeletedStatus", COURSE_MEMBER_STATUS.DELETED.getValue());
			map.put("courseActiveStatus", COURSE_STATUS.ACTIVE.getValue());
			map.put("attendStatus", LESSON_MEMBER_STATUS.CHECK.getValue());
			map.put("coachId", coachId);
			map.put("courseId", courseId);
			return this.getSqlSession().selectList("getMemberDetailByCoachId", map);
		} catch(RuntimeException e){
			log.error("getMemberByCoachId", e);
			throw e;
		}
	}

	@Override
	public List<Member> getMemberWithAttendHistory(Long coachId, Long courseId, Long memberId) {
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("cmActiveStatus", COURSE_MEMBER_STATUS.ACTIVE.getValue());
			map.put("attendStatus", LESSON_MEMBER_STATUS.CHECK.getValue());
			map.put("courseId", courseId);
			map.put("memberId", memberId);
			map.put("coachId", coachId);
			return this.getSqlSession().selectList("getMemberWithAttendHistory", map);
		} catch(RuntimeException e){
			log.error("getMemberByCoachIdAndKeyword", e);
			throw e;
		}
	}

	@Override
	public void updateMember(Member m) {
		try{
			this.getSqlSession().update("updateMember", m);
		} catch(RuntimeException e){
			log.error("updateMember", e);
			throw e;
		}
		
	}

	@Override
	public void updateMemberStatus(Long memberId, Long courseId, Integer status) {
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("memberId", memberId);
			map.put("courseId", courseId);
			map.put("status", status);
			this.getSqlSession().update("updateMemberStatus", map);
		} catch(RuntimeException e){
			log.error("updateMemberStatus", e);
			throw e;
		}
		
		
	}

	@Override
	public Member getMemberByCourseIdAndMemberId(Long courseId, Long memberId) {
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("courseId", courseId);
			map.put("memberId", memberId);
			return this.getSqlSession().selectOne("getMemberByCourseIdAndMemberId", map);
		} catch(RuntimeException e){
			log.error("getMemberByCourseIdAndMemberId", e);
			throw e;
		}
	}

}
