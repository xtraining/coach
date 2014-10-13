package com.coach.dao;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coach.common.Constants.NEWS_TYPE;
import com.coach.model.CoachNewsHistory;

public class NewsHistoryDaoImpl extends SqlSessionDaoSupport implements NewsHistoryDao{
	private static final Logger log = LoggerFactory
			.getLogger(NewsHistoryDaoImpl.class);
	public int insert(CoachNewsHistory h) {
		return this.getSqlSession().insert("insertNewsHistory", h);
	}
	@Override
	public void delete(NEWS_TYPE type, Integer coachId) {
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("coachId", coachId);
			map.put("type", type.getValue());
			this.getSqlSession().delete("deleteNewsHistory", map);
		} catch(RuntimeException e){
			log.error("deleteNewsHistory", e);
			throw e;
		}
		
	}


}
