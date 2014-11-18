package com.zhiqin.coach.admin.dao.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhiqin.coach.admin.dao.CoachDao;
import com.zhiqin.coach.admin.dao.SysResourceDao;
import com.zhiqin.coach.admin.dto.CoachDTO;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.SearchCoachDTO;
import com.zhiqin.coach.admin.entity.SysResource;


public class SysResourceDaoImpl extends BaseDaoImpl implements SysResourceDao
{
	private static final Logger log = LoggerFactory
			.getLogger(SysResourceDaoImpl.class);

	@Override
	public List<SysResource> getByUserId(int userId) {
		try{
			return  this.getSqlSession().selectList("resource.getByUserId", userId);
		} catch(RuntimeException e){
			log.error("getByUserId", e);
			throw e;
		}
	}

	@Override
	public List<SysResource> getAll() {
		try{
			return  this.getSqlSession().selectList("resource.getAll");
		} catch(RuntimeException e){
			log.error("getAll", e);
			throw e;
		}
	}
}
