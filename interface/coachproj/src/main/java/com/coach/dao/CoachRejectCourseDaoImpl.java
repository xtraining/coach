package com.coach.dao;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coach.model.CoachRejectCourse;

public class CoachRejectCourseDaoImpl extends SqlSessionDaoSupport implements CoachRejectCourseDao{
	private static final Logger log = LoggerFactory
			.getLogger(CoachRejectCourseDaoImpl.class);

	@Override
	public void saveRejectCourse(Integer coachId, Long courseId) {
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("coachId", coachId);
			map.put("courseId", courseId);
			this.getSqlSession().insert("saveRejectCourse", map);
		} catch (RuntimeException re) {
			log.error("saveDeclinedCourse", re);
			throw re;
		}
		
	}

	@Override
	public CoachRejectCourse getRejectCourseDetail(Integer coachId, Long courseId) {
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("coachId", coachId);
			map.put("courseId", courseId);
			return this.getSqlSession().selectOne("getRejectCourseDetail", map);
		} catch (RuntimeException re) {
			log.error("getRejectCourseDetail", re);
			throw re;
		}
	}

}
