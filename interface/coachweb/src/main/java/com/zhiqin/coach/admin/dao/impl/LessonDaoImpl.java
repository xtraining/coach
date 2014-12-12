package com.zhiqin.coach.admin.dao.impl;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhiqin.coach.admin.dao.LessonDao;
import com.zhiqin.coach.admin.dto.LessonDTO;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class LessonDaoImpl extends BaseDaoImpl implements LessonDao
{
	private static final Logger log = LoggerFactory
			.getLogger(LessonDaoImpl.class);

	@Override
	public void insert(LessonDTO lesson) {
		try{
			this.getSqlSession().insert("lesson.insertLesson", lesson);
		} catch(RuntimeException e){
			log.error("insert", e);
			throw e;
		}
		
	}

	@Override
	public List<LessonDTO> getLessonByCourseId(Long courseId) {
		try{
			return this.getSqlSession().selectList("lesson.getLessonByCourseId", courseId);
		} catch(RuntimeException e){
			log.error("getLessonByCourseId", e);
			throw e;
		}
	}

	@Override
	public List<LessonDTO> getLessonFrom(Long coachId, Date startTime) {
		try{
			Map map = new HashMap();
			map.put("coachId", coachId);
			map.put("startTime", startTime);
			return this.getSqlSession().selectList("lesson.getLessonFrom", map);
		} catch(RuntimeException e){
			log.error("getLessonByCourseId", e);
			throw e;
		}
	}
}
