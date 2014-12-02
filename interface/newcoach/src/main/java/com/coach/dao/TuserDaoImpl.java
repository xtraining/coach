package com.coach.dao;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coach.common.Constants.COACH_STATUS;
import com.coach.model.Tuser;




public class TuserDaoImpl extends SqlSessionDaoSupport implements TuserDao{
	private static final Logger log = LoggerFactory
			.getLogger(TuserDaoImpl.class);

	@Override
	public void save(Tuser c) {
		try{
			this.getSqlSession().insert("insertTuser", c);
		} catch(RuntimeException e){
			log.error("save", e);
			throw e;
		}
		
	}
	
	@Override
	public Long getByPhoneNumber(String phoneNumber) {
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("phoneNumber", phoneNumber);
			map.put("status", COACH_STATUS.DELETED.getValue());
			return  this.getSqlSession().selectOne("getByPhoneNumber", map);
		} catch(RuntimeException e){
			log.error("getByPhoneNumber", e);
			throw e;
		}
	}
	
}