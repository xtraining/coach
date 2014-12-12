package com.zhiqin.coach.admin.dao.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhiqin.coach.admin.dao.SysUserDao;
import com.zhiqin.coach.admin.dto.CourseDTO;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.SearchCourseDTO;
import com.zhiqin.coach.admin.entity.SysUser;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class SysUserDaoImpl extends BaseDaoImpl implements SysUserDao
{
	private static final Logger log = LoggerFactory
			.getLogger(SysUserDaoImpl.class);

	@Override
	public SysUser getByCredentials(String username, String password) {
		try{
			Map map = new HashMap();
			map.put("username", username);
			map.put("password", password);
			return this.getSqlSession().selectOne("sysuser.getByCredentials", map);
		} catch(RuntimeException e){
			log.error("getByCredentials", e);
			throw e;
		}
	}

	@Override
	public SysUser getUserByUsername(String username) {
		try{
			Map map = new HashMap();
			map.put("username", username);
			return this.getSqlSession().selectOne("sysuser.getUserByUsername", map);
		} catch(RuntimeException e){
			log.error("getUserByUsername", e);
			throw e;
		}
	}

	
	
}
