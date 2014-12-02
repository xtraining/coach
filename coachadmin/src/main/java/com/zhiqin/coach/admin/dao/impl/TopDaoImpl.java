package com.zhiqin.coach.admin.dao.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhiqin.coach.admin.dao.TopDao;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.TopDTO;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class TopDaoImpl extends BaseDaoImpl implements TopDao
{
	private static final Logger log = LoggerFactory
			.getLogger(TopDaoImpl.class);

	@Override
	public void save(TopDTO dto) {
		try{
			this.getSqlSession().insert("top.insertTop", dto);
		} catch(RuntimeException e){
			log.error("insertTop", e);
			throw e;
		}
	}

	@Override
	public Long getTopTotalNum(String name) {
		try{
			Map map = new HashMap();
			map.put("name", name);
			return this.getSqlSession().selectOne("top.getTopTotalNum", map);
		} catch(RuntimeException e){
			log.error("getTopTotalNum", e);
			throw e;
		}
	}

	@Override
	public List<TopDTO> getTopList(String name, PageInfoDTO pageInfo) {
		try{
			Map map = new HashMap();
			map.put("name", name);
			map.put("p", pageInfo);
			return this.getSqlSession().selectList("top.getTopList", map);
		} catch(RuntimeException e){
			log.error("getTopList", e);
			throw e;
		}
	}

	@Override
	public void deleteByIds(String ids) {
		try{
			Map map = new HashMap();
			map.put("ids", ids);
			this.getSqlSession().update("top.deleteByIds", map);
		} catch(RuntimeException e){
			log.error("getTopList", e);
			throw e;
		}
	}

	@Override
	public void deleteVersionByIds(String ids) {
		try{
			Map map = new HashMap();
			map.put("ids", ids);
			this.getSqlSession().update("top.deleteVersionByIds", map);
		} catch(RuntimeException e){
			log.error("getTopList", e);
			throw e;
		}
		
	}

	
	
}