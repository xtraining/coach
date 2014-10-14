package com.zhiqin.coach.admin.dao;


import java.util.List;

import com.zhiqin.coach.admin.entity.Roles;
import com.zhiqin.coach.admin.entity.UserRoles;


public interface RolesDao extends BaseDao<Roles>{
	public List<Roles> findAll();
	public void deleteUserRole(String userId);
	public void saveUserRole(UserRoles userRoles);
}
