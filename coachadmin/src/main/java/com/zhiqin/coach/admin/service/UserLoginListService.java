package com.zhiqin.coach.admin.service;

import com.zhiqin.coach.admin.entity.UserLoginList;
import com.zhiqin.coach.admin.util.PageView;

public interface UserLoginListService {
	public PageView query(PageView pageView,UserLoginList userLoginList);
	
	public void add(UserLoginList userLoginList);

}
