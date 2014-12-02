package com.coach.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coach.common.Constants.SMS_STATUS;
import com.coach.common.Constants.SMS_TYPE;
import com.coach.model.SmsHistory;

public class SmsHistoryDaoImpl extends SqlSessionDaoSupport implements SmsHistoryDao{
	private static final Logger log = LoggerFactory
			.getLogger(SmsHistoryDaoImpl.class);
	@Override
	public void updateStatus(SmsHistory msg) {
		try{
			this.getSqlSession().update("updateStatus", msg);
		} catch(RuntimeException e){
			log.error("update", e);
			throw e;
		}
		
	}
	@Override
	public Integer getTodaySmsNumber(SMS_TYPE type, String phoneNumber) {
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("status", SMS_STATUS.SENT_SUCCESS.getValue());
			map.put("phoneNumber", phoneNumber);
			map.put("type", type.getValue());
			map.put("createTime", new Date());
			return  this.getSqlSession().selectOne("getTodaySmsNumber", map);
		} catch(RuntimeException e){
			log.error("getTodaySmsNumber", e);
			throw e;
		}
	}
	@Override
	public void save(SmsHistory h) {
		try{
			this.getSqlSession().insert("insertSmsHistory", h);
		} catch(RuntimeException e){
			log.error("save", e);
			throw e;
		}
		
	}
	@Override
	public SmsHistory getLastestHistory(SMS_TYPE type, String phoneNumber) {
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("phoneNumber", phoneNumber);
			map.put("type", type.getValue());
			map.put("status", SMS_STATUS.SENT_SUCCESS.getValue());
			return  this.getSqlSession().selectOne("getLastestHistory", map);
		} catch(RuntimeException e){
			log.error("getTodaySmsNumber", e);
			throw e;
		}
	}
}
