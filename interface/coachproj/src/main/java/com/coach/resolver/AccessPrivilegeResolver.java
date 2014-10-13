package com.coach.resolver;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.coach.dao.AccessPrivilegeDao;
import com.coach.model.AccessPrivilege;
@Service
public class AccessPrivilegeResolver extends BaseResolver{
	@Resource AccessPrivilegeDao accessPrivilegeDao;
	public List<String> findClientPrivilege(String appkey) {
		return accessPrivilegeDao.getByAppKey(appkey);
	}

}
