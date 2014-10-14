package com.zhiqin.coach.admin.service;


import java.util.List;

import com.zhiqin.coach.admin.entity.Roles;
import com.zhiqin.coach.admin.entity.UserRoles;
import com.zhiqin.coach.admin.util.PageView;

public interface RolesService{
	public PageView query(PageView pageView,Roles roles);
	
	public void add(Roles roles);
	
	public void delete(String id);
	
	public void modify(Roles roles);
	
	public Roles getById(String id);
	
	public List<Roles> findAll();
	
	public void saveUserRole(UserRoles userRoles);
}
