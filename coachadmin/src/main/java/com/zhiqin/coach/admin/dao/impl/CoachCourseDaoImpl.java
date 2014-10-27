package com.zhiqin.coach.admin.dao.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhiqin.coach.admin.dao.CoachCourseDao;
import com.zhiqin.coach.admin.dao.CoachDao;
import com.zhiqin.coach.admin.dto.CoachDTO;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.SearchCoachDTO;


public class CoachCourseDaoImpl extends BaseDaoImpl implements CoachCourseDao
{
	private static final Logger log = LoggerFactory
			.getLogger(CoachCourseDaoImpl.class);

	@SuppressWarnings({ "unchecked", "rawtypes" })
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
}
