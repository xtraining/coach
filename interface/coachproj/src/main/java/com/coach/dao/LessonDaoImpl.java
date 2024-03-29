package com.coach.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coach.common.Constants.COACH_COURSE_STATUS;
import com.coach.common.Constants.COURSE_STATUS;
import com.coach.common.Constants.LESSON_CHECK_FLAG;
import com.coach.common.Constants.LESSON_STATUS;
import com.coach.model.Lesson;
import com.coach.request.GetCheckLessonRequest;
import com.coach.request.UpdateLessonRequest;
import com.coach.request.UpdateLifeRequest;
import com.coach.utils.DateUtils;

public class LessonDaoImpl extends SqlSessionDaoSupport implements LessonDao{
	private static final Logger log = LoggerFactory
			.getLogger(LessonDaoImpl.class);
	
	@Override
	public int insert(Lesson lesson) {
		try{
			return this.getSqlSession().insert("insertLesson", lesson);
		} catch(RuntimeException e){
			log.error("insert", e);
			throw e;
		}
	}

	@Override
	public List<Lesson> getLessonInRange(Long coachId, Date startDate,
			Date endDate) {
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("coachId", coachId);
			map.put("startDate", startDate);
			map.put("endDate", endDate);
			return this.getSqlSession().selectList("getLessonInRange", map);
		} catch(RuntimeException e){
			log.error("getMemberByCourseId", e);
			throw e;
		}
	}

	@Override
	public Map<String, Object> getNewsFlag(Long coachId) {
		try{
			return this.getSqlSession().selectOne("getNewsFlag", coachId);
		} catch(Throwable e){
			log.error("getNewsFlag", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public Lesson getLessonDetail(Long coachId, Long lessonId) {
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("coachId", coachId);
			map.put("lessonId", lessonId);
			return this.getSqlSession().selectOne("getLessonDetail", map);
		} catch(Throwable e){
			log.error("getLessonDetail", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Lesson> getDetailRecentInRange(Long coachId, Date startDate,
			Date endDate) {
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("coachId", coachId);
			map.put("startDate", startDate);
			map.put("endDate", endDate);
			return this.getSqlSession().selectList("getDetailRecentInRange", map);
		} catch(Throwable e){
			log.error("getDetailRecentInRange", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public void changeCheckFlag(Long lessonId) {
		try{
			this.getSqlSession().update("changeCheckFlag", lessonId);
		} catch(Throwable e){
			log.error("changeCheckFlag", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Map> getTotalLessonNum(Long coachId, Date startDate) {
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("coachId", coachId);
			map.put("startDate", startDate);
			map.put("courseActiveStatus", COURSE_STATUS.ACTIVE.getValue());
			map.put("lessonActiveStatus", LESSON_STATUS.ACTIVE.getValue());
			map.put("lessonCheckedFlag", LESSON_CHECK_FLAG.CHECKED.getValue());
			return this.getSqlSession().selectList("getTotalLessonNum", map);
		} catch(Throwable e){
			log.error("getTotalLessonNum", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public Double getPercent(Integer percent, Date startDate) {
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("percent", percent);
			map.put("startDate", startDate);
			map.put("courseAcceptedStatus", COACH_COURSE_STATUS.ACCEPTED.getValue());
			map.put("courseActiveStatus", COURSE_STATUS.ACTIVE.getValue());
			map.put("lessonActiveStatus", LESSON_STATUS.ACTIVE.getValue());
			map.put("lessonCheckedFlag", LESSON_CHECK_FLAG.CHECKED.getValue());
			return this.getSqlSession().selectOne("getPercent", map);
		} catch(Throwable e){
			log.error("getPercent", e);
			throw new RuntimeException(e);
		}

	}

	@Override
	public List<Lesson> getCheckLesson(GetCheckLessonRequest request, Date startDate) {
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("coachId", request.getCoachId());
			map.put("startDate", startDate);
			map.put("courseActiveStatus", COURSE_STATUS.ACTIVE.getValue());
			map.put("lessonActiveStatus", LESSON_STATUS.ACTIVE.getValue());
			map.put("lessonCheckedFlag", LESSON_CHECK_FLAG.CHECKED.getValue());
			map.put("acceptedStatus", COACH_COURSE_STATUS.ACCEPTED.getValue());
			map.put("pageNumber", (request.getPageNumber()-1) * request.getPageSize());
			map.put("pageSize", request.getPageSize());
			return this.getSqlSession().selectList("getCheckLesson", map);
		} catch(Throwable e){
			log.error("getCheckLesson", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Lesson> getLessonFrom(Long coachId, Timestamp startTime) {
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("coachId", coachId);
			String dateStr = DateUtils.dateToyyyyMMdd(startTime);
			map.put("startDate", DateUtils.yyyyMMddToDate(dateStr));
			map.put("courseActiveStatus", COURSE_STATUS.ACTIVE.getValue());
			map.put("lessonActiveStatus", LESSON_STATUS.ACTIVE.getValue());
			map.put("acceptedStatus", COACH_COURSE_STATUS.ACCEPTED.getValue());
			return this.getSqlSession().selectList("getLessonFrom", map);
		} catch(Throwable e){
			log.error("getLessonFrom", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public void deleteLesson(Long coachId, Long courseId) {
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("coachId", coachId);
			map.put("courseId", courseId);
			map.put("status", LESSON_STATUS.DELETED.getValue());
			this.getSqlSession().update("deleteLesson", map);
		} catch (RuntimeException re) {
			log.error("deleteLesson", re);
			throw re;
		}
		
	}

	@Override
	public void updateLessonStatus(Long lessonId, LESSON_STATUS deleted) {
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("lessonId", lessonId);
			map.put("status", deleted.getValue());
			this.getSqlSession().update("updateLessonStatus", map);
		} catch (RuntimeException re) {
			log.error("updateLessonStatus", re);
			throw re;
		}
		
		
	}

	@Override
	public Lesson getLifeDetail(Long coachId, Long lessonId) {
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("coachId", coachId);
			map.put("lessonId", lessonId);
			return this.getSqlSession().selectOne("getLifeDetail", map);
		} catch(Throwable e){
			log.error("getLifeDetail", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public void updateLesson(UpdateLessonRequest request, Timestamp startTime,
			Timestamp endTime) {
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("coachId", request.getCoachId());
			map.put("lessonId", request.getLessonId());
			map.put("startTime", startTime);
			map.put("endTime", endTime);
			map.put("description", request.getRemarks());
			map.put("alertSwitch", request.getAlertSwitch());
			map.put("address", request.getAddress());
			map.put("groundName", request.getGroundName());
			map.put("groundId", request.getGroundId());
			map.put("longitude", request.getLongitude());
			map.put("latitude", request.getLatitude());
			this.getSqlSession().update("updateLesson", map);
		} catch(Throwable e){
			log.error("updateLesson", e);
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public void updateLife(UpdateLifeRequest request, Timestamp startTime,
			Timestamp endTime) {
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("coachId", request.getCoachId());
			map.put("lessonId", request.getLessonId());
			map.put("startTime", startTime);
			map.put("endTime", endTime);
			map.put("description", request.getRemarks());
			map.put("alertSwitch", request.getAlertSwitch());
			map.put("longitude", request.getLongitude());
			map.put("latitude", request.getLatitude());
			map.put("address", request.getAddress());
			map.put("name", request.getName());
			this.getSqlSession().update("updateLesson", map);
		} catch(Throwable e){
			log.error("updateLesson", e);
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public float getAssignedHour(Long courseId) {
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("courseId", courseId);
			map.put("lessonActiveStatus", LESSON_STATUS.ACTIVE.getValue());
			return this.getSqlSession().selectOne("getAssignedHour", map);
		} catch(Throwable e){
			log.error("getAssignedHour", e);
			throw new RuntimeException(e);
		}
	}
}
