package com.zhiqin.coach.admin.dao.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhiqin.coach.admin.dao.AreaDao;
import com.zhiqin.coach.admin.dto.AreaDTO;
import com.zhiqin.coach.admin.dto.TagDTO;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class AreaDaoImpl extends BaseDaoImpl implements AreaDao
{
	private static final Logger log = LoggerFactory
			.getLogger(AreaDaoImpl.class);

	@Override
	public Integer getByAreaCode(String areaCode) {
		try{
			Map map = new HashMap();
			map.put("areaCode", areaCode);
			return this.getSqlSession().selectOne("area.getByAreaCode", map);
		} catch(RuntimeException e){
			log.error("getByAreaCode", e);
			throw e;
		}
	}

	@Override
	public List<AreaDTO> getSubareaById(int areaId) {
		try{
			return this.getSqlSession().selectList("area.getSubareaById", areaId);
		} catch(RuntimeException e){
			log.error("getSubareaById", e);
			throw e;
		}
	}

}
