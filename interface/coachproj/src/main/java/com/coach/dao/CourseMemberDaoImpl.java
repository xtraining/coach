package com.coach.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coach.model.CourseMember;
import com.coach.model.User;

public class CourseMemberDaoImpl extends SqlSessionDaoSupport implements CourseMemberDao{
	private static final Logger log = LoggerFactory
			.getLogger(CourseMemberDaoImpl.class);

	@Override
	public void save(List<CourseMember> cmList) {
		try{
			this.getSqlSession().insert("batchInsertCourseMember", cmList);
		} catch(RuntimeException e){
			log.error("save", e);
			throw e;
		}
		
	}

}
