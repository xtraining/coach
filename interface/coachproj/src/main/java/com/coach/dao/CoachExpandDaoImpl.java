package com.coach.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coach.common.Constants.SMS_STATUS;
import com.coach.model.CoachExpand;
import com.coach.model.SysSession;

public class CoachExpandDaoImpl extends SqlSessionDaoSupport implements CoachExpandDao{
	private static final Logger log = LoggerFactory
			.getLogger(CoachExpandDaoImpl.class);

	@Override
	public void save(CoachExpand ex) {
		try{
			this.getSqlSession().insert("insertCoachExpand", ex);
		} catch(RuntimeException e){
			log.error("save", e);
			throw e;
		}
		
	}

	@Override
	public void deleteBaiduBinding(Integer coachId, String baiduUserId,
			String baiduChannelId) {
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("coachId", coachId);
			map.put("baiduUserId", baiduUserId);
			map.put("baiduChannelId", baiduChannelId);
			this.getSqlSession().update("updateBaiduBinding", map);
		} catch(RuntimeException e){
			log.error("deleteBaiduBinding", e);
			throw e;
		}
		
		
	}


}
