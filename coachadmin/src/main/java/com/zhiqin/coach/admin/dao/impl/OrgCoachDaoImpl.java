package com.zhiqin.coach.admin.dao.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhiqin.coach.admin.dao.OrgCoachDao;
import com.zhiqin.coach.admin.dto.CoachDTO;
import com.zhiqin.coach.admin.dto.PageInfoDTO;


public class OrgCoachDaoImpl extends BaseDaoImpl implements OrgCoachDao
{
	private static final Logger log = LoggerFactory
			.getLogger(OrgCoachDaoImpl.class);
	@Override
	public Long getOrgCoachByIdNumberAndType(CoachDTO coach) {
		try{
			return this.getSqlSession().selectOne("orgCoach.getOrgCoachByIdNumberAndType", coach);
		} catch(RuntimeException e){
			log.error("getOrgCoachByIdNumberAndType", e);
			throw e;
		}
	}
	@Override
	public Object createCoach(CoachDTO coach) {
		try{
			return this.getSqlSession().insert("orgCoach.createCoach", coach);
		} catch(RuntimeException e){
			log.error("getOrgCoachByIdNumberAndType", e);
			throw e;
		}
	}
	@Override
	public void updateCoach(CoachDTO coach) {
		try{
			this.getSqlSession().update("orgCoach.updateCoach", coach);
		} catch(RuntimeException e){
			log.error("updateCoach", e);
			throw e;
		}
		
	}
	@Override
	public CoachDTO getOrgCoachById(Long orgCoachId) {
		try{
			return this.getSqlSession().selectOne("orgCoach.getOrgCoachById", orgCoachId);
		} catch(RuntimeException e){
			log.error("getOrgCoachById", e);
			throw e;
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<CoachDTO> getCoachByOrgId(Long orgId, PageInfoDTO pageInfo) {
		try{
			Map map = new HashMap();
			map.put("orgId", orgId);
			map.put("p", pageInfo);
			return this.getSqlSession().selectList("orgCoach.getCoachByOrgId", map);
		} catch(RuntimeException e){
			log.error("getCoachByOrgId", e);
			throw e;
		}
	}
	@Override
	public Long getTotalNum(Long orgId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<CoachDTO> getBindCoachById(Long orgId) {
		try{
			Map map = new HashMap();
			map.put("orgId", orgId);
			return this.getSqlSession().selectList("orgCoach.getBindCoachById", map);
		} catch(RuntimeException e){
			log.error("getBindCoachById", e);
			throw e;
		}
	}
}
