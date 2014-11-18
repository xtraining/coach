package com.zhiqin.coach.admin.dao;



import java.util.List;

import com.zhiqin.coach.admin.entity.SysResource;


public interface SysResourceDao extends BaseDao{


	List<SysResource> getByUserId(int userId);

	List<SysResource> getAll();

}
