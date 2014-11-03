package com.coach.dao;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coach.common.Constants;
import com.coach.common.Constants.COACH_COURSE_STATUS;
import com.coach.common.Constants.COURSE_MEMBER_STATUS;
import com.coach.common.Constants.COURSE_STATUS;
import com.coach.common.Constants.COURSE_TYPE;
import com.coach.common.Constants.LESSON_STATUS;
import com.coach.common.Constants.ORG_COACH_STATUS;
import com.coach.model.Course;
import com.coach.request.GetOrgCourseRequest;
import com.coach.response.PersonalCourseResponse;

public class CourseDaoImpl extends SqlSessionDaoSupport implements CourseDao{
	private static final Logger log = LoggerFactory
			.getLogger(CourseDaoImpl.class);

	@Override
	public List<Course> getChiefCourse(Long coachId, int maxNum) {
		try{
			Map<String, Object> map = buildParam(coachId, maxNum);
			return this.getSqlSession().selectList("getChiefCourse", map);
		} catch (RuntimeException re) {
			log.error("getChiefCourse", re);
			throw re;
		}
	}

	@Override
	public List<Course> getOrgChiefCourse(Long coachId, int maxNum) {
		try{
			Map<String, Object> map = buildParam(coachId, maxNum);
			return this.getSqlSession().selectList("getOrgChiefCourse", map);
		} catch (RuntimeException re) {
			log.error("getOrgChiefCourse", re);
			throw re;
		}
	}
	
	@Override
	public List<Course> getPersonalChiefCourse(Long coachId, int maxNum) {
		try{
			Map<String, Object> map = buildParam(coachId, maxNum);
			return this.getSqlSession().selectList("getPersonalChiefCourse", map);
		} catch (RuntimeException re) {
			log.error("getPersonalChiefCourse", re);
			throw re;
		}
	}
	
	private Map<String, Object> buildParam(Long coachId, int maxNum) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("coachId", coachId);
		map.put("maxNum", maxNum);
		map.put("acceptedStatus", COACH_COURSE_STATUS.ACCEPTED.getValue());
		map.put("courseStatus", COURSE_STATUS.ACTIVE.getValue());
		map.put("bindStatus", Arrays.asList(ORG_COACH_STATUS.COACH_ACCEPTED.getValue(), ORG_COACH_STATUS.ORG_ACCEPTED.getValue()));
		map.put("orgType", COURSE_TYPE.ORG.getValue());
		map.put("personalType", COURSE_TYPE.PERSONAL.getValue());
		return map;
	}
	
	@Override
	public Long checkNewCourse(Long coachId) {
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("coachId", coachId);
			map.put("draftStatus", COACH_COURSE_STATUS.DRAFT.getValue());
			map.put("courseStatus", COURSE_STATUS.ACTIVE.getValue());
			map.put("bindStatus", Arrays.asList(ORG_COACH_STATUS.COACH_ACCEPTED.getValue(), ORG_COACH_STATUS.ORG_ACCEPTED.getValue()));
			map.put("orgType", COURSE_TYPE.ORG.getValue());
			return this.getSqlSession().selectOne("checkNewCourse", map);
		} catch (RuntimeException re) {
			log.error("checkNewCourse", re);
			throw re;
		}
	}

	@Override
	public List<Course> getNewCourse(Long coachId,
			Integer pageNumber, Integer pageSize) {
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("coachId", coachId);
			map.put("courseStatus", COURSE_STATUS.ACTIVE.getValue());
			map.put("bindStatus", Arrays.asList(ORG_COACH_STATUS.COACH_ACCEPTED.getValue(), ORG_COACH_STATUS.ORG_ACCEPTED.getValue()));
			map.put("orgType", COURSE_TYPE.ORG.getValue());
			map.put("pageNumber", (pageNumber-1) * pageSize);
			map.put("pageSize", pageSize);
			return this.getSqlSession().selectList("getNewCourse", map);
		} catch (RuntimeException re) {
			log.error("checkNewCourse", re);
			throw re;
		}
	}

	@Override
	public void insert(Course c) {
		try{
			this.getSqlSession().insert("insertCourse", c);
		} catch (RuntimeException re) {
			log.error("insert", re);
			throw re;
		}
	}

	@Override
	public Course getCourseDetail(Long coachId, Long courseId) {
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("coachId", coachId);
			map.put("courseId", courseId);
			map.put("status", COACH_COURSE_STATUS.ACCEPTED.getValue());
			map.put("cmstatus", COURSE_MEMBER_STATUS.DELETED.getValue());
			return this.getSqlSession().selectOne("getCourseDetail", map);
		} catch (RuntimeException re) {
			log.error("getCourseDetail", re);
			throw re;
		}
	}

	@Override
	public List<Course> getOrgCourse(GetOrgCourseRequest request) {
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("coachId", request.getCoachId());
			map.put("orgId", request.getOrgId());
			map.put("acceptedStatus", COACH_COURSE_STATUS.ACCEPTED.getValue());
			map.put("courseStatus", COURSE_STATUS.ACTIVE.getValue());
			map.put("bindStatus", Arrays.asList(ORG_COACH_STATUS.COACH_ACCEPTED.getValue(), ORG_COACH_STATUS.ORG_ACCEPTED.getValue()));
			map.put("orgType", COURSE_TYPE.ORG.getValue());
			int pageNumber = request.getPageNumber();
			int pageSize = request.getPageSize();
			map.put("pageNumber", (pageNumber-1) * pageSize + Constants.COURSE_OFFSET); //5 是默认显示的偏移量
			map.put("pageSize", pageSize);
			return this.getSqlSession().selectList("getOrgCourse", map);
		} catch (RuntimeException re) {
			log.error("getOrgCourse", re);
			throw re;
		}
	}

	@Override
	public List<PersonalCourseResponse> getPersonalCourseList(Long coachId) {
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("coachId", coachId);
			map.put("acceptedStatus", COACH_COURSE_STATUS.ACCEPTED.getValue());
			map.put("courseStatus", COURSE_STATUS.ACTIVE.getValue());
			map.put("deletedStatus", COURSE_MEMBER_STATUS.DELETED.getValue());
			map.put("personalType", COURSE_TYPE.PERSONAL.getValue());
			return this.getSqlSession().selectList("getPersonalCourse", map);
		} catch (RuntimeException re) {
			log.error("getOrgCourse", re);
			throw re;
		}
	}

	@Override
	public Long checkCourse(Long coachId, Long courseId) {
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("coachId", coachId);
			map.put("courseId", courseId);
			return this.getSqlSession().selectOne("checkCourse", map);
		} catch (RuntimeException re) {
			log.error("getOrgCourse", re);
			throw re;
		}
	}

	@Override
	public void deleteCourse(Long coachId, Long courseId) {
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("coachId", coachId);
			map.put("courseId", courseId);
			map.put("status", COURSE_STATUS.DELETED.getValue());
			this.getSqlSession().update("deleteCourse", map);
		} catch (RuntimeException re) {
			log.error("deleteCourse", re);
			throw re;
		}
		
	}

	@Override
	public List<Course> getUnassignedCourse(Long coachId) {
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("coachId", coachId);
			map.put("acceptedStatus", COACH_COURSE_STATUS.ACCEPTED.getValue());
			map.put("courseStatus", COURSE_STATUS.ACTIVE.getValue());
			map.put("personalType", COURSE_TYPE.PERSONAL.getValue());
			map.put("lessonActiveStatus", LESSON_STATUS.ACTIVE.getValue());
			return this.getSqlSession().selectList("getUnassignedCourse", map);
		} catch (RuntimeException re) {
			log.error("getUnassignedCourse", re);
			throw re;
		}
	}

	@Override
	public List<Map<String, Object>> getCourseNum(Long coachId) {
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("coachId", coachId);
			map.put("acceptedStatus", COACH_COURSE_STATUS.ACCEPTED.getValue());
			map.put("courseStatus", COURSE_STATUS.ACTIVE.getValue());
			return this.getSqlSession().selectList("getCourseNum", map);
		} catch (RuntimeException re) {
			log.error("getCourseNum", re);
			throw re;
		}
	}

}
