package com.zhiqin.coach.admin.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhiqin.coach.admin.dao.SysResourceDao;
import com.zhiqin.coach.admin.entity.SysResource;
import com.zhiqin.coach.admin.service.SysResourceService;

@Service
public class SysResourceServiceImpl implements SysResourceService {
	
	@Resource private SysResourceDao sysResourceDao;

	@Override
	public List<com.zhiqin.coach.admin.entity.SysResource> getUserResources(int userId) {
		return sysResourceDao.getByUserId(userId);
	}

	@Override
	public List<SysResource> getAll() {
		return sysResourceDao.getAll();
	}

}
