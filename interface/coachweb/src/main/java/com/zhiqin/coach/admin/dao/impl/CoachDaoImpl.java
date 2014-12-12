package com.zhiqin.coach.admin.dao.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhiqin.coach.admin.dao.CoachDao;
import com.zhiqin.coach.admin.dto.CoachDTO;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.SearchCoachDTO;


public class CoachDaoImpl extends BaseDaoImpl implements CoachDao
{
	private static final Logger log = LoggerFactory
			.getLogger(CoachDaoImpl.class);
	@Override
	public Long getTotalNum(SearchCoachDTO coach) {
		try{
			return  this.getSqlSession().selectOne("coach.getTotalNum", coach);
		} catch(RuntimeException e){
			log.error("getCoachTotalNum", e);
			throw e;
		}
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<CoachDTO> getCoachList(SearchCoachDTO coach,
			PageInfoDTO pageInfo) {
		try{
			Map map = new HashMap();
			map.put("coach", coach);
			map.put("p", pageInfo);
			return this.getSqlSession().selectList("coach.getCoachList", map);
		} catch(RuntimeException e){
			log.error("getIdByCredentials", e);
			throw e;
		}
	}
}
