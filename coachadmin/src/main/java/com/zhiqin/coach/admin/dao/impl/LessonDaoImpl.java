package com.zhiqin.coach.admin.dao.impl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhiqin.coach.admin.dao.LessonDao;
import com.zhiqin.coach.admin.dto.LessonDTO;


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
}
