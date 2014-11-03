package com.zhiqin.coach.admin.service.impl;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhiqin.coach.admin.common.Constants.ALERT_SWITCH;
import com.zhiqin.coach.admin.common.Constants.COURSE_STATUS;
import com.zhiqin.coach.admin.common.Constants.COURSE_TYPE;
import com.zhiqin.coach.admin.common.Constants.LESSON_TYPE;
import com.zhiqin.coach.admin.common.Constants.ORG_COACH_STATUS;
import com.zhiqin.coach.admin.dao.CoachCourseDao;
import com.zhiqin.coach.admin.dao.CourseDao;
import com.zhiqin.coach.admin.dao.LessonDao;
import com.zhiqin.coach.admin.dao.OrgCoachDao;
import com.zhiqin.coach.admin.dao.OrgDao;
import com.zhiqin.coach.admin.dto.CoachDTO;
import com.zhiqin.coach.admin.dto.CourseDTO;
import com.zhiqin.coach.admin.dto.LessonDTO;
import com.zhiqin.coach.admin.dto.OrgDTO;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.SearchCourseDTO;
import com.zhiqin.coach.admin.dto.SearchOrgDTO;
import com.zhiqin.coach.admin.service.OrgService;
import com.zhiqin.coach.admin.util.DateUtils;
import com.zhiqin.coach.admin.util.RopUtils;

@Service
public class OrgServiceImpl implements OrgService {
	
	@Resource private OrgDao orgDao;
	@Resource private OrgCoachDao orgCoachDao;
	@Resource private CourseDao courseDao;
	@Resource private LessonDao lessonDao;
	@Resource private CoachCourseDao coachCourseDao;
	@Override
	public Long getTotalNum(SearchOrgDTO coach) {
		return orgDao.getTotalNum(coach);
	}

	@Override
	public List<OrgDTO> getOrgList(SearchOrgDTO coach,
			PageInfoDTO pageInfo) {
		List<OrgDTO> list = orgDao.getOrgList(coach, pageInfo);
		return list;
		
	}

	@Override
	public void create(OrgDTO org) {
		org.setCode(RopUtils.getUUID());
		org.setLevel("0000000000000000");
		orgDao.insert(org);
		
	}

	@Override
	public Long getOrgIdByName(String orgName) {
		return orgDao.getIdByName(orgName);
	}

	@Override
	public OrgDTO getOrgById(Long orgId) {
		return orgDao.getOrgById(orgId);
	}

	@Override
	public void update(OrgDTO org) {
		orgDao.update(org);
		
	}

	@Override
	public void deleteById(Long orgId) {
		orgDao.deleteById(orgId);
		
	}

	@Override
	public List<CoachDTO> getCoachByOrgId(Long orgId, PageInfoDTO pageInfo) {
		return orgCoachDao.getCoachByOrgId(orgId, pageInfo);
	}
	
	@Override
	public Long getCoachTotalNum(Long orgId) {
		return orgCoachDao.getTotalNum(orgId);
	}

	@Override
	public Long getOrgCoachByIdNumberAndType(CoachDTO coach) {
		return orgCoachDao.getOrgCoachByIdNumberAndType(coach);
	}

	@Override
	public void updateCoach(CoachDTO coach) {
		orgCoachDao.updateCoach(coach);
		
	}

	@Override
	public void createCoach(CoachDTO coach) {
		orgCoachDao.createCoach(coach);
	}

	@Override
	public CoachDTO getOrgCoachById(Long orgCoachId) {
		return orgCoachDao.getOrgCoachById(orgCoachId);
	}

	@Override
	public List<CourseDTO> getCourseByOrgId(SearchCourseDTO searchDto, PageInfoDTO pageInfo) {
		return courseDao.getByOrgId(searchDto, pageInfo);
	}

	@Override
	public Long getCourseTotalNum(SearchCourseDTO searchDto) {
		return courseDao.getTotalNum(searchDto);
	}

	@Override
	@Transactional
	public void createCourse(CourseDTO c) {
		c.setCode(StringUtils.replace(RopUtils.getUUID(), "-", ""));
		c.setStatus(COURSE_STATUS.ACTIVE.getValue());
		c.setType(COURSE_TYPE.ORG.getValue());
		c.setStartTime(DateUtils.yyyyMMddHHmmToTimestamp(c.getStartTimeStr()));
		c.setLessonNum((int)Math.ceil(c.getCourseHour()/c.getLessonHour()));
		c.setExpiryDate(DateUtils.yyyyMMddToDate(c.getExpiryDateStr()));
		if(StringUtils.isNotBlank(c.getRecycleDay())){
			c.setRecycleDay(c.getRecycleDay());
			Calendar cal = Calendar.getInstance();
	        cal.setTimeInMillis(c.getStartTime().getTime());
	        int today = cal.get(Calendar.DAY_OF_WEEK) - 1;
	        float assignedHour = 0f;
			while((c.getCourseHour() - assignedHour) >= 0.01 ){
				if(c.getRecycleDay().indexOf(today+"") >= 0){
					if(assignedHour < 0.01){
						double hours = c.getCourseHour() > c.getLessonHour() ? c.getLessonHour() : c.getCourseHour();
						buildLesson(c, hours, c.getStartTime());
						assignedHour += hours;
					} else {
						double hours = (c.getCourseHour()-assignedHour) > c.getLessonHour() ? c.getLessonHour() : (c.getCourseHour()-assignedHour);
						buildLesson(c, hours, new Timestamp(cal.getTimeInMillis()));
						assignedHour += hours;
					}
				}
				cal.add(Calendar.DAY_OF_MONTH, 1); //加一天
				today = cal.get(Calendar.DAY_OF_WEEK) - 1;
				if(today == 0)today = 7; //convert Sunday to 7
			}
			LessonDTO lastLesson = c.getLessonList().get(c.getLessonList().size() - 1);
			c.setEndTime(lastLesson.getEndTime());
		}
		
		courseDao.insert(c);
		for(LessonDTO lesson : c.getLessonList()){
			lesson.setCourseId(c.getCourseId());
			lessonDao.insert(lesson);
		}
		
	}
	
	private void buildLesson(CourseDTO c, double hours, Timestamp startTime) {
		LessonDTO lesson = new LessonDTO();
		lesson.setAddress(c.getAddress());
		lesson.setEndTime(new Timestamp(startTime.getTime() + (int)(hours * 3600000)));
		lesson.setHours(hours);
		lesson.setName(c.getName());
		lesson.setStartTime(startTime);
		lesson.setType(LESSON_TYPE.JOB.getValue());
		lesson.setAlertSwitch(ALERT_SWITCH.OFF.getValue());
		lesson.setGroundId(c.getGroundId());
		lesson.setGroundName(c.getGroundName());
		c.getLessonList().add(lesson);
	}

	@Override
	public List<CoachDTO> getBindCoachListById(Long orgId) {
		List<CoachDTO> list = orgCoachDao.getBindCoachById(orgId);
		return list;
	}

	@Override
	@Transactional
	public void assignCourse(Long coachId, Long courseId) {
		coachCourseDao.deleteByCourseId(courseId);
		coachCourseDao.insert(coachId, courseId);
	}

	@Override
	public List<Long> getAcceptedCoachByCourseId(Long courseId) {
		List<Long> list = coachCourseDao.getAcceptedCoachByCourseId(courseId);
		return list;
	}

	@Override
	@Transactional
	public void deleteCourseById(Long courseId) {
		courseDao.deleteById(courseId);
		coachCourseDao.deleteByCourseId(courseId);
		
	}

	@Override
	@Transactional
	public void updateBindStatus(Long coachId, Long orgCoachId, Integer status) {
		orgCoachDao.updateBindStatus(coachId, orgCoachId, status);
	}


}
