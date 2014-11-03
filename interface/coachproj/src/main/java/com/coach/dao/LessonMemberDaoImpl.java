package com.coach.dao;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coach.common.Constants.COURSE_MEMBER_STATUS;
import com.coach.common.Constants.LESSON_MEMBER_STATUS;
import com.coach.model.LessonMember;
import com.coach.model.Member;

public class LessonMemberDaoImpl extends SqlSessionDaoSupport implements LessonMemberDao{
	private static final Logger log = LoggerFactory
			.getLogger(LessonMemberDaoImpl.class);

	@Override
	public void save(List<LessonMember> cmList) {
		try{
			this.getSqlSession().insert("batchInsertLessonMember", cmList);
		} catch(RuntimeException e){
			log.error("save", e);
			throw e;
		}
	}

	@Override
	public List<Member> getLessonMember(Long lessonId) {
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("deletedStatus", LESSON_MEMBER_STATUS.DELETED.getValue());
			map.put("lessonId", lessonId);
			return this.getSqlSession().selectList("getLessonMember", map);
		} catch(RuntimeException e){
			log.error("getLessonMember", e);
			throw e;
		}
	}

	@Override
	public void updateStatus(Long lessonId, String[] memberIds, LESSON_MEMBER_STATUS status){
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("memberIds", Arrays.asList(memberIds));
			map.put("lessonId", lessonId);
			map.put("status", status.getValue());
			this.getSqlSession().update("updateLessonMemberStatus", map);
		} catch(RuntimeException e){
			log.error("updateStatus", e);
			throw e;
		}
		
	}

	@Override
	public void insertLessonMember(Long courseId, Long lessonId) {
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("lessonId", lessonId);
			map.put("courseId", courseId);
			map.put("status", COURSE_MEMBER_STATUS.ACTIVE.getValue());
			this.getSqlSession().insert("insertLessonMember", map);
		} catch(RuntimeException e){
			log.error("insertLessonMemeber", e);
			throw e;
		}
		
	}

}
