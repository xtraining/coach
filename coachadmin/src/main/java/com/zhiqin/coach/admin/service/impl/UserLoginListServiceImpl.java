package com.zhiqin.coach.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhiqin.coach.admin.dao.UserLoginListDao;
import com.zhiqin.coach.admin.entity.UserLoginList;
import com.zhiqin.coach.admin.service.UserLoginListService;
import com.zhiqin.coach.admin.util.PageView;

@Transactional
@Service("userLoginListService")
public class UserLoginListServiceImpl implements UserLoginListService{
	@Autowired
	private UserLoginListDao userLoginListDao;
	
	public PageView query(PageView pageView, UserLoginList userLoginList) {
		List<UserLoginList> list = userLoginListDao.query(pageView, userLoginList);
		pageView.setRecords(list);
		return pageView;
	}

	public void add(UserLoginList userLoginList) {
		userLoginListDao.add(userLoginList);
	}

}
