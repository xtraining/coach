package com.coach.dao;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coach.common.Constants.COACH_COURSE_STATUS;
import com.coach.common.Constants.COURSE_STATUS;
import com.coach.model.CoachCourse;
import com.coach.model.User;

public class CoachCourseDaoImpl extends SqlSessionDaoSupport implements CoachCourseDao{
	private static final Logger log = LoggerFactory
			.getLogger(CoachCourseDaoImpl.class);

	@Override
	public void save(CoachCourse cc) {
		try{
			this.getSqlSession().insert("insertCoachCourse", cc);
		} catch(RuntimeException e){
			log.error("save", e);
			throw e;
		}
		
		
	}

	@Override
	public void updateStatus(Integer coachId, Long courseId,
			COACH_COURSE_STATUS status) {
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("coachId", coachId);
			map.put("courseId", courseId);
			map.put("status", status.getValue());
			this.getSqlSession().update("updateCoachCourseStatus", map);
		} catch (RuntimeException re) {
			log.error("updateCoachCourseStatus", re);
			throw re;
		}
		
	}


}
