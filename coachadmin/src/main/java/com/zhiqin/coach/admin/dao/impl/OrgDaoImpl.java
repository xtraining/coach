package com.zhiqin.coach.admin.dao.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhiqin.coach.admin.dao.OrgDao;
import com.zhiqin.coach.admin.dto.OrgDTO;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.SearchCoachDTO;
import com.zhiqin.coach.admin.dto.SearchOrgDTO;


public class OrgDaoImpl extends BaseDaoImpl implements OrgDao
{
	private static final Logger log = LoggerFactory
			.getLogger(OrgDaoImpl.class);
	@Override
	public Long getTotalNum(SearchOrgDTO org) {
		try{
			return  this.getSqlSession().selectOne("org.getTotalNum", org);
		} catch(RuntimeException e){
			log.error("getCoachTotalNum", e);
			throw e;
		}
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<OrgDTO> getOrgList(SearchOrgDTO org,
			PageInfoDTO pageInfo) {
		try{
			Map map = new HashMap();
			map.put("org", org);
			map.put("p", pageInfo);
			return this.getSqlSession().selectList("org.getOrgList", map);
		} catch(RuntimeException e){
			log.error("getIdByCredentials", e);
			throw e;
		}
	}
	@Override
	public void insert(OrgDTO org) {
		try{
			this.getSqlSession().insert("insertOrg", org);
		} catch(RuntimeException e){
			log.error("save", e);
			throw e;
		}
		
	}
}
