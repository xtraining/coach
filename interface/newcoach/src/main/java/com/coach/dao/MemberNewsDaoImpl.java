package com.coach.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coach.common.Constants.COACH_COURSE_STATUS;
import com.coach.common.Constants.COURSE_MEMBER_STATUS;
import com.coach.common.Constants.COURSE_STATUS;
import com.coach.model.MemberNews;
import com.coach.response.MemberNewsResponse;

public class MemberNewsDaoImpl extends SqlSessionDaoSupport implements MemberNewsDao{
	private static final Logger log = LoggerFactory
			.getLogger(MemberNewsDaoImpl.class);

	@Override
	public int insert(MemberNews news) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<MemberNewsResponse> getNews(Long coachId) {
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("coachId", coachId);
			map.put("courseActiveStatus", COURSE_STATUS.ACTIVE.getValue());
			map.put("acceptedStatus", COACH_COURSE_STATUS.ACCEPTED.getValue());
			map.put("deletedStatus", COURSE_MEMBER_STATUS.DELETED.getValue());
			return this.getSqlSession().selectList("getNews", map);
		} catch(Throwable e){
			log.error("getCheckLesson", e);
			throw new RuntimeException(e);
		}
	}

}
