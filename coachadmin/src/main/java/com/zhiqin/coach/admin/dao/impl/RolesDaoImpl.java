package com.zhiqin.coach.admin.dao.impl;



import java.util.List;

import org.springframework.stereotype.Repository;

import com.zhiqin.coach.admin.dao.RolesDao;
import com.zhiqin.coach.admin.entity.Roles;
import com.zhiqin.coach.admin.entity.UserRoles;


@Repository("rolesDao")
public class RolesDaoImpl extends BaseDaoImpl<Roles> implements RolesDao
{

	public List<Roles> findAll() {
		return getSqlSession().selectList("roles.findAll");
	}

	public void saveUserRole(UserRoles userRoles ) {
		getSqlSession().insert("roles.saveUserRole", userRoles);
	}

	public void deleteUserRole(String userId) {
		getSqlSession().delete("roles.deleteUserRole", userId);
	}
}
