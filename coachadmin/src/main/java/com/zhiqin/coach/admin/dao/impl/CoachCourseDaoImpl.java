package com.zhiqin.coach.admin.dao.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhiqin.coach.admin.dao.CoachCourseDao;
import com.zhiqin.coach.admin.dto.CoachDTO;
import com.zhiqin.coach.admin.dto.CourseDTO;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.SearchCourseDTO;
import com.zhiqin.coach.admin.dto.SearchOrgDTO;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class CoachCourseDaoImpl extends BaseDaoImpl implements CoachCourseDao
{
	private static final Logger log = LoggerFactory
			.getLogger(CoachCourseDaoImpl.class);

	@Override
	public void insert(Long coachId, Long courseId) {
		try{
			Map map = new HashMap();
			map.put("coachId", coachId);
			map.put("courseId", courseId);
			this.getSqlSession().insert("coachCourse.insert", map);
		} catch(RuntimeException e){
			log.error("getCoachTotalNum", e);
			throw e;
		}
		
	}
	
	@Override
	public void deleteByCourseId(Long courseId) {
		try{
			Map map = new HashMap();
			map.put("courseId", courseId);
			this.getSqlSession().update("coachCourse.deleteByCourseId", map);
		} catch(RuntimeException e){
			log.error("getCoachTotalNum", e);
			throw e;
		}
		
	}

	@Override
	public List<Long> getAcceptedCoachByCourseId(Long courseId) {
		try{
			Map map = new HashMap();
			map.put("courseId", courseId);
			return this.getSqlSession().selectList("coachCourse.getAcceptedCoachByCourseId", map);
		} catch(RuntimeException e){
			log.error("getCoachTotalNum", e);
			throw e;
		}
	}

	@Override
	public Long getCourseTotalNum(SearchCourseDTO searchDto) {
		try{
			Map map = new HashMap();
			map.put("searchDto", searchDto);
			return this.getSqlSession().selectOne("coachCourse.getCourseTotalNum", map);
		} catch(RuntimeException e){
			log.error("getCourseTotalNum", e);
			throw e;
		}
	}

	@Override
	public List<CourseDTO> getCourseList(SearchCourseDTO searchDto,
			PageInfoDTO pageInfo) {
		try{
			Map map = new HashMap();
			map.put("searchDto", searchDto);
			return this.getSqlSession().selectList("coachCourse.getCourseList", map);
		} catch(RuntimeException e){
			log.error("getCourseTotalNum", e);
			throw e;
		}
	}

	
	
}
