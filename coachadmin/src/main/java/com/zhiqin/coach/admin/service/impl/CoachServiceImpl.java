package com.zhiqin.coach.admin.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhiqin.coach.admin.dao.CoachCourseDao;
import com.zhiqin.coach.admin.dao.CoachDao;
import com.zhiqin.coach.admin.dao.LessonDao;
import com.zhiqin.coach.admin.dao.OrgCoachDao;
import com.zhiqin.coach.admin.dto.CoachDTO;
import com.zhiqin.coach.admin.dto.CourseDTO;
import com.zhiqin.coach.admin.dto.LessonDTO;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.SearchCoachDTO;
import com.zhiqin.coach.admin.dto.SearchCourseDTO;
import com.zhiqin.coach.admin.dto.SearchOrgDTO;
import com.zhiqin.coach.admin.service.CoachService;
import com.zhiqin.coach.admin.util.DateUtils;

@Service
public class CoachServiceImpl implements CoachService {
	
	@Resource private CoachDao coachDao;
	@Resource private CoachCourseDao coachCourseDao;
	@Resource private OrgCoachDao orgCoachDao;
	@Resource private LessonDao lessonDao;
	
	@Override
	public Long getTotalNum(SearchCoachDTO coach) {
		return coachDao.getTotalNum(coach);
	}

	@Override
	public List<CoachDTO> getCoachList(SearchCoachDTO coach,
			PageInfoDTO pageInfo) {
		List<CoachDTO> list = coachDao.getCoachList(coach, pageInfo);
		return list;
		
	}

	@Override
	public Long getCourseTotalNum(SearchCourseDTO searchDto) {
		return coachCourseDao.getCourseTotalNum(searchDto);
	}

	@Override
	public List<CourseDTO> getCourseList(SearchCourseDTO searchDto,
			PageInfoDTO pageInfo) {
		return coachCourseDao.getCourseList(searchDto, pageInfo);
	}

	@Override
	public Long getUnbindCoachTotalNum(SearchOrgDTO searchDto) {
		return orgCoachDao.getUnbindCoachTotalNum(searchDto);
	}

	@Override
	public List<CoachDTO> getUnbindCoachList(SearchOrgDTO searchDto,
			PageInfoDTO pageInfo) {
		return orgCoachDao.getUnbindCoachList(searchDto, pageInfo);
	}

	@Override
	public LessonDTO getConflictLesson(Long coachId, Long courseId) {
		List<LessonDTO> courseLessonList = lessonDao.getLessonByCourseId(courseId);
		if(courseLessonList != null && courseLessonList.size() > 0){
			String startDateStr = DateUtils.dateToyyyyMMdd(courseLessonList.get(0).getStartTime());
			List<LessonDTO> lessonList = lessonDao.getLessonFrom(coachId, DateUtils.yyyyMMddToDate(startDateStr));
			for(LessonDTO newLesson : courseLessonList){
				for(LessonDTO coachLesson : lessonList){
					Timestamp newStart = newLesson.getStartTime();
					Timestamp newEnd = newLesson.getEndTime();
					Timestamp oldStart = coachLesson.getStartTime();
					Timestamp oldEnd = coachLesson.getEndTime();
					if((newStart.getTime() >= oldStart.getTime() && newStart.getTime() < oldEnd.getTime())
							|| (newEnd.getTime() > oldStart.getTime() && newEnd.getTime() <= oldEnd.getTime())
				            || (newStart.getTime() <= oldStart.getTime() && newEnd.getTime() >= oldEnd.getTime())){
						return coachLesson;
					}
				}
			}
		}
		return null;
	}

}
