package com.coach.dao;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coach.model.CourseMember;
import com.coach.common.Constants.COURSE_MEMBER_STATUS;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class CourseMemberDaoImpl extends SqlSessionDaoSupport implements CourseMemberDao{
	private static final Logger log = LoggerFactory
			.getLogger(CourseMemberDaoImpl.class);

	@Override
	public void save(List<CourseMember> cmList) {
		try{
			this.getSqlSession().insert("batchInsertCourseMember", cmList);
		} catch(RuntimeException e){
			log.error("save", e);
			throw e;
		}
		
	}

	@Override
	public void deleteMember(Long courseId, String[] deletedMemberIdArr) {
		try{
			Map map = new HashMap();
			map.put("courseId", courseId);
			map.put("status", COURSE_MEMBER_STATUS.DELETED.getValue());
			map.put("deletedMemberIdArr", deletedMemberIdArr);
			this.getSqlSession().update("deleteCourseMember", map);
		} catch(RuntimeException e){
			log.error("deleteCourseMember", e);
			throw e;
		}
		
	}

}
