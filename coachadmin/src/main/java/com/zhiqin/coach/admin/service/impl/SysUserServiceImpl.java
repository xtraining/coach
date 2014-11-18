package com.zhiqin.coach.admin.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhiqin.coach.admin.dao.SysUserDao;
import com.zhiqin.coach.admin.entity.SysUser;
import com.zhiqin.coach.admin.service.SysUserService;

@Service
public class SysUserServiceImpl implements SysUserService {
	
	@Resource private SysUserDao sysUserDao;

	@Override
	public SysUser getUserByCredentials(String username, String password) {
		return sysUserDao.getByCredentials(username, password);
	}

	@Override
	public SysUser getUserByUsername(String username) {
		return sysUserDao.getUserByUsername(username);
	}

}
