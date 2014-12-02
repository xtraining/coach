package com.coach.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coach.common.Constants.COACH_STATUS;
import com.coach.common.Constants.ORG_COACH_STATUS;
import com.coach.model.Coach;
import com.coach.response.BindOrgResponse;

public class CoachDaoImpl extends SqlSessionDaoSupport implements CoachDao{
	private static final Logger log = LoggerFactory
			.getLogger(CoachDaoImpl.class);
	@Override
	public Coach getIdByCredentials(String phoneNumber, String password) {
		try{
			Map<String, String> map = new HashMap<String, String>();
			map.put("phoneNumber", phoneNumber);
			map.put("password", password);
			return  this.getSqlSession().selectOne("getIdByCredentials", map);
		} catch(RuntimeException e){
			log.error("getIdByCredentials", e);
			throw e;
		}
	}


	@Override
	public void save(Coach c) {
		try{
			this.getSqlSession().insert("insertCoach", c);
		} catch(RuntimeException e){
			log.error("save", e);
			throw e;
		}
		
	}

	@Override
	public Long getByThirdPartyId(String thirdPartyId, Integer type) {
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("thirdPartyId", thirdPartyId);
			map.put("thirdPartyType", type);
			map.put("status", COACH_STATUS.DELETED.getValue());
			return  this.getSqlSession().selectOne("getByThirdPartyId", map);
		} catch(RuntimeException e){
			log.error("getByThirdPartyId", e);
			throw e;
		}
	}

	@Override
	public void resetPassword(String phoneNumber, String password) {
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("phoneNumber", phoneNumber);
			map.put("password", password);
			this.getSqlSession().update("resetPassword", map);
		} catch(RuntimeException e){
			log.error("resetPassword", e);
			throw e;
		}
		
	}

	@Override
	public Coach getBasicById(Long coachId) {
		try{
			return this.getSqlSession().selectOne("getBasicById", coachId);
		} catch(RuntimeException e){
			log.error("getBasicById", e);
			throw e;
		}
	}

	@Override
	public void updateProfile(Coach c) {
		try{
			this.getSqlSession().update("updateProfile", c);
		} catch(RuntimeException e){
			log.error("getBasicById", e);
			throw e;
		}
		
	}

	@Override
	public Coach getDetailById(Long coachId) {
		try{
			return this.getSqlSession().selectOne("getDetailById", coachId);
		} catch(RuntimeException e){
			log.error("getBasicById", e);
			throw e;
		}
	}

	@Override
	public void updateProfileDetail(Coach c) {
		try{
			this.getSqlSession().update("updateProfileDetail", c);
		} catch(RuntimeException e){
			log.error("getBasicById", e);
			throw e;
		}
		
	}

	@Override
	public int updateLastAccessTime(Long coachId) {
		try{
			return this.getSqlSession().update("updateLastAccessTime", coachId);
		} catch(RuntimeException e){
			log.error("getBasicById", e);
			throw e;
		}
	}

	@Override
	public void unbindThirdParty(int type, Long coachId) {
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("coachId", coachId);
			map.put("type", type+"");
			this.getSqlSession().update("unbindThirdParty", map);
		} catch(RuntimeException e){
			log.error("unbindThirdParty", e);
			throw e;
		}
		
	}

	@Override
	public void updateAvatar(Long coachId, String fileNameInQiniu) {
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("coachId", coachId);
			map.put("fileNameInQiniu", fileNameInQiniu);
			this.getSqlSession().update("updateAvatar", map);
		} catch(RuntimeException e){
			log.error("updateAvatar", e);
			throw e;
		}
		
	}

	@Override
	public List<BindOrgResponse> getBindOrg(Long coachId) {
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("coachId", coachId);
			return this.getSqlSession().selectList("getBindOrg", map);
		} catch(RuntimeException e){
			log.error("getBindOrg", e);
			throw e;
		}
	}

	@Override
	public void updateBindOrgStatus(Coach c, Integer orgId) {
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("coachObj", c);
			map.put("orgId", orgId);
			map.put("status", ORG_COACH_STATUS.ORG_NONE.getValue());
			this.getSqlSession().update("updateBindOrgStatus", map);
		} catch(RuntimeException e){
			log.error("updateBindOrgStatus", e);
			throw e;
		}
		
	}

	@Override
	public Long getCoachIdByPhoneNumber(String phoneNumber) {
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("phoneNumber", phoneNumber);
			map.put("status", COACH_STATUS.DELETED.getValue());
			return  this.getSqlSession().selectOne("getCoachIdByPhoneNumber", map);
		} catch(RuntimeException e){
			log.error("getByPhoneNumber", e);
			throw e;
		}
	}
}
