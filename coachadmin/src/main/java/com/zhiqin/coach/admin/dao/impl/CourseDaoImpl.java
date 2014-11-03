package com.zhiqin.coach.admin.dao.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhiqin.coach.admin.dao.CourseDao;
import com.zhiqin.coach.admin.dto.CoachDTO;
import com.zhiqin.coach.admin.dto.CourseDTO;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.SearchCoachDTO;
import com.zhiqin.coach.admin.dto.SearchCourseDTO;


@SuppressWarnings({ "rawtypes", "unchecked" })
public class CourseDaoImpl extends BaseDaoImpl implements CourseDao
{
	private static final Logger log = LoggerFactory
			.getLogger(CourseDaoImpl.class);
	@Override
	public List<CourseDTO> getByOrgId(SearchCourseDTO searchDto, PageInfoDTO pageInfo) {
		try{
			Map map = new HashMap();
			map.put("searchDto", searchDto);
			map.put("p", pageInfo);
			return this.getSqlSession().selectList("course.getCourseList", map);
		} catch(RuntimeException e){
			log.error("getCourseList", e);
			throw e;
		}
	}
	@Override
	public Long getTotalNum(SearchCourseDTO searchDto) {
		try{
			return  this.getSqlSession().selectOne("course.getTotalNum", searchDto);
		} catch(RuntimeException e){
			log.error("getTotalNum", e);
			throw e;
		}
	}
	@Override
	public void insert(CourseDTO c) {
		try{
			this.getSqlSession().insert("course.insertCourse", c);
		} catch(RuntimeException e){
			log.error("insert", e);
			throw e;
		}
	}
	@Override
	public void deleteById(Long courseId) {
		try{
			Map map = new HashMap();
			map.put("courseId", courseId);
			this.getSqlSession().update("course.deleteById", map);
		} catch(RuntimeException e){
			log.error("deleteById", e);
			throw e;
		}
		
	}
		
}
