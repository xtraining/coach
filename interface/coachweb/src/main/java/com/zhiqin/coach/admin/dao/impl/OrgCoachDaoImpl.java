package com.zhiqin.coach.admin.dao.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhiqin.coach.admin.dao.OrgCoachDao;
import com.zhiqin.coach.admin.dto.CoachDTO;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.SearchOrgDTO;

@SuppressWarnings({ "rawtypes", "unchecked" })
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
		try{
			Map map = new HashMap();
			map.put("orgId", orgId);
			return this.getSqlSession().selectOne("orgCoach.getTotalNum", map);
		} catch(RuntimeException e){
			log.error("getTotalNum", e);
			throw e;
		}
	}
	
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
	
	@Override
	public Long getUnbindCoachTotalNum(SearchOrgDTO searchDto) {
		try{
			Map map = new HashMap();
			map.put("searchDto", searchDto);
			return this.getSqlSession().selectOne("orgCoach.getUnbindCoachTotalNum", map);
		} catch(RuntimeException e){
			log.error("getUnbindCoachTotalNum", e);
			throw e;
		}
	}

	@Override
	public List<CoachDTO> getUnbindCoachList(SearchOrgDTO searchDto,
			PageInfoDTO pageInfo) {
		try{
			Map map = new HashMap();
			map.put("searchDto", searchDto);
			map.put("p", pageInfo);
			return this.getSqlSession().selectList("orgCoach.getUnbindCoachList", map);
		} catch(RuntimeException e){
			log.error("getUnbindCoachList", e);
			throw e;
		}
	}
	
	@Override
	public void updateBindStatus(Long coachId, Long orgCoachId, Integer status) {
		try{
			Map map = new HashMap();
			map.put("coachId", coachId);
			map.put("orgCoachId", orgCoachId);
			map.put("status", status);
			this.getSqlSession().update("orgCoach.updateBindStatus", map);
		} catch(RuntimeException e){
			log.error("updateBindStatus", e);
			throw e;
		}
		
	}

}
